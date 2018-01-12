package dam.spring.defenation;


import dam.cache.MethodResultCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;


public class Reservoir implements FactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(Reservoir.class);

    private long duration = 10;

    private long size = 1000;

    private MethodResultCache underling;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @PostConstruct
    private void init() {
        underling = new MethodResultCache(size, duration);
    }

    @Override
    public Object getObject() throws Exception {
        return underling;
    }

    @Override
    public Class<?> getObjectType() {
        if (this.underling == null) {
            return null;
        }
        return this.underling.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}