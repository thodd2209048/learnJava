package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder @Getter @Setter
public class Zipcode {
    private String zipcode;
    private Double lat;
    private Double lng;

    @Override
    public String toString() {
        return "Zipcode{" +
                "zipcode='" + zipcode + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
