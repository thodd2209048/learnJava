package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    LIKE("LIKE"), DISLIKE("DISLIKE"), CRY("CRY"), LOVE("LOVE");

    private final String value;

    public static Type convert(String value){
        if(value.equals(LIKE.getValue())){
            return LIKE;
        }
        if(value.equals(DISLIKE.getValue())){
            return DISLIKE;
        }
        if(value.equals(CRY.getValue())){
            return CRY;
        }
        if(value.equals(LOVE.getValue())){
            return LOVE;
        }
        return null;
    }
}
