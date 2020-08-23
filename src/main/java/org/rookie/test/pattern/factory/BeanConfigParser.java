package org.rookie.test.pattern.factory;

import org.dom4j.DocumentException;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream) throws DocumentException;
    List<BeanDefinition> parse(String configContent);
}
