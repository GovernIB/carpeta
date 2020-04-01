package es.caib.carpeta.back;

import es.caib.carpeta.commons.utils.Constants;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

/**
 * Servlet d'exemple per imprimir tota la informació disponible del sistema d'autenticació.
 * Imprimeix la informació disponible d'acord amb l'especificació Servlet 4.0, d'acord amb l'especificació
 * JACC 1.5, i la informació disponible de keycloak.
 *
 * @author areus
 */
@WebServlet(urlPatterns = "/principalInfo")
public class PrincipalInfoServlet extends HttpServlet {

    private static final long serialVersionUID = -6071413123148684294L;

    @SuppressWarnings("unchecked")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        writer.println("Principal INFO");

        // API especificació Servlets
        writer.println("--------------------------------");
        writer.println("Informació standard del request:");
        writer.println("request.remoteUser = " + request.getRemoteUser());
        Principal principal = request.getUserPrincipal();
        writer.println("request.userPrincipal.name = " + principal.getName());
        writer.println("request.userPrincipal.class = " + principal.getClass());
        writer.println("request.isUserInRole(" + Constants.CAR_ADMIN + ") = "
                + request.isUserInRole(Constants.CAR_ADMIN));
        writer.println("request.isUserInRole(" + Constants.CAR_USER + ") = "
                + request.isUserInRole(Constants.CAR_USER));

        // API especificació JACC - JSR 115
        try {
            writer.println("--------------------------------");
            writer.println("Informació de PolicyContext:");
            writer.println("PolicyContext.handlerKeys: " + PolicyContext.getHandlerKeys());
            Subject subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");

            if (subject != null) {
                writer.println("Subject principals:");
                for (Principal subjectPrincipal : subject.getPrincipals()) {
                    writer.println(subjectPrincipal.getName() + ", " + subjectPrincipal.getClass());
                }
            }

        } catch (PolicyContextException e) {
            writer.println("Error obtenint subject: " + e.getMessage());
        }


        // API keycloak
        if (principal instanceof KeycloakPrincipal<?>) {
            writer.println("--------------------------------");
            writer.println("Informació del token de keycloak:");

            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            KeycloakSecurityContext keycloakSecurityContext = kp.getKeycloakSecurityContext();

            AccessToken token = keycloakSecurityContext.getToken();
            writer.println("subject = " + token.getSubject());
            writer.println("username = " + token.getPreferredUsername());
            writer.println("email = " + token.getEmail());
            writer.println("familyName = " + token.getFamilyName());
            writer.println("givenName = " + token.getGivenName());
            writer.println("realmAccess.roles = " + token.getRealmAccess().getRoles());
            writer.println("scope = " + token.getScope());

            writer.println("--------------------------------");
            writer.println("resourceAccessRoles:");
            Map<String, AccessToken.Access> resourceAccess = token.getResourceAccess();
            for (String key : resourceAccess.keySet()) {
                writer.println(key + " = " + resourceAccess.get(key).getRoles());
            }

            writer.println("--------------------------------");
            writer.println("otherClaims:");
            Map<String, Object> otherClaims = token.getOtherClaims();
            for (String key : otherClaims.keySet()) {
                writer.println(key + " = " + otherClaims.get(key));
            }

            writer.println("--------------------------------");
            writer.println("accessToken (JWT / Bearer token) = " + keycloakSecurityContext.getTokenString());
        }
    }
}
