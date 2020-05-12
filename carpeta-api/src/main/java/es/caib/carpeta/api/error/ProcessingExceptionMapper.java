package es.caib.carpeta.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Mapeja una excepció processant la petició en una resposta HTTP.
 * Aquest tipus d'excepció és produeix quan el format de la petició no és correcte, com ara
 * una cadena JSON invàlida.
 * La resposta retornada serà un codi 400, ja que és un error del client.
 *
 * @author areus
 */
@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessingExceptionMapper.class);

    @Override
    public Response toResponse(ProcessingException e) {
        LOG.error("Rebuda una ProcessingException: " + e.getMessage());

        ErrorBean errorBean = new ErrorBean();
        errorBean.setType(ErrorType.PETICIO);
        errorBean.setMessage(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorBean).build();
    }
}
