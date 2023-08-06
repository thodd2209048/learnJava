package FlightTwo;

//Dinh nghia 2 class
//DomesticFlight: chuyen bay noi dia
//OverseaFlight: chuyen bay quoc te

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Location hanoi = Location.builder()
                .city("Hanoi")
                .country("Vietnam")
                .continent(Continent.ASIA)
                .latitude(21.213035)
                .longitude(105.799157)
                .build();
        Location saigon = Location.builder()
                .city("Ho Chi Minh City")
                .country("Vietnam")
                .continent(Continent.ASIA)
                .latitude(10.776888)
                .longitude(106.700729)
                .build();
        Location tokyo = Location.builder()
                .city("Tokyo")
                .country("Japan")
                .continent(Continent.ASIA)
                .latitude(35.682839)
                .longitude(139.759455)
                .build();
        Location chicago = Location.builder()
                .city("Chicago")
                .country("USA")
                .continent(Continent.NORTH_AMERICA)
                .latitude(41.878113)
                .longitude(-87.629799)
                .build();

        OverseaFlight flightA = OverseaFlight.builder()
                .number(1)
                .departureCity(hanoi)
                .destinationCity(tokyo)
                .startTime(ZonedDateTime.of(2023, 8, 5, 8, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 5, 10, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .build();

        System.out.println(flightA.getPrice());
    }

}
