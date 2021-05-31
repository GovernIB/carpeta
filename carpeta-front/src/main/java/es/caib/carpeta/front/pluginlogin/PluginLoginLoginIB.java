package es.caib.carpeta.front.pluginlogin;

import java.net.HttpURLConnection;
import java.util.HashSet;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RDatosRepresentante;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;

/**
 * 
 * @author anadal
 *
 */
public class PluginLoginLoginIB implements IPluginLogin {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String METODES_AUTENTICACIO = Configuracio.getLoginIBMethodAuth();
    private String CODI_ENTITAT = Configuracio.getLoginIBEntidad();
    private String APLICACIO_CODI = Configuracio.getLoginIBAplicacionCode();
    private String APLICACIO_DESCRIPCIO = Configuracio.getLoginIBAplicacionDescription();
    private String NIVELL_QAA = Configuracio.getLoginIBNivelQAA();
    private String LOGINIB_USER = Configuracio.getLoginIBUser();
    private String LOGINIB_PASS = Configuracio.getLoginIBPassword();
    private String LOGINIB_URL = Configuracio.getLoginIBUrl();

    private boolean ignoreServerCertificates = false;

    private static final HashSet<Character> EMPRESES = new HashSet<Character>();

    /**
     * 
     * A Sociedades anónimas. B Sociedades de responsabilidad limitada. C Sociedades
     * colectivas D Sociedades comanditarias E Comunidades de bienes y herencias
     * yacentes y demás entidades carentes de personalidad jurídica no incluidas
     * expresamente en otras claves. F Sociedades cooperativas G Asociaciones H
     * Comunidades de propietarios en régimen de propiedad horizontal J Sociedades
     * civiles P Corporaciones Locales Q Organismos públicos R Congregaciones e
     * instituciones religiosas S Órganos de la Administración del Estado y de las
     * Comunidades Autónomas U Uniones Temporales de Empresas V Otros tipos no
     * definidos en el resto de las claves
     * 
     */
    static {
        EMPRESES.add('A');
        EMPRESES.add('B');
        EMPRESES.add('C');
        EMPRESES.add('D');
        EMPRESES.add('E');
        EMPRESES.add('F');
        EMPRESES.add('G');
        EMPRESES.add('H');
        EMPRESES.add('J');
        EMPRESES.add('P');
        EMPRESES.add('Q');
        EMPRESES.add('R');
        EMPRESES.add('S');
        EMPRESES.add('U');
        EMPRESES.add('V');
    }

