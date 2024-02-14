package org.fundaciobit.pluginsib.carpetafront.notib.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.TransmissioV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.HttpBasicAuth;
import org.joda.time.DateTime;

public class NotibTesterFromApi {

    public static void main(String[] args) {

        //String endPoint = "https://proves.caib.es/notib";
        //String endPoint = "https://dev.caib.es/notib";
        // String endPoint = "https://localhost:9999/notib";
        
        String url="https://se.caib.es/notibapi/interna/";
        String user="$carpeta_notib";
        String pass="carpeta_notib";
        
        ApiClient apiClient = new ApiClient();
        
        apiClient.setBasePath(url);
        HttpBasicAuth basicAuth = (HttpBasicAuth) apiClient.getAuthentication("basic");
        basicAuth.setUsername(user);
        basicAuth.setPassword(pass);

        ConsultaV2Api api = new ConsultaV2Api(apiClient);

        String nif = "XXXXXX";
        Integer pagina = 1;
        Integer mida = 20;

        /* Filtre dates */
        //String formDataInici = "13%2F01%2F2022";
        //String formDataFi = "13%2F06%2F2022";
        
        Date dataInici = new Date("13%2F01%2F2022");
        Date dataFi = new Date("13%2F06%2F2022");

        // Notificacions
        RespostaConsultaV2 resposta;
        try {
            resposta = api.notificacionsByTitular(nif, new DateTime(dataInici), new DateTime(dataFi), true, "ca", pagina, mida);
        
        // Comunicacions
        // Resposta resposta = api.consultaComunicacions(nif, formDataInici, formDataFi, pagina, mida);

        System.out.println(" ------------  OK " + resposta + "---------------");

        System.out.println(" ------------  NUm Elements Retornats: " + resposta.getNumeroElementsRetornats());
        System.out.println(" ------------  NUm Elements Totals: " + resposta.getNumeroElementsTotals());
        List<TransmissioV2> list = resposta.getResultat();

        for (TransmissioV2 t : list) {
            System.out.println("Trans => " + t.getDescripcio());
        }
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
