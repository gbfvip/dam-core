package dam.spring.defenation;

import org.springframework.beans.factory.FactoryBean;

/**
 * this Install class have no actual need right now,just for further use
 */
public class Install implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
