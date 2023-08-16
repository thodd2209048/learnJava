package org.example;

import java.time.LocalDate;

public class Student {
    private String name;
    private Gender gender;
    private LocalDate dob;

    public Student(String name, Gender gender, LocalDate dob) {
        validateName(name);
        validateDob(dob);

        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    private void validateName(String name) {
        if(!name.matches("[a-zA-z ]+")) {
            throw new InvalidNameException(name);
        }
    }

    private void validateDob(LocalDate dob){
        if(dob.getYear() < 2006 || dob.getYear() > 2017) {
            throw new InvalidDobException(dob);
        }
    }
}
