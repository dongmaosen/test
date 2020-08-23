package org.rookie.test.pattern.factory;

public interface ApplicationContext {
    Object getBean(String beanId);
}