    public PluginLoginLoginIB() {
        super();
    }

    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError, String language)
            throws Exception {
        final RLoginParams param = new RLoginParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(urlCallBackLoginOk);
        param.setUrlCallbackError(urCallBackLoginError);
        param.setIdioma(language);
        param.setForzarAutenticacion(false);
        param.setQaa(Integer.parseInt(NIVELL_QAA));
        param.setMetodosAutenticacion(METODES_AUTENTICACIO);
        param.setAplicacion(APLICACIO_DESCRIPCIO);

        /*
         * XYZ ZZZ Jersey LoginIBApiJersey api = getLoginApiJersey(); String url =
         * api.login(param);
         * 
         * 
         */

        /*
         * XYZ ZZZ RestEasy
         */
        /*
         * LoginIBApiRestEasy easy = new LoginIBApiRestEasy();
         * 
         * String url = easy.login(param);
         */

        /* XYZ ZZZ RestTemplate Spring */
        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        final HttpEntity<RLoginParams> peticion = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseLoginIB = restTemplate.postForEntity(LOGINIB_URL + "/login", peticion,
                String.class);

        log.info("LoginIB: response: " + responseLoginIB.toString());
        log.info("LoginIB: URL: " + responseLoginIB.getBody());

        String url = responseLoginIB.getBody();

        log.info("\n\n  URL LOGIN = " + url + "\n\n\n");

        return url;

    }

    public LoginInfo validateAuthenticationTicket(final String ticket) throws Exception {

        try {
            final RestTemplate restTemplate = getLoginIbRestTemplate();

            final RDatosAutenticacion datosAutenticacion = restTemplate.getForObject(LOGINIB_URL + "/ticket/" + ticket,
                    RDatosAutenticacion.class);

            log.info("LoginIB:: NIF " + datosAutenticacion.getNif());
            log.info("LoginIB:: Name " + datosAutenticacion.getNombre());
            log.info("LoginIB:: Surname1 " + datosAutenticacion.getApellido1());
            log.info("LoginIB:: Surname2 " + datosAutenticacion.getApellido2());
            log.info("LoginIB:: AdministrationID" + datosAutenticacion.getNif());
            log.info("LoginIB:: AuthenticationMethod " + datosAutenticacion.getMetodoAutenticacion());
            RDatosRepresentante rep = datosAutenticacion.getRepresentante();

            if (rep != null) {
                log.info("Representant:: getNombre -> " + rep.getNombre());
                log.info("Representant:: getApellido1-> " + rep.getApellido1());
                log.info("Representant:: getApellido1 getApellido2 -> " + rep.getApellido2());
                log.info("Representant:: getApellidos -> " + rep.getApellidos());
                log.info("Representant:: getNif -> " + rep.getNif());
            }

            final String username = datosAutenticacion.getNif();
            final String name = datosAutenticacion.getNombre();
            final String surname1 = datosAutenticacion.getApellido1();
            final String surname2 = datosAutenticacion.getApellido2();
            final String administrationID = datosAutenticacion.getNif();
            final String authenticationMethod = datosAutenticacion.getMetodoAutenticacion();
            int qaa;
            try {
                qaa = Integer.parseInt(datosAutenticacion.getQaa());
            } catch (Exception e) {
                log.error(" Nivell d'Autenticacio QAA desconegut ]" + datosAutenticacion.getQaa() + "[", e);
                qaa = LoginInfo.NIVELL_AUTENTICACIO_BAIX;
            }

            // S'ha mogut la petició al issue "LoginIB no retorna informació del Provider de
            // l'autenticacio #310 "
            // TODO este valor no viene informado por LoginIB, tendriamos que hablar
            // con loginIb a ver si pueden enviarnoslo.
            final String identityProvider = "Clave";

            boolean business;
            {
                char primera = administrationID.charAt(0);
                if (Character.isDigit(primera)) {
                    business = false;
                } else {

                    if (EMPRESES.contains(Character.valueOf(primera))) {
                        business = true;
                    } else {
                        business = false;
                    }
                }
            }

            LoginInfoRepresentative representative = null;
            if (rep == null) {
                representative = null;
            } else {
                representative = new LoginInfoRepresentative(rep.getNombre(),
                        rep.getApellido1(),rep.getApellido2(),rep.getNif());
            }

            final LoginInfo loginInfo = new LoginInfo(username, name, surname1, surname2, administrationID,
                    authenticationMethod, qaa, identityProvider, business, representative);

            return loginInfo;
        } catch (Exception e) {
            log.error(" ERROR DESCONEGUT en validateAuthenticationTicket: " + e.getMessage(), e);
            throw e;
        }

    }

    public String logout(String urlCallBackLogout, String language) throws Exception {

        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final RLogoutParams param = new RLogoutParams();
        param.setAplicacion(APLICACIO_CODI);
        param.setEntidad(CODI_ENTITAT);
        param.setUrlCallback(urlCallBackLogout);
        param.setIdioma(language);
        param.setAplicacion(APLICACIO_DESCRIPCIO);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

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

    private LoginIBApiJersey loginAPI = null;

    protected LoginIBApiJersey getLoginApiJersey() {
        if (loginAPI == null) {
            loginAPI = new LoginIBApiJersey(LOGINIB_USER, LOGINIB_PASS, ignoreServerCertificates);
        }

        return loginAPI;
    }

    public class LoginIBApiRestEasy {

        public String login(RLoginParams param) throws Exception {
            // Client client = ClientBuilder.newClient();
            //

            HttpURLConnection.setFollowRedirects(true);

            ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newBuilder()
                    // .sslContext(buildSSLContext())
                    // .hostnameVerifier(buildHostVerifier())
                    // .withConfig(config)
                    .build();

            ResteasyWebTarget target = client.target(LOGINIB_URL);
            target.register(new BasicAuthentication(LOGINIB_USER, LOGINIB_PASS));

            String data = AbstractJersey2ConnectionManager.serializeJson(param);

            System.out.println(" OBJECT PARAMETER: " + data);

            Entity<?> json = Entity.entity(data, javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE);

            Response response = target.request().accept(MediaType.APPLICATION_JSON).post(json);

            int status = response.getStatus();

            log.info("\n\n\n STATUS = " + status + "\n\n");

            if (status == 200 || status == 201) {
                String raw_msg = response.readEntity(String.class);

                log.info("RAW MESSAGE : " + raw_msg);

                return raw_msg;
            }
            if (status == 302) {
                String newEndPoint = response.getHeaderString("Location");

                System.out.println(" \n\n LOCATION HEADER = ]" + newEndPoint + "[\n\n");

                throw new Exception("HAN FET FER UNA REDIRECCIO: " + newEndPoint);
            } else {
                String raw_msg = response.readEntity(String.class);

                throw new Exception("Error desconegut (Codi de servidor " + response.getStatus() + "): \n " + raw_msg
                        + response.toString());
            }

        }
    }

    public class LoginIBApiJersey extends AbstractJersey2ConnectionManager {

        public LoginIBApiJersey(String token, boolean ignoreServerCertificates) {
            super(token, ignoreServerCertificates);
            // TODO Auto-generated constructor stub
        }

        public LoginIBApiJersey(String username, String password, boolean ignoreServerCertificates) {
            super(username, password, ignoreServerCertificates);
            setConnectionTimeoutMs(60000);
            setReadTimeoutMs(60000);
        }

        public String login(RLoginParams param) throws Exception {
            String endPoint = LOGINIB_URL + "/login";

            log.info("\n\n\n CRIDADA LOGOIN URL-REST: " + endPoint + "\n\n");

            Response response = commonCall(param, endPoint);

            String rjson = response.readEntity(String.class);

            return rjson;

        }

    }

}
