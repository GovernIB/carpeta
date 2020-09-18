package org.fundaciobit.pluginsib.carpetafront.regweb3;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.core.utils.XTrustProvider;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author anadal
 * @author mgonzalez
 */
public class Regweb3CarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String REGWEB3_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "regweb3.";


    /**
     *
     */
    public Regweb3CarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public Regweb3CarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public Regweb3CarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getTitle(Locale locale) {
        return getTraduccio("title", locale);
    }

    @Override
    public String getSubTitle(Locale locale) {
        return getTraduccio("subtitle", locale);
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontregweb3";
    }


    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
                              HttpServletRequest request, String administrationID, String administrationIDEncriptat) throws Exception {

        String startURL = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }


    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                    HttpServletRequest request, HttpServletResponse response, String administrationID,
                                    String administrationEncriptedID, Locale locale, boolean isGet) {


        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationID: " + administrationID);
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: " + administrationEncriptedID);

        if (query.startsWith(LLISTAT_REGISTRES_PAGE)) {

            llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, administrationID, administrationEncriptedID, locale, isGet);
        }


    }


    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB3_PROPERTY_BASE + "development"));
    }

    public String getWeb() throws Exception {
        return getPropertyRequired(REGWEB3_PROPERTY_BASE + "web");

    }

    public String getEntidad() throws Exception {
        return getPropertyRequired(REGWEB3_PROPERTY_BASE + "entidad");

    }


    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     L L I S T A T   DE   REGISTRES                ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------


    protected static final String LLISTAT_REGISTRES_PAGE = "llistatRegistres";

    public void llistatDeRegistres(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                   HttpServletRequest request, HttpServletResponse response, String administrationID,
                                   String administrationEncriptedID, Locale locale, boolean isGet) {

        try {


            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");


            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, administrationID, getEntidad(), locale, isGet);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }


    }


    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String administrationID, String entidad, Locale locale, boolean isGet) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        List<AsientoRegistralWs> registres;


        if (isDevelopment()) {
            registres = getRegistresDebug(administrationID, entidad);
        } else {
            registres = getRegistres(administrationID, entidad);
        }


        InputStream input = this.getClass().getResourceAsStream("/webpage/regweb3.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCE);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        String[] traduccions = {"registro.listado", "registro.descripcion", "registro.numero",
           "registro.fecha", "registro.extracto", "registro.destinatario", "registro.vacio"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }


        map.put("registros", registres);
        map.put("web", getWeb());

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }


    private List<AsientoRegistralWs> getRegistresDebug(String administrationID, String entidad) throws Exception {


        List<AsientoRegistralWs> registros = this.getRegistres(administrationID, entidad);


        if (registros == null || registros.isEmpty()) {
            System.out.println(" REGISTRES NULL o EMPTY: " + registros);
        } else {
            int x = 1;
            for (AsientoRegistralWs ar : registros) {

                System.out.println(" -------------  REGISTRE [" + x + " ] -------------------");
                System.out.println("ar.getNumeroRegistroFormateado() => " + (ar.getNumeroRegistroFormateado()));
                System.out.println("ar.getResumen() => " + ar.getResumen());
                System.out.println("ar.getFechaRegistro(); => " + ar.getFechaRegistro());
                System.out.println("ar.getUnidadTramitacionDestinoDenominacion() => " + ar.getUnidadTramitacionDestinoDenominacion());

                x++;

            }

        }

        return registros;
    }


    public List<AsientoRegistralWs> getRegistres(String administrationID, String entidad)
       throws Exception {


        RegWebAsientoRegistralWs service = getRegWebAsientoRegistralWsService();


        ResultadoBusquedaWs result = service.obtenerAsientosCiudadano(entidad, administrationID, 0);


        @SuppressWarnings("unchecked")
        List<AsientoRegistralWs> registros = (List<AsientoRegistralWs>) (List<?>) result.getResults();
        return registros;
    }


    /**
     * @return
     * @throws Exception
     */
    private RegWebAsientoRegistralWs getRegWebAsientoRegistralWsService() throws Exception {

        final String regweb3Url = getPropertyRequired(REGWEB3_PROPERTY_BASE + "url");

        final String username = getPropertyRequired(REGWEB3_PROPERTY_BASE + "user");

        final String password = getPropertyRequired(REGWEB3_PROPERTY_BASE + "pass");

        if (regweb3Url.startsWith("https") && isDevelopment()) {
            XTrustProvider.install();
        }

        final URL wsdl = new URL(regweb3Url + "?wsdl");


        RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);

        RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();


        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, regweb3Url);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, username);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, password);

        return api;
    }


}
