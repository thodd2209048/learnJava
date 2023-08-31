package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
                        "t2301e",
                        "t2301e"
                );
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table users_thodd\n" +
                "(\n" +
                "    id         serial,\n" +
                "    first_name varchar not null,\n" +
                "    last_name  varchar not null,\n" +
                "    dob        date    not null,\n" +
                "    constraint users_thodd_pk\n" +
                "        primary key (id)\n" +
                ");");

        statement.executeUpdate("create table posts_thodd\n" +
                "(\n" +
                "    id           serial,\n" +
                "    user_id    integer   not null,\n" +
                "    published_at timestamp not null,\n" +
                "    content      varchar   not null,\n" +
                "    constraint posts_thodd_pk\n" +
                "        primary key (id)\n" +
                ");");
//
        statement.executeUpdate("CREATE TABLE reactions_thodd (\n" +
                "    id serial PRIMARY KEY,\n" +
                "    post_id int NOT NULL,\n" +
                "    user_id int NOT NULL,\n" +
                "    type varchar(10) CHECK (type IN ('LIKE', 'DISLIKE', 'CRY', 'LOVE'))\n" +
                ");");

        statement.close();
        connection.close();
    }
}
