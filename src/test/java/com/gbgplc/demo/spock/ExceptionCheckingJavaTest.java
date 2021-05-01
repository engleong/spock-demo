package com.gbgplc.demo.spock;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExceptionCheckingJavaTest {

    // could not perform assertion on exception properties
    @Test(expected = IllegalArgumentException.class)
    public void negativeValueShouldThrowException() {
        new CalculationService().calculate(Arrays.asList(3, -2, 5));
    }

    @Test
    public void negativeValueShouldThrowException2() {
        try {
            new CalculationService().calculate(Arrays.asList(3, -2, 5));
            fail("should throw IllegalArgumentException");

        } catch (IllegalArgumentException e) {
            assertEquals("Invalid value -2", e.getMessage());
        }
    }

    @Test
    public void negativeValueShouldNotThrowException() {
        try {
            new CalculationService().calculate(Arrays.asList(3, 2, 5));
        } catch (Exception e) {
            fail("should not throw exception");
        }

    }
}
