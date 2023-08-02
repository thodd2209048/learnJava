package manageEmployee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.*;

@Getter @Setter
public class Engineer extends Employee {
    private EngineerMajor major;
    private EngineerLevel level;
    private StringBuilder codeBase;

    public Engineer(String name, int age, Gender gender, String address, EngineerMajor major, EngineerLevel level) {
        super(name, age, gender, address);
        this.major = major;
        this.level = level;
        this.codeBase = generateCode();
    }

    StringBuilder generateCode() {
        StringBuilder concatenatedData = new StringBuilder();
        Class<?> employeeClass = this.getClass();
        List<Field> fields = getAllFields(new ArrayList<>(), employeeClass);
        try {
            for (Field field : fields
            ) {
                field.setAccessible(true);
                if (!field.getName().equals("baseCode")) {
                    concatenatedData.append(field.getName()).append(field.get(this));
                    System.out.println(field);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return concatenatedData;
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {

        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if(type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
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
