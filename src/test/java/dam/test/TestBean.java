package dam.test;

import java.util.UUID;

public class TestBean implements TestInterface {
    public TestBean() {

    }

    @Override
    public String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
