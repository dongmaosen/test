package org.rookie.test.annotation;

public class Person {
    @MyAnnotation
    @Deprecated
    public void empty() {
        System.out.println("\nempty");
    }
    @MyAnnotation(value = {"girl", "boy"})
    public void somebody(String name, int age) {
        System.out.println("\nsombody: " + name + ", " + age);
    }
}
