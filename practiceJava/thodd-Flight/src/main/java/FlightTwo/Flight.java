package FlightTwo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
public class Flight {
    private int number;
    private Location departureCity;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    public Flight() {
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number=" + number +
                ", departure=" + departureCity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
