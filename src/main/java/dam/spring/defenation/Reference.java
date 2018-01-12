package dam.spring.defenation;


import dam.cache.ArgsWrapper;
import dam.cache.MethodResultCache;
import dam.exception.NullValueException;
import dam.spring.SpringContextHolder;
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

    private String ref;

    private Object underling;

    @Autowired
    private MethodResultCache cache;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @PostConstruct
    private void init() {
        //todo during spring initial step,there is no ApplicationContext yet?
        underling = SpringContextHolder.getBean(ref);
        Proxy.newProxyInstance(underling.getClass().getClassLoader(), underling.getClass().getInterfaces(), (proxy, method, args) -> {
            ArgsWrapper argsWrapper = new ArgsWrapper(args);
            String key = DigestUtils.md5DigestAsHex((method.toString() + argsWrapper.toString()).getBytes());
            Object result = null;
            try {
                result = cache.get(key, () -> {
                    Object result1 = method.invoke(proxy, args);
                    if (result1 == null) {
                        throw new NullValueException();
                    }
                    return result1;
                });
            } catch (NullValueException ignored) {
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