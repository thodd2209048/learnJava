package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", 10, "Ams"),
                new Student("Bob", 12, "NguyenTatThanh"),
                new Student("Elsa", 10, "NguyenTatThanh")
        );

        List<School> schools = List.of(
                new School("Ams", "Ams", "hanoi"),
                new School("NguyenTatThanh", "NguyenTatThanh", "hanoi")
        );


        Map<School, List<Student>> groupBySchool = students.stream().collect(Collectors.groupingBy(s -> {
            String nameSchool = s.getSchoolId();

            School schoolResult = schools.stream().filter(school -> {
                return school.getSchoolId().equals(nameSchool);
            }).findFirst().orElse(null);

            return schoolResult;
        }));

        for( School key : groupBySchool.keySet()) {
            System.out.println(key);
            System.out.println(groupBySchool.get(key));
        }
    }
}

