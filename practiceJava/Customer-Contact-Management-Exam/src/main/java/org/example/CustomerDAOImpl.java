package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl implements  CustomerDAO{
    private Map<String, Customer> customerHashMap;

    public CustomerDAOImpl() {
        customerHashMap = new HashMap<>();
    }
    @Override
    public void addNewCustomer(String name, String email, String phone) {
        Customer newCustomer = new Customer(name, email, phone);
        customerHashMap.put(email, newCustomer);
    }

    @Override
    public Customer findByName(String name) {
        Iterator<Customer> customerIterator = customerHashMap.values().iterator();
        while (customerIterator.hasNext()){
            Customer customer = customerIterator.next();
            if (name.equals(customer.getName())) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Map<String, Customer> findAll() {
        return customerHashMap;
    }
}
