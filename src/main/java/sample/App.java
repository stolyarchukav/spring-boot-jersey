package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by andrey on 11.11.14.
 */
@ComponentScan
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
