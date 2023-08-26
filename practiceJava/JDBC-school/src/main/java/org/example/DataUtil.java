package org.example;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.List;

public class DataUtil {
    private List<Student> students;
    private List<School> schools;

    public static void insertStudents(List<Student> students) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager
                .getConnection(
//                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
//                        "t2301e",
//                        "t2301e"
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );

        PreparedStatement statement = connection.prepareStatement("INSERT INTO student_thodd (id,first_name,last_name,code,gender,school_code,dob, created_at, updated_at)" +
                "VALUES (?,?,?,?,?,?,?,?,?)");
        connection.setAutoCommit(false);

        for (Student s : students
        ) {
            statement.setInt(1, s.getId());
            statement.setString(2, s.getFirstName());
            statement.setString(3, s.getLastName());
            statement.setString(4, s.getCode());
            statement.setString(5, s.getGender().getValue());
            statement.setString(6, s.getSchoolCode());
            statement.setDate(7, Date.valueOf(s.getDob()));
            statement.setTimestamp(8, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            statement.setTimestamp(9, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.close();
    }

    public static void insertSchool(List<School> schools) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager
                .getConnection(
//                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
//                        "t2301e",
//                        "t2301e"
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO school_thodd (id,name,code,address,created_at, updated_at)" +
                "VALUES (?,?,?,?,?,?)");
        connection.setAutoCommit(false);

        for (School s : schools
        ) {
            statement.setInt(1, s.getId());
            statement.setString(2, s.getName());
            statement.setString(3, s.getCode());
            statement.setString(4, s.getAddress());
            statement.setTimestamp(5, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            statement.setTimestamp(6, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.close();
    }
}
