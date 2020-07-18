package org.rookie.test.jdk;

import java.io.IOException;

public class ThreadLocalTest {

    static ThreadLocal<String> tl = new ThreadLocal<String>();

    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                tl.set("123");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tl.get());
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                tl.set("456");

                System.out.println(tl.get());
            }
        }).start();

        System.in.read();
    }
}
