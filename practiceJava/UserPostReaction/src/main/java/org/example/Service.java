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
        PreparedStatement statement = connection.prepareStatement(
                " SELECT DISTINCT ucrt.id, urct.id AS user_reaction_id, urct.first_name, urct.last_name, urct.dob " +
                        " FROM users_thodd ucrt " +
                        " JOIN posts_thodd p ON ucrt.id = p.user_id " +
                        " JOIN reactions_thodd r ON r.post_id = p.id " +
                        " JOIN users_thodd urct ON r.user_id = urct.id" +
                        " WHERE ucrt.id = ? "
        );

        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        List<User> userList = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt("user_reaction_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            userList.add(new User(id, firstName, lastName, dob));
        }
        statement.close();
        return userList;
    }
}
