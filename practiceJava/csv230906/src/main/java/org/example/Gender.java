package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female"), BIGENDER("Bigender"),
    GENDERFLUID("Genderfluid"), GENDERQUEER("Genderqueer"),
    AGENDER("Agender"), NON_BINARY("Non-binary");

    private final String value;
    public static Gender convert(String value) {
        List<Gender> genders = new ArrayList<>();
        genders.add(MALE);
        genders.add(FEMALE);
        genders.add(BIGENDER);
        genders.add(GENDERFLUID);
        genders.add(GENDERQUEER);
        genders.add(AGENDER);
        genders.add(NON_BINARY);

        for (Gender Gender : genders) {
            if (value.equals(Gender.getValue())) {
                return Gender;
            }
        }
        return null;
    }

}
