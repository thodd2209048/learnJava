package FlightTwo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OverseaFlight extends Flight {
    private Location destinationCity;
    private String destinationCountry;

    public double getPrice(){
        double lon1 = this.getDepartureCity().getLongitude();
        double lon2 = this.getDestinationCity().getLongitude();
        double lat1 = this.getDepartureCity().getLatitude();
        double lat2 = this.getDestinationCity().getLatitude();

        Continent departureContinent = this.getDepartureCity().getContinent();
        Continent destinationContinent = this.getDestinationCity().getContinent();

        double distance = getDistance(lat1, lat2, lon1, lon2);
        double price;
        if(destinationContinent == departureContinent) {
            price = 1490  * distance;
        } else {
            price = 1780  * distance;
        }

        return price;
    }
}
