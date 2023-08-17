package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        addStudent("Alice", Gender.FEMALE, LocalDate.of(2013, 4, 4));
        addStudent("Bob@", Gender.MALE, LocalDate.of(1019, 1, 1));
    }

    private static final ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(String name, Gender gender, LocalDate dob) {
        try {
            Student newStudent = new Student(name, gender, dob);
            students.add(newStudent);
        } catch (InputException e) {
            for (CommonException commonException : e.getErrors()
            ) {
                System.out.println(commonException);
            }
        }

    }
}