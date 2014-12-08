package sample.rest.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
public class ServerErrorMapper implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerErrorMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        String id = UUID.randomUUID().toString();
        LOGGER.error("Internal server error {}", id, exception);
        String message = "Internal server error occurred";
        return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity(new ErrorMessage(message, id)).build();
    }

    @AllArgsConstructor
    @Getter
    public static class ErrorMessage {
        private String message;
        private String eventId;
    }

}
