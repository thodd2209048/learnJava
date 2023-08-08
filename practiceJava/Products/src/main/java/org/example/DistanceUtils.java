package org.example;

import java.util.List;
import java.util.Optional;

public class DistanceUtils {
    public static Double getDistance(Double lat1,
                                     Double lat2, Double lng1,
                                     Double lng2) {

        lng1 = Math.toRadians(lng1);
        lng2 = Math.toRadians(lng2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        Double dlon = lng2 - lng1;
        Double dlat = lat2 - lat1;
        Double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        Double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        return (c * r);
    }

    public static Zipcode findZipcode(String zipcodeString, List<Zipcode> zipcodeList) {
        Zipcode result = zipcodeList.stream()
                .filter(zipcode -> zipcode.getZipcode().equals(zipcodeString))
                .findFirst()
                .orElse(null);

        return result;
    }

    public static Double getDistance(String zipcodeString1, String zipcodeString2, List<Zipcode> zipcodeList){
        Zipcode zipcode1 = findZipcode(zipcodeString1, zipcodeList);
        Zipcode zipcode2 = findZipcode(zipcodeString2, zipcodeList);
        return getDistance(zipcode1.getLat(), zipcode2.getLat(), zipcode1.getLng(), zipcode2.getLng());
    }
}
