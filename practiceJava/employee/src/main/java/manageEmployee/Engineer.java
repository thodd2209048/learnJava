package manageEmployee;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Engineer extends Employee {
    private EngineerMajor major;
    private EngineerLevel level;
    private int baseCode;

    public Engineer() {

    }

    public EngineerMajor getMajor() {
        return major;
    }

    public void setMajor(EngineerMajor major) {
        this.major = major;
    }

    public EngineerLevel getLevel() {
        return level;
    }

    public void setLevel(EngineerLevel level) {
        this.level = level;
    }

    public Engineer(String name, int age, Gender gender, String address, EngineerMajor major, EngineerLevel level) {
        super(name, age, gender, address);
        this.major = major;
        this.level = level;

    }


    public double getSalary() {
        Map<EngineerMajor, Double> mapMajorBaseSalary = new HashMap<>();
        Map<EngineerLevel, Double> mapLevelCoefficient = new HashMap<>();

        mapMajorBaseSalary.put(EngineerMajor.IT, 10.0);
        mapMajorBaseSalary.put(EngineerMajor.MECHANICS, 9.0);
        mapMajorBaseSalary.put(EngineerMajor.ELECTRONIC, 10.5);
        mapMajorBaseSalary.put(EngineerMajor.CIVIL, 8.0);

        mapLevelCoefficient.put(EngineerLevel.INTERN, 0.5);
        mapLevelCoefficient.put(EngineerLevel.FRESHER, 1.0);
        mapLevelCoefficient.put(EngineerLevel.JUNIOR, 2.0);
        mapLevelCoefficient.put(EngineerLevel.SENIOR, 5.0);

        double baseSalary = mapMajorBaseSalary.get(major);
        double coEfficient = mapLevelCoefficient.get(level);
        return baseSalary * coEfficient;
    }
}
