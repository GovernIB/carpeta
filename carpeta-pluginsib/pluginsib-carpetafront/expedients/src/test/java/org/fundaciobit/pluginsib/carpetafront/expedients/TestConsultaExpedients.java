package org.fundaciobit.pluginsib.carpetafront.expedients;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author anadal
 *
 */
public class TestConsultaExpedients {

    public static void main(String[] args) {

        try {
            org.fundaciobit.pluginsib.core.utils.XTrustProvider.install();

            Properties testProperties = new Properties();

            testProperties.load(new FileInputStream("test.properties"));

            Properties pluginProperties = new Properties();

            pluginProperties.load(new FileInputStream("plugin.properties"));

            final String propertyBase = "es.caib.carpeta.";

            ExpedientsCarpetaFrontPlugin elsMeusExpedientsPlugin = new ExpedientsCarpetaFrontPlugin(propertyBase,
                    pluginProperties);

            String language = testProperties.getProperty("language");

            testGetExpedients(testProperties, elsMeusExpedientsPlugin, language);

            //testGetCodiSIA(testProperties, elsMeusExpedientsPlugin, language);

            //testGetUnidadesAdministrativas(elsMeusExpedientsPlugin, language);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    protected static void testGetUnidadesAdministrativas(ExpedientsCarpetaFrontPlugin elsMeusExpedientsPlugin,
            String language) throws Exception {

        Map<String, String> unidades = elsMeusExpedientsPlugin.getNomUnidadesAdministrativas(language);

        for (Map.Entry<String, String> entry : unidades.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();

            System.out.println("   - " + key + " => " + val);

        }

    }

    protected static void testGetCodiSIA(Properties testProperties,
            ExpedientsCarpetaFrontPlugin elsMeusExpedientsPlugin, String language) throws Exception {
        String codiSia = testProperties.getProperty("sia");

        String nomSia = elsMeusExpedientsPlugin.findProcedimentAmbCodiSia(codiSia, language);

        System.out.println(" NOM SIA del codi [" + codiSia + "]=> " + nomSia);
    }

    protected static void testGetExpedients(Properties testProperties,
            ExpedientsCarpetaFrontPlugin elsMeusExpedientsPlugin, String language) throws Exception {
        // TODO FalTA AFEGIR CODI SIA (Classificacio) A ARXIU !!!! 

        String nif = testProperties.getProperty("dni");

        int pagina = 0;
        int totalPagines;

        final int elementsPerPagina = 5;

        do {

            ExpedientConsulta consulta = new ExpedientConsulta(language, pagina, elementsPerPagina);

            ExpedientResposta resposta = elsMeusExpedientsPlugin.getExpedientsPerAdministrationID(nif, consulta);

            if (resposta.getError() != null) {

                System.err.println("S'ha produit un error: " + resposta.getError());
                break;

            }

            System.out.println();
            System.out.println();

            System.out.println("======================= PAGINA " + (resposta.getPaginaActual() + 1) + "/"
                    + resposta.getTotalPagines() + " ==================");
            System.out.println();
            System.out.println();

            System.out.println();

            System.out.println("resposta.getElementsPerPagina() => " + resposta.getElementsPerPagina());
            System.out.println("resposta.getPaginaActual() => " + resposta.getPaginaActual());
            System.out.println("resposta.getRegistresRetornats() => " + resposta.getRegistresRetornats());
            System.out.println("resposta.getTotalPagines() => " + resposta.getTotalPagines());
            System.out.println("resposta.getTotalRegistres() => " + resposta.getTotalRegistres());

            totalPagines = resposta.getTotalPagines();

            List<ExpedientInfo> resultats = resposta.getExpedients();

            for (ExpedientInfo ei : resultats) {
                System.out.println("____________________________________________________________________________");
                System.out.println("(" + ei.getCodiSia() + ") " + ei.getNomProcediment());
                System.out.println("        NÚMERO EXPEDIENT: " + ei.getExpedientNom());
                System.out.println("        DESCRIPCIO: " + ei.getExpedientDesc());
                System.out.println("        ESTAT: " + ei.getExpedientEstat());
                System.out.println("        ORGANS: " + ei.getExpedientOrgans());
                System.out.println("        DATA OBERTURA: " + ei.getExpedientObertura());
                System.out.println("_____________________________________________________________________________");
                System.out.println(" ");
            }

            pagina++;

        } while (pagina < totalPagines);
    }

}
