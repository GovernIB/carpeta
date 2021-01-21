package es.caib.carpeta.front.pluginlogin;

import java.net.HttpURLConnection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
//import org.springframework.http.MediaType;
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
    private String APLICACIO_CODI = Configuracio.getLoginIBAplicacionCode();
    private String APLICACIO_DESCRIPCIO = Configuracio.getLoginIBAplicacionDescription();
    private String NIVELL_QAA = Configuracio.getLoginIBNivelQAA();
    private String LOGINIB_USER = Configuracio.getLoginIBUser();
    private String LOGINIB_PASS = Configuracio.getLoginIBPassword();
    private String LOGINIB_URL = Configuracio.getLoginIBUrl();
    
    private boolean ignoreServerCertificates = false;
    

    

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

        /* XYZ ZZZ  Jersey
        LoginIBApiJersey api = getLoginApiJersey();
        String url = api.login(param);
        
        
        */
        
        /*
         * XYZ ZZZ RestEasy
         */
        /*
        LoginIBApiRestEasy easy = new LoginIBApiRestEasy();
        
        String url = easy.login(param);
        */
        
        
        /* XYZ ZZZ  RestTemplate Spring */
        final RestTemplate restTemplate = getLoginIbRestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        final HttpEntity<RLoginParams> peticion = new HttpEntity<>(param, headers);
        final ResponseEntity<String> responseTramite = restTemplate.postForEntity(LOGINIB_URL + "/login", peticion,
                String.class);

        log.info("response: " + responseTramite.toString());
        log.info("URL: " + responseTramite.getBody());

        String url = responseTramite.getBody();
        
        
        
        log.info("\n\n  URL LOGIN = " + url + "\n\n\n");
        
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
            // S'ha mogut la petició al issue "LoginIB no retorna informació del Provider de l'autenticacio #310 "
            //  TODO este valor no viene informado por LoginIB, tendriamos que hablar
            // con loginIb a ver si pueden enviarnoslo.
            loginInfo.setIdentityProvider("Clave"); 
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
    
    
    
    private LoginIBApiJersey loginAPI =  null;
    
    
    protected LoginIBApiJersey getLoginApiJersey() {
        if (loginAPI == null) {            
            loginAPI = new LoginIBApiJersey(LOGINIB_USER, LOGINIB_PASS, ignoreServerCertificates);
        }
        
        return loginAPI;
    }
    
    
    
    
    public class LoginIBApiRestEasy {
        
        
        
        public String login(RLoginParams param) throws Exception {
            //Client client = ClientBuilder.newClient();    
            //
          
            HttpURLConnection.setFollowRedirects(true); 
            
            ResteasyClient client = (ResteasyClient)ResteasyClientBuilder.newBuilder()
                    //.sslContext(buildSSLContext())
                    //.hostnameVerifier(buildHostVerifier())
                    //.withConfig(config)
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
            } if (status == 302) {
                String newEndPoint =  response.getHeaderString("Location");
                
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
