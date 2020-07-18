package org.rookie.test.thread;

import org.junit.Test;

public class ThreadTest {
    @Test
    public void runnableTest() throws InterruptedException {
        new Thread(new Task()).start();
        Thread.sleep(10000000);
    }
    class Task implements Runnable {
        public void run() {
            System.out.println("I am a task.");
        }
    }

    /**
     * 自定义线程池名字，使其看起来有意义
     */
    @Test
    public void threadPoolCustom() {

    }
}
