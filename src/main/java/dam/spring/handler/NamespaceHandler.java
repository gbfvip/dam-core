package dam.spring.handler;

import dam.spring.defenation.Gate;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class NamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("gate", new BeanDefinitionParser(Gate.class));
    }
}