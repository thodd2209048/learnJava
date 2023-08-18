package org.example;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database <T extends Record> {
    private ArrayList<T> records = new ArrayList<>();

    public void save(T record) {
        if (record.getId() == null) {
            Integer maxId = records.stream().map(T::getId).max(Integer::compareTo).orElse(0);
            record.setId(maxId + 1);
        } else {
            T recordOptional = find(record.getId());
            if(recordOptional != null) {
                Integer index = records.indexOf(recordOptional);
                records.set(index, record);
                return;
            }
        }

        records.add(record);
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
        return records.stream().filter(r -> r.getCreatedAt() != null && r.getCreatedAt().isAfter(time)).toList();
    }

    public List<T> findByUpdatedAtAfter(ZonedDateTime time) {
        return records.stream().filter(r -> r.getUpdatedAt() != null && r.getUpdatedAt().isAfter(time)).toList();
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