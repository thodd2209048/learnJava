package org.example;

import java.time.LocalDate;

public class InvalidDobException extends  CommonException{
    private static final int ERROR_CODE = 200;

    public InvalidDobException(LocalDate dobInput) {
        super(ERROR_CODE, "Year of birth " + dobInput.getYear() + " is not valid");
    }
}
