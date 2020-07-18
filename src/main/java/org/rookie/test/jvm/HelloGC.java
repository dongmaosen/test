package org.rookie.test.jvm;

import java.util.LinkedList;
import java.util.List;

public class HelloGC {
    public static void main(String[] args) {
        List<byte[]> list = new LinkedList<byte[]>();
        for (;;) {
            list.add(new byte[1024*1024]);
        }
    }
}
