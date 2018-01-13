package dam.spring.defenation;


import dam.annotation.Restriction;
import dam.cache.ArgsWrapper;
import dam.cache.MethodResultCache;
import dam.exception.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;


public class Reference implements FactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(Reference.class);

    private String id;

    private Object underling;

    @Autowired
    private MethodResultCache cache;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getUnderling() {
        return underling;
    }

    public void setUnderling(Object underling) {
        this.underling = underling;
    }

    public void setCache(MethodResultCache cache) {
        this.cache = cache;
    }

    @PostConstruct
    private void init() {
        Proxy.newProxyInstance(underling.getClass().getClassLoader(), underling.getClass().getInterfaces(), (proxy, method, args) -> {
            Object result = null;
            Restriction annotation = method.getAnnotation(Restriction.class);
            if (annotation != null) {
                ArgsWrapper argsWrapper = new ArgsWrapper(args);
                String key = DigestUtils.md5DigestAsHex((method.toString() + argsWrapper.toString()).getBytes());
                try {
                    result = cache.get(key, () -> {
                        Object cacheResult = method.invoke(proxy, args);
                        if (cacheResult == null) {
                            throw new NullValueException();
                        }
                        return cacheResult;
                    });
                } catch (NullValueException ignored) {
                }
            }
            return result;
        });
    }

    @Override
    public Object getObject() throws Exception {
        return this.underling;
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