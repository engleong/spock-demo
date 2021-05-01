package com.gbgplc.demo.spock;

import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
public class CalculationService {

    private ExternalDataService externalDataService;
    private AuditService auditService;
    private int limit;

    public int calculate(List<Integer> values) {

        // if found negative value throw exception
        Optional<Integer> negativeValue = values.stream().filter(i -> i < 0).findAny();
        if (negativeValue.isPresent()) {
            throw new IllegalArgumentException("Invalid value " + negativeValue.get());
        }

        return values.stream().mapToInt(Integer::valueOf).sum();
    }

    public int calculate(String id, List<Integer> values) {
        int dataServiceValue = externalDataService.getValue(id);
        return values.stream().mapToInt(Integer::valueOf).sum() + dataServiceValue;
    }

    public int calculateWithAudit(String id, List<Integer> values) {
        int result = values.stream().mapToInt(Integer::valueOf).sum();
        auditService.audit(id, result, System.currentTimeMillis());
        return result;
    }

    public boolean exceedLimit(List<Integer> values) {
        return calculate(values) > limit;
    }
}
