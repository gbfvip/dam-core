package dam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "classpath:spring/*.xml"
        });
        System.out.println(context.getBean("testBean1",TestInterface.class).getRandomString());
        System.out.println(context.getBean("testBean1",TestInterface.class).getRandomString());
    }
}
