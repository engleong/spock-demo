package com.gbgplc.demo.spock

import spock.lang.Specification
import spock.lang.Subject


class StubbingTest extends Specification {

    def "calculate with data service"() {
        given:
        ExternalDataService externalDataService = Stub()
        externalDataService.getValue(_) >> 20

        and:
        @Subject
        CalculationService calculationService = new CalculationService()
        calculationService.externalDataService = externalDataService

        expect:
        calculationService.calculate("id1", [10, 30, 40]) == 100

    }

}

