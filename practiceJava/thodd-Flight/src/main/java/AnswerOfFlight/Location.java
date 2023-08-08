package AnswerOfFlight;

import FlightTwo.Continent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
public class Location {
    private String city;
    private String country;
    private Continent continent;
    private Double latitude;
    private Double longitude;
}
