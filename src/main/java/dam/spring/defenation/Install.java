package dam.spring.defenation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Install implements FactoryBean, ApplicationContextAware {

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
}
