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
        double lon1 = this.getDepartureCity().getLongitude();
        double lon2 = this.getDestinationCity().getLongitude();
        double lat1 = this.getDepartureCity().getLatitude();
        double lat2 = this.getDestinationCity().getLatitude();

        double price = 1120 * getDistance(lat1, lat2, lon1, lon2);

        return price;
    }

    public double test() {
        return getDistance(53.32055555555556, 53.31861111111111, -1.7297222222222221, -1.6997222222222223);
    }









}
