package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by andrey on 11.11.14.
 */
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
public class App {

    //TODO remove
    public static void main(String[] args) {
        run(args);
    }

    public static void run(Object config, String... args) {
        SpringApplication.run(new Object[] {App.class, config}, args);
    }

    public static void run(String... args) {
        SpringApplication.run(new Object[] {App.class}, args);
    }

}
