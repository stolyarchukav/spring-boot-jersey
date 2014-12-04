package sample;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import sample.config.CommonConfig;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
* Created by andrey on 10.11.14.
*/
@Component
public class JerseyResourceConfig extends ResourceConfig {

    @Autowired
    private CommonConfig config;

    private final static Collection<Class> controllers = new ArrayList<>();

    @PostConstruct
    public void init() {
        register(JacksonFeature.class);
        if (config != null) {
            scanControllers();
        } else {
            registerScannedControllers();
        }
    }

    private void scanControllers() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Path.class));

        Set<BeanDefinition> definitions = provider.findCandidateComponents(config.getRestPackage());
        for (BeanDefinition definition : definitions) {
            String className = definition.getBeanClassName();
            try {
                controllers.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void registerScannedControllers() {
        for (Class controller : controllers) {
            register(controller);
            System.out.println("Registered: " + controller);
        }
    }
}
