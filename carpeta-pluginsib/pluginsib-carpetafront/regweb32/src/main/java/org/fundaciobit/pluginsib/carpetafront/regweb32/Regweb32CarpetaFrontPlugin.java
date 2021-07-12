package org.fundaciobit.pluginsib.carpetafront.regweb32;


import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.fundaciobit.pluginsib.carpetafront.regwebdetallcomponent.RegwebDetallComponent;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
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
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.DateFormat;

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
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, 
            String parameter, IListenerLogCarpeta logCarpeta) throws Exception {

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
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

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

                llistatDeRegistres(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet, logCarpeta);

            }  else {

                super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, locale, isGet, logCarpeta);
            }

        } catch (Exception e) {
            try{
            	
                errorPage(e.getLocalizedMessage(), e, request, response, locale);
                log.error("Error plugin registre: " + e.getMessage(), e);
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
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        try {
        	
        	/* Filtre número de registre */
        	String formNumero = request.getParameter("numero");
        	
        	/* Filtre pàgina */
        	String pageNumber = request.getParameter("pageNumber");
            if (pageNumber == null) {
                pageNumber = "1";
            }
            
            /* Filtre estat */
            String formEstat = (request.getParameter("estado") != null) ? request.getParameter("estado") : "0";
        	
            /* Filtre dates*/
        	Date formDataInici;
            Date formDataFi;
            String formDataIniciStr = request.getParameter("fechaInicio");
            String formDataFiStr = request.getParameter("fechaFin");
            
            String parametros = "";
    		Calendar cal = Calendar.getInstance();
    		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
            
            if(formDataFiStr != null && formDataFiStr != "") {
            	formDataFi = SDF.parse(formDataFiStr);
            	parametros += "&fechaFin=" + formDataFiStr;
            }else {
            	formDataFi = cal.getTime();
            }
            
            if  (formDataIniciStr != null && formDataIniciStr != ""){
            	formDataInici = SDF.parse(formDataIniciStr);
            	parametros +=  "&fechaInicio=" + formDataIniciStr;
            }else {
            	/* Inicialitzam darrers 6 mesos */
            	cal.add(Calendar.MONTH, -6);
            	formDataInici = cal.getTime();
            }
            
            if(formEstat != null && Integer.parseInt(formEstat)> 0){
            	parametros +=   "&estado=" + formEstat;
            }
            
            if (isDevelopment()) {
            	log.info("NUMERO => " + formNumero);
            	log.info("PAGE NUMBER =>" + pageNumber);
            	log.info("DATA INICI => " + formDataIniciStr );
            	log.info("DATA FI => " + formDataFiStr);
            	log.info("ESTAT => " + formEstat); 
            }
        
            //Thread.sleep(5000);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String webpage = getLlistatDeRegistresPage(absolutePluginRequestPath, relativePluginRequestPath, userData,
                    getEntidad(), formNumero, formDataInici, formDataFi, formEstat, parametros, Integer.parseInt(pageNumber) - 1, locale, isGet);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }
        } catch (Exception e) {

            try{
            	
            	StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEB32] Error").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("Error: " + e.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEB32] Error plugin", peticio.toString(), "[REGWEB32] Error plugin");
            	
                errorPage(e.getLocalizedMessage(), e,request, response, locale);
                log.error("Error llistant registres: " + e.getMessage(), e);
            } catch(Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    public String getLlistatDeRegistresPage(String absolutePluginRequestPath, String relativePluginRequestPath,
            UserData userData, String entidad, String formNumero, Date formDataInici, Date formDataFi, String formEstat, String parametros, int pageNumber, Locale locale, boolean isGet) throws Exception {


        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("form_numero", formNumero);
        map.put("form_dataFi", formDataFi);
        map.put("form_dataInici", formDataInici);
        map.put("form_estat", formEstat);
        
        @SuppressWarnings("unchecked")
        List<AsientoWs> registres;
        int totalResults = 0;
        
        if(isGet) {
        	registres = null;
        	map.put("cerca", "");
        }else {

        	formDataFi = DateUtils.sumarRestarDiasFecha(formDataFi, 1);
        	
	        ResultadoBusquedaWs result;
	        result = getRegistres(userData.getAdministrationID(), entidad, formNumero, formDataInici, formDataFi, formEstat, pageNumber, locale);
	
	        //@SuppressWarnings("unchecked")
	        registres = (List<AsientoWs>) (List<?>) result.getResults();
	        totalResults = result.getTotalResults();
	        map.put("cerca", "true");
        }
        
        InputStream input = this.getClass().getResourceAsStream("/webpage/regweb32.html");

        String plantilla = IOUtils.toString(input, "UTF-8");


        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        map.put("form_action", absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE);
        map.put("lang", locale.getLanguage());

        map.put("title", getTitle(locale));
        
        String[] registroEstado = { "", getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.1", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.2", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.3", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.4", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.5", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.6", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.7", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.8", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.9", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.10", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.11", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.12", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.13", locale)};

        map.put("registroEstado", registroEstado);
        
        String[] registroEstadoSelect = { 
        		getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.todos", locale), 
        		getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.1", locale),
        		getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.4", locale),
        		getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.10", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.11", locale)};
        
        map.put("registroEstadoSelect", registroEstadoSelect);
        
        String[] registroExplicacionEstado = { "", 
        		getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.1", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.2", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.3", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.4", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.5", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.6", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.7", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.8", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.9", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.10", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.11", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.12", locale),
                getTraduccio(RESOURCE_BUNDLE_NAME, "registro.estado.explicacion.13", locale)};

        map.put("registroExplicacionEstado", registroExplicacionEstado);

        String[] traduccions = {"registro.listado", "registro.descripcion", "registro.numero", "registro.fecha",
                "registro.extracto", "registro.destinatario", "registro.vacio", "carpeta.acciones",  "registro.estado",
                "registro.detalle", "carpeta.fecha.inicio", "carpeta.fecha.fin", "carpeta.todos", "carpeta.buscar"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        String urlDetalle = absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE + "?numeroRegistroFormateado=";

        map.put("urlDetalle", urlDetalle);
        map.put("registros", registres);

        // Calculam la paginació
        
        Paginacio paginacio = new Paginacio(totalResults, pageNumber);
        String firstUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber=1" + parametros;
        String lastUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + paginacio.getTotalPages() + parametros;
        int prevUrlIndex = (paginacio.getCurrentIndex() > 1) ? (paginacio.getCurrentIndex() - 1) : 1;
        String prevUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + prevUrlIndex + parametros;
        String nextUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?pageNumber="
                + (paginacio.getCurrentIndex() + 2) + parametros;
        String pageUrl = absolutePluginRequestPath + "/" + LLISTAT_REGISTRES_PAGE + "?"+parametros+"&pageNumber=";
        
        map.put("firstUrl", firstUrl);
        map.put("lastUrl", lastUrl);
        map.put("prevUrl", prevUrl);
        map.put("nextUrl", nextUrl);
        map.put("pageUrl", pageUrl);

        map.put("paginacio", paginacio);
        
        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);


        return generat;

    }

    public ResultadoBusquedaWs getRegistres(String administrationID, String entidad, String formNumero, 
    		Date formDataInici, Date formDataFi, String formEstat, int pageNumber, Locale locale)
            throws Exception {

        RegWebAsientoRegistralWs service = super.getRegWebAsientoRegistralWsService();

        /* Equivalencia estats REGWEB3.2 amb estats visibles al SELECT */
        List<Integer> formEstats = new ArrayList<Integer>();
        if (formEstat != null) {
        	switch(formEstat) {
	    		case "1":
	    			formEstats.add(1);
	    			formEstats.add(3);
	    			formEstats.add(5);
	    			formEstats.add(6);
	    			formEstats.add(7);
	    			formEstats.add(12);
	    			break;
	    		case "2":
	    			formEstats.add(4);
	    			formEstats.add(13);
	    			break;
	    		case "3":
	    			formEstats.add(10);
	    			break;
	    		case "4":
	    			formEstats.add(11);
	    			break;
	    	}
        }
            
        if (isDevelopment()) {
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        log.info("================== PARAMETROS LLAMADA API REGWEB ===============");
	        log.info("locale => " + locale.getLanguage());
	        log.info("pageNumber => " + pageNumber);
	        log.info("administrationID => " + administrationID);
	        log.info("entidad => " + entidad);
	        log.info("formNumero => " + formNumero);
	        log.info("formDataInici => " + dateFormat.format(formDataInici));
	        log.info("formDataFi => " + dateFormat.format(formDataFi));
	        log.info("formEstats.size => " + formEstats.size());
        }
        
        ResultadoBusquedaWs result = service.obtenerAsientosCiudadanoCarpeta(entidad, administrationID, pageNumber,
                locale.getLanguage(), new Timestamp(formDataInici.getTime()), new Timestamp(formDataFi.getTime()), formNumero, formEstats);

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
                    log.info("ar.getEstado() => " + ar.getEstado());

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
