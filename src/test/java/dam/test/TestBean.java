package dam.test;

import dam.annotation.UnderDamControl;
import dam.annotation.Restriction;

import java.util.UUID;

@UnderDamControl(value = "test", duration = 5, size = 10)
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
