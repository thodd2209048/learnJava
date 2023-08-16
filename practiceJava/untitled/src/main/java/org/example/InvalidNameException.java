package org.example;

public class InvalidNameException extends CommonException{
    private static final int ERROR_CODE = 100;

    public InvalidNameException(String name) {
        super(ERROR_CODE,
                "Name " + name + " is not valid");
    }
}
