package com.gbgplc.demo.spock;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubbingJavaTest {

    @Test
    public void calculateWithDataService() {
        ExternalDataService externalDataService = mock(ExternalDataService.class);
        when(externalDataService.getValue(anyString())).thenReturn(20);

        CalculationService calculationService = new CalculationService();
        calculationService.setExternalDataService(externalDataService);

        int result = calculationService.calculate("id1", Arrays.asList(10, 30, 40));
        assertEquals(100, result);
    }


}
