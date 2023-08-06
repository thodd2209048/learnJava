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

    public double getDistance(double lat1,
                            double lat2, double lon1,
                            double lon2) {

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        return(c * r);
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
