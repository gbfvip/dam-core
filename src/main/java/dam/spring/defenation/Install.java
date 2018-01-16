package dam.spring.defenation;

import org.springframework.beans.factory.FactoryBean;

public class Install implements FactoryBean {

    private static final Install INSTANCE = new Install();

    @Override
    public Object getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return Install.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
