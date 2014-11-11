package sample;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import sample.rest.TestJerseyController;

/**
 * Created by andrey on 10.11.14.
 */
@Component
public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {
        register(TestJerseyController.class);
        register(JacksonFeature.class);
    }
}
