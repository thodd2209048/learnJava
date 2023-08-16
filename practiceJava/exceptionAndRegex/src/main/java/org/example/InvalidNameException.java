package org.example;

public class InvalidNameException extends CommonException{
    private static final int errorCode = 100;

    public InvalidNameException(String name) {
        super(errorCode, "Name " + name + " is not valid");
    }

}
