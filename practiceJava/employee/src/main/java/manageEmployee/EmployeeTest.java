package manageEmployee;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Anh", 32, Gender.MALE, "Ha Noi");
        System.out.println(employee1.getBaseCode());
    }
}
