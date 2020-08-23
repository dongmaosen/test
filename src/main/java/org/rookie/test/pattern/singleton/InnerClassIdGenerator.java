package org.rookie.test.pattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用静态内部类实现单例
 */
public class InnerClassIdGenerator {

    private AtomicLong id = new AtomicLong(0);

    private InnerClassIdGenerator(){}

    private static class SingletonHolder {
        private static final InnerClassIdGenerator instance = new InnerClassIdGenerator();
    }

    public static InnerClassIdGenerator getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
