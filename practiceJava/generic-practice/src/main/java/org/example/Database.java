package org.example;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
    private ArrayList<Record> records = new ArrayList<>();

    public void save(Record record) {
        if (record.getId() == null) {
            Integer maxId = records.stream().map(Record::getId).max(Integer::compareTo).orElse(0);
            record.setId(maxId + 1);
        } else {
            Record recordOptional = find(record.getId());
            if(recordOptional != null) {
                Integer index = records.indexOf(recordOptional);
                records.set(index, record);
                return;
            }
        }

        records.add(record);
    }

    public Record find(Integer id) {
       return records.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst().orElse(null);
    }

    public Record delete(Integer id) {
        Record result = find(id);
        if(result == null) {
            return null;
        }
        records.remove(result);
        return result;
    }

    public List<Record> findByCreatedAtAfter(ZonedDateTime time) {
        return records.stream().filter(r -> r.getCreatedAt() != null && r.getCreatedAt().isAfter(time)).toList();
    }

    public List<Record> findByUpdatedAtAfter(ZonedDateTime time) {
        return records.stream().filter(r -> r.getUpdatedAt() != null && r.getUpdatedAt().isAfter(time)).toList();
    }



    public ArrayList<Record> getRecords() {
        return records;
    }

    public Database(ArrayList<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Database{" +
                "records=" + records +
                '}';
    }
}
