package dam.test;

import java.util.UUID;

public class TestBean {
    public TestBean() {

    }

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
