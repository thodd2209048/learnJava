package org.example;

import java.time.ZonedDateTime;

public class School extends Record{
    private String name;
    private String address;

    public School(Integer id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public School(Integer id, ZonedDateTime createdAt, ZonedDateTime updatedAt, String name, String address) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
