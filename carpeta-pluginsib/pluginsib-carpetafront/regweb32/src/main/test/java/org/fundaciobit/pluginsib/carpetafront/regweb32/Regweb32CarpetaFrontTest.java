package org.fundaciobit.pluginsib.carpetafront.regweb32;

import javax.xml.ws.BindingProvider;

import es.caib.regweb3.ws.api.v3.FileContentWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import java.net.URL;
import java.util.Map;

public class Regweb32CarpetaFrontTest {

   @org.junit.Test
   public void test() {

      System.out.println();
   }


   public static void main(String[] args) {

      try {

         String user = "caibapp";
         String pass = "caibapp";
         String entidad = "A04003003";
         String endpoint = "http://registre3.fundaciobit.org/regweb3/ws/v3/RegWebAsientoRegistral";
         final URL wsdl = new URL(endpoint + "?wsdl");

         RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);

         RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();

         configAddressUserPassword(user, pass, endpoint, api);

        // ResultadoBusquedaWs registros = api.obtenerAsientosCiudadanoCarpeta(entidad, "44328254D", 1,"ca");

        // AsientoWs asiento = api.obtenerAsientoCiudadanoCarpeta(entidad, "44328254D", "GOIB-E-33/2021","ca");

     //    System.out.println("Justificante Nombre "+ asiento.getJustificante());

         FileContentWs anexo = api.obtenerAnexoCiudadano(entidad, 112293L,"ca");
         System.out.println("MIME: " + anexo.getFileInfoWs().getMime());
         System.out.println("SIZE: " + anexo.getFileInfoWs().getSize());
         System.out.println("SIZE: " + anexo.getFileInfoWs().getName());
         System.out.println("FILENAME: " + anexo.getFileInfoWs().getFilename());


        /* FileContentWs justificante = api.obtenerAnexoCiudadano(entidad, 120390L,"ca");
         System.out.println("MIME: " + justificante.getFileInfoWs().getMime());
         System.out.println("SIZE: " + justificante.getFileInfoWs().getSize());
         System.out.println("SIZE: " + justificante.getFileInfoWs().getName());
*/
         /*for(Object registro: registros.getResults()){
            AsientoWs asientoWs = (AsientoWs)registro;
            System.out.println("Registro Numero "+ asientoWs.getNumeroRegistro());
            System.out.println("Justificante Nombre "+ asientoWs.getJustificante());
         }*/

         //System.out.println("Total registros: " + registros.getTotalResults());


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