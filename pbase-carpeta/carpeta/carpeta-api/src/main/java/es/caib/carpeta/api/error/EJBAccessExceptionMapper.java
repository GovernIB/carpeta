package es.caib.carpeta.api.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Permet mapejar a una respota qualsevol excepció unchecked de la capa EJB.
 * Envia un codi d'error 500 i evita que els detalls de l'excepció arribin al client,
 * ja que un error de sistema l'ha de mirar l'administrador.
 *
 * @author areus
 */
@Provider
public class EJBAccessExceptionMapper implements ExceptionMapper<EJBAccessException> {

    private static final Logger LOG = LoggerFactory.getLogger(EJBAccessExceptionMapper.class);

    @Override
    public Response toResponse(EJBAccessException e) {
        LOG.error("Rebuda una EJBAccessException: " + e.getMessage());
        LOG.error("Retornam un codi 403 al client");
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
