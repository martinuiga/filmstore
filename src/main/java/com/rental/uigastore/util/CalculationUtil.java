package com.rental.uigastore.util;

public class CalculationUtil {

    private static final int PREMIUM_PRICE = 4;
    private static final int BASIC_PRICE = 3;

    public static int calculateRentalPrice(FilmType filmType, int rentalLength) {

        if (filmType.equals(FilmType.NEW_RELEASE)) {
            return PREMIUM_PRICE * rentalLength;
        } else if (filmType.equals(FilmType.CLASSIC)) {
            if (rentalLength < 4) {
                return BASIC_PRICE;
            } else {
                return BASIC_PRICE + (BASIC_PRICE * (rentalLength - 3));
            }
        } else if (filmType.equals(FilmType.REGULAR)) {
            if (rentalLength < 6) {
                return BASIC_PRICE;
            } else {
                return BASIC_PRICE + (BASIC_PRICE * (rentalLength - 5));
            }
        }
        return 0;
    }
}
