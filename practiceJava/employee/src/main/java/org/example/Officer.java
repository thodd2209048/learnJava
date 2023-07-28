package org.example;

public class Officer extends Employee {
    private int exp;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Officer(String name, int age, int gender, String address, int exp) {
        super(name, age, gender, address);
        this.exp = exp;
    }

    public Double calcSalary() {
        if (exp <= 2) {
            return 8.00;
        } else if (exp <=5) {
            return 15.00;
        } else if (exp <= 10) {
            return 25.00;
        } else {
            return 35.00;
        }
    }
}
