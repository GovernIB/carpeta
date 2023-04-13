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
import java.util.Base64;
import java.sql.Timestamp;
import java.text.DateFormat;

import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    // public static final String SESSION_PARAMETER = "REGWEB32_PLUGIN_PARAMETER";

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat,
            String parameter, IListenerLogCarpeta logCarpeta) throws Exception {

        registerUserData(userData);

        String startURL;

        startURL =  absolutePluginRequestPath + "/" + INDEX_REACT_PAGE;
        
        if (parameter != null && parameter.trim().length() != 0) {
            log.info("Regweb32CarpetaFrontPlugin:: PARAMETER => ]" + parameter + "[");
            
            startURL = startURL + "?numeroRegistro=" + parameter;
        } else {
        	
            //startURL = absolutePluginRequestPath + "/" + ESPERA_LLISTAT_PAGE;
        	
        }

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public String getEntidad() throws Exception {

        return getPropertyRequired(REGWEB32_PROPERTY_BASE + "entidad");

    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        if (isDevelopment()) {
            log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info(
                    "Regweb32CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                            + administrationEncriptedID);
        }

        try {
           if (query.startsWith(INDEX_REACT_PAGE)) {
                
            	index(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, 0, locale, isGet);
            
            } else if (query.startsWith(REACT_JS_PAGE)) {
            	
                reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                        administrationEncriptedID, locale, isGet);
                
           } else if (query.startsWith(URL_REST_SERVICE)) {
                	
                llistatDeRegistresJson(absolutePluginRequestPath, relativePluginRequestPath, query,
                        request, response, userData, administrationEncriptedID, locale, isGet,
                        logCarpeta);
           
            } else {

                super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath,
                        query, request, response, userData, administrationEncriptedID, locale,
                        isGet, logCarpeta);
            }

        } catch (Exception e) {
            try {

                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath,
                        locale);
                log.error("Error plugin registre: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    @Override
    public boolean isDevelopment() {
        return "true".equals(getProperty(REGWEB32_PROPERTY_BASE + "development"));
    }
    
    @Override
    public boolean isReactComponent() {
    	if(getProperty(REGWEB32_PROPERTY_BASE + "isreact") != null) {
    	   log.error("La propietat [" + REGWEB32_PROPERTY_BASE + "isreact"  + "] del plugin " 
    	   + this.getClass().getName() + " està deprecada. Per favor esborri aquesta propietat.") ;   
    	}
    	return true;
    }

    @Override
    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- I N D E X  R E A C T  P A G E ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_REACT_PAGE = "regweb32_index.html";
    
    public void index(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, int pageNumber, Locale locale,
            boolean isGet) {
    	
    	try {
    		
	    	response.setContentType("text/html");
	
	        String resource = "/webpage/" + INDEX_REACT_PAGE;
	
	        response.setHeader("Content-Disposition",
	                "inline;filename=\"" + java.net.URLEncoder.encode(INDEX_REACT_PAGE, "UTF-8") + "\"");
	
	        response.setCharacterEncoding("utf-8");
	
	        InputStream input = this.getClass().getResourceAsStream(resource);
	
	        String plantilla = IOUtils.toString(input, "UTF-8");
	
	        Map<String, Object> map = new HashMap<String, Object>();
	
	        Gson json = new Gson();
	
	        TitlesInfo titles = getTitlesInfo();

	        map.put("titles", json.toJson(titles.getTitlesByLang()));
	
	        map.put("subtitles", json.toJson(titles.getSubtitlesByLang()));
	
	        log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);
	
	        String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;
	
	        map.put("pathtojs", pathtojs);
	        
	        String pathtoservei = absolutePluginRequestPath + "/" + URL_REST_SERVICE;
	
	        map.put("pathtoservei", pathtoservei);
	        
	        String detallpathtoservei = absolutePluginRequestPath + "/" + RegwebDetallComponent.DETALL_REACT_PAGE;
	        
	        log.info("detallpathtoservei => " + detallpathtoservei);	        
	        
	        map.put("detallpathtoservei", detallpathtoservei);

	        String numeroRegistro = request.getParameter("numeroRegistro");
	        log.info(" XXXXXXXXXXXXXXXXXXXXXX   numeroRegistro => " + numeroRegistro);
	        
	        if (numeroRegistro == null) {
	            map.put("numeroRegistro", "");
	        } else {
	            numeroRegistro = new String(Base64.getDecoder().decode(numeroRegistro));
	            log.info(" XXXXXXXXXXXXXXXXXXXXXX   numeroRegistro DECODED => " + numeroRegistro);
	            map.put("numeroRegistro", numeroRegistro);
	        }

	        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);
	        
	        try {
	            response.getWriter().println(generat);
	            response.flushBuffer();
	        } catch (IOException e) {
	            log.error("Error obtening writer: " + e.getMessage(), e);
	        }
	
	    } catch (Exception e) {
	        // XYZ ZZZ
	        log.error("Error generant pàgina bàsica: " + e.getMessage(), e);
	    }
    	
    }
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- C O N S U L T A  S E R V I C E ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    
    protected static final String URL_REST_SERVICE = "consultaLlistat";
    
    public void llistatDeRegistresJson(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {
        
        
        log.info("\n\n\n================================\nllistatDeRegistresJson:: ENTRA");
    	
    	try {
    		
    		/* Filtre número de registre */
            String formNumero = request.getParameter("numero");

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            /* Filtre pàgina */
            String pageNumber = request.getParameter("pageNumber");
            if (pageNumber == null) {
                pageNumber = "1";
            }

			// Número de registres a mostrar per pàgina
            //final int numItems = 10;

            /* Filtre estat */
            String formEstat = (request.getParameter("estado") != null)
                    ? request.getParameter("estado")
                    : "0";

            /* Filtre dates */
            Date formDataInici;
            Date formDataFi;
            String formDataIniciStr = request.getParameter("fechaInicio");
            String formDataFiStr = request.getParameter("fechaFin");
    		
            String parametros = "";
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

            if (formDataFiStr != null && formDataFiStr != "") {
                formDataFi = SDF.parse(formDataFiStr);
                parametros += "&fechaFin=" + formDataFiStr;
            } else {
                formDataFi = cal.getTime();
            }

            if (formDataIniciStr != null && formDataIniciStr != "") {
                formDataInici = SDF.parse(formDataIniciStr);
                parametros += "&fechaInicio=" + formDataIniciStr;
            } else {
                /* Inicialitzam darrers 6 mesos */
                cal.add(Calendar.MONTH, -6);
                formDataInici = cal.getTime();
            }

            if (formEstat != null && Integer.parseInt(formEstat) > 0) {
                parametros += "&estado=" + formEstat;
            }

            if (isDevelopment()) {
                log.info("NUMERO => " + formNumero);
                log.info("PAGE NUMBER =>" + pageNumber);
                log.info("DATA INICI => " + formDataIniciStr);
                log.info("DATA FI => " + formDataFiStr);
                log.info("ESTAT => " + formEstat);
                log.info("PARAMETROS => " + parametros);
            }
    		

    		response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            // CERCA
            List<AsientoWs> registresRegweb;
            
            formDataFi = DateUtils.sumarRestarDiasFecha(formDataFi, 1);

            log.info("llistat::registrosPorPagina: " + formRegPorPagina);
            log.info("llistat::pageNumber: " + pageNumber);
            final String nif = userData.getAdministrationID();
            log.info("llistat::nif: " + nif);
            
            int elementsPerPagina = Integer.parseInt(formRegPorPagina);

            ResultadoBusquedaWs result;
            result = getRegistres(nif, getEntidad(), formNumero,
                    formDataInici, formDataFi, formEstat, Integer.parseInt(pageNumber), elementsPerPagina, locale);

            //@SuppressWarnings("unchecked")
            registresRegweb = (List<AsientoWs>) (List<?>) result.getResults();
            
            
            Paginacio paginacio = new Paginacio();
            
            paginacio.setElementsPerPagina(elementsPerPagina);
            paginacio.setElementsRetornats(registresRegweb.size());

            paginacio.setPaginaActual(result.getPageNumber());
            final int totalElements = result.getTotalResults();
            paginacio.setTotalElements(totalElements);
            paginacio.setTotalPagines((int)(Math.floor(totalElements/elementsPerPagina)) + ((totalElements % elementsPerPagina == 0)?0:1));
            
            
            if (isDevelopment()) {
            log.info("\n\n   === PAGINACIO ===============");
            log.info(" + REGWEB: paginacio.getPaginaActual() => " + paginacio.getPaginaActual() );
            log.info(" + REGWEB: paginacio.getTotalElements() => " + paginacio.getTotalElements() );
            log.info(" + REGWEB: paginacio.getTotalPagines() => " + paginacio.getTotalPagines());
            log.info(" + REGWEB: paginacio.getElementsPerPagina() => " + paginacio.getElementsPerPagina());
            log.info(" + REGWEB: paginacio.getElementsRetornats() => " + paginacio.getElementsRetornats());
            log.info(" + REGWEB: paginacio.getTotalPagines PART 1 => " + (int) (Math.floor(totalElements / elementsPerPagina)));
            log.info(" + REGWEB: paginacio.getTotalPagines PART 2 => " +  ((totalElements % elementsPerPagina == 0) ? 0 : 1));
            }

            List<Registre> registres = new ArrayList<Registre>();
            for (AsientoWs a : registresRegweb) {
                registres.add(new Registre(a.getNumeroRegistro(), a.getFechaRegistro(), a.getExtracto(), a.getEstado(),
                        a.getDenominacionDestino()));
            }

            Map<String, Object> dades = new HashMap<String, Object>();
            
            dades.put("registres", registres);
            dades.put("paginacio", paginacio);
                      
            Gson json = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
            String generat = json.toJson(dades);
            
            //log.info("XYZ ZZZ Generat: " + generat);
            
            try {
                response.getWriter().println(generat);
                response.flushBuffer();
                
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Throwable e) {
            log.error("Error consultant registres a traves de API: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);
        }
    	
    }
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "regweb32_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage/regweb32_reactjs_main.js";

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            try {
                response.getWriter().println(plantilla);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres XYZ ZZZ: " + e.getMessage(), e);
        }

    }

    
    

    public ResultadoBusquedaWs getRegistres(String administrationID, String entidad,
            String formNumero, Date formDataInici, Date formDataFi, String formEstat,
            int pageNumber, int numItems, Locale locale) throws Exception {

        RegWebAsientoRegistralWs service = super.getRegWebAsientoRegistralWsService();

        /* Equivalencia estats REGWEB3.2 amb estats visibles al SELECT */
        List<Integer> formEstats = new ArrayList<Integer>();
        if (formEstat != null) {
            switch (formEstat) {
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

        // XYZ ZZZ if (isDevelopment()) 
        {
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

        ResultadoBusquedaWs result = service.obtenerAsientosCiudadanoCarpeta(entidad,
                administrationID, pageNumber, locale.getLanguage(),
                new Timestamp(formDataInici.getTime()), new Timestamp(formDataFi.getTime()),
                formNumero, formEstats, "", numItems);

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
                    log.info("ar.getUnidadTramitacionDestinoDenominacion() => "
                            + ar.getDenominacionDestino());
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
