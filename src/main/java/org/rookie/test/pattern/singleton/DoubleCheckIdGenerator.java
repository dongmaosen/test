package org.rookie.test.pattern.singleton;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 双重检查单例
 */
public class DoubleCheckIdGenerator {
    private AtomicLong id = new AtomicLong(0);
    /**
     * 高版本的JDK，不用加volatile，已经支持原子
     */
    private static DoubleCheckIdGenerator instance;
    private DoubleCheckIdGenerator() {}
    public static DoubleCheckIdGenerator getInstance() {
        if (instance == null) {
            synchronized(DoubleCheckIdGenerator.class) { // 此处为类级别的锁
                if (instance == null) {
                    instance = new DoubleCheckIdGenerator();
                }
            }
        }
        return instance;
    }
    public long getId() {
        return id.incrementAndGet();
    }
}
