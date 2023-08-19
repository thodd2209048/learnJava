package org.example;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database <T extends Record> {
    private ArrayList<T> records = new ArrayList<>();

    public void save(T record) {
        Integer maxId = records.stream().map(Record::getId).max(Integer::compareTo).orElse(0);
        if (record.getId() == null || find(record.getId()) == null) {
            if (record.getId() == null) {
                record.setId(maxId + 1);
            }
            record.setCreatedAt(ZonedDateTime.now());
            records.add(record);
            return;
        }

        Record recordOptional = find(record.getId());
        if (recordOptional.equals(record)) {
            return;
        }
        Integer index = records.indexOf(recordOptional);
        record.setUpdatedAt(ZonedDateTime.now());
        records.set(index, record);
    }

    public T find(Integer id) {
       return records.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst().orElse(null);
    }

    public T delete(Integer id) {
        T result = find(id);
        if(result == null) {
            return null;
        }
        records.remove(result);
        return result;
    }

    public List<T> findByCreatedAtAfter(ZonedDateTime time) {
        return records.stream().filter(r -> r.getCreatedAt() != null && !r.getCreatedAt().isBefore(time)).toList();
    }

    public List<T> findByUpdatedAtAfter(ZonedDateTime time) {
        return records.stream().filter(r -> r.getUpdatedAt() != null && !r.getUpdatedAt().isBefore(time)).toList();
    }



    public ArrayList<T> getRecords() {
        return records;
    }

    public Database(ArrayList<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Database{" +
                "records=" + records +
                '}';
    }
}
