import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);

    public static void main(String[] args) {
         WeatherManager manager = context.getBean("weatherManager", WeatherManager.class);
         manager.run();
    }

    public static ApplicationContext applicationContext() {
        return context;
    }

}
