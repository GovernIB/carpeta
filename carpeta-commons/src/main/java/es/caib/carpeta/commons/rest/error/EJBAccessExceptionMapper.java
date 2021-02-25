package es.caib.carpeta.commons.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Permet mapejar a una respota a un error de permisos a la capa EJB.
 * Envia un codi d'error 403 al client.
 *
 * @author areus
 */
@Provider
public class EJBAccessExceptionMapper implements ExceptionMapper<EJBAccessException> {

    private static final Logger LOG = LoggerFactory.getLogger(EJBAccessExceptionMapper.class);

    @Override
    public Response toResponse(EJBAccessException e) {
        LOG.error("Rebuda una EJBAccessException: {}", e.getMessage());
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
