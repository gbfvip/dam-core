package dam.spring.handler;

import dam.spring.defenation.Reference;
import dam.spring.defenation.Reservoir;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class NamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("reference", new BeanDefinitionParser(Reference.class));
        registerBeanDefinitionParser("reservoir", new BeanDefinitionParser(Reservoir.class));
    }
}