package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female");
    private final String value;

    public static Gender convert(String value){
        if(MALE.getValue().equals(value)){
            return MALE;
        }
        if(FEMALE.getValue().equals(value)){
            return FEMALE;
        }
        return null;
    }
}
