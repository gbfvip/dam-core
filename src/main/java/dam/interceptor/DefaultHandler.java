package dam.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultHandler implements InvocationHandler {

    private String id;
    private String ref;

    public DefaultHandler(String id, String ref) {
        this.id = id;
        this.ref = ref;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //todo do dam logic
        Object result = method.invoke(proxy, args);
        return result;
    }
}
