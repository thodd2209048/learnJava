package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO student_thodd (id,first_name,last_name,code,gender,school_code,dob, created_at, updated_at)" +
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

    public static void updateStudents(List<Student> students) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE student_thodd " +
                        "SET first_name = ?," +
                        "last_name =?," +
                        "gender= ?," +
                        "school_code = ?," +
                        "dob = ?," +
                        "updated_at = ? " +
                        "WHERE code = ? "
        );
        connection.setAutoCommit(false);

        for (Student s : students
        ) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getGender().getValue());
            statement.setString(4, s.getSchoolCode());
            statement.setDate(5, Date.valueOf(s.getDob()));
            statement.setTimestamp(6, Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime()));
            statement.setString(7, s.getCode());
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.close();
    }

    public static List<Student> listByAge(Integer age) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );
        Statement statement = connection.createStatement();
        List<Student> studentListByAge = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM student_thodd WHERE EXTRACT(YEAR FROM age(now(), dob)) > 18; ");
        Integer id = 0;

        while (resultSet.next()) {
            id += 1;
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String code = resultSet.getString("code");
            Gender gender = Gender.convert(resultSet.getString("gender"));
            String schoolCode = resultSet.getString("code");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            ZonedDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime().atZone(ZoneId.of("+07:00"));
            ZonedDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime().atZone(ZoneId.of("+07:00"));
            studentListByAge.add(new Student(id, firstName, lastName, code, gender, schoolCode, dob, createdAt,updatedAt));

        }

        statement.close();
        connection.close();

        System.out.println("1");
        return studentListByAge;
    }

    public static void insertOrUpdateStudents(List<Student> students) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );

        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO student_thodd (first_name,last_name,code,gender,school_code,dob, created_at, updated_at)\n" +
                        "VALUES (?,?,?,?,?,?,NOW(), NOW())" +
                        "ON CONFLICT (code) DO UPDATE " +
                        "    SET first_name = EXCLUDED.first_name, " +
                        "        last_name = EXCLUDED.last_name, " +
                        "        gender = EXCLUDED.gender, " +
                        "        school_code = EXCLUDED.school_code, " +
                        "        dob = EXCLUDED.dob, " +
                        "        updated_at = NOW();");

        connection.setAutoCommit(false);
        for (Student s: students
             ) {
            statement.setString(1, s.getFirstName());
            statement.setString(2, s.getLastName());
            statement.setString(3, s.getCode());
            statement.setString(4, s.getGender().getValue());
            statement.setString(5, s.getSchoolCode());
            statement.setDate(6, Date.valueOf(s.getDob()));
            statement.addBatch();
        }

        statement.executeBatch();
        connection.commit();

        statement.close();
        connection.close();

    }
}
