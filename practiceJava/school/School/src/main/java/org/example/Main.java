package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", 10, "ams"),
                new Student("Bob", 12, "ntt"),
                new Student("Elsa", 10, "ntt")
        );

        List<School> schools = List.of(
                new School("ams", "Ams", "hanoi"),
                new School("ntt", "NguyenTatThanh", "hanoi")
        );



        Map<String, School> schoolMap = schools.stream().collect(Collectors.toMap(School::getSchoolId, Function.identity()));

        Map<School, List<Student>> groupBySchool = students.stream().collect(Collectors.groupingBy(s -> schoolMap.get(s.getSchoolId())));

        for( School key : groupBySchool.keySet()) {
            System.out.println(key);
            System.out.println(groupBySchool.get(key));
        }
    }
}

