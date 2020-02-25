package com.rental.uigastore.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CalculationUtilTests {

    @Test
    public void calculationWithNewRelease() {
        assertEquals(12, CalculationUtil.calculateRentalPrice(FilmType.NEW_RELEASE, 3));
    }

    @Test
    public void calculationWithClassicLessThan4Days() {
        assertEquals(3, CalculationUtil.calculateRentalPrice(FilmType.CLASSIC, 3));
    }

    @Test
    public void calculationWithClassicMoreThan4Days() {
        assertEquals(15, CalculationUtil.calculateRentalPrice(FilmType.CLASSIC, 7));
    }

    @Test
    public void calculationWithRegularLessThan5Days() {
        assertEquals(3, CalculationUtil.calculateRentalPrice(FilmType.REGULAR, 3));
    }

    @Test
    public void calculationWithRegularMoreThan5Days() {
        assertEquals(9, CalculationUtil.calculateRentalPrice(FilmType.REGULAR, 7));
    }

    @Test
    public void calculationWithRegularNegativeDays() {
        assertEquals(0, CalculationUtil.calculateRentalPrice(FilmType.REGULAR, -1));
    }

    @Test
    public void calculationWithRegularZeroDays() {
        assertEquals(0, CalculationUtil.calculateRentalPrice(FilmType.REGULAR, 0));
    }
}
