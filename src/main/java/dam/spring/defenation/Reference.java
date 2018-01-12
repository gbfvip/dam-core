package dam.spring.defenation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;


public class Reference<T> implements FactoryBean {

    //todo need init proxy of "ref" instance
    private T proxy;

    private static final Logger logger = LoggerFactory.getLogger(Reference.class);

    @PostConstruct
    private void init() {

    }

    @Override
    public Object getObject() throws Exception {
        return this.proxy;
    }

    @Override
    public Class<?> getObjectType() {
        if (this.proxy == null) {
            return null;
        }
        return this.proxy.getClass();
    }
}