package sample.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by andrey on 10.11.14.
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/jersey")
public class TestJerseyController {

    @GET
    public Response test() {
        return Response.created(URI.create("http://google.com/123")).
                entity("Test_resp").build();
    }

    @Path("{param}")
    @GET
    public TestResource test2(@PathParam("param") String param) {
        TestResource resource = new TestResource();
        resource.setFieldOne(param);
        resource.setFieldTwo(21);
        return resource;
    }

}
