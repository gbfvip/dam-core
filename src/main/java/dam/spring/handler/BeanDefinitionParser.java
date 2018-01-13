package dam.spring.handler;


import dam.cache.MethodResultCache;
import dam.spring.defenation.Gate;
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
        String clazz = element.getAttribute("class");
        logger.debug("[dam:reference] id : [{}] start init", id);
        if (Gate.class.equals(beanClass)) {
            bean.addPropertyValue("id", id);
            bean.addPropertyValue("clazz", clazz);
            bean.addPropertyValue("cache", new MethodResultCache(Long.valueOf(element.getAttribute("size")), Long.valueOf(element.getAttribute("duration"))));
            logger.debug("[dam:reference] id : [{}] finish init", id);
        }
    }
}