package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecurityServiceImpl implements SecurityService{

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Value("${es.caib.carpeta.loginib.metodos_auth}") private String METODES_AUTENTICACIO;
    @Value("${es.caib.carpeta.loginib.entidad}") private String CODI_ENTITAT;
    @Value("${es.caib.carpeta.loginib.aplicacion}") private String APLICACIO_CODI;
    @Value("${es.caib.carpeta.loginib.url_callback_login}") private String URL_CALLBACK_LOGIN;
    @Value("${es.caib.carpeta.loginib.url_callback_error}") private String URL_CALLBACK_ERROR;
    @Value("${es.caib.carpeta.loginib.url_callback_logout}") private String URL_CALLBACK_LOGOUT;
    @Value("${es.caib.carpeta.loginib.idioma}") private String IDIOMA;
    @Value("${es.caib.carpeta.loginib.nivel_qaa}") private String NIVELL_QAA;
    @Value("${es.caib.carpeta.loginib.user}") private String USER;
    @Value("${es.caib.carpeta.loginib.pass}") private String PASS;
    @Value("${es.caib.carpeta.loginib.url}") private String URL;

    public String iniciarSesionAutentificacion() throws Exception {


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
        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USER, PASS));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RLoginParams> peticion = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(URL + "/login",
                peticion, String.class);

        log.info("URL: " + responseTramite.getBody());

        return responseTramite.getBody();
    }

    @Override
    public UsuarioClave validarTicketAutentificacion(final String ticket) throws Exception {

        log.info("Dentro de validarTicketAutentificacion: " + ticket);

        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(
                USER, PASS));

        final RDatosAutenticacion datosAutenticacion = restTemplate.getForObject(
                URL + "/ticket/" + ticket, RDatosAutenticacion.class);

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
    public String iniciarSesionLogout() throws Exception {

        log.info("Dentro de iniciarSesionLogout");
        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USER, PASS));

        final RLogoutParams param = new RLogoutParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(URL_CALLBACK_LOGOUT);
        param.setIdioma(IDIOMA);
        param.setAplicacion("CARPETA");
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RLogoutParams> request = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(URL + "/logout",
                request, String.class);

        String url = responseTramite.getBody();

        log.info("Url logout: " + url);

        return url;
    }
}
