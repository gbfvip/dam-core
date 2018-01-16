package dam.spring.handler;


import dam.cache.MethodResultCache;
import dam.spring.defenation.Gate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.w3c.dom.Element;


public class InstallBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final Logger logger = LoggerFactory.getLogger(InstallBeanDefinitionParser.class);

    private final Class<?> beanClass;

    public InstallBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    protected Class getBeanClass(Element element) {
        return this.beanClass;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean) {
        bean.setLazyInit(false);
        AnnotationScanner scanner = new AnnotationScanner( parserContext.getReaderContext().getRegistry());
        scanner.setResourceLoader(parserContext.getReaderContext().getResourceLoader());
        scanner.scan(element.getAttribute("base-package"));
    }

    @Override
    protected boolean shouldGenerateId() {
        return true;
    }
}