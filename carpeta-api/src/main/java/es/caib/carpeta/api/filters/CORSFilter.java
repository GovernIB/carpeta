package es.caib.carpeta.api.filters;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Filtre per implementar el protocol Cross-Origin Resource Sharing https://www.w3.org/TR/cors/.
 * Bàsicament només és necessari en cas que l'API REST s'hagi de cridar des d'una aplicació JS a un navegador
 * que estigui a un domini diferent al que està l'API.
 * Veure: https://www.html5rocks.com/en/tutorials/cors/
 * Activam {@link PreMatching} perquè cal capturar les cridades amb el mètode OPTIONS quen no corresponen a cap resource
 * Amb la prioritat 200 asseguram que s'executa després del logging, però abans que qualsevol altre.
 *
 * @author areus
 */
@Provider
@PreMatching
@Priority(200)
public class CORSFilter implements ContainerRequestFilter, ContainerResponseFilter {

    /**
     * Si és una petició preflight aborta la petició perquè no es processi cap a cap recurs, i al filtre de sortida
     * s'establiran les capçaleres.
     */
    @Override
    public void filter(ContainerRequestContext request) {
        if (isPreflightRequest(request)) {
            request.abortWith(Response.ok().build());
        }
    }

    /**
     * Estableix les capçaleres necessaries de CORS a la resposta.
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {

        // Si no hi ha capçalera Origin no és una peticó CORS, per tant, no hem de fer res
        if (request.getHeaderString("Origin") == null) {
            return;
        }

        // Si és una petició preflight fixam totes les capçaleres necessàries CORS
        if (isPreflightRequest(request)) {
            // Permetre el pas de credencials a la petició
            response.getHeaders().add("Access-Control-Allow-Credentials", "true");
            // Mètodes admesos. Els normals més OPTIONS i HEAD. Afegir o llevar el que calgui.
            response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            // Capçaleres admeses. Afegir les que calgui.
            response.getHeaders().add("Access-Control-Allow-Headers", "Authorization,  Content-Type");

            // Permetre cachear la resposta 1 hora. El valor per defecte són 5 segons, per això evita que el navegador
            // estigui constantment fent peticions OPTIONS.
            response.getHeaders().add("Access-Control-Max-Age", 3600);
        }

        // Totes les cridades CORS siguin normals o preflight requereixien aquesta capçalera
        // Es podria variar * per el site que volem permetre.
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
    }

    /**
     * Indica si és una petició preflight que és una petició amb el mètode OPTIONS i una capçalera Origin.
     */
    private static boolean isPreflightRequest(ContainerRequestContext request) {
        return request.getHeaderString("Origin") != null
                && request.getMethod().equalsIgnoreCase("OPTIONS");
    }
}