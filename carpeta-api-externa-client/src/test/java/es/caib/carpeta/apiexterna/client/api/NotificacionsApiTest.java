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

package es.caib.carpeta.apiexterna.client.api;

import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.ApiException;
import es.caib.carpeta.apiexterna.client.services.auth.HttpBasicAuth;
import org.junit.Test;
import java.io.FileInputStream;
import java.util.Properties;

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

    public static void main(String[] args) {
        try {
            new NotificacionsApiTest().existCiutadaTest();
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

        System.out.println("Cridant existCiutadaTest(" + nif + ") ");
        Boolean response = api.existCiutada(nif, lang);

        System.out.println("existCiutadaTest(" + nif + ") => " + response);
        // TODO: test validations
    }

    /**
     * Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void sendMessageTest() throws Exception {
        String nif = null;
        String title = null;
        String message = null;
        String lang = null;
        String response = getNotificacionsApi().sendMessage(nif, title, message, lang);

        // TODO: test validations
    }
}
