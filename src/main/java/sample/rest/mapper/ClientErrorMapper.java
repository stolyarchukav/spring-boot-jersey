package sample.rest.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientErrorMapper implements ExceptionMapper<ClientErrorException> {

    @Override
    public Response toResponse(ClientErrorException exception) {
        return Response.status(exception.getResponse().getStatus()).
                entity(new ErrorMessage(exception.getMessage())).
                build();
    }

    @AllArgsConstructor
    @Getter
    public static class ErrorMessage {
        private String message;
    }

}
