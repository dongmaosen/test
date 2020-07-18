package org.rookie.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用戶自定義綫程名
 */
public class UserDefineThreadFactory {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), new UserThreadFactory("test"),new ThreadPoolExecutor.AbortPolicy());
        for (;;) {
            executor.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
            Thread.sleep(2000);
        }
    }
}
