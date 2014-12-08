package sample;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import sample.config.CommonConfig;

import javax.inject.Inject;

/**
 * Created by andrey on 11.11.14.
 */
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
public class App {

    @Inject
    private CommonConfig commonConfig;

    //TODO remove
    public static void main(String[] args) {
        run(args);
    }

    public static void run(Object config, String... args) {
        SpringApplication.run(new Object[]{App.class, config}, args);
    }

    public static void run(String... args) {
        SpringApplication.run(new Object[] {App.class}, args);
    }

    @Bean
    public ServletRegistrationBean jerseyServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(),
                "/" + commonConfig.getRootRestPath() + "/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyResourceConfig.class.getName());
        return registration;
    }

}
