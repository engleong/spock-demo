package com.gbgplc.demo.spock;

import lombok.SneakyThrows;
import org.junit.Test;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleAssertion2JavaTest {

    @SneakyThrows
    @Test
    public void differentURIWithSameSchemaAndHost() {

        // 2 different URIs
        URI firstURI = new URI("http://user1:passw1@localhost:8080/some/path");
        URI secondURI = new URI("http://localhost:8080/another/path");

        // although different URI, but they should return same scheme and host
        assertTrue(firstURI.getScheme().equals(secondURI.getScheme()));
        assertTrue(firstURI.getHost().equals(secondURI.getHost()));
    }
}
