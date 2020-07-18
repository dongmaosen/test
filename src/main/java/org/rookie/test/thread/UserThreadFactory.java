package org.rookie.test.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用戶自定義綫程工廠類
 */
public class UserThreadFactory implements ThreadFactory {
    /**
     * 固定綫程組名稱
     */
    static String groupName = "agent";

    private static ThreadGroup group = new ThreadGroup(groupName);

    private final AtomicInteger nextId = new AtomicInteger(1);
    /**
     * 每個綫程池自定義綫程名稱（根據業務自己指定）
     */
    String namePrefix;

    public UserThreadFactory(String businessThreadName) {
        this.namePrefix = businessThreadName;
    }

    public Thread newThread(Runnable task) {
        String name = groupName + "-" + namePrefix + "-" + nextId.getAndIncrement();
        Thread newThread = new Thread(new ThreadGroup(groupName), task, name);
        return newThread;
    }
}
