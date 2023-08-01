package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank("1", "Vietcombank", "hanoi");
        bank.createAccount("VCB001", 100.1);
        bank.createAccount("VCB002", 200.1);
        bank.createAccount("VCB003", 300.1);

        bank.withdraw("VCB002", 100);
    }
}
