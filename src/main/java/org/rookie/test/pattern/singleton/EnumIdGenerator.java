package org.rookie.test.pattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 枚举实现单例
 */
public enum EnumIdGenerator {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);
    public long getId() {
        return id.incrementAndGet();
    }
}
