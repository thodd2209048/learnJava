package org.example;

import java.util.List;
import java.util.Map;

public interface CustomerDAO {

    public void addNewCustomer(String name, String email, String phone);
    public Customer findByName(String name);
    public Map<String, Customer> findAll();

}
