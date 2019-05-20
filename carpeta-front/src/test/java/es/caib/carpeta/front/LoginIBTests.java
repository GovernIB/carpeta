package es.caib.carpeta.front;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;



public class LoginIBTests extends CarpetaTestUtils{

    private static final String METODES_AUTENTICACIO = getLoginIbMetodosAuth();
    private static final String CODI_ENTITAT = getLoginIbEntidad();
    private static final String APLICACIO_CODI = getLoginIbAplicacion();
    private static final String URL_CALLBACK_LOGIN = getLoginIbUrlCallBackLogin();
    private static final String URL_CALLBACK_ERROR = getLoginIbUrlCallBackError();
    private static final String URL_CALLBACK_LOGOUT = getLoginIbUrlCallBackLogout();
    private static final String IDIOMA = getLoginIbIdioma();
    private static final String NIVELL_QAA = getLoginIbNivelQaa();
    private static final String USUARI = getLoginIbUser();
    private static final String CONTRASENYA = getLoginIbPass();
    private static final String URL = getLoginIbUrl();

    @Test
    public void iniciarSesionAutenticacion(){

        final RLoginParams param = new RLoginParams();

        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(URL_CALLBACK_LOGIN);
        param.setUrlCallbackError(URL_CALLBACK_ERROR);
        param.setIdioma(IDIOMA);
        param.setForzarAutenticacion(false);
        param.setQaa(Integer.parseInt(NIVELL_QAA));
        param.setMetodosAutenticacion(METODES_AUTENTICACIO);

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(
                USUARI, CONTRASENYA));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RLoginParams> request = new HttpEntity<>(param,
                headers);

        final ResponseEntity<String> responseTramite = restTemplate
                .postForEntity(URL + "/login", request, String.class);

        System.out.println("Respuesta: " + responseTramite.getBody());
    }

    public void validarTicketAutenticacion(final String pTicket){

        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(
                USUARI, CONTRASENYA));

        final RDatosAutenticacion datosAutenticacion = restTemplate.getForObject(
                URL + "/ticket/" + pTicket,
                RDatosAutenticacion.class);

        System.out.println(datosAutenticacion);
    }

    @Test
    public void iniciarSesionLogout(){

        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(
                USUARI, CONTRASENYA));

        final RLogoutParams param = new RLogoutParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(URL_CALLBACK_LOGOUT);
        param.setIdioma(IDIOMA);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RLogoutParams> request = new HttpEntity<>(param,
                headers);
        final ResponseEntity<String> responseTramite = restTemplate
                .postForEntity(URL + "/logout", request,
                        String.class);

        System.out.println(responseTramite.getBody());
    }
}
