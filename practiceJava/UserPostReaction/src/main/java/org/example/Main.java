package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws SQLException {
        Service service = new Service();

        System.out.println(service.listReactedUsers(1));

        service.closeConnection();
    }
}