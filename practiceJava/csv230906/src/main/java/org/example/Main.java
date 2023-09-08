package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<User> userList = readCsv("usersTemp.csv");
        System.out.println(userList);
    }

    public static User readRow(CSVRecord row) {
        String stringId = row.get("id");
        Integer id =Integer.valueOf(stringId);

        String firstName = row.get("first_name");

        String lastName = row.get("last_name");

        String email = row.get("email");

        String stringGender = row.get("gender");
        Gender gender = Gender.convert(stringGender);

        for(String header : row.toMap().keySet()) {
            String value = row.get(header);
            if(value == null || value.isEmpty()){
                throw new IllegalArgumentException (String.format("%s is empty", header));
            }
        }
        return new User(id, firstName, lastName, email, gender);
    }

    public static String readString(CSVRecord row, String column){
        return row.get(column);
    }


    public static List<User> readCsv(String filePath) throws IOException {
        String[] HEADERS = {"id", "first_name", "last_name", "email", "gender"};
        Reader in = new FileReader(filePath);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        Iterable<CSVRecord> records = csvFormat.parse(in);
        List<User> userList = new ArrayList<>();

        for (CSVRecord record : records
        ) {
            try {
                userList.add(readRow(record));
            } catch (Exception e) {
                System.err.println("Error in record: " + record.toString());
                e.printStackTrace();
            }
        }
        return userList;
    }
}