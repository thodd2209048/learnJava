package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate departure = LocalDate.of(2023, 07, 28);
        Booking booking1 = new Booking("A23S", departure, 3, 350, "class1", "Hue");
        System.out.println(booking1.getTotalPrice());
        booking1.ticketConfirmation();
    }
}
