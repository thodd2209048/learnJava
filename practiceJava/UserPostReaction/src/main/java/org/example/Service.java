package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private static Connection connection;

    public Service() throws SQLException {
        this.connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/UserPostReaction",
                        "postgres",
                        "123"
                );
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public List<User> listUsers() throws SQLException {
        Statement statement = connection.createStatement();
        List<User> userList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users_thodd");
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            userList.add(new User(id, firstName, lastName, dob));
        }
        statement.close();
        return userList;
    }

    public List<Post> listPosts(Integer userId) throws SQLException {
        List<Post> postList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM posts_thodd WHERE user_id = ? ");
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            ZonedDateTime publishedAt = resultSet.getTimestamp("published_at").toLocalDateTime().atZone(ZoneId.of("+07:00"));
            String content = resultSet.getString("content");

            postList.add(new Post(id,userId,publishedAt,content));
        }

        statement.close();
        return postList;
    }

    public List<Reaction> listReactions (int postId, Type type) throws SQLException {
        List<Reaction> reactionList = new ArrayList<>();
        String stringValueOfType = type == null ? null : type.getValue();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM reactions_thodd WHERE post_id = ? AND (? IS NULL OR type = ?)"
        );
        statement.setInt(1, postId);
        statement.setString(2, stringValueOfType);
        statement.setString(3, stringValueOfType);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            Type queryType = Type.convert(resultSet.getString("type"));
            reactionList.add(new Reaction(id, postId, userId, queryType));
        }

        statement.close();
        return reactionList;
    }

    public List<User> listReactedUsers(int userId) throws SQLException {
        Set<User> userSet = new HashSet<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.id AS user_id, u.first_name, u.last_name, u.dob, p.id AS post_id, r.id AS reaction_id" +
                        " FROM users_thodd u " +
                        "JOIN reactions_thodd r ON u.id = r.user_id " +
                        "JOIN posts_thodd p ON r.post_id = p.id " +
                        "WHERE p.user_id = ? "
        );

        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Integer id = resultSet.getInt("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            userSet.add(new User(id, firstName, lastName, dob));
        }
        List<User> userList = new ArrayList<>(userSet);

        statement.close();
        return userList;
    }
}
