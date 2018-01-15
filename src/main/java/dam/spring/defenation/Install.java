package dam.spring.defenation;

import dam.spring.handler.AnnotationScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Install implements FactoryBean, BeanFactoryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object getObject() throws Exception {
        return applicationContext;
    }

    @Override
    public Class<?> getObjectType() {
        return applicationContext.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AnnotationScanner scanner = new AnnotationScanner((BeanDefinitionRegistry) beanFactory);
        scanner.setResourceLoader(this.applicationContext);
        scanner.scan("*");
    }
}
