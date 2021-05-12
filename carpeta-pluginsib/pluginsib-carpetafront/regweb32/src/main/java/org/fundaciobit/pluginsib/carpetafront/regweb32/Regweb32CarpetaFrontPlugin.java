package org.fundaciobit.pluginsib.carpetafront.regweb32;


import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.fundaciobit.pluginsib.carpetafront.regwebdetallcomponent.RegwebDetallComponent;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


/**
 * @author anadal
 * @author mgonzalez
 */
public class Regweb32CarpetaFrontPlugin extends RegwebDetallComponent {

    public static final String MIME_JPG = "image/jpeg";
    public static final String MIME_XML1 = "text/xml";
    public static final String MIME_XML2 = "application/xml";
    public static final String MIME_PDF = "application/pdf";
    public static final String MIME_PNG = "image/png";
    public static final String MIME_RTF = "text/rtf";
    public static final String MIME_RTF2 = "application/rtf";
    public static final String MIME_SVG = "image/svg+xml";
    public static final String MIME_TIFF = "image/tiff";
    public static final String MIME_TXT = "text/plain";
    public static final String MIME_ODT = "application/vnd.oasis.opendocument.text";
    public static final String MIME_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String MIME_PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
    public static final String MIME_ODP = "application/vnd.oasis.opendocument.presentation";
    public static final String MIME_ODS = "application/vnd.oasis.opendocument.spreadsheet";
    public static final String MIME_ODG = "application/vnd.oasis.opendocument.graphics";
    public static final String MIME_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    public static final String MIME_HTML = "text/html";
    public static final String MIME_DEFAULT = "application/octet-stream";

    public static final Map<String, String> TEXTO_REDUCIDO_BY_TIPO_MIME = new HashMap<String, String>() {
        {
            put(MIME_JPG, "JPG");
            put(MIME_XML1, "XML");
            put(MIME_XML2, "XML");
            put(MIME_PDF, "PDF");
            put(MIME_PNG, "PNG");
            put(MIME_RTF, "RTF");
            put(MIME_RTF2, "RTF2");
            put(MIME_SVG, "SVG");
            put(MIME_TIFF, "TIFF");
            put(MIME_TXT, "TXT");
            put(MIME_ODT, "ODT");
            put(MIME_XLSX, "XLSX(EXCEL)");
            put(MIME_PPTX, "PPTX");
            put(MIME_ODP, "ODP");
            put(MIME_ODS, "ODS");
            put(MIME_ODG, "ODG");
            put(MIME_DOCX, "DOCX");
            put(MIME_HTML, "HTML");
            put(MIME_DEFAULT, "OCTET-STREAM");
        }
    };

