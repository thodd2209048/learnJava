package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String phone;
}
