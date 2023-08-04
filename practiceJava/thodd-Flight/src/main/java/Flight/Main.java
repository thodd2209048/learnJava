package Flight;

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
        DomesticFlight flightA = DomesticFlight.builder()
                .number(1)
                .departureCity("Hanoi")
                .departureCountry("Vietnam")
                .startTime(ZonedDateTime.of(2023, 8, 5, 8, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 5, 10, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .destinationCity("Saigon")
                .build();

        DomesticFlight flightB = DomesticFlight.builder()
                .number(2)
                .departureCity("Danang")
                .departureCountry("Vietnam")
                .startTime(ZonedDateTime.of(2023, 8, 6, 10, 30, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 6, 12, 30, 0, 0, ZoneId.of("Asia/Bangkok")))
                .destinationCity("Hanoi")
                .build();

        DomesticFlight flightC = DomesticFlight.builder()
                .number(3)
                .departureCity("Saigon")
                .departureCountry("Vietnam")
                .startTime(ZonedDateTime.of(2023, 8, 4, 14, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 4, 16, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .destinationCity("Hanoi")
                .build();

        OverseaFlight flightD = OverseaFlight.builder()
                .number(4)
                .departureCity("Hanoi")
                .departureCountry("Vietnam")
                .startTime(ZonedDateTime.of(2023, 8, 4, 14, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 4, 20, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .destinationCity("Beijing")
                .destinationCountry("China")
                .build();
        OverseaFlight flightE = OverseaFlight.builder()
                .number(4)
                .departureCity("Manila")
                .departureCountry("Philippine")
                .startTime(ZonedDateTime.of(2023, 8, 4, 15, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .endTime(ZonedDateTime.of(2023, 8, 4, 23, 0, 0, 0, ZoneId.of("Asia/Bangkok")))
                .destinationCity("Tokyo")
                .destinationCountry("Japan")
                .build();

        List<Flight> flights = List.of(flightA, flightB, flightC, flightD, flightE);

        LocalDate targetDate = LocalDate.of(2023, 8, 4);
        List<Flight> toHanoiList = flights.stream()
                .filter(s -> s instanceof DomesticFlight)
                .filter(s -> ((DomesticFlight) s).getDestinationCity().equals("Hanoi"))
                .filter(s -> s.getEndTime().toLocalDate().equals(targetDate))
                .collect(Collectors.toList());

        System.out.println("Cac chuyen bay noi dia toi Hanoi ngay 4/8/2023");
        for (Flight flight : toHanoiList
        ) {
            System.out.println(flight);
        }

        List<Flight> fromHanoiList = flights.stream()
                .filter(s -> s instanceof OverseaFlight)
                .filter(s -> s.getDepartureCity().equals("Hanoi"))
                .filter(s -> s.getStartTime().toLocalDate().equals(targetDate))
                .collect(Collectors.toList());

        System.out.println("Cac chuyen bay quoc te tu Hanoi ngay 4/8/2023");
        for (Flight flight : fromHanoiList
        ) {
            System.out.println(flight);
        }
    }
}
