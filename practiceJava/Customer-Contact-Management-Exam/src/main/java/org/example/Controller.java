package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class Controller {
    private CustomerDAO customerDAO;

    public Controller(){
        customerDAO = new CustomerDAOImpl();
    }

    public boolean isSuccessAddNewCustomer(String name, String email, String phone){
        if(isInvalidName(name) && isInvalidEmail(email)) {
            customerDAO.addNewCustomer(name, email, phone);
            return true;
        } else {
            return false;
        }
    }

    public Customer findByName(String name){
        return customerDAO.findByName(name);
    }
    public Map<String, Customer> findAll(){
        return customerDAO.findAll();
    };

    public boolean isInvalidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public boolean isInvalidEmail(String email){
        String regex = "(([^<>()[\\\\]\\\\.,;:\\\\s@\\\\\\\"]+(\\\\.[^<>()[\\\\]\\\\.,;:\\\\s@\\\\\\\"]+)*)|(\\\\\\\".+\\\\\\\"))@(([^<>()[\\\\]\\\\.,;:\\\\s@\\\\\\\"]+\\\\.)+[^<>()[\\\\]\\\\.,;:\\\\s@\\\\\\\"]{2,})";
        return email.matches(regex);
    }
}
