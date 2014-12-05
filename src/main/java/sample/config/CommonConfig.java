package sample.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by andrey on 30.11.14.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="common")
public class CommonConfig {

    private String restPackage;

    private String rootRestPath;

    private int version;

}
