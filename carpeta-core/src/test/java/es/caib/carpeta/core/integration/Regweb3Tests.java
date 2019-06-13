package es.caib.carpeta.core.integration;

import es.caib.carpeta.core.CarpetaTestUtils;
import es.caib.regweb3.ws.api.v3.*;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Regweb3Tests extends CarpetaTestUtils {

    private final static String host = getRegWeb3Host();
    private final static String api = getRegWeb3Api();
    private final static String entidad = getRegWeb3Entidad();
    private final static String user = getRegWeb3User();
    private final static String pass = getRegWeb3Pass();

    @Test
    public void obtenerRegistros(){

        try {
            String endpoint = host + api;
            final URL wsdl = new URL(endpoint + "?wsdl");

            RegWebAsientoRegistralWsService service =  new RegWebAsientoRegistralWsService(wsdl);

            RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();

            configAddressUserPassword(user, pass, endpoint, api);

           ResultadoBusquedaWs registros = api.obtenerAsientosCiudadano(entidad, "43146650F", 0);

            System.out.println("Total registros: " + registros.getTotalResults());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (WsI18NException e) {
            e.printStackTrace();
        } catch (WsValidationException e) {
            e.printStackTrace();
        }

    }

    private static void configAddressUserPassword(String usr, String pwd,
                                                 String endpoint, Object api) {

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
    }
}
