package es.caib.carpeta.front.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private String METODES_AUTENTICACIO = Configuracio.getLoginIBMethodAuth();
    private String CODI_ENTITAT = Configuracio.getLoginIBEntidad();
    private String APLICACIO_CODI = Configuracio.getLoginIBAplicacion();
    /*
     * XYZ ZZZ private String URL_CALLBACK_LOGIN =
     * Configuracio.getLoginIBUrlCallbackLogin(); private String URL_CALLBACK_ERROR
     * = Configuracio.getLoginIBUrlCallbackError(); private String
     * URL_CALLBACK_LOGOUT = Configuracio.getLoginIBUrlCallbackLogout(); private
     * String IDIOMA = Configuracio.getLoginIBIdioma();
     */
    private String NIVELL_QAA = Configuracio.getLoginIBNivelQAA();
    private String LOGINIB_USER = Configuracio.getLoginIBUser();
    private String LOGINIB_PASS = Configuracio.getLoginIBPassword();
    private String LOGINIB_URL = Configuracio.getLoginIBUrl();

    @Override
    public String iniciarSesionAutentificacion(String URL_CALLBACK_LOGIN, String URL_CALLBACK_ERROR, String IDIOMA) {
        final RLoginParams param = new RLoginParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(URL_CALLBACK_LOGIN);
        param.setUrlCallbackError(URL_CALLBACK_ERROR);
        param.setIdioma(IDIOMA);
        param.setForzarAutenticacion(false);
        param.setQaa(Integer.parseInt(NIVELL_QAA));
        param.setMetodosAutenticacion(METODES_AUTENTICACIO);
        param.setAplicacion("CARPETA");

        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<RLoginParams> peticion = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(LOGINIB_URL + "/login", peticion,
                String.class);

        log.info("response: " + responseTramite.toString());
        log.info("URL: " + responseTramite.getBody());

        return responseTramite.getBody();
    }

    @Override
    public UsuarioClave validarTicketAutentificacion(final String ticket) throws Exception {

        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final RDatosAutenticacion datosAutenticacion = restTemplate.getForObject(LOGINIB_URL + "/ticket/" + ticket,
                RDatosAutenticacion.class);

        final UsuarioClave usuarioClave = new UsuarioClave();

        usuarioClave.setNombre(datosAutenticacion.getNombre());
        usuarioClave.setApellido1(datosAutenticacion.getApellido1());
        usuarioClave.setApellido2(datosAutenticacion.getApellido2());
        usuarioClave.setNif(datosAutenticacion.getNif());
        usuarioClave.setMetodoAutentificacion(datosAutenticacion.getMetodoAutenticacion());
        usuarioClave.setQaa(datosAutenticacion.getQaa());

        return usuarioClave;
    }

    @Override
    public String iniciarSesionLogout(String URL_CALLBACK_LOGOUT, String IDIOMA) throws Exception {

        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final RLogoutParams param = new RLogoutParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(URL_CALLBACK_LOGOUT);
        param.setIdioma(IDIOMA);
        param.setAplicacion("CARPETA");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RLogoutParams> request = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(LOGINIB_URL + "/logout", request,
                String.class);

        String url = responseTramite.getBody();

        return url;
    }

    /**
     *
     * @return
     */
    private RestTemplate getLoginIbRestTemplate() {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(LOGINIB_USER, LOGINIB_PASS));

        return restTemplate;
    }
}
