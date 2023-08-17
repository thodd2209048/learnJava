package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private Gender gender;
    private LocalDate dob;

    public Student(String name, Gender gender, LocalDate dob) throws InputException {
        List<CommonException> errors = new ArrayList<>();
        if (validateName(name)) {
            errors.add(new InvalidNameException(name));
        }
        if(validateDob(dob)) {
            errors.add(new InvalidDobException(dob));
        }
        if (!errors.isEmpty()) {
            throw new InputException(errors);
        }

        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    private boolean validateName(String name) {
        return !name.matches("[a-zA-Z ]+");
    }

    private boolean validateDob(LocalDate dob){
        return (dob.getYear() < 2006 || dob.getYear() > 2017) ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
