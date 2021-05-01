package com.gbgplc.demo.spock;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockingJavaTest {

    @Test
    public void calculateWithAuditService() {
        ExternalDataService externalDataService = mock(ExternalDataService.class);
        AuditService auditService = mock(AuditService.class);

        CalculationService calculationService = new CalculationService();
        calculationService.setAuditService(auditService);
        calculationService.setExternalDataService(externalDataService);

        String id = "id1";
        List<Integer> values = Arrays.asList(10, 30, 40);

        int result = calculationService.calculateWithAudit("id1", values);
        assertEquals(80, result);

        // auditService invoked once
        verify(auditService, times(1)).audit(eq(id), eq(80), anyLong());

        // not invoked with externalDataService.getValue
        verify(externalDataService, times(0)).getValue(any());
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void calculateWithExternalDataServiceMockingAndStubbing(String inputId, List<Integer> inputValues, int expectedResult) {
        ExternalDataService externalDataService = mock(ExternalDataService.class);
        when(externalDataService.getValue(eq("id1"))).thenReturn(10);
        when(externalDataService.getValue(eq("id2"))).thenReturn(20);

        CalculationService calculationService = new CalculationService();
        calculationService.setExternalDataService(externalDataService);

        int result = calculationService.calculate(inputId, inputValues);
        assertEquals(expectedResult, result);

        // externalDataService.getValue invoked
        verify(externalDataService, times(1)).getValue(inputId);

    }

    private static Stream<Object[]> generateData() {
        return Stream.of(
            new Object[] { "id1", Arrays.asList(10, 30, 40), 90 },
            new Object[] { "id2", Arrays.asList(10, 30, 40), 100 }
        );
    }


}
