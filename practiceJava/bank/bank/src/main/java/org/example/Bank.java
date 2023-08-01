//package org.example;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bank {
//    private int bankId;
//    private String bankName;
//    private String branch;
//    private int lastAccNumber;
//    private List<Account> accounts = new ArrayList<>();
//
//    public Bank(int bankId, String bankName, String branch) {
//        this.bankId = bankId;
//        this.bankName = bankName;
//        this.branch = branch;
//
//    }
//
//    public void createAccount(String username, double balance) {
//        lastAccNumber += 1;
//        String newAccountNumberString = String.format("%06d", lastAccNumber);
//        Account newAccount = new Account(username, newAccountNumberString, balance);
//        accounts.add(newAccount);
//    }
//
//    public void displayAccountDetails (String accNumber) {
//        Account result = findAccount(accNumber);
//        displayAccountDetailsAcc(result);
//        System.out.println("-----------------");
//    }
//    private void displayAccountDetailsAcc(Account account) {
//        Field[] fields = account.getClass().getDeclaredFields();
//        try {
//            for (Field field : fields
//            ) {
//                field.setAccessible(true);
//                String nameString = field.getName();
//                String valueString = field.get(account).toString();
//                System.out.println(nameString + ": " + valueString);
//            }
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private Account findAccount(String accountNumber) {
//        try {
//            for (Account account : accounts
//            ) {
//                if (account.getAccountNumber().equals(accountNumber)) {
//                    return account;
//                }
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }
//
//    public void withdraw(String accountNumber, double amount) {
//        boolean isFound = false;
//        try {
//            for (Account account : accounts
//            ) {
//                if (account.getAccountNumber().equals(accountNumber)) {
//                    isFound = true;
//                    if (account.getBalance() < amount) {
//                        throw new BallanceError("Ballance is not enough");
//                    } else {
//                        double currentBalance = account.getBalance();
//                        currentBalance -= amount;
//                        account.setBalance(currentBalance);
//                    }
//                }
//            }
//            if (!isFound) {
//                throw new InsufficientBalanceException("Insufficient balance!");
//            }
//        } catch (BallanceError e) {
//            throw new RuntimeException(e);
//        } catch (InsufficientBalanceException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void deposit(String accountNumber, double amount) {
//        Account acc = findAccount(accountNumber);
//        double currentBalance = acc.getBalance();
//        double newBalance = currentBalance + amount;
//        acc.setBalance(newBalance);
//        displayAccountDetails(accountNumber);
//    }
//
//    public int getBankId() {
//        return bankId;
//    }
//
//    public void setBankId(int bankId) {
//        this.bankId = bankId;
//    }
//
//    public String getBankName() {
//        return bankName;
//    }
//
//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }
//
//    public String getBranch() {
//        return branch;
//    }
//
//    public void setBranch(String branch) {
//        this.branch = branch;
//    }
//}

package org.example;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    @Getter
    @Setter
    private String bankId;
    @Getter
    @Setter
    private String bankName;
    @Getter
    @Setter
    private String bankBranch;


    public Bank(String bankId, String bankName, String bankBranch) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
    }

    private List<Account> accounts = new ArrayList<>();
    private int lastAccNumber = 0;

    public void displayAccount(String userName) {
        Account result = findAccount(userName);
        displayAccount(result);
    }

    private Account findAccount(String userName) {
        boolean isFound = false;
        for (Account acc : accounts
        ) {
            if (acc.getCustomerName().equals(userName)) {
                isFound = true;
                return acc;
            }
        }
        if (!isFound) {
            System.out.println("Khong tim thay tai khoan");
        }
        return null;
    }

    private void displayAccount(Account acc) {
        Field[] fields = acc.getClass().getDeclaredFields();
        try {
            for (Field field : fields
            ) {
                field.setAccessible(true);
                String nameString = field.getName();
                String valueString = field.get(acc).toString();
                System.out.println(nameString + ": " + valueString);

            }
            System.out.println("-----------------------------");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void createAccount(String username, double balance) {
        lastAccNumber += 1;
        String newAccNumber = String.format("%06d", lastAccNumber);
        Account newAcc = Account.builder()
                .accountNumber(newAccNumber)
                .customerName(username)
                .balance(balance)
                .build();

        accounts.add(newAcc);
        System.out.println("Tao tai khoan moi thanh cong");
        displayAccount(username);
    }

    public void withdraw(String username, double amount) {
        Account withdrawAcc = findAccount(username);
        if (withdrawAcc != null) {
            if (withdrawAcc.getBalance() < amount) {
                System.out.println("So du khong du");
                displayAccount(withdrawAcc);
                return;
            } else {
                double newBalance = withdrawAcc.getBalance() - amount;
                withdrawAcc.setBalance(newBalance);
                System.out.println("Giao dich rut tien " + amount + " thanh cong");
                displayAccount(withdrawAcc);
            }
        }
        return;
    }

    public void deposit(String username, double amount) {
        Account depositAcc = findAccount(username);
        if (depositAcc != null) {
            double newBalance = depositAcc.getBalance() + amount;
            depositAcc.setBalance(newBalance);
            System.out.println("Giao dich gui tien " + amount + " thanh cong");
            displayAccount(depositAcc);
        }
        return;
    }
}

