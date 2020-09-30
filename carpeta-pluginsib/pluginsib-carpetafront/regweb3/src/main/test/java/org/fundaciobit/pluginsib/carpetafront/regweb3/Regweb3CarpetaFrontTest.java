package org.fundaciobit.pluginsib.carpetafront.regweb3;

import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

public class Regweb3CarpetaFrontTest {

   @org.junit.Test
   public void test() {

      System.out.println();
   }


   public static void main(String[] args) {

      try {

         String user = "caibapp";
         String pass = "caibapp";
         String entidad = "A04003003";
         String endpoint = "http://localhost:8080/regweb3/ws/v3/RegWebAsientoRegistral";
         final URL wsdl = new URL(endpoint + "?wsdl");

         RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);

         RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();

         configAddressUserPassword(user, pass, endpoint, api);

         ResultadoBusquedaWs registros = api.obtenerAsientosCiudadano(entidad, "44328254D", 0);

         System.out.println("Total registros: " + registros.getTotalResults());
         System.out.println("Total registros: " + ((AsientoRegistralWs) registros.getResults().get(0)));

        /* FileInfo fileInfo = Regweb3CarpetaFrontPlugin.getIcon(new Locale("ca"));
         System.out.println("XXXXXXX    Tamany"+fileInfo.getName());*/


      } catch (Exception e) {
         // TODO Auto-generated catch block
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