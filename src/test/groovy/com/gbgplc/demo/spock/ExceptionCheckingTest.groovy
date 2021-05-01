package com.gbgplc.demo.spock

import spock.lang.Specification


class ExceptionCheckingTest extends Specification {

    def "negative value should throw exception"() {
        when:
        new CalculationService().calculate([3, -2, 5])

        then:
        def e = thrown(IllegalArgumentException)
        e.getMessage() == "Invalid value -2"
    }

    def "positive value should not throw exception"() {
        when:
        new CalculationService().calculate([3, 2, 5])

        then:
        noExceptionThrown()
    }

}