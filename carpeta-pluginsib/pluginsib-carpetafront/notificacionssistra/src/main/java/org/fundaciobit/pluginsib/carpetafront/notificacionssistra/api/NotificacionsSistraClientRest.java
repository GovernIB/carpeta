package org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api;

import java.rmi.RemoteException;
import java.util.ArrayList;

//import javax.ejb.CreateException;
import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandler;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.representation.Form;


/**
 * Client REST per al servei de carpeta de NOTIB.
 *
 */
public class NotificacionsSistraClientRest {

    private static final String CARPETA_SERVICE_PATH = "/api/consulta/v1";
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    private String baseUrl;
    private String username;
    private String password;

    private boolean autenticacioBasic = false;

    public NotificacionsSistraClientRest() {}
    public NotificacionsSistraClientRest(
         String baseUrl,
         String username,
         String password) {
     super();
     this.baseUrl = baseUrl;
     this.username = username;
     this.password = password;
    }
    
    public NotificacionsSistraClientRest(
         String baseUrl,
         String username,
         String password,
         boolean autenticacioBasic) {
     super();
     this.baseUrl = baseUrl;
     this.username = username;
     this.password = password;
     this.autenticacioBasic = autenticacioBasic;
    }

    public Resposta consultaNotificacions(
         String nif,
         Integer pagina,
         Integer mida) {
     try {
         String urlAmbMetode = baseUrl + CARPETA_SERVICE_PATH + "/notificacions/" + nif;
         String paginacio = "";
         if (pagina != null && mida != null) {
             paginacio = "?pagina=" + pagina + "&mida=" + mida;
         }
         Client jerseyClient = generarClient();
         if (username != null) {
             autenticarClient(
                     jerseyClient,
                     urlAmbMetode,
                     username,
                     password);
         }
         String json = jerseyClient.
                 resource(urlAmbMetode + paginacio).
                 type("application/json").
                 get(String.class);
         ObjectMapper mapper  = new ObjectMapper();
         return mapper.readValue(json, Resposta.class);
     } catch (Exception ex) {
         throw new RuntimeException(ex);
     }
    }
    
    public Arxiu consultaDocument(
         Long notificacioId) {
     try {
         String urlAmbMetode = baseUrl + CARPETA_SERVICE_PATH + "/document/" + notificacioId;
         Client jerseyClient = generarClient();
         if (username != null) {
             autenticarClient(
                     jerseyClient,
                     urlAmbMetode,
                     username,
                     password);
         }
         String json = jerseyClient.
                 resource(urlAmbMetode).
                 type("application/json").
                 get(String.class);
         ObjectMapper mapper  = new ObjectMapper();
         return mapper.readValue(json, Arxiu.class);
     } catch (Exception ex) {
         throw new RuntimeException(ex);
     }
    }
    
    public boolean isAutenticacioBasic() {
     return autenticacioBasic;
    }

    private Client generarClient() {
     Client jerseyClient = Client.create();
     jerseyClient.addFilter(
             new ClientFilter() {
                 private ArrayList<Object> cookies;
                 @Override
                 public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
                     if (cookies != null) {
                         request.getHeaders().put("Cookie", cookies);
                     }
                     ClientResponse response = getNext().handle(request);
                     if (response.getCookies() != null) {
                         if (cookies == null) {
                             cookies = new ArrayList<Object>();
                         }
                         cookies.addAll(response.getCookies());
                     }
                     return response;
                 }
             }
     );
     jerseyClient.addFilter(
             new ClientFilter() {
                 @Override
                 public ClientResponse handle(ClientRequest request) throws ClientHandlerException {
                     ClientHandler ch = getNext();
                    ClientResponse resp = ch.handle(request);

                    if (resp.getStatusInfo().getFamily() != Response.Status.Family.REDIRECTION) {
                        return resp;
                    } else {
                        String redirectTarget = resp.getHeaders().getFirst("Location");
                        request.setURI(UriBuilder.fromUri(redirectTarget).build());
                        return ch.handle(request);
                    }
                 }
             }
     );
     return jerseyClient;
    }

    private void autenticarClient(
         Client jerseyClient,
         String urlAmbMetode,
         String username,
         String password) throws InstanceNotFoundException, MalformedObjectNameException, RemoteException, NamingException {
     if (!autenticacioBasic) {
         logger.debug(
                 "Autenticant client REST per a fer peticions cap a servei desplegat a damunt jBoss (" +
                 "urlAmbMetode=" + urlAmbMetode + ", " +
                 "username=" + username +
                 "password=********)");
         jerseyClient.resource(urlAmbMetode).get(String.class);
         Form form = new Form();
         form.putSingle("j_username", username);
         form.putSingle("j_password", password);
         jerseyClient.
         resource(baseUrl + "/j_security_check").
         type("application/x-www-form-urlencoded").
         post(form);
     } else {
         logger.debug(
                 "Autenticant REST amb autenticació de tipus HTTP basic (" +
                 "urlAmbMetode=" + urlAmbMetode + ", " +
                 "username=" + username +
                 "password=********)");
         jerseyClient.addFilter(
                 new HTTPBasicAuthFilter(username, password));
     }
    }


}

