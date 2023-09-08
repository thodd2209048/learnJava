package org.example;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataUtil {
    private static Connection connection;

    static {
        // Khởi tạo kết nối đến cơ sở dữ liệu ở đây
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://4.194.217.58:5432/t2301e",
                    "t2301e",
                    "t2301e"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void insertOrders(List<Order> orders) throws SQLException {
        Instant start = Instant.now();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_thodd (id,user_id, product, quantity) " +
                        " SELECT ?,?,?,? " +
                        " WHERE EXISTS (SELECT 1 FROM users_thodd WHERE id = ?);"
        );
        connection.setAutoCommit(false);
        for (Order order : orders
        ) {
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getUserId());
            statement.setString(3, order.getProduct());
            statement.setInt(4, order.getQuantity());
            statement.setInt(5, order.getUserId());
            statement.addBatch();
        }

        statement.executeBatch();
        connection.commit();

        statement.close();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    public static void insertOrdersWay2(List<Order> orders) throws SQLException, ClassNotFoundException {
        Instant start = Instant.now();
        Statement statement = connection.createStatement();
        List<Integer> userIdList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(
                "SELECT id FROM users_thodd"
        );
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            userIdList.add(id);
        }
        List<Order> filterOrder = orders.stream()
                .filter(o -> userIdList.contains(o.getUserId()))
                .toList();

        PreparedStatement statement2 = connection.prepareStatement(
                "INSERT INTO orders_thodd " +
                        " VALUES (?,?,?,?)"
        );
        connection.setAutoCommit(false);
        for (Order order : filterOrder
        ) {
            statement2.setInt(1, order.getId());
            statement2.setInt(2, order.getUserId());
            statement2.setString(3, order.getProduct());
            statement2.setInt(4, order.getQuantity());
            statement2.addBatch();
        }
        ;
        statement2.executeBatch();
        connection.commit();

        statement2.close();
        statement.close();

        System.out.println(userIdList);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    public static void insertOrdersWay3(List<Order> orders) throws SQLException {
        Instant start = Instant.now();
        Set<Integer> userIdSet = orders.stream().map(Order::getUserId).collect(Collectors.toSet());
        PreparedStatement statement = connection.prepareStatement(
                "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS result " +
                        " FROM users_thodd " +
                        " WHERE id = ?; "
        );
        connection.setAutoCommit(false);
        for (Integer userId : userIdSet
        ) {
            statement.setInt(1, userId);
            statement.addBatch();
        }
        int[] batchResults = statement.executeBatch();
        List<Integer> userIdList = new ArrayList<>(userIdSet);
        for (int i = batchResults.length - 1; i >= 0; i--) {
            if (batchResults[i] == 0) {
                userIdList.remove(i);
            }
        }


        connection.commit();

        List<Order> filterOrder = orders.stream()
                .filter(o -> userIdList.contains(o.getUserId()))
                .toList();

        PreparedStatement statement2 = connection.prepareStatement(
                "INSERT INTO orders_thodd " +
                        " VALUES (?,?,?,?)"
        );
        connection.setAutoCommit(false);
        for (Order order : filterOrder
        ) {
            statement2.setInt(1, order.getId());
            statement2.setInt(2, order.getUserId());
            statement2.setString(3, order.getProduct());
            statement2.setInt(4, order.getQuantity());
            statement2.addBatch();
        }
        ;
        statement2.executeBatch();
        connection.commit();
        statement.close();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
}
