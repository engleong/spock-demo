package com.gbgplc.demo.spock;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DataDrivenJavaTest {

    @ParameterizedTest
    @MethodSource("generateData")
    public void checkWhetherExceedLimit(int inputLimit, List<Integer> inputValues, boolean isLimitExceeded) {
        CalculationService calculationService = new CalculationService();
        calculationService.setLimit(inputLimit);
        assertEquals(isLimitExceeded, calculationService.exceedLimit(inputValues));
    }

    private static Stream<Object[]> generateData() {
        return Stream.of(
                new Object[]{100, Arrays.asList(1, 2, 3), false},
                new Object[]{100, Arrays.asList(20, 40, 60), true},
                new Object[]{100, Arrays.asList(20, 20, 60), false},
                new Object[]{200, Arrays.asList(20, 40, 60), false}
        );
    }


}
