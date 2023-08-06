package FlightTwo;

public class LocationTest {
    public static void main(String[] args) {
        Location hanoi = Location.builder()
                .city("Hanoi")
                .country("Vietnam")
                .latitude(21.213035)
                .longitude(105.799157)
                .build();
        Location saigon = Location.builder()
                .city("Ho Chi Minh City")
                .country("Vietnam")
                .latitude(10.776888)
                .longitude(106.700729)
                .build();
        Location tokyo = Location.builder()
                .city("Tokyo")
                .country("Japan")
                .latitude(35.682839)
                .longitude(139.759455)
                .build();
        Location chicago = Location.builder()
                .city("Chicago")
                .country("USA")
                .latitude(41.878113)
                .longitude(-87.629799)
                .build();
    }
}
