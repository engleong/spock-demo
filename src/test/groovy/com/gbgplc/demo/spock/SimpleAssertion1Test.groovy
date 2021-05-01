package com.gbgplc.demo.spock

import spock.lang.Specification

class SimpleAssertion1Test extends Specification {

    def "calculate return sum of values in list"() {
        given:
        CalculationService calculationService = new CalculationService()

        when:
        int result = calculationService.calculate([3, 2, 5])

        then:
        result == 10
    }

}
