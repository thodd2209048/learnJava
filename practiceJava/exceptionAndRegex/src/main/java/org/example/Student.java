package org.example;

import java.time.LocalDate;

public class Student {
    private String name;
    private Gender gender;
    private LocalDate dob;

    public Student(String name, Gender gender, LocalDate dob) {
        validateDob(dob);
        validateName(name);

        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    private void validateName(String name) {
        if(!name.matches("[a-zA-Z ]+")){
            throw new InvalidNameException(name);
        }
    }

    private void validateDob(LocalDate dob){
        if (dob.getYear() <= 2006 || dob.getYear() >= 2017) {
         throw new InvalidDobException(dob);
        }
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
