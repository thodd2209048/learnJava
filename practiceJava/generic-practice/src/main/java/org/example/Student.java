package org.example;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Student extends Record{
    private String name;
    private LocalDate dob;

    public Student(Integer id, String name, LocalDate dob) {
        super(id);
        this.name = name;
        this.dob = dob;
    }

    public Student(Integer id, ZonedDateTime createdAt, ZonedDateTime updatedAt, String name, LocalDate dob) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
