package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

//        Connection connection = DriverManager
//                .getConnection(
//                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
//                        "t2301e",
//                        "t2301e"
//                );

        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://localhost:5432/jdbcSchool",
                        "postgres",
                        "123"
                );

        Statement statement = connection.createStatement();
        statement.executeUpdate("create table student_thodd\n" +
                "(\n" +
                "    id          serial,\n" +
                "    first_name  varchar   not null,\n" +
                "    last_name   varchar   not null,\n" +
                "    code        varchar   not null,\n" +
                "    gender      varchar   not null CHECK(gender IN ('Male', 'Female')),\n" +
                "    school_code varchar   not null,\n" +
                "    dob         date      not null,\n" +
                "    created_at  timestamp not null,\n" +
                "    updated_at  timestamp not null,\n" +
                "    constraint student_thodd_pk\n" +
                "        primary key (code)\n" +
                ");\n" +
                "\n");
//        statement.executeUpdate("create table school_thodd\n" +
//                "(\n" +
//                "    id          serial,\n" +
//                "    name  varchar   not null,\n" +
//                "    code        varchar   not null,\n" +
//                "    address varchar not null,\n" +
//                "    created_at  timestamp not null,\n" +
//                "    updated_at  timestamp not null,\n" +
//                "    constraint school_thodd_pk\n" +
//                "        primary key (code)\n" +
//                ");\n" +
//                "\n");

        statement.close();
        connection.close();
    }
}
