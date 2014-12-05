package sample.rest.sub;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by andrey on 06.12.14.
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/test2")
public class Test2Controller {

    @GET
    public String test() {
        return "test2";
    }

}
