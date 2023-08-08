package FlightTwo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
public class DomesticFlight extends Flight {
    private Location destinationCity;

    public double getPrice(){
        double lng1 = this.getDepartureCity().getLongitude();
        double lng2 = this.getDestinationCity().getLongitude();
        double lat1 = this.getDepartureCity().getLatitude();
        double lat2 = this.getDestinationCity().getLatitude();

        double price = 1120 * Utils.getDistance(lat1, lat2, lng1, lng2);

        return price;
    }











}
