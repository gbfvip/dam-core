package dam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "classpath:spring/*.xml"
        });
        System.out.println(context.getBean("test", TestInterface.class).getRandomString());
        System.out.println(context.getBean("test", TestInterface.class).getRandomString());
        System.out.println(context.getBean("test", TestInterface.class).getRandomStringRT());
        System.out.println(context.getBean("test", TestInterface.class).getRandomStringRT());
        Thread.sleep(TimeUnit.SECONDS.toMillis(11));
        System.out.println(context.getBean("test", TestInterface.class).getRandomString());
    }
}
