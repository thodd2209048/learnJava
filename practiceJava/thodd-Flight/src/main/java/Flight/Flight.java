package Flight;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
public class Flight {
    private int number;
    private String departureCity;
    private String departureCountry;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    public Flight() {
    }

    public Flight(int number, String departureCity, String departureCountry, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.number = number;
        this.departureCity = departureCity;
        this.departureCountry = departureCountry;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number=" + number +
                ", departureCity='" + departureCity + '\'' +
                ", departureCountry='" + departureCountry + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
