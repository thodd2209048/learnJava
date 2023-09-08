package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@AllArgsConstructor
public class UserInterface {
    private Controller controller;

    private Scanner scanner ;

    public UserInterface(){
        controller = new Controller();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Chose an option:");
            System.out.println("1. Add new customer");
            System.out.println("2. Find by name");
            System.out.println("3. Display all");
            System.out.println("4. Exit");
            Integer option = scanner.nextInt();
//            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    scanner.nextLine();
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    scanner.nextLine();
                    String phone = scanner.nextLine();
                    Customer newCustomer = new Customer(name, email, phone);
                    System.out.println("-----");
                    if(controller.isSuccessAddNewCustomer(name, email, phone)){
                        System.out.println(String.format("Customer %s has been added to the list", name));
                    } else {
                        System.out.println("Invalid input");
                    }
                    System.out.println("-----");
                    break;

                case 2:
                    System.out.println("Enter name");
                    String targetName = scanner.nextLine();
                    Customer customer = controller.findByName(targetName);
                    if (customer != null){
                        System.out.println("-----");
                        System.out.println("Customer with the name " + targetName + " has been found.");
                        System.out.println("Customer Information:");
                        System.out.println("Name: " + customer.getName());
                        System.out.println("Email: " + customer.getEmail());
                        System.out.println("Phone: " + customer.getPhone());
                        break;
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case 3:
                    System.out.println("-----");
                    System.out.println("This is list of customers");
                    Set<Map.Entry<String, Customer>> entrySet = controller.findAll().entrySet();
                    for ( Map.Entry<String, Customer> entry: entrySet
                         ) {
                        System.out.println("---");
                        System.out.println(entry.getValue().getName());
                        System.out.println(entry.getValue().getEmail());
                        System.out.println(entry.getValue().getPhone());
                    }
                    break;

                case 4:
                    isContinue = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
