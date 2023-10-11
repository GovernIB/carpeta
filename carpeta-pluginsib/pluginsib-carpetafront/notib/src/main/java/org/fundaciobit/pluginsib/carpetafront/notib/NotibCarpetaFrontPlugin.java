package org.fundaciobit.pluginsib.carpetafront.notib;

import com.google.gson.Gson;
import es.caib.carpeta.pluginsib.carpetafront.api.*;
import es.caib.notib.client.NotificacioRestClientV2;
import es.caib.notib.client.domini.IdiomaEnumDto;
import es.caib.notib.client.domini.consulta.*;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import org.apache.commons.io.IOUtils;
//import org.fundaciobit.pluginsib.carpetafront.notib.api.*;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
            HttpServletRequest request, UserData userData, String administrationIDEncriptat,
            String parameter, IListenerLogCarpeta logCarpeta) throws Exception {

        registerUserData(userData);

        String startURL = (absolutePluginRequestPath + "/" + INDEX_HTML_PAGE);

        log.info(" NOTIB getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        if (isDevelopment()) {
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info("NotibCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                    + administrationEncriptedID);
        }

        if (query.startsWith(OPCIONS_PAGE)) {
            pageOpcions(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                    response, userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_ESPERA_SISTRA_PAGE)) {

            pageEsperaSistra(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                    response, userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_ESPERA_NOTIB_PAGE)) {

            pageEsperaNotib(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                    response, userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_SISTRA_PAGE)) {

            comunicacionsSistra(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(NOTIFICACIONS_NOTIB_PAGE)) {

            pageNotificacionsNotib(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_TOTES)) {

            consultaTotes(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                    response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_TOTES_PENDENTS)) {

            consultaTotesPendents(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_TOTES_LLEGIDES)) {

            consultaTotesLlegides(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_NOTIFICACIONS)) {

            consultaNotificacions(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_NOTIFICACIONS_PENDENTS)) {

            consultaNotificacionsPendents(absolutePluginRequestPath, relativePluginRequestPath,
                    query, request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_NOTIFICACIONS_LLEGIDES)) {

            consultaNotificacionsLlegides(absolutePluginRequestPath, relativePluginRequestPath,
                    query, request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_COMUNICACIONS)) {

            consultaComunicacions(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_COMUNICACIONS_PENDENTS)) {

            consultaComunicacionsPendents(absolutePluginRequestPath, relativePluginRequestPath,
                    query, request, response, userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_COMUNICACIONS_LLEGIDES)) {

            consultaComunicacionsLlegides(absolutePluginRequestPath, relativePluginRequestPath,
                    query, request, response, userData, administrationEncriptedID, locale, isGet);

        } /*
           * else if (query.startsWith(NOTIFICACIONS_NOTIB_DETALL_PAGE)) {
           * 
           * notificacioNotibDetall(absolutePluginRequestPath, relativePluginRequestPath,
           * query, request, response, userData, administrationEncriptedID, locale,
           * isGet); }
           */ else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet,
                    logCarpeta);
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

    public void pageOpcions(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            Map<String, Object> map = new HashMap<String, Object>();

            InputStream input = this.getClass().getResourceAsStream("/webpage/opcions.html");

            String plantilla = IOUtils.toString(input, "UTF-8");

            String[] traduccions = { "menu.notificaciones", "notificaciones.descripcion",
                    "menu.notificacion", "notificaciones.notificaciones",
                    "menu.notificaciones.otras", "notificaciones.otras", "boto_tornar",
                    "menu.boton" };

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            // GOVERN CENTRAL - NOTIB
            boolean useNotibApi = "true"
                    .equalsIgnoreCase(getProperty(NOTIB_PROPERTY_BASE + "usenotibapi"));
            String rutaDesti;
            if (useNotibApi) {
                rutaDesti = absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_NOTIB_PAGE
                        + "/0";
                rutaDesti = "window.location.href ='" + rutaDesti + "'";
            } else {
                rutaDesti = getPropertyRequired(NOTIB_PROPERTY_BASE + "notificaciones.url");
                rutaDesti = "javascript:window.open('" + rutaDesti + "', '_blank')";
            }
            map.put("notificacionesUrl", rutaDesti);

            // CAIB - SISTRA 1
            String comunicacionesURL = absolutePluginRequestPath + "/"
                    + NOTIFICACIONS_ESPERA_SISTRA_PAGE;

            map.put("comunicacionesURL", comunicacionesURL);

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map,
                    generat);

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

    public void pageEsperaSistra(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

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

    public void pageEsperaNotib(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            int pagina = Integer
                    .parseInt(query.substring(query.lastIndexOf('/') + 1, query.length()));
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
    // -------------------- INDEX -----------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "notib_index.html";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage/notib_index.html";

            response.setHeader("Content-Disposition", "inline;filename=\""
                    + java.net.URLEncoder.encode(INDEX_HTML_PAGE, "UTF-8") + "\"");

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            Map<String, Object> map = new HashMap<String, Object>();

            Gson json = new Gson();

            TitlesInfo titles = getTitlesInfo();

            map.put("titles", json.toJson(titles.getTitlesByLang()));

            map.put("subtitles", json.toJson(titles.getSubtitlesByLang()));

            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);

            String pathtojsNotib = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojsNotib", pathtojsNotib);

            String pathtoservei = absolutePluginRequestPath + "/" + URL_REST_SERVICE_TOTES;
            map.put("pathtoservei", pathtoservei);

            String pathtoserveiPendientesUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_TOTES_PENDENTS;
            map.put("pathtoserveiPendientesUrl", pathtoserveiPendientesUrl);

            String pathtoserveiLeidasUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_TOTES_LLEGIDES;
            map.put("pathtoserveiLeidasUrl", pathtoserveiLeidasUrl);

            // GOVERN CENTRAL - NOTIB
            // boolean useNotibApi = "true".equalsIgnoreCase(getProperty(NOTIB_PROPERTY_BASE
            // + "usenotibapi"));

            String notificacionesTodasUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_NOTIFICACIONS;
            map.put("notificacionesTodasUrl", notificacionesTodasUrl);

            String notificacionesPendientesUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_NOTIFICACIONS_PENDENTS;
            map.put("notificacionesPendientesUrl", notificacionesPendientesUrl);

            String notificacionesLeidasUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_NOTIFICACIONS_LLEGIDES;
            map.put("notificacionesLeidasUrl", notificacionesLeidasUrl);

            String comunicacionesTodasUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_COMUNICACIONS;
            map.put("comunicacionesTodasUrl", comunicacionesTodasUrl);

            String comunicacionesPendientesUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_COMUNICACIONS_PENDENTS;
            map.put("comunicacionesPendientesUrl", comunicacionesPendientesUrl);

            String comunicacionesLeidasUrl = absolutePluginRequestPath + "/"
                    + URL_REST_SERVICE_COMUNICACIONS_LLEGIDES;
            map.put("comunicacionesLeidasUrl", comunicacionesLeidasUrl);

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
    // ------------------- CONSULTA REST NOTIFICACIONS NOTIB
    // --------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_NOTIFICACIONS = "consultaNotificacions";

    public void consultaNotificacions(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> notificacionsList;
            List<ComunicacioNotificacio> sortedNotificacions = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaNotificacions = notibClientRest.notificacionsByTitular(nif, formDataInici,
                        formDataFi, true, getIdiomaEnumDto(locale.getLanguage()), 0, mida);

                notificacionsList = respostaNotificacions.getResultat();
                for (TransmissioV2 notificacio : notificacionsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(notificacio);
                    cn.setData(notificacio.getDataEnviament());
                    cn.setTipus("notificacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedNotificacions = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedNotificacions.size();

            }

            int notificacionsTotals;
            if (notificacionsList == null) {
                notificacionsList = new ArrayList<TransmissioV2>();
                notificacionsTotals = 0;
            } else {
                notificacionsTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> notificacionsMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);
            
            if (notificacionsMap == null) {
                notificacionsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        notificacionsMap);
            }

            for (ComunicacioNotificacio t : sortedNotificacions) {
                
                t.getTransmissio()
                .setOrganGestor(t.getTransmissio().getOrganGestor());
                notificacionsMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoNotificacions = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= notificacionsTotals) {
                infoNotificacions.put("comunicacions",
                        sortedNotificacions.subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoNotificacions.put("comunicacions",
                        sortedNotificacions.subList(pagina * regPag, notificacionsTotals));
            }
            infoNotificacions
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoNotificacions
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoNotificacions.put("registresPagina", formRegPorPagina);
            infoNotificacions.put("totalRegistres", notificacionsTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoNotificacions);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Notificacions NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- NOTIFICACIONS SISTRA ---------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_SISTRA_PAGE = "notificacionssistra";

    public void comunicacionsSistra(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet) {

        try {

            String webpage = getComunicacionsPageSistra(absolutePluginRequestPath, userData,
                    locale);

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

    protected void responsePage(HttpServletRequest request, HttpServletResponse response,
            Locale locale, String webpage, String absolutePluginRequestPath) {
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

    public String getComunicacionsPageSistra(String absolutePluginRequestPath, UserData userData,
            Locale locale) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        // TRADUCCIONS

        String[] traduccions = { "comunicacion.listado", "comunicacion.vacio",
                "comunicacion.descripcion.comunicacion", "comunicacion.fecha",
                "comunicacion.descripcion", "boto_tornar", "notificaciones.otras" };

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
    // ------------------- Notificacions NOTIB----------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    private NotificacioRestClientV2 notibClientRest = null;

    protected static final String SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB = "SESSIO_CACHE_COMUNICACION_NOTIB";

    protected static final String NOTIFICACIONS_NOTIB_PAGE = "notificacionsnotib";

    public void pageNotificacionsNotib(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet) {

        try {

            log.info("\n\n  PAGINA NOTIB QUERY => ]" + query + "[");

            // TODO XYZ ZZZ aqui ha d'anar QUERY
            int pagina;
            try {
                pagina = Integer
                        .parseInt(query.substring(query.lastIndexOf('/') + 1, query.length()));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> notificacions;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 resposta = notibClientRest.notificacionsByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()), pagina, mida);

                // System.out.println(" ------------ OK " + resposta + "---------------");
                // System.out.println(" ------------ NUm Elements Retornats: " +
                // resposta.getNumeroElementsRetornats());
                // System.out.println(" ------------ NUm Elements Totals: " +
                // resposta.getNumeroElementsTotals());
                notificacions = resposta.getResultat();

            }

            if (notificacions == null) {
                notificacions = new ArrayList<TransmissioV2>();
            }
            /*
             * if (notificacions.size() == 0) { Date dataEnviament = new
             * Date(System.currentTimeMillis()); Date dataEstat = new
             * Date(System.currentTimeMillis() - 24*60*60*1000); Document document = null;
             * Persona titular = null; List<Persona> destinataris = null; Date dataSubestat
             * = new Date(System.currentTimeMillis() - 48*60*60*1000); Date errorData = new
             * Date(System.currentTimeMillis() - 96*60*60*1000); TransmissioV2 t = new
             * TransmissioV2(666L, "emisor",
             * "String organGestor","String procediment","String numExpedient",
             * "String concepte"," String descripcio",dataEnviament,
             * Estat.ENVIADA,dataEstat, document, titular,destinataris, SubEstat.MORT,
             * dataSubestat, true,
             * errorData,"String errorDescripcio","String justificant","String certificacio"
             * );
             * 
             * notificacions.add(t); notificacions.add(t); }
             */

            // notificacions.get(0).getDescripcio()
            // notificacions.get(0).getDataEnviament()
            // notificacions.get(0).getDocument().getUrl()
            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> comunicacionsMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsMap == null) {
                comunicacionsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        comunicacionsMap);
            }

            for (TransmissioV2 t : notificacions) {
                t.setOrganGestor(t.getOrganGestor());
                comunicacionsMap.put(t.getId(), t);
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("comunicacions", notificacions);

            /*
             * if (isDevelopment()) {
             * 
             * for (TransmissioV2 t : notificacions) { log.info("NE: " + t.getNumExpedient() +
             * " | DEn: " + t.getDataEnviament() + " | DEs: " + t.getDataEstat() +
             * " | DSu: " + t.getDataSubestat() + " | Desc: " + t.getDescripcio() ); }
             * 
             * }
             */

            // map.put("urldetallbase", absolutePluginRequestPath + "/" +
            // NOTIFICACIONS_NOTIB_DETALL_PAGE);
            map.put("urldetallbase",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "notificaciones.url") + "#");

            // TRADUCCIONS

            String[] traduccions = { "comunicacion.listado.notib", "comunicacion.vacio",
                    "comunicacion.descripcion.comunicacion", "comunicacion.fecha",
                    "comunicacion.descripcion", "comunicacion_emissor", "comunicacion_concepte",
                    "comunicacion_estat", "comunicacion_organo", "comunicacion_dataEstat",
                    "comunicacion_justificant", "comunicacion_certificacio", "boto_tornar",
                    "notificaciones.notificaciones" };

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

            InputStream input = this.getClass().getResourceAsStream("/webpage/notib.html");

            String plantilla = IOUtils.toString(input, "UTF-8");

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map,
                    generat);

            responsePage(request, response, locale, fullPage, absolutePluginRequestPath);

        } catch (Exception e) {

            log.error("Error llistant notificacions notib: " + e.getMessage(), e);
            errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST NOTIFICACIONS PENDENTS NOTIB
    // -----------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_NOTIFICACIONS_PENDENTS = "notificacionspendentsnotib";

    public void consultaNotificacionsPendents(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> notificacionsPendentsList;
            List<ComunicacioNotificacio> sortedNotificacionsPendents = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }


                RespostaConsultaV2 respostaNotificacionsPendents = notibClientRest
                        .notificacionsPendentsByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0,
                                mida);

                notificacionsPendentsList = respostaNotificacionsPendents.getResultat();
                for (TransmissioV2 notificacio : notificacionsPendentsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(notificacio);
                    cn.setData(notificacio.getDataEnviament());
                    cn.setTipus("notificacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedNotificacionsPendents = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedNotificacionsPendents.size();

            }

            int notificacionsPendentsTotals;
            if (notificacionsPendentsList == null) {
                notificacionsPendentsList = new ArrayList<TransmissioV2>();
                notificacionsPendentsTotals = 0;
            } else {
                notificacionsPendentsTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> notificacionsPendentsMap = (Map<Long, TransmissioV2>) request
                    .getSession().getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (notificacionsPendentsMap == null) {
                notificacionsPendentsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        notificacionsPendentsMap);
            }

            for (ComunicacioNotificacio t : sortedNotificacionsPendents) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                notificacionsPendentsMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoNotificacionsPendents = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= notificacionsPendentsTotals) {
                infoNotificacionsPendents.put("comunicacions", sortedNotificacionsPendents
                        .subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoNotificacionsPendents.put("comunicacions", sortedNotificacionsPendents
                        .subList(pagina * regPag, notificacionsPendentsTotals));
            }
            infoNotificacionsPendents
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoNotificacionsPendents
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoNotificacionsPendents.put("registresPagina", formRegPorPagina);
            infoNotificacionsPendents.put("totalRegistres", notificacionsPendentsTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoNotificacionsPendents);

 //           log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Notificacions Pendents NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST NOTIFICACIONS LLEGIDES NOTIB
    // -----------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_NOTIFICACIONS_LLEGIDES = "notificacionsllegidesnotib";

    public void consultaNotificacionsLlegides(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> notificacionsLlegidesList;
            List<ComunicacioNotificacio> sortedNotificacionsLlegides = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }


                RespostaConsultaV2 respostaNotificacionsLlegides = notibClientRest.notificacionsLlegidesByTitular(nif,
                        formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0, mida);

                notificacionsLlegidesList = respostaNotificacionsLlegides.getResultat();
                for (TransmissioV2 notificacio : notificacionsLlegidesList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(notificacio);
                    cn.setData(notificacio.getDataEnviament());
                    cn.setTipus("notificacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedNotificacionsLlegides = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedNotificacionsLlegides.size();

            }

            int notificacionsLlegidesTotals;
            if (notificacionsLlegidesList == null) {
                notificacionsLlegidesList = new ArrayList<TransmissioV2>();
                notificacionsLlegidesTotals = 0;
            } else {
                notificacionsLlegidesTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> notificacionsLlegidesMap = (Map<Long, TransmissioV2>) request
                    .getSession().getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (notificacionsLlegidesMap == null) {
                notificacionsLlegidesMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        notificacionsLlegidesMap);
            }

            for (ComunicacioNotificacio t : sortedNotificacionsLlegides) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                notificacionsLlegidesMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoNotificacionsLlegides = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= notificacionsLlegidesTotals) {
                infoNotificacionsLlegides.put("comunicacions", sortedNotificacionsLlegides
                        .subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoNotificacionsLlegides.put("comunicacions", sortedNotificacionsLlegides
                        .subList(pagina * regPag, notificacionsLlegidesTotals));
            }
            infoNotificacionsLlegides
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoNotificacionsLlegides
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoNotificacionsLlegides.put("registresPagina", formRegPorPagina);
            infoNotificacionsLlegides.put("totalRegistres", notificacionsLlegidesTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoNotificacionsLlegides);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Notificacions Llegides NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST COMUNICACIONS NOTIB
    // --------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_COMUNICACIONS = "comunicacionsnotib";

    public void consultaComunicacions(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsList;
            List<ComunicacioNotificacio> sortedComunicacions = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaComunicacions = notibClientRest.comunicacionsByTitular(nif, formDataInici,
                        formDataFi,true, getIdiomaEnumDto(locale.getLanguage()), 0,
                        mida);

                comunicacionsList = respostaComunicacions.getResultat();
                for (TransmissioV2 comunicacio : comunicacionsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(comunicacio);
                    cn.setData(comunicacio.getDataEnviament());
                    cn.setTipus("comunicacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedComunicacions = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedComunicacions.size();

            }

            int comunicacionsTotals;
            if (comunicacionsList == null) {
                comunicacionsList = new ArrayList<TransmissioV2>();
                comunicacionsTotals = 0;
            } else {
                comunicacionsTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> comunicacionsMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsMap == null) {
                comunicacionsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        comunicacionsMap);
            }

            for (ComunicacioNotificacio t : sortedComunicacions) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                comunicacionsMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoComunicacions = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= comunicacionsTotals) {
                infoComunicacions.put("comunicacions",
                        sortedComunicacions.subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoComunicacions.put("comunicacions",
                        sortedComunicacions.subList(pagina * regPag, comunicacionsTotals));
            }
            infoComunicacions.put("urldetallbase",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacions.put("urldetallbase2",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacions.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacions.put("registresPagina", formRegPorPagina);
            infoComunicacions.put("totalRegistres", comunicacionsTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoComunicacions);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Comunicacions NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST COMUNICACIONS PENDENTS NOTIB
    // -----------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_COMUNICACIONS_PENDENTS = "comunicacionspendentsnotib";

    public void consultaComunicacionsPendents(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsPendentsList;
            List<ComunicacioNotificacio> sortedComunicacionsPendents = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaComunicacionsPendents = notibClientRest.comunicacionsPendentsByTitular(nif,
                        formDataInici, formDataFi,true, getIdiomaEnumDto(locale.getLanguage()),  0, mida);

                comunicacionsPendentsList = respostaComunicacionsPendents.getResultat();
                for (TransmissioV2 comunicacio : comunicacionsPendentsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(comunicacio);
                    cn.setData(comunicacio.getDataEnviament());
                    cn.setTipus("comunicacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedComunicacionsPendents = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedComunicacionsPendents.size();

            }

            int comunicacionsPendentsTotals;
            if (comunicacionsPendentsList == null) {
                comunicacionsPendentsList = new ArrayList<TransmissioV2>();
                comunicacionsPendentsTotals = 0;
            } else {
                comunicacionsPendentsTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> comunicacionsPendentsMap = (Map<Long, TransmissioV2>) request
                    .getSession().getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsPendentsMap == null) {
                comunicacionsPendentsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        comunicacionsPendentsMap);
            }

            for (ComunicacioNotificacio t : sortedComunicacionsPendents) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                comunicacionsPendentsMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoComunicacionsPendents = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= comunicacionsPendentsTotals) {
                infoComunicacionsPendents.put("comunicacions", sortedComunicacionsPendents
                        .subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoComunicacionsPendents.put("comunicacions", sortedComunicacionsPendents
                        .subList(pagina * regPag, comunicacionsPendentsTotals));
            }
            infoComunicacionsPendents.put("urldetallbase",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsPendents.put("urldetallbase2",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsPendents.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsPendents.put("registresPagina", formRegPorPagina);
            infoComunicacionsPendents.put("totalRegistres", comunicacionsPendentsTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoComunicacionsPendents);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Comunicacions Pendents NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST COMUNICACIONS LLEGIDES NOTIB
    // -----------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_COMUNICACIONS_LLEGIDES = "comunicacionsllegidesnotib";

    public void consultaComunicacionsLlegides(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsLlegidesList;
            List<ComunicacioNotificacio> sortedComunicacionsLlegides = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaComunicacionsLlegides = notibClientRest.comunicacionsLlegidesByTitular(nif,
                        formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()), 0, mida);

                comunicacionsLlegidesList = respostaComunicacionsLlegides.getResultat();
                for (TransmissioV2 comunicacio : comunicacionsLlegidesList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(comunicacio);
                    cn.setData(comunicacio.getDataEnviament());
                    cn.setTipus("comunicacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedComunicacionsLlegides = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedComunicacionsLlegides.size();

            }

            int comunicacionsLlegidesTotals;
            if (comunicacionsLlegidesList == null) {
                comunicacionsLlegidesList = new ArrayList<TransmissioV2>();
                comunicacionsLlegidesTotals = 0;
            } else {
                comunicacionsLlegidesTotals = comNumero;
            }

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> comunicacionsLlegidesMap = (Map<Long, TransmissioV2>) request
                    .getSession().getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsLlegidesMap == null) {
                comunicacionsLlegidesMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        comunicacionsLlegidesMap);
            }

            for (ComunicacioNotificacio t : sortedComunicacionsLlegides) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                comunicacionsLlegidesMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoComunicacionsLlegides = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= comunicacionsLlegidesTotals) {
                infoComunicacionsLlegides.put("comunicacions", sortedComunicacionsLlegides
                        .subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoComunicacionsLlegides.put("comunicacions", sortedComunicacionsLlegides
                        .subList(pagina * regPag, comunicacionsLlegidesTotals));
            }
            infoComunicacionsLlegides.put("urldetallbase",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsLlegides.put("urldetallbase2",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsLlegides.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacionsLlegides.put("registresPagina", formRegPorPagina);
            infoComunicacionsLlegides.put("totalRegistres", comunicacionsLlegidesTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoComunicacionsLlegides);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Comunicacions Llegides NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST NOTIFICACIONS I COMUNICACIONS NOTIB
    // --------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_TOTES = "totesnotib";

    public void consultaTotes(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsList;
            List<TransmissioV2> notificacionsList;
            //List<TransmissioV2> totes = new ArrayList<TransmissioV2>();
            List<ComunicacioNotificacio> sortedTotes = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");


                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);

                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);

                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                log.info(" ===============   INICI PROCESS CONSULTES API NOTIB ================");

                int reintents = 3;
                while(reintents > 0) {
                    try {
                        
                   
                        RespostaConsultaV2 respostaNotificacions = notibClientRest.notificacionsByTitular(nif, formDataInici,
                                formDataFi, true, getIdiomaEnumDto(locale.getLanguage()), 0, mida);
                        
                        notificacionsList = respostaNotificacions.getResultat();
                        for (TransmissioV2 notificacio : notificacionsList) {
                            ComunicacioNotificacio cn = new ComunicacioNotificacio();
                            cn.setTransmissio(notificacio);
                            cn.setData(notificacio.getDataEnviament());
                            cn.setTipus("notificacio");
                            cns.add(cn);
                        }
                        break;
                    } catch (Exception e) {
                        log.info("CLASS EXCEPTION[notificacionsByTitular]: " + e.getClass().getName());
                        
                        String msg_exc = e.getMessage();
                        
                        boolean isLoginJsonError = msg_exc.startsWith("com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)):");
                        
                        log.info(" CLASS MESSAGE[notificacionsByTitular]: IS LOGIN-JSON ERROR " + isLoginJsonError);
                        
                        if (isLoginJsonError) {
                            reintents--;
                            if (reintents <= 0) {
                                throw e;
                            }
                            Thread.sleep(750);
                            continue;
                        } else {
                            throw e;
                        }                        
                    }
                
                }
                
                
                reintents = 3;
                while (reintents > 0) {
                    try {
                        RespostaConsultaV2 respostaComunicacions = notibClientRest.comunicacionsByTitular(nif,
                                formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()), 0, mida);

                        comunicacionsList = respostaComunicacions.getResultat();
                        for (TransmissioV2 comunicacio : comunicacionsList) {
                            ComunicacioNotificacio cn = new ComunicacioNotificacio();
                            cn.setTransmissio(comunicacio);
                            cn.setData(comunicacio.getDataEnviament());
                            cn.setTipus("comunicacio");
                            cns.add(cn);
                        }

                        break;
                    } catch (Exception e) {
                        log.info("CLASS EXCEPTION[comunicacionsByTitular]: " + e.getClass().getName());

                        String msg_exc = e.getMessage();

                        boolean isLoginJsonError = msg_exc.startsWith(
                                "com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)):");

                        log.info(" CLASS MESSAGE[comunicacionsByTitular]: IS LOGIN-JSON ERROR " + isLoginJsonError);

                        if (isLoginJsonError) {
                            reintents--;
                            if (reintents <= 0) {
                                throw e;
                            }
                            Thread.sleep(750);
                            continue;
                        } else {
                            throw e;
                        }
                    }
                }
                

                // Ordenam comunicacions a mostrar
                sortedTotes = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedTotes.size();

            }

            int totesTotals = comNumero;
            

//            Collections.reverse(comunicacions);

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> totesMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (totesMap == null) {
                totesMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB, totesMap);
            }

            for (ComunicacioNotificacio t : sortedTotes) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                totesMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoComunicacions = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= totesTotals) {
                infoComunicacions.put("comunicacions",
                        sortedTotes.subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoComunicacions.put("comunicacions",
                        sortedTotes.subList(pagina * regPag, totesTotals));
            }
            infoComunicacions
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoComunicacions
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoComunicacions.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoComunicacions.put("registresPagina", formRegPorPagina);
            infoComunicacions.put("totalRegistres", totesTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoComunicacions);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            
            
            
            
            log.error("Error llistant Comunicacions NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------- CONSULTA REST NOTIFICACIONS I COMUNICACIONS PENDENTS NOTIB -----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_TOTES_PENDENTS = "totespendentsnotib";

    public void consultaTotesPendents(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsPendentsList;
            List<TransmissioV2> notificacionsPendentsList;
            List<ComunicacioNotificacio> sortedTotesPendents = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaNotificacionsPendents = notibClientRest.notificacionsPendentsByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0,
                        mida);
                RespostaConsultaV2 respostaComunicacionsPendents = notibClientRest.comunicacionsPendentsByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0,
                        mida);

                comunicacionsPendentsList = respostaComunicacionsPendents.getResultat();
                for (TransmissioV2 comunicacio : comunicacionsPendentsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(comunicacio);
                    cn.setData(comunicacio.getDataEnviament());
                    cn.setTipus("comunicacio");
                    cns.add(cn);
                }

                notificacionsPendentsList = respostaNotificacionsPendents.getResultat();
                for (TransmissioV2 notificacio : notificacionsPendentsList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(notificacio);
                    cn.setData(notificacio.getDataEnviament());
                    cn.setTipus("notificacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedTotesPendents = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedTotesPendents.size();

            }

            int totesPendentsTotals = comNumero;
            

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> totesPendentsMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (totesPendentsMap == null) {
                totesPendentsMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        totesPendentsMap);
            }

            for (ComunicacioNotificacio t : sortedTotesPendents) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                totesPendentsMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoTotesPendents = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= totesPendentsTotals) {
                infoTotesPendents.put("comunicacions",
                        sortedTotesPendents.subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoTotesPendents.put("comunicacions",
                        sortedTotesPendents.subList(pagina * regPag, totesPendentsTotals));
            }
            infoTotesPendents
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoTotesPendents
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoTotesPendents.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoTotesPendents.put("registresPagina", formRegPorPagina);
            infoTotesPendents.put("totalRegistres", totesPendentsTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoTotesPendents);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Comunicacions Pendents NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ---------- CONSULTA REST NOTIFICACIONS I COMUNICACIONS LLEGIDES NOTIB ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_TOTES_LLEGIDES = "totesllegidesnotib";

    public void consultaTotesLlegides(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, Boolean isGet) {

        try {

            int pagina;
            // int itemsPagina = 10;

            /* Filtre número de registres per pàgina */
            String formRegPorPagina = request.getParameter("registrosPorPagina");

            try {
                pagina = Integer.parseInt(request.getParameter("pageNumber"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }
            List<TransmissioV2> comunicacionsLlegidesList;
            List<TransmissioV2> notificacionsLlegidesList;
            //List<TransmissioV2> llegides = new ArrayList<TransmissioV2>();
            List<ComunicacioNotificacio> sortedTotesLlegides = new ArrayList<ComunicacioNotificacio>();
            ArrayList<ComunicacioNotificacio> cns = new ArrayList<ComunicacioNotificacio>();

            int regPag = Integer.parseInt(formRegPorPagina);
            int comNumero = 0;

            {

                if (notibClientRest == null) {

                    String url = getPropertyRequired(NOTIB_PROPERTY_BASE + "url");
                    String user = getPropertyRequired(NOTIB_PROPERTY_BASE + "user");
                    String pass = getPropertyRequired(NOTIB_PROPERTY_BASE + "pass");

                    notibClientRest = new NotificacioRestClientV2(url, user, pass);
                }

                String nif = userData.getAdministrationID();
                Integer mida = 200;

                /* Filtre dates */
                Date formDataInici;
                Date formDataFi;
                String formDataIniciStr = request.getParameter("dataInici");
                String formDataFiStr = request.getParameter("dataFi");

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

                if (formDataFiStr != null && formDataFiStr != "") {
                    formDataFi = SDF.parse(formDataFiStr);
                } else {
                    formDataFi = cal.getTime();
                }

                if (formDataIniciStr != null && formDataIniciStr != "") {
                    formDataInici = SDF.parse(formDataIniciStr);
                } else {
                    /* Inicialitzam darrers 6 mesos */
                    cal.add(Calendar.MONTH, -6);
                    formDataInici = cal.getTime();
                }

                RespostaConsultaV2 respostaNotificacionsLlegides = notibClientRest.notificacionsLlegidesByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0,
                        mida);
                RespostaConsultaV2 respostaComunicacionsLlegides = notibClientRest.comunicacionsLlegidesByTitular(nif, formDataInici, formDataFi, true, getIdiomaEnumDto(locale.getLanguage()),0,
                        mida);

                comunicacionsLlegidesList = respostaComunicacionsLlegides.getResultat();
                for (TransmissioV2 comunicacio : comunicacionsLlegidesList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(comunicacio);
                    cn.setData(comunicacio.getDataEnviament());
                    cn.setTipus("comunicacio");
                    cns.add(cn);
                }

                notificacionsLlegidesList = respostaNotificacionsLlegides.getResultat();
                for (TransmissioV2 notificacio : notificacionsLlegidesList) {
                    ComunicacioNotificacio cn = new ComunicacioNotificacio();
                    cn.setTransmissio(notificacio);
                    cn.setData(notificacio.getDataEnviament());
                    cn.setTipus("notificacio");
                    cns.add(cn);
                }

                // Ordenam comunicacions a mostrar
                sortedTotesLlegides = cns.stream()
                        .sorted(Comparator.comparing(ComunicacioNotificacio::getData).reversed())
                        .collect(Collectors.toList());

                comNumero = sortedTotesLlegides.size();

            }

            int totesLlegidesTotals = comNumero;
            

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> totesLlegidesMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (totesLlegidesMap == null) {
                totesLlegidesMap = new HashMap<Long, TransmissioV2>();
                request.getSession().setAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB,
                        totesLlegidesMap);
            }

            for (ComunicacioNotificacio t : sortedTotesLlegides) {
                t.getTransmissio()
                        .setOrganGestor(t.getTransmissio().getOrganGestor());
                totesLlegidesMap.put(t.getTransmissio().getId(), t);
            }

            Map<String, Object> infoTotesLlegides = new HashMap<String, Object>();
            if ((pagina + 1) * regPag <= totesLlegidesTotals) {
                infoTotesLlegides.put("comunicacions",
                        sortedTotesLlegides.subList(pagina * regPag, (pagina + 1) * regPag));
            } else {
                infoTotesLlegides.put("comunicacions",
                        sortedTotesLlegides.subList(pagina * regPag, totesLlegidesTotals));
            }
            infoTotesLlegides
                    .put("urldetallbase",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.pendientes.url")
                                    + "#");
            infoTotesLlegides
                    .put("urldetallbase2",
                            getPropertyRequired(
                                    NOTIB_PROPERTY_BASE + "notificaciones.detalle.realizadas.url")
                                    + "#");
            infoTotesLlegides.put("urldetallbase3",
                    getPropertyRequired(NOTIB_PROPERTY_BASE + "comunicaciones.detalle.url") + "#");
            infoTotesLlegides.put("registresPagina", formRegPorPagina);
            infoTotesLlegides.put("totalRegistres", totesLlegidesTotals);

            Gson gson = new Gson();
            String json = gson.toJson(infoTotesLlegides);

//            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {

            log.error("Error llistant Comunicacions Llegides NOTIB: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DETALL Notificacio NOTIB----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String NOTIFICACIONS_NOTIB_DETALL_PAGE = "notibdetall";

    public void notificacioNotibDetall(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet) {

        log.info("\n\n  PAGINA NOTIB DETALL QUERY => ]" + query + "[");

        try {

            @SuppressWarnings("unchecked")
            Map<Long, TransmissioV2> comunicacionsMap = (Map<Long, TransmissioV2>) request.getSession()
                    .getAttribute(SESSIO_CACHE_COMUNICACIONS_MAP_NOTIB);

            if (comunicacionsMap == null) {
                // TODO ERRORS
                response.sendRedirect(absolutePluginRequestPath + NOTIFICACIONS_NOTIB_PAGE + "/0");
                return;
            }

            Long id = Long.parseLong(query.substring(query.lastIndexOf('/') + 1, query.length()));
            ;

            TransmissioV2 n = comunicacionsMap.get(id);

            if (n == null) {
                // TODO ERRORS
                response.sendRedirect(absolutePluginRequestPath + NOTIFICACIONS_NOTIB_PAGE + "/0");
                return;
            }

            Map<String, Object> map = new HashMap<String, Object>();

            // TRADUCCIONS

            String[] traduccions = { "tornar" };

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            map.put("titol", "Notificacio amb ID " + n.getId());
            map.put("urltornar",
                    absolutePluginRequestPath + "/" + NOTIFICACIONS_ESPERA_NOTIB_PAGE + "/0");
            map.put("subtitol", (n.getDescripcio() == null) ? "" : n.getDescripcio());

            List<KeyValue> camps = new ArrayList<NotibCarpetaFrontPlugin.KeyValue>();

            // XYZ ZZZ Falten tracduccions de LABELS

            camps.add(newKeyValue(T("id"), n.getId()));
            camps.add(newKeyValue(T("emisor"), n.getEmisor()));
            camps.add(newKeyValue(T("organGestor"), n.getOrganGestor().getCodi()));

            camps.add(newKeyValue(T("procediment"), n.getProcediment().getCodi()));
            camps.add(newKeyValue(T("numExpedient"), n.getNumExpedient()));
            camps.add(newKeyValue(T("concepte"), n.getConcepte()));
            camps.add(newKeyValue(T("descripcio"), n.getDescripcio()));
            camps.add(newKeyValue(T("dataEnviament"), n.getDataEnviament()));

            String estatStr;
            estatStr = n.getEstat().getCodi();

            camps.add(newKeyValue(T("estat"), estatStr));
            camps.add(newKeyValue(T("dataEstat"), n.getDataEstat()));

            DocumentConsultaV2 document = n.getDocument();
            if (document != null) {
                camps.add(newKeyValue(T("document_nom"), document.getNom()));
                camps.add(newKeyValue(T("document_url"), document.getUrl()));
                camps.add(newKeyValue(T("document_mida"), document.getMida()));
                camps.add(newKeyValue(T("document_mediaType"), document.getMediaType()));
            }

            // Enviament
            processPersona(camps, n.getTitular(), "titular");

            List<PersonaConsultaV2> destinataris = n.getDestinataris();
            if (destinataris != null) {
                int x = 1;
                for (PersonaConsultaV2 persona : destinataris) {
                    processPersona(camps, persona, "destinatari" + x);
                    x++;
                }

            }

            // SubEstat subestat; XYZ ZZZZ MIRAR SI AIXÔ ES FA Bé !!!
            //camps.add(newKeyValue(T("dataSubestat"), n.getDataSubestat()));

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

            String fullPage = encapsulaEnPaginaHtml(absolutePluginRequestPath, locale, map,
                    generat);

            responsePage(request, response, locale, fullPage, absolutePluginRequestPath);

        } catch (Exception e) {
            log.error("Error detall notificació notib: " + e.getMessage(), e);
            errorPage(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "notib_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition", "inline;filename=\""
                    + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage/notib_reactjs_main.js";

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

    protected void processPersona(List<KeyValue> camps, PersonaConsultaV2 titular, String base) {
        if (titular != null) {

            String tipusStr = titular.getTipus().getCodi();

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

    public KeyValue newKeyValue(String k, String v) {

        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }

    }

    public KeyValue newKeyValue(String k, Long v) {

        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }

    }

    public KeyValue newKeyValue(String k, Date v) {

        if (v == null) {
            return new KeyValue(k, "");
        } else {
            return new KeyValue(k, v);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- U T I L I T A T S ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected String encapsulaEnPaginaHtml(String absolutePluginRequestPath, Locale locale,
            Map<String, Object> map, String generat) throws IOException {
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
    
    private IdiomaEnumDto getIdiomaEnumDto(String lang) {
        if(lang.equals("es")) {
            return IdiomaEnumDto.ES;
        }
        return IdiomaEnumDto.CA;
        
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
            this.value = value == null ? "" : value;
        }

        public KeyValue(String key, Long value) {
            super();
            this.key = key;
            this.value = value == null ? "" : String.valueOf(value);
        }

        public KeyValue(String key, Date value) {
            super();
            this.key = key;
            this.value = value == null ? "" : SDF.format(value);
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }

}
