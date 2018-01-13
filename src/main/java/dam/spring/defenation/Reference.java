package dam.spring.defenation;


import dam.annotation.Restriction;
import dam.cache.ArgsWrapper;
import dam.cache.MethodResultCache;
import dam.exception.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.DigestUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class Reference implements FactoryBean, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(Reference.class);

    private String id;

    private String clazz;

    private Object underling;

    private Object proxy;

    private MethodResultCache cache;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCache(MethodResultCache cache) {
        this.cache = cache;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
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

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        underling = Class.forName(this.clazz).newInstance();
        proxy = Proxy.newProxyInstance(underling.getClass().getClassLoader(), underling.getClass().getInterfaces(), (proxy1, method, args) -> {
            Object result = null;
            Restriction annotation = method.getAnnotation(Restriction.class);
            if (annotation == null) {
                result = method.invoke(underling, args);
            } else {
                ArgsWrapper argsWrapper = new ArgsWrapper(args);
                String key = DigestUtils.md5DigestAsHex((method.toString() + argsWrapper.toString()).getBytes());
                try {
                    result = cache.get(key, () -> {
                        Object cacheResult = method.invoke(proxy1, args);
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
}