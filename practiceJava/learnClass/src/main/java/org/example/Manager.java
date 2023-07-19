package org.example;

class Manager extends Employee {
    private String deparment;

    public Manager(String name, int age, double salary, String deparment) {
        super(name, age, salary);
        this.deparment = deparment;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public void work() {
        System.out.println(getName() + " is managing the " + deparment + " department.");
    }
}
