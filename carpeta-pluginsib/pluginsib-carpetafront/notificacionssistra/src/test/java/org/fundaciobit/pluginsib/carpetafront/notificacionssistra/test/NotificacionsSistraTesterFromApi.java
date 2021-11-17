package org.fundaciobit.pluginsib.carpetafront.notificacionssistra.test;

import java.util.List;

import org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api.NotificacionsSistraClientRest;
import org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api.Resposta;
import org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api.Transmissio;

public class NotificacionsSistraTesterFromApi {

    public static void main(String[] args) {

        String endPoint = "https://proves.caib.es/notib";
        //String endPoint = "https://dev.caib.es/notib";
        // String endPoint = "https://localhost:9999/notib";
        String usuari = "$carpeta_notib";
        String password = "carpeta_notib";

        NotificacionsSistraClientRest api = new NotificacionsSistraClientRest(endPoint, usuari, password);

        String nif = "43096845C";
        Integer pagina = 1;
        Integer mida = 20;

        Resposta resposta = api.consultaNotificacions(nif, pagina, mida);

        System.out.println(" ------------  OK " + resposta + "---------------");

        System.out.println(" ------------  NUm Elements Retornats: " + resposta.getNumeroElementsRetornats());
        System.out.println(" ------------  NUm Elements Totals: " + resposta.getNumeroElementsTotals());
        List<Transmissio> list = resposta.getResultat();

        for (Transmissio t : list) {
            System.out.println("Trans => " + t.getDescripcio());
        }

    }

}
