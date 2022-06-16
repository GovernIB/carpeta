package org.fundaciobit.pluginsib.carpetafront.notib.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.fundaciobit.pluginsib.carpetafront.notib.api.NotibClientRest;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Resposta;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Transmissio;

public class NotibTesterFromApi {

    public static void main(String[] args) {

        //String endPoint = "https://proves.caib.es/notib";
        String endPoint = "https://dev.caib.es/notib";
        // String endPoint = "https://localhost:9999/notib";
        String usuari = "$carpeta_notib";
        String password = "carpeta_notib";

        NotibClientRest api = new NotibClientRest(endPoint, usuari, password);

        String nif = "XXXXXX";
        Integer pagina = 1;
        Integer mida = 20;

        /* Filtre dates */
        String formDataInici = "13%2F01%2F2022";
        String formDataFi = "13%2F06%2F2022";

        // Notificacions
        Resposta resposta = api.consultaNotificacions(nif, formDataInici, formDataFi, pagina, mida);
        // Comunicacions
        // Resposta resposta = api.consultaComunicacions(nif, formDataInici, formDataFi, pagina, mida);

        System.out.println(" ------------  OK " + resposta + "---------------");

        System.out.println(" ------------  NUm Elements Retornats: " + resposta.getNumeroElementsRetornats());
        System.out.println(" ------------  NUm Elements Totals: " + resposta.getNumeroElementsTotals());
        List<Transmissio> list = resposta.getResultat();

        for (Transmissio t : list) {
            System.out.println("Trans => " + t.getDescripcio());
        }

    }

}
