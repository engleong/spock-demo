package com.gbgplc.demo.spock

import spock.lang.Specification
import spock.lang.Unroll


class DataDrivenTest extends Specification {

    @Unroll
    def "check whether exceed limit #inputLimit for #inputValues should be #isLimitExceeded"() {

        given:
        CalculationService calculationService = new CalculationService();

        when:
        calculationService.limit = inputLimit

        then:
        isLimitExceeded == calculationService.exceedLimit(inputValues)

        where:
        inputLimit | inputValues  || isLimitExceeded
        100        | [1, 2, 3]    || false
        100        | [20, 40, 60] || true
        100        | [20, 20, 60] || false
        200        | [20, 40, 60] || false

    }

}