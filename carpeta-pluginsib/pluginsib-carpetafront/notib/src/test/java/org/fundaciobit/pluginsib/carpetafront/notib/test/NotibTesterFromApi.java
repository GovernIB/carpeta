package org.fundaciobit.pluginsib.carpetafront.notib.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import es.caib.notib.client.NotificacioRestClientV2;
import es.caib.notib.client.domini.IdiomaEnumDto;
import es.caib.notib.client.domini.consulta.RespostaConsultaV2;
import es.caib.notib.client.domini.consulta.TransmissioV2;

public class NotibTesterFromApi {

    public static void main(String[] args) {

        //String endPoint = "https://proves.caib.es/notib";
        String endPoint = "https://dev.caib.es/notib";
        // String endPoint = "https://localhost:9999/notib";
        String usuari = "$carpeta_notib";
        String password = "carpeta_notib";

        NotificacioRestClientV2 api = new NotificacioRestClientV2(endPoint, usuari, password);

        String nif = "XXXXXX";
        Integer pagina = 1;
        Integer mida = 20;

        /* Filtre dates */
        //String formDataInici = "13%2F01%2F2022";
        //String formDataFi = "13%2F06%2F2022";
        
        Date dataInici = new Date("13%2F01%2F2022");
        Date dataFi = new Date("13%2F06%2F2022");

        // Notificacions
        RespostaConsultaV2 resposta = api.notificacionsByTitular(nif, dataInici, dataFi, true, IdiomaEnumDto.CA, pagina, mida);
        // Comunicacions
        // Resposta resposta = api.consultaComunicacions(nif, formDataInici, formDataFi, pagina, mida);

        System.out.println(" ------------  OK " + resposta + "---------------");

        System.out.println(" ------------  NUm Elements Retornats: " + resposta.getNumeroElementsRetornats());
        System.out.println(" ------------  NUm Elements Totals: " + resposta.getNumeroElementsTotals());
        List<TransmissioV2> list = resposta.getResultat();

        for (TransmissioV2 t : list) {
            System.out.println("Trans => " + t.getDescripcio());
        }

    }


}
