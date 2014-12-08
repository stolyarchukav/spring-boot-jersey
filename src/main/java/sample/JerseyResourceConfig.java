package sample;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import sample.config.CommonConfig;
import sample.rest.filter.CustomFilters;
import sample.rest.mapper.ClientErrorMapper;
import sample.rest.mapper.ServerErrorMapper;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyResourceConfig.class);

    @Autowired
    private CommonConfig config;

    @Autowired(required = false)
    private CustomFilters customFilters;

    private final static Collection<Class> controllers = new ArrayList<>();
    private static CustomFilters filters;

    @PostConstruct
    public void init() {
        if (config != null) {
            scanControllers();
            filters = customFilters;
        } else {
            registerFilters();
            registerScannedControllers();
        }
    }

    private void registerFilters() {
        if (filters != null) {
            registerCustomFilters(filters.firstFilters());
        }

        register(HttpMethodOverrideFilter.class);
        register(JacksonFeature.class);

        //error mapping
        register(ClientErrorMapper.class);
        register(ServerErrorMapper.class);

        if (filters != null) {
            registerCustomFilters(filters.lastFilters());
        }
    }

    private void registerCustomFilters(Collection<Class> filters) {
        if (filters != null) {
            for (Class filter : filters) {
                register(filter);
                LOGGER.info("Custom jersey filter registered: {}", filter);
            }
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
                LOGGER.error("Error on REST controllers scanning: ", e);
            }
        }
    }

    private void registerScannedControllers() {
        for (Class controller : controllers) {
            register(controller);
            LOGGER.info("REST Controller registered: {}", controller);
        }
    }
}
