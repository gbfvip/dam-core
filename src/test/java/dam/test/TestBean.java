package dam.test;

import dam.annotation.Restriction;

import java.util.UUID;

public class TestBean implements TestInterface {
    public TestBean() {

    }

    @Override
    @Restriction
    public String getRandomString() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getRandomStringRT() {
        return UUID.randomUUID().toString();
    }
}
