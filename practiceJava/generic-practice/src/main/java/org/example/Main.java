package org.example;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student an =  new Student(1, "An", LocalDate.of(2004, 3, 2));
        Student bac = new Student(2, "Bac", LocalDate.of(2002, 3, 2));
        School fpt =  new School(5,
                ZonedDateTime.parse("2023-08-01T10:00:00+00:00"),
                ZonedDateTime.parse("2023-08-01T11:30:00+00:00"),
                "FPT",
                "123 Ton That Thuyet");

        ArrayList<Record> myRecord = new ArrayList<>();
        myRecord.add(an);
        myRecord.add(bac);
        myRecord.add(fpt);
        Database myDatabase = new Database(myRecord);


        Record newRecord = new Student(2, "Cong", LocalDate.of(2005, 3, 2));
        myDatabase.save(newRecord);

//        List<Record> result = myDatabase.findByUpdatedAtAfter(ZonedDateTime.parse("2023-08-01T10:00:00+00:00"));
//        for (Record r: result) {
//            System.out.println(r);
//        }

//        Print
        for (Record record: myDatabase.getRecords()
             ) {
            System.out.println(record);
        }
    }
}