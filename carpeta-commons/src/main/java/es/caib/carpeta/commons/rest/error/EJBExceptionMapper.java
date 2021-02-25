package es.caib.carpeta.commons.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJBException;
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
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    private static final Logger LOG = LoggerFactory.getLogger(EJBExceptionMapper.class);

    @Override
    public Response toResponse(EJBException e) {
        LOG.error("Rebuda una EJBException: {}", e.getMessage());
        return Response.serverError().build();
    }
}
