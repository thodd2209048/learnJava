package org.example;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Booking {
    private String bookingID;
    private LocalDate departureDate;
    private int numberOfTickets;
    private double price;
    private String cabinType;
    private double totalPrice;
    private String destination;

    public Booking(String bookingID, LocalDate departureDate, int numberOfTickets, double price, String cabinType, String destination) {
        this.bookingID = bookingID;
        this.departureDate = departureDate;
        this.numberOfTickets = numberOfTickets;
        this.price = price;
        this.cabinType = cabinType;
        this.destination = destination;
    }

    public void ticketConfirmation() {
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields
            ) {
               String nameString = field.getName();
               String valueString = field.get(this).toString();
                System.out.println(nameString + ": " + valueString);

            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCabinType() {
        return cabinType;
    }

    public void setCabinType(String cabinType) {
        this.cabinType = cabinType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = numberOfTickets * totalPrice;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
