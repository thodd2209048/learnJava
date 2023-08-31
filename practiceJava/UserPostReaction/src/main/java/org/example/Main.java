package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws SQLException {
        Service service = new Service();

        service.listReactedUsers(1);

        service.closeConnection();
    }
}