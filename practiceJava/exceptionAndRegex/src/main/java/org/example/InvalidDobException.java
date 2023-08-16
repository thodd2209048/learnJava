package org.example;

import java.time.LocalDate;

public class InvalidDobException extends  CommonException{
    private static final int errorCode = 200;

    public InvalidDobException(LocalDate dobInput) {
        super(errorCode, "Year of birth " + dobInput.getYear() + " is not valid");
    }
}
