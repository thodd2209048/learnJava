package org.example;

public class Main {
    public static void main(String[] args) {
        Booking booking1 = new Booking("A23S", "2023/25/09", 3, 350, "class1", "Hue");
        System.out.println(booking1.getTotalPrice());
        booking1.ticketConfirmation();
    }
}