    /**
     *
     */
    public Regweb32CarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public Regweb32CarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public Regweb32CarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }


    @Override
    public String getResourceBundleName() {
        return "carpetafrontregweb32";
    }
    
    //public static final String SESSION_PARAMETER = "REGWEB32_PLUGIN_PARAMETER";

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, String parameter) throws Exception {

        registerUserData(userData);

        String startURL;
        
        if (parameter != null && parameter.trim().length() != 0) {
           log.info("DadesPersonalsReactCarpetaFrontPlugin:: PARAMETER => ]" + parameter + "[");
           //request.getSession().setAttribute(SESSION_PARAMETER, parameter);
            startURL = absolutePluginRequestPath + "/" + ESPERA_DETALL_PAGE + "?numeroRegistroFormateado=" + parameter;
        } else {
            startURL = absolutePluginRequestPath + "/" + ESPERA_LLISTAT_PAGE ;
        }

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public String getEntidad() throws Exception {

        return getPropertyRequired(REGWEB32_PROPERTY_BASE + "entidad");

    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        if (isDevelopment()) {
            log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                    + administrationEncriptedID);
        }

        try {
            if (query.startsWith(ESPERA_LLISTAT_PAGE)) {
            	
            	esperaLlistat(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, 0, locale, isGet);
            	
            } else if (query.startsWith(ESPERA_DETALL_PAGE)) {
                
                esperaDetall(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData, administrationEncriptedID, 0, locale, isGet);
                
            } else if (query.startsWith(LLISTAT_REGISTRES_PAGE)) {

                String pageNumber = request.getParameter("pageNumber");
                if (pageNumber == null) {
                    pageNumber = "1";
                }

                log.info("PAGE NUMBER " + pageNumber);

                llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, Integer.parseInt(pageNumber) - 1, locale, isGet);

            }  else {

                super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, locale, isGet);
            }

        } catch (Exception e) {
            try{
                errorPage(e.getLocalizedMessage(), e, request, response, locale);
                log.error("Error detall registre: " + e.getMessage(), e);
            }catch(Exception e2){
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    @Override
    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB32_PROPERTY_BASE + "development"));
    }

    @Override
    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }
    
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     E   S   P   E   R   A    L L I S T A T        ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String ESPERA_LLISTAT_PAGE = "esperallistat";
    
    public void esperaLlistat(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                       HttpServletRequest request, HttpServletResponse response, UserData userData,
                       String administrationEncriptedID, int pageNumber, Locale locale, boolean isGet) {
    	
    	try {
    		String rutaDesti = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE;
    		esperaPage(absolutePluginRequestPath, response, locale, rutaDesti);
    		
    	}catch(Exception e) {

            try{
                errorPage(e.getMessage(), e, request, response, locale);
                log.error("Error enviant a página d'espera de Registre " + e.getMessage(), e);
            }catch(Exception e2){
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }
    	}
    	
    	
    }
    
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------     E   S   P   E   R   A    D E T A L L          ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String ESPERA_DETALL_PAGE = "esperadetall";
    
    public void esperaDetall(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                       HttpServletRequest request, HttpServletResponse response, UserData userData,
                       String administrationEncriptedID, int pageNumber, Locale locale, boolean isGet) {
        
        try {
            
            // DETALL_REGISTRE_PAGE + "?numeroRegistroFormateado=" + parameter;
            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
            String rutaDesti = absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE + "?numeroRegistroFormateado=" + numeroRegistroFormateado;
            esperaPage(absolutePluginRequestPath, response, locale, rutaDesti);
            
        }catch(Exception e) {

            try{
                errorPage(e.getMessage(), e, request, response, locale);
                log.error("Error enviant a página d'espera de Registre " + e.getMessage(), e);
            }catch(Exception e2){
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }
        }
        
        
    }
    
    

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- L L I S T A T   D E   R E G I S T R E S ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String LLISTAT_REGISTRES_PAGE = "llistatRegistres32";


    public void llistatDeRegistres(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, int pageNumber, Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            

            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, relativePluginRequestPath, userData,
                    getEntidad(), pageNumber, locale, isGet);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }
        } catch (Exception e) {

            try{
                errorPage(e.getLocalizedMessage(), e,request, response, locale);
                log.error("Error llistant registres: " + e.getMessage(), e);
            } catch(Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String relativePluginRequestPath,
            UserData userData, String entidad, int pageNumber, Locale locale, boolean isGet) throws Exception {


        Map<String, Object> map = new HashMap<String, Object>();

        ResultadoBusquedaWs result;
        result = getRegistres(userData.getAdministrationID(), entidad, pageNumber, locale);

        @SuppressWarnings("unchecked")
        List<AsientoWs> registres = (List<AsientoWs>) (List<?>) result.getResults();
        int totalResults = result.getTotalResults();

        InputStream input = this.getClass().getResourceAsStream("/webpage/regweb32.html");

        String plantilla = IOUtils.toString(input, "UTF-8");

        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        map.put("title", getTitle(locale));

        String[] traduccions = {"registro.listado", "registro.descripcion", "registro.numero", "registro.fecha",
                "registro.extracto", "registro.destinatario", "registro.vacio", "carpeta.acciones",
                "registro.detalle"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        String urlDetalle = absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE + "?numeroRegistroFormateado=";

        map.put("urlDetalle", urlDetalle);

        map.put("registros", registres);

        // Calculam la paginació
        Paginacio paginacio = new Paginacio(totalResults, pageNumber);
        String firstUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber=1";
        String lastUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + paginacio.getTotalPages();
        String prevUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + (paginacio.getCurrentIndex() - 1);
        String nextUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + (paginacio.getCurrentIndex() + 1);
        String pageUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber=";

        map.put("firstUrl", firstUrl);
        map.put("lastUrl", lastUrl);
        map.put("prevUrl", prevUrl);
        map.put("nextUrl", nextUrl);
        map.put("pageUrl", pageUrl);

        map.put("paginacio", paginacio);

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);


        return generat;

    }

    public ResultadoBusquedaWs getRegistres(String administrationID, String entidad, int pageNumber, Locale locale)
            throws Exception {

        RegWebAsientoRegistralWs service = super.getRegWebAsientoRegistralWsService();

        ResultadoBusquedaWs result = service.obtenerAsientosCiudadanoCarpeta(entidad, administrationID, pageNumber,
                locale.getLanguage());

        if (isDevelopment()) {
            @SuppressWarnings("unchecked")
            List<AsientoWs> registros = (List<AsientoWs>) (List<?>) result.getResults();

            if (registros == null || registros.isEmpty()) {
                log.info(" REGISTRES NULL o EMPTY: " + registros);

            } else {
                int x = 1;
                for (AsientoWs ar : registros) {

                    log.info(" -------------  REGISTRE [" + x + " ] -------------------");
                    log.info("ar.getNumeroRegistroFormateado() => " + (ar.getNumeroRegistro()));
                    log.info("ar.getResumen() => " + ar.getExtracto());
                    log.info("ar.getFechaRegistro(); => " + ar.getFechaRegistro());
                    log.info("ar.getUnidadTramitacionDestinoDenominacion() => " + ar.getDenominacionDestino());

                    x++;

                }

            }
        }

        return result;
    }


    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {

        return getImageFromResource(locale, "/logo/logo-regweb32.png", "image/png");

    }

    @Override
    public String getPropertyBase() {
        return REGWEB32_PROPERTY_BASE;
    }

}
