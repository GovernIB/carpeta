package es.caib.carpeta.api.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * Realitza un log de totes les peticions rebudes. Es configura com amb {@link PreMatching} perquè la petició
 * encara que no es correspongui amb cap resource. Sinó només és loguejarien les petcions una vegada ja s'ha determinat
 * a quin resource pertanyen.
 * Els filtres s'ordenen per prioritat ascendent a l'entrada i prioritat descendent a la sortida. Això assegura que
 * a l'entrada sigui el primer i a la sortida el darrer.
 *
 * @author areus
 */
@Provider
@PreMatching
@Priority(100)
public class LoggingRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestFilter.class);

    /**
     * Realitza un log del mètode HTTP i el request URI.
     *
     * @param request informació de context de la petició
     */
    @Override
    public void filter(ContainerRequestContext request) {
        LOG.debug("Request: " + request.getMethod() + " " + request.getUriInfo().getRequestUri());
    }

    /**
     * Realitza un log del mètode HTTP i el request URI amb l'status de la resposta.
     *
     * @param request  informació de context de la petició
     * @param response informació de context de la resposta
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        LOG.debug("Response: " + request.getMethod() + " " + request.getUriInfo().getRequestUri()
                + " = " + response.getStatus());
    }
}