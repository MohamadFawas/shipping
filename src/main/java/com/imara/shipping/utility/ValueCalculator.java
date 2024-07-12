package com.imara.shipping.utility;
import java.text.DecimalFormat;

public class ValueCalculator {

    private static final int EARTH_RADIUS_KM = 6371;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        // Format distance to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        distance = Double.parseDouble(df.format(distance));

        return distance;
    }

    public static double calculateCompanyShare(double shipmentValue, long percentage) {
        return (shipmentValue / 100) * percentage;
    }
}
