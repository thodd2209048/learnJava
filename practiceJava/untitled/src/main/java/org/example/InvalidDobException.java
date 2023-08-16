package org.example;

import java.time.LocalDate;

public class InvalidDobException extends CommonException{
    private static final int ERROR_CODE = 200;

    public InvalidDobException(LocalDate dob) {
        super(ERROR_CODE,
                "Year of birth " + dob.getYear() + "is not valid");
    }

}
