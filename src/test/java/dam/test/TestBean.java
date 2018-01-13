package dam.test;

import dam.annotation.Restriction;

import java.util.UUID;

public class TestBean implements TestInterface {
    public TestBean() {

    }

    @Restriction
    @Override
    public String getRandomString() {
        return UUID.randomUUID().toString();
    }
}
