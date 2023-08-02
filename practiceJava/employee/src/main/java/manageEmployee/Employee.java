package manageEmployee;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuperBuilder
public class Employee {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int age;
    @Getter @Setter
    private Gender gender;
    @Getter @Setter
    private String address;
    @Getter
    private StringBuilder baseCode;

    public Employee(String name, int age, Gender gender, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.baseCode = generateCode();
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
        return 0;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", baseCode='" + baseCode + '\''
                ;
    }
}
