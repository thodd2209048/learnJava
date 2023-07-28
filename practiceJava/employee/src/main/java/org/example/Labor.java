package org.example;

public class Labor extends Employee {
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Labor(String name, int age, int gender, String address, int level) {
        super(name, age, gender, address);
        this.level = level;
    }

    public Double calcSalary() {
        return (double) level * 5;
    }
}
