package org.fundaciobit.pluginsib.carpetafront.notib;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TiposElementoExpediente;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Document;
import org.fundaciobit.pluginsib.carpetafront.notib.api.NotibClientRest;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Persona;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Resposta;
import org.fundaciobit.pluginsib.carpetafront.notib.api.Transmissio;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author anadal
 */
public class NotibCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String NOTIB_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "notib.";

    /**
     *
     */
    public NotibCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     */
    public NotibCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public NotibCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontnotib";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, String parameter,
            IListenerLogCarpeta logCarpeta) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + OPCIONS_PAGE;

        log.info(" NOTIB getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        if (isDevelopment()) {
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                    + administrationEncriptedID);
        }

        if (query.startsWith(OPCIONS_PAGE)) {
            pageOpcions(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_ESPERA_SISTRA_PAGE)) {

            pageEsperaSistra(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_ESPERA_NOTIB_PAGE)) {

            pageEsperaNotib(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_SISTRA_PAGE)) {

            comunicacionsSistra(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_NOTIB_PAGE)) {

            pageNomunicacionsNotib(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
        } /*else if (query.startsWith(NOTIFICACIONS_NOTIB_DETALL_PAGE)) {

            notificacioNotibDetall(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
        } */ else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    protected boolean isDevelopment() {
        return "true".equals(getProperty(NOTIB_PROPERTY_BASE + "development"));
    }

    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- L L I S T A T D E O P C I O N S ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String OPCIONS_PAGE = "opcions";

    public void pageOpcions(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

           

            Map<String, Object> map = new HashMap<String, Object>();

            InputStream input = this.getClass().getResourceAsStream("/webpage/opcions.html");

            String plantilla = IOUtils.toString(input, "UTF-8");

            String[] traduccions = { "menu.notificaciones", "notificaciones.descripcion", "menu.notificacion",
                    "notificaciones.notificaciones", "menu.notificaciones.otras", "notificaciones.otras", "boto_tornar", "menu.boton"};

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            // GOVERN CENTRAL - NOTIB
            boolean useNotibApi = "true".equalsIgnoreCase(getProperty(NOTIB_PROPERTY_BASE + "usenotibapi"));
            String rutaDesti;
            if (useNotibApi) {
                rutaDesti = absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_NOTIB_PAGE + "/0";
                rutaDesti = "window.location.href ='" + rutaDesti + "'";
            } else {
                rutaDesti = getPropertyRequired(NOTIB_PROPERTY_BASE + "notificaciones.url");
                rutaDesti = "javascript:window.open('" + rutaDesti + "', '_blank')";
            }                
            map.put("notificacionesUrl", rutaDesti);

            // CAIB - SISTRA 1
            String comunicacionesURL = absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_SISTRA_PAGE;

            map.put("comunicacionesURL", comunicacionesURL);

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            response.getWriter().println(fullPage);
            response.flushBuffer();
            

        } catch (Exception e) {

            try {

                log.error("Error llistant registres: " + e.getMessage(), e);
                errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

            } catch (Exception exception) {
                log.info(exception);
            }

        }

    }



    // --------------------------------------------------------------------------------------
    // ------------------- NOTIFICACIONS ESPERA ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_ESPERA_SISTRA_PAGE = "esperasistra";

    public void pageEsperaSistra(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            String rutaDesti;
            rutaDesti = absolutePluginRequestPath + "/" + NOTIFICACIONS_SISTRA_PAGE;

            esperaPage(absolutePluginRequestPath, response, locale, rutaDesti);

        } catch (Exception e) {

            try {

                log.error("Error enviant pagian d'espera de Sistra: " + e.getMessage(), e);
                errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

            } catch (Exception exception) {
                log.info(exception);
            }

        }
    }
    
    // --------------------------------------------------------------------------------------
    // ------------------- NOTIFICACIONS ESPERA NOTIB ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_ESPERA_NOTIB_PAGE = "esperanotib";

    public void pageEsperaNotib(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            int pagina = Integer.parseInt(query.substring(query.lastIndexOf('/') + 1, query.length()));
            String rutaDesti;
            rutaDesti = absolutePluginRequestPath + "/" + NOTIFICACIONS_NOTIB_PAGE + "/" + pagina;  
            
            log.info("pageEsperaNotib => ]" + rutaDesti + "[");
            
            
            esperaPage(absolutePluginRequestPath, response, locale, rutaDesti);

        } catch (Exception e) {

            try {

                log.error("Error enviant pagian d'espera de NOTIB: " + e.getMessage(), e);
                errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

            } catch (Exception exception) {
                log.info(exception);
            }

        }
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- NOTIFICACIONS SISTRA ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_SISTRA_PAGE = "notificacionssistra";

    public void comunicacionsSistra(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            String webpage = getComunicacionsPageSistra(absolutePluginRequestPath, userData, locale);

            responsePage(request, response, locale, webpage, absolutePluginRequestPath);

        } catch (Exception e) {

            try {

                log.error("Error llistant notificaions sistra: " + e.getMessage(), e);
                errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

            } catch (Exception exception) {
                log.info(exception);
            }

        }

    }

    protected void responsePage(HttpServletRequest request, HttpServletResponse response, Locale locale,
            String webpage, String absolutePluginRequestPath) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            response.getWriter().println(webpage);
            response.flushBuffer();
        } catch (IOException e) {

            try {

                log.error("Error obtening writer: " + e.getMessage(), e);
                errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

            } catch (Exception exception) {
                log.info(exception);
            }

        }
    }

    public String getComunicacionsPageSistra(String absolutePluginRequestPath, UserData userData, Locale locale)
            throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        // TRADUCCIONS

        String[] traduccions = { "comunicacion.listado", "comunicacion.vacio", "comunicacion.descripcion.comunicacion",
                "comunicacion.fecha", "comunicacion.descripcion" , "boto_tornar", "notificaciones.otras"};

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();

        coms.add(TipoElementoExpediente.COMUNICACION);
        coms.add(TipoElementoExpediente.NOTIFICACION);

        Sistra1ServiceImpl sistra1Service = getSistra1ServiceImpl();

        List<ElementoExpediente> comunicaciones = sistra1Service.obtenerElementosExpediente(coms,
                Sistra1ServiceImpl.ELEMENTO_TODOS, userData.getAdministrationID(), locale);

        map.put("comunicacions", comunicaciones);
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        InputStream input = this.getClass().getResourceAsStream("/webpage/notibsistra.html");

        String plantilla = IOUtils.toString(input, "UTF-8");

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

        return fullPage;

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- Notificacions NOTIB----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    
    private NotibClientRest notibClientRest = null;

    protected static final String SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB = "SESSIO_CACHE_COMUNICACION_NOTIB";
    
    
    protected static final String NOTIFICACIONS_NOTIB_PAGE = "notificacionsnotib";

    public void pageNomunicacionsNotib(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            log.info("\n\n  PAGINA NOTIB QUERY => ]" + query + "[");


            // TODO XYZ ZZZ aqui ha d'anar QUERY
            int pagina;
            try {
                pagina = Integer.parseInt(query.substring(query.lastIndexOf('/') + 1, query.length()));
            }catch (NumberFormatException e){
                pagina = 0;
            }
            List<Transmissio> notificacions;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotibClientRest(url, user, pass);
                }

                String nif = userData.getAdministrationID();                
                Integer mida = 200;

                Resposta resposta = notibClientRest.consultaNotificacions(nif, pagina, mida);

                // System.out.println(" ------------ OK " + resposta + "---------------");
                // System.out.println(" ------------ NUm Elements Retornats: " +
                // resposta.getNumeroElementsRetornats());
                // System.out.println(" ------------ NUm Elements Totals: " +
                // resposta.getNumeroElementsTotals());
                notificacions = resposta.getResultat();

            }
            
            if (notificacions == null) {
                notificacions = new ArrayList<Transmissio>();
            }
            /*
            if (notificacions.size() == 0) {
                Date dataEnviament = new Date(System.currentTimeMillis());
                Date dataEstat = new Date(System.currentTimeMillis() - 24*60*60*1000);
                Document document = null;
                Persona titular = null;
                List<Persona> destinataris = null;
                Date dataSubestat = new Date(System.currentTimeMillis() - 48*60*60*1000);
                Date errorData  = new Date(System.currentTimeMillis() - 96*60*60*1000);
                Transmissio t = new Transmissio(666L, "emisor", "String organGestor","String procediment","String numExpedient",
                        "String concepte"," String descripcio",dataEnviament, Estat.ENVIADA,dataEstat, document,
                        titular,destinataris, SubEstat.MORT, dataSubestat, true,
                        errorData,"String errorDescripcio","String justificant","String certificacio");
                
                notificacions.add(t);
                notificacions.add(t);
            }
            */
            
            

            // notificacions.get(0).getDescripcio()
            // notificacions.get(0).getDataEnviament()
            // notificacions.get(0).getDocument().getUrl()
            Map<Long, Transmissio> comunicacionsMap = (Map<Long, Transmissio>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsMap == null) {
                comunicacionsMap = new HashMap<Long, Transmissio>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB, comunicacionsMap);
            } 
            
            for (Transmissio t : notificacions) {
                t.setOrganGestor(t.getOrganGestor().substring(t.getOrganGestor().indexOf("', nom='") + 8, t.getOrganGestor().indexOf("', llibre='")));
                comunicacionsMap.put(t.getId(), t);
            }
        


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("comunicacions", notificacions);
            
            /*
            if (isDevelopment()) {
                
                for (Transmissio t : notificacions) {
                    log.info("NE: " + t.getNumExpedient() 
                    + " | DEn: " + t.getDataEnviament() 
                    + " | DEs: " + t.getDataEstat() 
                    + " | DSu: " + t.getDataSubestat() 
                    + " | Desc: " + t.getDescripcio() );
                }
                
            }
            */
            
            

            //map.put("urldetallbase", absolutePluginRequestPath + "/" + NOTIFICACIONS_NOTIB_DETALL_PAGE);
            map.put("urldetallbase", getPropertyRequired(NOTIB_PROPERTY_BASE + "notificaciones.url") + "#");

            // TRADUCCIONS

            String[] traduccions = { "comunicacion.listado.notib", "comunicacion.vacio", "comunicacion.descripcion.comunicacion",
                    "comunicacion.fecha", "comunicacion.descripcion", "comunicacion_emissor", "comunicacion_concepte", "comunicacion_estat", "comunicacion_organo", "comunicacion_dataEstat", "comunicacion_justificant", "comunicacion_certificacio", "boto_tornar", "notificaciones.notificaciones" };

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

            InputStream input = this.getClass().getResourceAsStream("/webpage/notib.html");

            String plantilla = IOUtils.toString(input, "UTF-8");

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

            responsePage(request, response, locale, fullPage, absolutePluginRequestPath);

        } catch (Exception e) {

            log.error("Error llistant notificacions notib: " + e.getMessage(), e);
            errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }





    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DETALL Notificacio NOTIB----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_NOTIB_DETALL_PAGE = "notibdetall";

    public void notificacioNotibDetall(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        log.info("\n\n  PAGINA NOTIB DETALL QUERY => ]" + query + "[");

        try {

            Map<Long, Transmissio> comunicacionsMap = (Map<Long, Transmissio>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsMap == null) {
                // TODO ERRORS
                response.sendRedirect(absolutePluginRequestPath + NOTIFICACIONS_NOTIB_PAGE + "/0");
                return;
            }
            

            Long id = Long.parseLong(query.substring(query.lastIndexOf('/') + 1, query.length()));;

            Transmissio n = comunicacionsMap.get(id);

            if (n == null) {
                // TODO ERRORS
                response.sendRedirect(absolutePluginRequestPath + NOTIFICACIONS_NOTIB_PAGE + "/0");
                return;
            }

            Map<String, Object> map = new HashMap<String, Object>();

            // TRADUCCIONS
           
             String[] traduccions = { "tornar" };
             
             for (String t : traduccions) {
               map.put(t.replace('.', '_'), getTraduccio(t,locale)); 
             }
             

            map.put("titol", "Notificacio amb ID " + n.getId());
            map.put("urltornar", absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_NOTIB_PAGE + "/0");
            map.put("subtitol", (n.getDescripcio()==null)? "":n.getDescripcio());

            List<KeyValue> camps = new ArrayList<NotibCarpetaFrontPlugin.KeyValue>();

            // XYZ ZZZ Falten tracduccions de LABELS

            camps.add(newKeyValue(T("id"), n.getId()));
            camps.add(newKeyValue(T("emisor"), n.getEmisor()));
            camps.add(newKeyValue(T("organGestor"), n.getOrganGestor()));

            camps.add(newKeyValue(T("procediment"), n.getProcediment()));
            camps.add(newKeyValue(T("numExpedient"), n.getNumExpedient()));
            camps.add(newKeyValue(T("concepte"), n.getConcepte()));
            camps.add(newKeyValue(T("descripcio"), n.getDescripcio()));
            camps.add(newKeyValue(T("dataEnviament"), n.getDataEnviament()));

            String estatStr;
            switch (n.getEstat()) {

                case PENDENT:
                    estatStr = T("estat.PENDENT");
                break;
                case ENVIADA:
                    estatStr = T("estat.ENVIADA");
                break;
                case REGISTRADA:
                    estatStr = T("estat.REGISTRADA");
                break;
                case FINALITZADA:
                    estatStr = T("estat.FINALITZADA");
                break;
                case PROCESSADA:
                    estatStr = T("estat.PROCESSADA");
                break;

                default:
                    estatStr = "Desconegut";
            }
            camps.add(newKeyValue(T("estat"), estatStr));
            camps.add(newKeyValue(T("dataEstat"), n.getDataEstat()));

            Document document = n.getDocument();
            if (document != null) {
                camps.add(newKeyValue(T("document_nom"), document.getNom()));
                camps.add(newKeyValue(T("document_url"), document.getUrl()));
                camps.add(newKeyValue(T("document_mida"), document.getMida()));
                camps.add(newKeyValue(T("document_mediaType"), document.getMediaType()));
            }

            // Enviament
            processPersona(camps, n.getTitular(), "titular");

            List<Persona> destinataris = n.getDestinataris();
            if (destinataris != null) {
                int x = 1;
                for (Persona persona : destinataris) {
                    processPersona(camps, persona, "destinatari" + x);
                    x++;
                }

            }

            // SubEstat subestat; XYZ ZZZZ   MIRAR SI AIXÔ ES FA Bé !!!
            camps.add(newKeyValue(T("dataSubestat"), n.getDataSubestat()));

            // Error
            camps.add(newKeyValue(T("error"), String.valueOf(n.isError())));
            camps.add(newKeyValue(T("errorData"), n.getErrorData()));
            camps.add(newKeyValue(T("errorDescripcio"), n.getErrorDescripcio()));

            camps.add(newKeyValue(T("justificant"), n.getJustificant()));
            camps.add(newKeyValue(T("certificacio"), n.getCertificacio()));

            map.put("clauvalors", camps);

            InputStream input = this.getClass().getResourceAsStream("/webpage/notibdetall.html");

            String plantilla = IOUtils.toString(input, "UTF-8");

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map, generat);

            responsePage(request, response, locale, fullPage, absolutePluginRequestPath);

        } catch (Exception e) {
            log.error("Error detall notificació notib: " + e.getMessage(), e);
            errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);
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

        return getImageFromResource(locale, "/logo/logo-notib.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return NOTIB_PROPERTY_BASE;
    }

    private Sistra1ServiceImpl sistra1ServiceImpl = null;

    protected synchronized Sistra1ServiceImpl getSistra1ServiceImpl() throws Exception {

        if (sistra1ServiceImpl == null) {

            String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.url");
            String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.user");
            String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "sistra1.pass");
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
