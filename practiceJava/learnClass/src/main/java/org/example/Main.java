package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("John", 34, 5400.45);
        employee1.work();
        Manager johnManager = new Manager(employee1.getName(), employee1.getAge(), employee1.getSalary(), "Sale");
        System.out.println(johnManager.getSalary());
        johnManager.increaseSalary(24);
        System.out.println(johnManager.getSalary());
    }
}
