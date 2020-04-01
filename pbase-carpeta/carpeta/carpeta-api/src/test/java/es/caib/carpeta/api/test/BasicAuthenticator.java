package es.caib.carpeta.api.test;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Classe per incloure autenticació Basic dins un client JAX-RS mitjançant un filtre de client que
 * afegeix la capçalera <i>Authorization</i> corresponent d'acord amb un usuari i password.
 * Basada en http://www.adam-bien.com/roller/abien/entry/client_side_http_basic_access
 */
public class BasicAuthenticator implements ClientRequestFilter {

    private final String user;
    private final String password;

    public BasicAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void filter(ClientRequestContext requestContext) {
        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
    }

    private String getBasicAuthentication() {
        String token = this.user + ":" + this.password;
        return "Basic " + Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }
}