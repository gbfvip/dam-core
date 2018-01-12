package dam.spring.handler;


import dam.spring.defenation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;


public class BeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final Logger logger = LoggerFactory.getLogger(BeanDefinitionParser.class);

    private final Class<?> beanClass;

    public BeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    protected Class getBeanClass(Element element) {
        return this.beanClass;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        bean.setLazyInit(false);
        String id = element.getAttribute("id");
        String ref = element.getAttribute("ref");
        logger.debug("[dam:reference] id : [{}] start init", id);
        if (Reference.class.equals(beanClass)) {
            bean.addPropertyValue("id", id);
            bean.addPropertyValue("ref", ref);
            bean.addPropertyValue("duration", element.getAttribute("duration"));
            bean.addPropertyValue("size", element.getAttribute("size"));
            bean.addDependsOn(ref);
            logger.debug("[dam:reference] id : [{}] finish init", id);
        }
    }
}