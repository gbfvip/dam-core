package dam.test;

import dam.annotation.Restriction;

public interface TestInterface {
    
    @Restriction
    public String getRandomString();
}
