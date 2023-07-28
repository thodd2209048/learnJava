package org.example;

public class Engineer extends Employee{
    private String major; //IT, mechanics, electronic, civil
    private int level; //intern, fresher, junior, senior



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public Engineer(String name, int age, int gender, String address, String major, int level) {
        super(name, age, gender, address);
        this.major = major;
        this.level = level;
    }
    public Double calcSalara() {
    double baseSalary = 0;
    double coEfficient = 0;

    switch (major) {
        case("it"):
            baseSalary = 10.00;
            break;
        case("mechanics"):
            baseSalary = 9.00;
            break;
        case("electronic"):
            baseSalary = 10.5;
            break;
        case("civil"):
            baseSalary = 8;
            break;
        default:
            baseSalary = 0;
            System.out.println("Can not find " + getName() + "'s major");
            return 0.0;
    }

    switch (level) {
        case(1):
            coEfficient = 0.5;
            break;
        case(2):
            coEfficient = 1;
            break;
        case(3):
            coEfficient = 2;
            break;
        case(4):
            coEfficient = 5;
            break;
        default:
            coEfficient = 0;
            System.out.println("Can not find" + getName() + "'s level");
            return 0.0;
    }

    return baseSalary * coEfficient;
    };
}
