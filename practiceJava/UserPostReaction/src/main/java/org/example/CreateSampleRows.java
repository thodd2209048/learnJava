package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSampleRows {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:postgresql://4.194.217.58:5432/t2301e",
                        "t2301e",
                        "t2301e"
                );
        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO reactions_thodd (post_id, user_id, type)\n" +
                "VALUES\n" +
                "    (1, 1, 'LIKE'),\n" +
                "    (1, 2, 'LOVE'),\n" +
                "    (2, 3, 'DISLIKE'),\n" +
                "    (2, 1, 'CRY'),\n" +
                "    (3, 4, 'LIKE');");

        statement.executeUpdate("INSERT INTO posts_thodd (user_id, published_at, content)\n" +
                "VALUES\n" +
                "    (1, '2023-08-30 12:00:00', 'Nội dung bài viết 1'),\n" +
                "    (2, '2023-08-30 13:30:00', 'Nội dung bài viết 2'),\n" +
                "    (3, '2023-08-30 14:45:00', 'Nội dung bài viết 3'),\n" +
                "    (1, '2023-08-30 16:20:00', 'Nội dung bài viết 4'),\n" +
                "    (4, '2023-08-30 17:10:00', 'Nội dung bài viết 5');");

        statement.executeUpdate("INSERT INTO users_thodd (first_name, last_name, dob)\n" +
                "VALUES\n" +
                "    ('John', 'Doe', '1990-05-15'),\n" +
                "    ('Jane', 'Smith', '1985-12-10'),\n" +
                "    ('Michael', 'Johnson', '1992-08-20'),\n" +
                "    ('Emily', 'Williams', '1998-03-25'),\n" +
                "    ('David', 'Brown', '1982-11-03');");

        statement.close();
        connection.close();
    }
}
