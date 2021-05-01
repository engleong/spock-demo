package com.gbgplc.demo.spock

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class MockingTest extends Specification {

    @Subject
    def calculationService = new CalculationService()

    def "calculate with auditService"() {
        given:
        AuditService auditService = Mock()
        ExternalDataService externalDataService = Mock()
        calculationService.auditService = auditService
        calculationService.externalDataService = externalDataService
        def id = "id1"
        def values = [10, 30, 40]

        when:
        int result = calculationService.calculateWithAudit(id, values)

        then:
        result == 80

        and: "auditService invoked once"
        1 * auditService.audit(id, 80, _)

        and: "externalDataService not invoked"
        0 * externalDataService.getValue(_)
    }

    @Unroll
    def "calculate with externalDataService mocking + stubbing inputId=#inputId"() {
        given:
        ExternalDataService externalDataService = Mock()
        calculationService.externalDataService = externalDataService

        when:
        int result = calculationService.calculate(inputId, inputValues)

        then:
        result == expectedResult
        1 * externalDataService.getValue(_) >> { args ->
            args[0] == "id1" ? 10 : 20
        }

        where:
        inputId | inputValues  || expectedResult
        "id1"   | [10, 30, 40] || 90
        "id2"   | [10, 30, 40] || 100

    }

}