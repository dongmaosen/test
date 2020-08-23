package org.rookie.test.pattern.factory;

public class RedisCounter {
    private String ipAddress;
    private String port;

    public RedisCounter(String ipAddress, String port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

}
