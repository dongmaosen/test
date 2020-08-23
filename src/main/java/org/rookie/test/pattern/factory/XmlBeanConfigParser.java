package org.rookie.test.pattern.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements("bean");
        List<BeanDefinition> beanDefinitionList = new ArrayList<BeanDefinition>();
        for (Element bean: elementList) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(bean.attributeValue("id"));
            beanDefinition.setClassName(bean.attributeValue("class"));
            String scope = bean.attributeValue("scope");
            if ("singleton".equals(scope)) {
                beanDefinition.setScope(BeanDefinition.Scope.SINGLETON);
            } else {
                beanDefinition.setScope(BeanDefinition.Scope.PROTOTYPE);
            }
            String lazyInit = bean.attributeValue("lazy-init");
            if (lazyInit == null) {
                beanDefinition.setLazyInit(false);
            } else {
                beanDefinition.setLazyInit(Boolean.parseBoolean(lazyInit));
            }
            List<Element> constructorArgList = bean.elements("constructor-arg");
            if (constructorArgList != null) {
                List<BeanDefinition.ConstructorArg> args = new ArrayList<>();
                for (Element arg: constructorArgList) {
                    BeanDefinition.ConstructorArg constructorArg = new BeanDefinition.ConstructorArg();
                    String ref = arg.attributeValue("ref");
                    if (ref == null) {
                        constructorArg.setIsRef(false);
                    } else {
                        constructorArg.setIsRef(true);
                        constructorArg.setArg(ref);
                    }

                    String type = arg.attributeValue("type");
                    if (type != null) {
                        if (type.equals("String")) {
                            constructorArg.setType(String.class);
                        }
                        if (type.equals("int")) {
                            constructorArg.setType(int.class);
                        }
                    }

                    String value = arg.attributeValue("value");
                    if (value != null) {
                        constructorArg.setArg(value);
                    }

                    args.add(constructorArg);
                }
                beanDefinition.setConstructorArgs(args);
            }

            beanDefinitionList.add(beanDefinition);
        }
        return beanDefinitionList;
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        return null;
    }
}
