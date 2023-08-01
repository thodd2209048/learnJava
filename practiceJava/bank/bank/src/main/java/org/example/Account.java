package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Account {
    private String customerName;
    private String accountNumber;
    private double balance;


}
