package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        List<User> userList = readCsv("users_new.csv");
//        System.out.println(userList);
//        insertUsers(userList);
        List<Order> orders = readOrder("orders.csv");
        insertOrders(orders);
        insertOrdersWay2(orders);
    }

    public static User readRow(CSVRecord row) {
        String stringId = row.get("id");
        Integer id = Integer.valueOf(stringId);

        String firstName = row.get("first_name");

        String lastName = row.get("last_name");

        String email = row.get("email");

        String stringGender = row.get("gender");
        Gender gender = Gender.convert(stringGender);

        for (String header : row.toMap().keySet()) {
            String value = row.get(header);
            if (value == null || value.isEmpty()) {
                throw new WarningException(new DataWarning(id, header));
            }
        }
        return new User(id, firstName, lastName, email, gender);
    }

    public static String readString(CSVRecord row, String column) {
        return row.get(column);
    }


    public static List<User> readCsv(String filePath) throws IOException {
        List<User> userList = new ArrayList<>();
        List<DataWarning> dataWarningList = new ArrayList<>();
        String[] HEADERS = {"id", "first_name", "last_name", "email", "gender"};
        Reader in = new FileReader(filePath);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        Iterable<CSVRecord> records = csvFormat.parse(in);


        for (CSVRecord record : records
        ) {
            try {
                userList.add(readRow(record));
            } catch (WarningException e) {
                dataWarningList.add(e.getDataWarning());
//                e.printStackTrace();
            }
        }
//        System.out.println(dataWarningList);
        return userList;
    }

    public static void insertUsers(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
                        "t2301e",
                        "t2301e"
//                        "jdbc:postgresql://localhost:5432/aaaTest",
//                        "postgres",
//                        "123"
                );
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users_thodd (id, first_name, last_name, email, gender) " +
                        " VALUES (?,?,?,?,?) "
        );
        connection.setAutoCommit(false);
        for (User u : users) {
            statement.setInt(1, u.getId());
            statement.setString(2, u.getFirstName());
            statement.setString(3, u.getLastName());
            statement.setString(4, u.getEmail());
            statement.setString(5, u.getGender().getValue());
            statement.addBatch();
        }

        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.commit();
    }

    public static List<Order> readOrder(String path) throws IOException {
        String[] HEADERS = {"id", "user id", "product", "quantity"};
        Reader in = new FileReader(path);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        List<Order> orders = new ArrayList<>();
        for (CSVRecord record : records
        ) {
            Integer id = Integer.valueOf(record.get("id"));
            Integer userId = Integer.valueOf(record.get("user id"));
            String product = record.get("product");
            Integer quantity = Integer.valueOf(record.get("quantity"));
            orders.add(new Order(id, userId, product, quantity));
        }
        return orders;
    }

    public static void insertOrders(List<Order> orders) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
                        "t2301e",
                        "t2301e"
//                        "jdbc:postgresql://localhost:5432/aaaTest",
//                        "postgres",
//                        "123"
                );
//        orders.stream().sorted(Comparator.comparing(Order::getUserId));
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
        connection.close();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    public static void insertOrdersWay2(List<Order> orders) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
                        "t2301e",
                        "t2301e"
                );
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
        for (Order order: filterOrder
             ) {
            statement2.setInt(1, order.getId());
            statement2.setInt(2, order.getUserId());
            statement2.setString(3, order.getProduct());
            statement2.setInt(4, order.getQuantity());
            statement2.addBatch();
        };
        statement2.executeBatch();
        connection.commit();

        statement2.close();
        statement.close();
        connection.close();

        System.out.println(userIdList);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }
}