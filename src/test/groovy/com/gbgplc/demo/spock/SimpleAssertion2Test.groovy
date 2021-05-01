package com.gbgplc.demo.spock

import spock.lang.Specification


class SimpleAssertion2Test extends Specification {

    def "Different URIs with same scheme and host"() {

        given: "2 different URIs"
        def firstURI = new URI("http://user1:passw1@localhost:8080/some/path")
        def secondURI = new URI("http://localhost:8080/another/path")

        expect: "although different URI, but they should return same scheme and host"
        firstURI.scheme == secondURI.scheme
        firstURI.host == secondURI.host
    }
}


