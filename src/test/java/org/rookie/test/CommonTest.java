package org.rookie.test;

import org.junit.Test;

public class CommonTest {
    @Test
    public void testStrDiffJDK() {
        String str2 = new String("1") +  new String("2");
        str2.intern();
        String str1 = "12";
        System.out.println(str1 == str2);
    }
}
