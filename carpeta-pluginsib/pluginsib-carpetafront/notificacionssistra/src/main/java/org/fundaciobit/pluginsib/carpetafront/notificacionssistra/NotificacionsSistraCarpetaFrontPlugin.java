package org.fundaciobit.pluginsib.carpetafront.notificacionssistra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.caib.carpeta.pluginsib.carpetafront.api.*;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api.Persona;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.reflect.Modifier.TRANSIENT;


/**
 * @author jpernia
 */
public class NotificacionsSistraCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String NOTIFICACIONSSISTRA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "notificacionssistra.";

    /**
     *
     */
    public NotificacionsSistraCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     */
    public NotificacionsSistraCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public NotificacionsSistraCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontnotificacionssistra";
    }

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- CACHE DE TITOLS i SUBTITOLS ----------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------


    private TitlesInfo titlesInfo = null;;


    @Override
    public void setTitlesInfo(TitlesInfo titlesInfo) {
        this.titlesInfo = titlesInfo;
    }

    @Override
    public TitlesInfo getTitlesInfo() {
        return titlesInfo;
    }


    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, String parameter,
            IListenerLogCarpeta logCarpeta) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        log.info(" NOTIFICACIONSSISTRA getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public boolean isReactComponent() {
        return true;
    }


    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {


        if (isDevelopment()) {
            log.info("NotificacionsSistraCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("NotificacionsSistraCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info("NotificacionsSistraCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                    + administrationEncriptedID);
        }

        if (query.startsWith(INDEX_HTML_PAGE)) {
            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {
            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE)) {
            consultaNotificacions(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else {
            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    protected boolean isDevelopment() {
        return "true".equals(getProperty(NOTIFICACIONSSISTRA_PROPERTY_BASE + "development"));
    }

    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }



    protected static final String NOTIFICACIONS_ESPERA_SISTRA_PAGE = "esperasistra";


    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------- INDEX -----------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "notificacionssistra_index.html";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                      HttpServletRequest request, HttpServletResponse response, UserData userData,
                      String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage/notificacionssistra_index.html";

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(INDEX_HTML_PAGE, "UTF-8") + "\"");

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            Map<String, Object> map = new HashMap<String, Object>();

            Gson json = new Gson();

            TitlesInfo titles = getTitlesInfo();

            map.put("titles", json.toJson(titles.getTitlesByLang()));

            map.put("subtitles", json.toJson(titles.getSubtitlesByLang()));

            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);

            String pathtojsNotificacionsSistra = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojsNotificacionsSistra", pathtojsNotificacionsSistra);

            String pathtoservei = absolutePluginRequestPath + "/" + URL_REST_SERVICE;

            map.put("pathtoservei", pathtoservei);

            // CAIB - SISTRA 1
            String comunicacionesURL = absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_SISTRA_PAGE;
            map.put("comunicacionesUrl", comunicacionesURL);

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
    // ------------------- CONSULTA REST ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE = "consultaNotificacions";

    public void consultaNotificacions(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                                HttpServletRequest request, HttpServletResponse response, UserData userData,
                                String administrationEncriptedID, Locale locale, Boolean isGet) {

        Map<String, Object> comunicacionsMap = new HashMap<String, Object>();
        int itemsPagina = 10;

        try {

            List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();

            coms.add(TipoElementoExpediente.COMUNICACION);
            coms.add(TipoElementoExpediente.NOTIFICACION);

            Sistra1ServiceImpl sistra1Service = getSistra1ServiceImpl();

            List<ElementoExpediente> comunicaciones = sistra1Service.obtenerElementosExpediente(coms,
                    Sistra1ServiceImpl.ELEMENTO_TODOS, userData.getAdministrationID(), locale);

            log.info("***********************************************************");
            for(ElementoExpediente comunicacion : comunicaciones){
                log.info("DESCRIPCIOON: " + comunicacion.getDescripcion());
                log.info("URL: " + comunicacion.getUrl());
                log.info("FECHA: " + comunicacion.getFecha());
            }
            log.info("***********************************************************");

            comunicacionsMap.put("comunicacions", comunicaciones);
            comunicacionsMap.put("registresPagina", itemsPagina);
            comunicacionsMap.put("totalRegistres", comunicaciones.size());

//            Gson gson = new Gson();

            Gson gson = new GsonBuilder()
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .excludeFieldsWithModifiers(TRANSIENT)
                    .create();

            comunicacionsMap.forEach((key, value) -> System.out.println(key + ":" + value));

            String json = gson.toJson(comunicacionsMap);

            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }


        } catch (Exception e) {

            log.error("Error llistant notificacions Sistra: " + e.getMessage(), e);
            errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }




    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "notificacionssistra_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
                        HttpServletRequest request, HttpServletResponse response, UserData userData,
                        String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage/notificacionssistra_reactjs_main.js";

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
            log.error("Error llistant tràmits XYZ ZZZ: " + e.getMessage(), e);
        }

    }


    protected void processPersona(List<KeyValue> camps, Persona titular, String base) {
        if (titular != null) {

            String tipusStr;
            switch (titular.getTipus()) {
                case ADMINISTRACIO:
                    tipusStr = "ADMINISTRACIO";
                break;
                case FISICA:
                    tipusStr = "FISICA";
                break;
                case JURIDICA:
                    tipusStr = "JURIDICA";
                break;

                default:
                    tipusStr = "DESCONEGUT";

            }

            camps.add(newKeyValue(T(base + "_tipus"), tipusStr));
            camps.add(newKeyValue(T(base + "_nom"), titular.getNom()));
            camps.add(newKeyValue(T(base + "_llinatge1"), titular.getLlinatge1()));
            camps.add(newKeyValue(T(base + "_llinatge2"), titular.getLlinatge2()));
            camps.add(newKeyValue(T(base + "_nif"), titular.getNif()));
            camps.add(newKeyValue(T(base + "_email"), titular.getEmail()));

        }
    }

    public String T(String nt) {
        return nt;
    }
    
    
    public KeyValue newKeyValue(String k , String v) {
        
        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }
        
    }
    
    public KeyValue newKeyValue(String k , Long v) {
        
        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }
        
    }
    
   public KeyValue newKeyValue(String k , Date v) {
        
        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }
        
    }
    
    
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- U T I L I T A T S    ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    
    


    protected String encapsulaEnPaginaHtml(String absolutePluginRequestPath, Locale locale, Map<String, Object> map,
            String generat) throws IOException {
        String fullPage;
        {

            InputStream inputfp = this.getClass().getResourceAsStream("/webpage/plantilla.html");

            String fullPageTemplate = IOUtils.toString(inputfp, "UTF-8");

            map.put("title", getTitle(locale));

            map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

            map.put("contingut", generat);

            map.put("lang", locale.getLanguage());

            fullPage = TemplateEngine.processExpressionLanguage(fullPageTemplate, map, locale);

        }
        return fullPage;
    }
    
    
    

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {

        return getImageFromResource(locale, "/logo/logo-notificacionssistra.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return NOTIFICACIONSSISTRA_PROPERTY_BASE;
    }

    private Sistra1ServiceImpl sistra1ServiceImpl = null;

    protected synchronized Sistra1ServiceImpl getSistra1ServiceImpl() throws Exception {

        if (sistra1ServiceImpl == null) {

            String url = getPropertyRequired(NOTIFICACIONSSISTRA_PROPERTY_BASE + "sistra1.url");
            String user = getPropertyRequired(NOTIFICACIONSSISTRA_PROPERTY_BASE + "sistra1.user");
            String pass = getPropertyRequired(NOTIFICACIONSSISTRA_PROPERTY_BASE + "sistra1.pass");
            boolean development = isDevelopment();

            Sistra1ServiceImpl tmp = new Sistra1ServiceImpl(url, user, pass, development);

            sistra1ServiceImpl = tmp;
        }

        return sistra1ServiceImpl;

    }

    /**
     * 
     * @author anadal
     *
     */
    public static class KeyValue {

        protected static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        final String key;
        final String value;

        public KeyValue(String key, String value) {
            super();
            this.key = key;
            this.value = value==null?"":value;
        }

        public KeyValue(String key, Long value) {
            super();
            this.key = key;
            this.value = value==null?"":String.valueOf(value);
        }

        public KeyValue(String key, Date value) {
            super();
            this.key = key;
            this.value = value==null?"":SDF.format(value);
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }

}
