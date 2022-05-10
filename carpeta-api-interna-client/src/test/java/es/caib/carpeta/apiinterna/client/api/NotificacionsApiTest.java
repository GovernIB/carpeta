/*
 * API REST EXTERNA de Carpeta
 * Conjunt de Serveis REST de Carpeta per ser accedits des de l'exterior
 *
 * OpenAPI spec version: 1.0.0
 * Contact: governdigital.carpeta@fundaciobit.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package es.caib.carpeta.apiinterna.client.api;

import es.caib.carpeta.apiinterna.client.model.SendMessageResult;
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.auth.HttpBasicAuth;
import org.junit.Test;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Ignore;

/**
 * API tests for NotificacionsApi
 * 
 * @author anadal
 */
@Ignore
public class NotificacionsApiTest {

    private NotificacionsApi apiNotif = null;

    private String nifTest;
    
    private String notificationCode;

    public static void main(String[] args) {
        try {
            NotificacionsApiTest n = new NotificacionsApiTest();
            
            //n.existCiutadaTest();
            
            //n.sendMessageTest();
            
            n.helpTest();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected NotificacionsApi getNotificacionsApi() throws Exception {

        if (apiNotif == null) {

            Properties prop = new Properties();
            prop.load(new FileInputStream("connection.properties"));
            String urlBase = prop.getProperty("urlbase");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            nifTest = prop.getProperty("niftest");
            
            notificationCode = prop.getProperty("notificationCode");

            ApiClient apiClient = new ApiClient();
            apiClient.setBasePath(urlBase);
            HttpBasicAuth basicAuth = (HttpBasicAuth) apiClient.getAuthentication("BasicAuth");
            basicAuth.setUsername(username);
            basicAuth.setPassword(password);

            apiNotif = new NotificacionsApi(apiClient);

        }
        return apiNotif;
    }

    /**
     * Consulta si tenim donat d&#x27;alta el mòbil d&#x27;un ciutadà/empresa a
     * partir del seu NIF.
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void existCiutadaTest() throws Exception {

        NotificacionsApi api = getNotificacionsApi();

        String nif = this.nifTest;
        String lang = null;

        try {
            api.existCitizen(nif, lang);
            Assert.fail("Hem passat parametre lang = null i no ha fallat: hauria d'haver fallat.");
        } catch (Exception e) {
            // TODO: handle exception
        } 
        
        lang = "es";
        System.out.println("Cridant existCiutadaTest(" + nif + ") ");
        Boolean response = api.existCitizen(nif, lang);

        System.out.println("existCiutadaTest(" + nif + ") => " + response);
        // TODO: test validations
    }

    /**
     * Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void sendMessageTest() throws Exception {

        List<String> notificationParameters = new ArrayList<String>();
        notificationParameters.add("param1XXX");
        notificationParameters.add("param2YYYY");
        notificationParameters.add("param3ZZZZ");
        final String notificationLang = "ca";
        final String langError = "ca";
        SendMessageResult smr = getNotificacionsApi().sendNotificationToMobile(this.nifTest, this.notificationCode, 
                notificationParameters, notificationLang,langError);
        
        System.out.println("CODE = " + smr.getCode());
        System.out.println("MSG = " + smr.getMessage());

    }
    
    
    /**
     * Retorna ajuda de certa NotificacioApp
     * @throws Exception
     */
    @Test
    public void helpTest() throws Exception {

        
  
        final String langError = "ca";
        String help  = getNotificacionsApi().help(this.notificationCode, langError);
        
        System.out.println("AJUDA =>\n" + help);

    }
    
    
    
    
}
