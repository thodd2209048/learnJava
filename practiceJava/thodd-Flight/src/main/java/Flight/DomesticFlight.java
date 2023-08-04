package Flight;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
public class DomesticFlight extends Flight {
    private String destinationCity;

    public DomesticFlight(int number, String departureCity, String departureCountry, ZonedDateTime startTime, ZonedDateTime endTime, String destinationCity) {
        super(number, departureCity, departureCountry, startTime, endTime);
        this.destinationCity = destinationCity;
    }


}
