package com.gbgplc.demo.spock;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SimpleAssertion1JavaTest {

    @Test
    public void calculateReturnSumOfValuesInList() {
        CalculationService calculationService = new CalculationService();
        int result = calculationService.calculate(Arrays.asList(3, 2, 5));
        assertEquals(10, result);
    }
}
