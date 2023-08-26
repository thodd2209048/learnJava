package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String code;
    private Gender gender;
    private String schoolCode;
    private LocalDate dob;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static List<Student> readStudent(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        String[] HEADERS = {"#","First name","Last name","Code","Gender","School code","DOB"};
        Reader in = new FileReader(fileName);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        DateTimeFormatter zoneDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd- HH:mm:ss z");
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (CSVRecord record : records
        ) {
            Integer id = Integer.valueOf(record.get("#"));
            String firstName = record.get("First name");
            String lastName = record.get("Last name");
            String code = record.get("Code");
            Gender gender = Gender.convert(record.get("Gender"));
            String schoolCode = record.get("School code");
            LocalDate dob = LocalDate.parse(record.get("DOB"), localDateFormatter);
//            ZonedDateTime createdAt = record.get("Created At").isEmpty()? null : ZonedDateTime.parse(record.get("Created At"), zoneDateTimeFormatter);
//            ZonedDateTime updatedAt = record.get("Updated At").isEmpty()? null :ZonedDateTime.parse(record.get("Updated At"), zoneDateTimeFormatter);
            students.add(new Student(id,firstName,lastName,code,gender,schoolCode,dob));
        }

        return students;
    }

    public Student(Integer id, String firstName, String lastName, String code, Gender gender, String schoolCode, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
        this.gender = gender;
        this.schoolCode = schoolCode;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", code='" + code + '\'' +
                ", gender=" + gender +
                ", schoolCode='" + schoolCode + '\'' +
                ", dob=" + dob +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
