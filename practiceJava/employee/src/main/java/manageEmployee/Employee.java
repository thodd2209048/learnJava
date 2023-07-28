package manageEmployee;

import java.lang.reflect.Field;

public class Employee {
    private String name;
    private int age;
    private Gender gender;
    private String address;
    private int baseCode;

    private int generateCode()  {
        StringBuilder concatenatedData = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        String classString = String.valueOf(this.getClass());
        try {
            for(Field field: fields) {
                field.setAccessible(true);
                concatenatedData.append(field.getName()).append(field.get(this));
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int hashCode = concatenatedData.toString().hashCode();
        return hashCode;
    }

    public int getBaseCode() {
        return baseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee(String name, int age, Gender gender, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.baseCode = generateCode();
    }

    public double getSalary() {
        return 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", code='" + baseCode + '\'' +
                '}';
    }
}
