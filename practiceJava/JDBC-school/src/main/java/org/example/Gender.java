package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female");
    private final String value;

    public static Gender convert(String value){
        if(value.equals(MALE.getValue())){
            return MALE;
        }
        if(value.equals(FEMALE.getValue())){
            return FEMALE;
        }
        return null;
    }
}
