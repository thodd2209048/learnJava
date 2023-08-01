package manageEmployee;


import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Employee employee1 = new Employee("Anh", 32, Gender.MALE, "Ha Noi");
        Labor employee2 = new Labor("Bac", 35, Gender.MALE, "Hai Phong", LaborLevel.LEVEL_7);
        Engineer employee3 = new Engineer("Chuc", 22, Gender.FEMALE, "Bac Ninh", EngineerMajor.ELECTRONIC, EngineerLevel.FRESHER);
        Officer employee4 = new Officer("Duong", 23, Gender.FEMALE, "Thai Nguyen", 3);
        Engineer employee5 = new Engineer("Anh", 32, Gender.MALE, "Ha Noi", EngineerMajor.ELECTRONIC, EngineerLevel.FRESHER);

        System.out.println(employee1.getBaseCode());
        System.out.println(employee5.getBaseCode());
        System.out.println(employee3.getBaseCode());

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);


        int option;
        for (option = 1; option != 0; ) {
            System.out.println("----------------***----------------");
            System.out.println("Cac tinh nang quan ly nhan vien:");
            System.out.println("(1) Cho mot danh sach nhan vien");
            System.out.println("(2) Them moi nhan vien tu ban phim");
            System.out.println("(3) Tim kiem nhan vien theo ten");
            System.out.println("(0) Ket thuc chuong trinh");

            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();

            if (option == 1) {
                for (Employee employee : employeeList
                ) {
                    System.out.println(employee);
                }
            }

            if (option == 2) {
                System.out.println("Vui long nhap thong tin nhan vien moi");

                System.out.println("Ten: ");
                String newName = scanner.next();
                System.out.println("Tuoi: ");
                int newAge = scanner.nextInt();
                System.out.println("Gioi tinh: (MALE, FEMALE, OTHERS)");
                Gender newGender = Gender.valueOf(scanner.next());
                scanner.nextLine();
                System.out.println("Dia chi: ");
                String newAddress = scanner.nextLine();

                Employee newEmployee = new Employee(newName, newAge, newGender, newAddress);
                boolean isDuplicate = false;

                for (Employee employee : employeeList) {
                    if (employee.getBaseCode() == (newEmployee.getBaseCode())) {
                        isDuplicate = true;
                        System.out.println("Thong tin trung lap");
                    }
                }
                if (!isDuplicate) {
                    employeeList.add(newEmployee);
                }

            }

            if (option == 3) {
                System.out.println("Nhap ten nhan vien can tim kiem:");
                String searchInput = scanner.next();
                searchInput = searchInput.toLowerCase();
                searchInput = RemoveAccent.removeAccent(searchInput);

                for (Employee employee : employeeList) {
                    String employeeName = employee.getName().toLowerCase();
                    employeeName = RemoveAccent.removeAccent(employeeName);
                    if (employeeName.contains(searchInput)) {
                        System.out.println(employee);
                    }
                }
            }
            if (option == 4) {
                System.out.println("Tim kiem nang cao");
                System.out.println("Nhap truong thong tin can tim kiem: ");
                System.out.println("1. Tuoi");
                System.out.println("2. Gioi tinh");
                System.out.println("3. Dia chi");
                int searchField = scanner.nextInt();
            }
        }
    }

}
