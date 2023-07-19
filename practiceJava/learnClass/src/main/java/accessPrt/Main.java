package accessPrt;

import org.example.Employee;

public class Main {
    public static void main(String[] args) {
        accessPrt.Employee mike = new accessPrt.Employee("mike", 3000, 29);
        mike.increaseSalary(4000);
    }
}
