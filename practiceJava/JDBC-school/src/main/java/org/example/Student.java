package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Student {
    private Integer id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public static List<Student> readStudent(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        String[] HEADERS = {"Id", "Name", "Created At", "Updated At"};
        Reader in = new FileReader(fileName);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");

        for (CSVRecord record : records
        ) {
            Integer id = Integer.valueOf(record.get("Id"));
            String name = record.get("Name");
            ZonedDateTime createdAt = ZonedDateTime.parse(record.get("Created At"), formatter);
            ZonedDateTime updatedAt = ZonedDateTime.parse(record.get("Updated At"), formatter);
            students.add(new Student(id, name, createdAt, updatedAt));
        }

        return students;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
