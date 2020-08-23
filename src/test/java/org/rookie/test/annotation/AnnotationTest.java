package org.rookie.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        Class c = Person.class;
        Method method = c.getMethod("somebody", String.class, int.class);
        method.invoke(person, new Object[]{"lily", 18});
        iteratorAnnotations(method);

        Method empty = c.getMethod("empty", new Class[]{});
        empty.invoke(person, new Object[]{});
        iteratorAnnotations(empty);
    }


    public static void iteratorAnnotations(Method method) {
        // 判断 somebody() 方法是否包含MyAnnotation注解
        if(method.isAnnotationPresent(MyAnnotation.class)){
            // 获取该方法的MyAnnotation注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            // 获取 myAnnotation的值，并打印出来
            String[] values = myAnnotation.value();
            for (String str:values)
                System.out.printf(str+", ");
            System.out.println();
        }
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }
    }
}
