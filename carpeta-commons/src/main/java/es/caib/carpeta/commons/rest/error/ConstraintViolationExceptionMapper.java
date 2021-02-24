package es.caib.carpeta.commons.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapeja una excepció de validació a una resposta HTTP.
 * Aquest tipus d'excepció es produeix quan fallen les validacions establertes als camps de la petició.
 * La resposta retornada serà un codi 400, ja que és un error del client.
 *
 * @author areus
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException e) {
        LOG.error("Rebuda una ConstraintViolationException: {}", e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorBean.errorValidacio(e.getMessage()))
                .build();
    }
}
