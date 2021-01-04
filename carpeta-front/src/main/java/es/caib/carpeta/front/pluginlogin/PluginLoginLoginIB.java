package es.caib.carpeta.front.pluginlogin;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;

/**
 * 
 * @author anadal
 *
 */
public class PluginLoginLoginIB {

    private static final Logger log = LoggerFactory.getLogger(PluginLoginLoginIB.class);

    private String METODES_AUTENTICACIO = Configuracio.getLoginIBMethodAuth();
    private String CODI_ENTITAT = Configuracio.getLoginIBEntidad();
    private String APLICACIO_CODI = Configuracio.getLoginIBAplicacion();

    private String NIVELL_QAA = Configuracio.getLoginIBNivelQAA();
    private String LOGINIB_USER = Configuracio.getLoginIBUser();
    private String LOGINIB_PASS = Configuracio.getLoginIBPassword();
    private String LOGINIB_URL = Configuracio.getLoginIBUrl();
    
    private boolean ignoreServerCertificates = false;
    

    private String APLICACIO_DESCRIPCIO = "CARPETA"; // XYZ ZZZ

    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError, String language) throws Exception {
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

        /* XYZ ZZZ
        LoginIBApi api = getLoginApi();
        String url = api.login(param);
        
        log.info("\n\n  URL LOGIN = " + url + "\n\n\n");
        */
        
        
        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<RLoginParams> peticion = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(LOGINIB_URL + "/login", peticion,
                String.class);

        log.info("response: " + responseTramite.toString());
        log.info("URL: " + responseTramite.getBody());

        String url = responseTramite.getBody();
        
        
        return url;

    }

    public LoginInfo validateAuthenticationTicket(final String ticket) throws Exception {

        try {
            final RestTemplate restTemplate = getLoginIbRestTemplate();

            final RDatosAutenticacion datosAutenticacion = restTemplate.getForObject(LOGINIB_URL + "/ticket/" + ticket,
                    RDatosAutenticacion.class);

            final LoginInfo loginInfo = new LoginInfo();

            loginInfo.setUsername(datosAutenticacion.getNif());
            loginInfo.setName(datosAutenticacion.getNombre());
            loginInfo.setSurname1(datosAutenticacion.getApellido1());
            loginInfo.setSurname2(datosAutenticacion.getApellido2());
            loginInfo.setAdministrationID(datosAutenticacion.getNif());
            loginInfo.setAuthenticationMethod(datosAutenticacion.getMetodoAutenticacion());
            loginInfo.setQaa(datosAutenticacion.getQaa());
            loginInfo.setIdentityProvider("Clave"); // XYZ ZZZ ZZZ TODO este valor no viene informado por LoginIB,
                                                       // tendriamos que hablar
            // con loginIb a ver si pueden enviarnoslo.

            return loginInfo;
        } catch (Exception e) {
            // XYZ ZZZ ZZZ
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
    
    
    
    private LoginIBApi loginAPI =  null;
    
    
    protected LoginIBApi getLoginApi() {
        if (loginAPI == null) {            
            loginAPI = new LoginIBApi(LOGINIB_USER, LOGINIB_PASS, ignoreServerCertificates);
        }
        
        return loginAPI;
    }
    
    
    
    public class LoginIBApi extends AbstractJersey2ConnectionManager {

        public LoginIBApi(String token, boolean ignoreServerCertificates) {
            super(token, ignoreServerCertificates);
            // TODO Auto-generated constructor stub
        }

        public LoginIBApi(String username, String password, boolean ignoreServerCertificates) {
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
