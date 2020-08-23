package org.rookie.test.pattern.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式（问题，并发效率低）
 */

public class LazyInitIdGenerator {
    private AtomicLong id = new AtomicLong(0);
    private static LazyInitIdGenerator instance;
    private LazyInitIdGenerator() {}
    public static synchronized LazyInitIdGenerator getInstance() {
        if (instance == null) {
            instance = new LazyInitIdGenerator();
        }
        return instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}