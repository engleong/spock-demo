package com.gbgplc.demo.spock;

public interface AuditService {
    void audit(String id, int value, long timestamp);
}
