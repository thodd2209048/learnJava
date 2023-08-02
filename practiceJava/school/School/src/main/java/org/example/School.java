package org.example;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class School {
    private String schoolId;
    private String name;
    private String address;

    @Override
    public String toString() {
        return "School{" +
                "schoolId='" + schoolId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}