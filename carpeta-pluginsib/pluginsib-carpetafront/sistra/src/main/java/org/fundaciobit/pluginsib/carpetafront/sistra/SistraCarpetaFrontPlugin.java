package org.fundaciobit.pluginsib.carpetafront.sistra;

import es.caib.carpeta.commons.utils.BasicAuthenticator;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.carpeta.pluginsib.carpetafront.api.UserDataRepresentative;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramiteFinalizado;
import es.caib.sistramit.rest.api.externa.v1.RInfoTicketAcceso;
import es.caib.sistramit.rest.api.externa.v1.RRepresentanteInfo;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RTramiteFinalizado;
import es.caib.sistramit.rest.api.externa.v1.RUsuarioAutenticadoInfo;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.FiltroElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ObjectFactory;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TiposElementoExpediente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.regwebdetallcomponent.RegwebDetallComponent;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPFaultException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * 
 * 
 * @author anadal
 */
public class SistraCarpetaFrontPlugin extends RegwebDetallComponent {

    public static final String SISTRA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra.";
    public static final String SISTRA1_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra1.";
    public static final String SISTRA2_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "sistra2.";

    /**
     * 
     */
    public SistraCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public SistraCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public SistraCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontsistra";
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

        super.registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public boolean isReactComponent() {
        //        try {
        //            return ("true".equals(getPropertyRequired(SISTRA_PROPERTY_BASE + "isreact")));
        //        } catch (Exception e) {
        //            return false;
        //        }
        return true;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        if (isDevelopment()) {
            log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
            log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                    + userData.getAdministrationID());
            log.info("SistraCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                    + administrationEncriptedID);
        }

        if (query.startsWith(OBTENER_TIQUET)) {

            obtenerTiquetSistra2(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE)) {

            consultaTramits(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // -------------------- INDEX -----------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "sistra_index.html";
    //    protected static final String DETALL_REACT_PAGE = "detallRegistreJson";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage/sistra_index.html";

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

            String pathtojsSistra = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojsSistra", pathtojsSistra);

            String pathtoservei = absolutePluginRequestPath + "/" + URL_REST_SERVICE;

            map.put("pathtoservei", pathtoservei);

            String detallpathtoservei = absolutePluginRequestPath + "/" + RegwebDetallComponent.DETALL_REACT_PAGE;

            map.put("detallpathtoservei", detallpathtoservei);

            String mesosFiltre = getProperty(SISTRA_PROPERTY_BASE + "mesos", "6");

            map.put("mesosFiltre", mesosFiltre);

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

    protected static final String URL_REST_SERVICE = "consultaTramits";

    public void consultaTramits(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, Boolean isGet) {

        String formEstat;

        formEstat = request.getParameter("estat");
        //        int pagina;
        //        pagina = Integer.parseInt(request.getParameter("pageNumber"));
        //        log.info("pageNumber: " + pagina);

        /* Filtre número de registres per pàgina */
        //        String formRegPorPagina = request.getParameter("registrosPorPagina");

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //sdf.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

            List<TramitePersistenteGenerico> tramitesGenericos = new ArrayList<TramitePersistenteGenerico>();

            if (formEstat == null) {
                throw new Exception(getTraduccio("error.estat.null", locale));
            }
            
            
            String mes = getProperty(SISTRA_PROPERTY_BASE+"mesos");
            int mesos = Integer.parseInt(mes);
            
            
            Date formDataFi = new Date();
            Calendar primeraData = Calendar.getInstance();
            
            primeraData.setTime(formDataFi);
            primeraData.add(Calendar.MONTH, -mesos);
            Date formDataInici = primeraData.getTime();
            
            //String missatgeError = "";

            /* SISTRA1 */
            try {
                List<TramitePersistenteGenerico> tramits = null;
                if (isDevelopment()) {
                    tramits = getTramitsDebug(formDataInici, formDataFi, userData.getAdministrationID(), formEstat,
                            locale, absolutePluginRequestPath);
                } else {
                    tramits = getTramits(formDataInici, formDataFi, userData.getAdministrationID(), formEstat, locale,
                            absolutePluginRequestPath);
                }

                log.info(" Afegits " + tramits.size() + " tramits SISTRA 1 al llistat ...");

                tramitesGenericos.addAll(tramits);

            } catch (SOAPFaultException e) {

                // Controlar excepció Sistra1 dintre plugin de tramitació #478
                if (Configuracio.isCAIB() && e.getMessage().contains("es.caib.zonaper.modelInterfaz.ExcepcionPAD")) {

                    final String msg = "Sistra1 Ignoram Error: " + e.getMessage() + " ...\n";
                    log.error(msg);
                } else {
                    final String msg = "Sistra1 Error: " + e.getMessage() + "\n";
                    log.error(msg, e);
                    throw new Exception(msg, e);
                }

            }

            /* SISTRA2 */
            try {
                List<TramitePersistenteGenerico> tramits = null;
                if (isDevelopment()) {
                    tramits = obtenerTramitesDebug(userData.getAdministrationID(), formDataInici, formDataFi, formEstat,
                            absolutePluginRequestPath);
                } else {
                    tramits = obtenerTramites(userData.getAdministrationID(), formDataInici, formDataFi, formEstat,
                            absolutePluginRequestPath);
                }
                tramitesGenericos.addAll(tramits);
                log.info(" Afegits " + tramits.size() + " tramits SISTRA 2 al llistat ...");
            } catch (javax.ws.rs.client.ResponseProcessingException e) {
                final String msg = "Sistra2 - No hi ha tramits:" + e.getMessage();
                log.error(msg, e);
                throw new Exception(msg, e);
            } catch (Exception e) {
                final String msg = "Error desconegut en Sistra2: " + e.getMessage();
                log.error(msg, e);
                throw new Exception(msg, e);
            }

            // Ordenam tràmits a mostrar

            List<TramitePersistenteGenerico> sortedTramit = new ArrayList<TramitePersistenteGenerico>(
                    tramitesGenericos);

            sortedTramit = tramitesGenericos.stream()
                    .sorted(Comparator.comparing(TramitePersistenteGenerico::getFechaInicioDate).reversed())
                    .collect(Collectors.toList());

            List<TramitePersistenteGenerico> tramitsPagina = sortedTramit;
            /*List<TramitePersistenteGenerico> tramitsPagina = new ArrayList<TramitePersistenteGenerico>();
            int calcul = (pagina * Integer.parseInt(formRegPorPagina)) + Integer.parseInt(formRegPorPagina);
            
            
            
            if (sortedTramit.size() > 0) {
                if (calcul < sortedTramit.size()) {
                    tramitsPagina = sortedTramit.subList(pagina * Integer.parseInt(formRegPorPagina),
                            (pagina * Integer.parseInt(formRegPorPagina)) + Integer.parseInt(formRegPorPagina));
                } else {
                    tramitsPagina = sortedTramit.subList(pagina * Integer.parseInt(formRegPorPagina),
                            sortedTramit.size());
                }
            }
            */

            Map<String, Object> infoTramits = new HashMap<String, Object>();
            infoTramits.put("tramits", tramitsPagina);
            //            infoTramits.put("totalRegistres", sortedTramit.size());
            //            infoTramits.put("registresPagina", formRegPorPagina);

            Gson gson = new Gson();
            String json = gson.toJson(infoTramits);

            if (isDevelopment()) {
                //log.info("TRAMITS SISTRA JSON:\n" + json);
            }

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error generant pàgina bàsica: " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- U T I L S  ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public boolean isDevelopment() {
        return "true".equals(getProperty(SISTRA_PROPERTY_BASE + "development"));
    }

    public String getWeb() throws Exception {
        return getPropertyRequired(SISTRA1_PROPERTY_BASE + "web");
    }

    /* SISTRA 1 */
    /**
     * Mètode que retorna els tràmits sense acabar de Sistra1
     * 
     * @param fechaInicio
     * @param fechaFin
     * @param documento
     * @return List<TramitePersistenteGenerico>
     * @throws Exception
     */
    protected List<TramitePersistenteGenerico> getTramits(Date fechaInicio, Date fechaFin, String documento,
            String finalizado, Locale locale, String absolutePluginRequestPath) throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);

        List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();

        // Tramits acabats o inacabats si son del tipus PREREGISTRO o PREENVIO
        {
            final ObjectFactory objectFactoryElement = new ObjectFactory();
            final FiltroElementosExpediente filtroElementosExpediente = objectFactoryElement
                    .createFiltroElementosExpediente();

            // Els tràmits de tipos Registre es mostren a través del plugin de REGWEB #231
            // Els tràmits de tipus Comunicacion o notificacion es mostren a través del
            // plugin de NOTIB #231
            // Només mostram els de tipus ENVIO, PREENVIO i PREREGISTRO
            List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();

            //            if (finalizado.equals("R")) {
            if (finalizado.equals("S")) {
                coms.add(TipoElementoExpediente.REGISTRO);
            } else if (finalizado.equals("P")) {
                coms.add(TipoElementoExpediente.PREENVIO);
                coms.add(TipoElementoExpediente.PREREGISTRO);
            } else {
                coms.add(TipoElementoExpediente.ENVIO);
                coms.add(TipoElementoExpediente.PREENVIO);
                coms.add(TipoElementoExpediente.PREREGISTRO);
                coms.add(TipoElementoExpediente.REGISTRO);
            }

            TiposElementoExpediente teess = new TiposElementoExpediente();
            for (TipoElementoExpediente tee : coms) {
                teess.getTipo().add(tee);
            }

            filtroElementosExpediente.setNif(documento);

            filtroElementosExpediente.setFechaInicio(objectFactoryElement.createFiltroElementosExpedienteFechaInicio(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio)));
            filtroElementosExpediente.setFechaFin(objectFactoryElement.createFiltroElementosExpedienteFechaFin(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy)));
            filtroElementosExpediente.setIdioma(locale.getLanguage());
            filtroElementosExpediente.setTipos(teess);

            // long num =
            // backofficeFacade.obtenerTotalElementosExpediente(filtroElementosExpediente);
            // int pagina = 0;
            // int tamPagina = ( num > 0 ) ? (int) num : 0;

            ElementosExpediente tramitesAcabados = backofficeFacade
                    .obtenerElementosExpediente(filtroElementosExpediente, null, null); // pagina, tamPagina

            for (ElementoExpediente item : tramitesAcabados.getElemento()) {

                TramitePersistenteGenerico tpg = new TramitePersistenteGenerico(item, 1);

                Boolean estaPendent = item.isPendiente();

                // Casuística tràmits SISTRA1 #317
                tpg.setMostraModal(item.getTipo() != TipoElementoExpediente.ENVIO && estaPendent);

                // Si es tipus Registro => redirigim a pagina de detall Registre
                if (item.getTipo() == TipoElementoExpediente.REGISTRO) {
                    tpg.setUrl(tpg.getNumero());
                }

                if (finalizado.equals("A") || ((((estaPendent && finalizado.equals("N") && !tpg.esMostraModal())
                        || (!estaPendent && finalizado.equals("S")))
                        && item.getTipo() != TipoElementoExpediente.REGISTRO)) ||
                //                		(finalizado.equals("R") && item.getTipo() == TipoElementoExpediente.REGISTRO) ||
                        (finalizado.equals("S") && item.getTipo() == TipoElementoExpediente.REGISTRO)
                        || (finalizado.equals("P") && estaPendent)) {
                    tramits.add(tpg);
                }
            }

        }

        // Tràmits no acabats
        if (!finalizado.equals("S") && !finalizado.equals("P") && !finalizado.equals("R")) {
            TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));

            TramitePersistenteGenerico tpg;

            for (TramitePersistente tp : tramites.getTramitePersistente()) {
                tpg = new TramitePersistenteGenerico(tp, 1);
                tramits.add(tpg);
            }
        }

        return tramits;
    }

    private List<TramitePersistenteGenerico> getTramitsDebug(Date formDataInici, Date formDataFi,
            String administrationID, String finalizado, Locale locale, String absolutePluginRequestPath)
            throws Exception {

        List<TramitePersistenteGenerico> tramits = this.getTramits(formDataInici, formDataFi, administrationID,
                finalizado, locale, absolutePluginRequestPath);

        if (tramits == null || tramits.isEmpty()) {
            log.info(" TRAMITS SISTRA1 NULL o EMPTY: " + tramits);
        } else {
            int x = 1;
            for (TramitePersistenteGenerico tp : tramits) {
                log.info(" -------------  TRAMIT [" + x + " ] SISTRA1 -------------------");
                log.info("tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
                log.info("tp.getIdTramite() => " + tp.getIdTramite());
                log.info("tp.getUrl() => " + tp.getUrl());
                log.info("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
                log.info("tp.getFechaInicio() => " + tp.getFechaInicio());
                log.info("tp.isPendiente() => " + tp.isPendiente());
                log.info("tp.getVersionSistra() => " + tp.getVersionSistra());
                log.info("tp.getVersionTramite(); => " + tp.getVersionTramite());
                log.info("tp.getIdioma() => " + tp.getIdioma());
                log.info("tp.getTipo() => " + tp.getTipo());
                log.info("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
                if (tp.esRegistrado())
                    log.info("tp.getNumero() => " + tp.getNumero());
                x++;
            }
        }
        return tramits;
    }

    private BackofficeFacade getBackofficeFacade() throws Exception {

        final String sistraUrl = getPropertyRequired(SISTRA1_PROPERTY_BASE + "url");
        final String username = getPropertyRequired(SISTRA1_PROPERTY_BASE + "user");
        final String password = getPropertyRequired(SISTRA1_PROPERTY_BASE + "pass");

        final URL wsdl = new URL(sistraUrl + "?wsdl");
        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade = service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, sistraUrl);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        return backofficeFacade;
    }

    /* SISTRA2 */
    /**
     * Mètode per obtenir els tràmits pendents de Sistra2
     * 
     * @param documento
     * @param fechaInicio
     * @param fechaFin
     * @return List<TramitePersistenteGenerico>
     * @throws Exception
     */
    public List<TramitePersistenteGenerico> obtenerTramites(String documento, Date fechaInicio, Date fechaFin,
            String finalizado, String absolutePluginRequestPath) throws Exception {

        Client client = getClientBasicAuthenticator();

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(fechaInicio);
        filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(fechaFin, 1));
        filtroPer.setNif(documento);

        List<RTramitePersistencia> tramites = client
                .target(getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/tramite")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(filtroPer, MediaType.APPLICATION_JSON),
                        new GenericType<List<RTramitePersistencia>>() {
                        });

        List<TramitePersistenteGenerico> tramits = new ArrayList<TramitePersistenteGenerico>();

        for (RTramitePersistencia tp : tramites) {
            if (finalizado.equals("A") || finalizado.equals("N")) {
                TramitePersistenteGenerico tpg = new TramitePersistenteGenerico(tp, 2);
                tpg.setUrl(
                        absolutePluginRequestPath + "/" + OBTENER_TIQUET + "?tramite=" + tpg.getIdSesionTramitacion());
                tramits.add(tpg);
            }
        }

        // tramiteFinalizado

        final RFiltroTramiteFinalizado filtroTraFin = new RFiltroTramiteFinalizado();
        filtroTraFin.setFechaDesde(fechaInicio);
        filtroTraFin.setFechaHasta(DateUtils.sumarRestarDiasFecha(fechaFin, 1));
        filtroTraFin.setNif(documento);

        Client clientCaibTer = getClientBasicAuthenticator();

        List<RTramiteFinalizado> tramitesFinalizados = clientCaibTer
                .target(getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/tramiteFinalizado")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(filtroTraFin, MediaType.APPLICATION_JSON),
                        new GenericType<List<RTramiteFinalizado>>() {
                        });

        if (tramitesFinalizados != null && !tramitesFinalizados.isEmpty()) {
            for (RTramiteFinalizado tf : tramitesFinalizados) {
                //        		if ((finalizado.equals("A") || finalizado.equals("R"))) {
                if ((finalizado.equals("A") || finalizado.equals("S"))) {
                    TramitePersistenteGenerico tpg = new TramitePersistenteGenerico(tf, 2);
                    tpg.setUrl(tpg.getNumero());
                    tramits.add(tpg);
                }
            }
        }

        return tramits;
    }

    private List<TramitePersistenteGenerico> obtenerTramitesDebug(String documento, Date fechaInicio, Date fechaFin,
            String finalizado, String absolutePluginRequestPath) throws Exception {

        List<TramitePersistenteGenerico> tramits = this.obtenerTramites(documento, fechaInicio, fechaFin, finalizado,
                absolutePluginRequestPath);

        if (tramits == null || tramits.isEmpty()) {
            tramits = new ArrayList<TramitePersistenteGenerico>();
            log.info("SISTRA2 TRAMITES NULL o EMPTY: " + tramits);
        } else {
            int x = 1;
            /*for (TramitePersistenteGenerico tp : tramits) {
                log.info(" -------------  TRAMITE [" + x + " ] SISTRA 2-------------------");
                log.info("tp.getIdTramite() => " + tp.getIdTramite());
                log.info("tp.getDescripcionTramite() => " + tp.getDescripcionTramite());
                log.info("tp.getVersionTramite(); => " + tp.getVersionTramite());
                log.info("tp.getIdioma() => " + tp.getIdioma());
                log.info("tp.getFechaInicio() => " + tp.getFechaInicio());
                log.info("tp.getFechaUltimoAcceso() => " + tp.getFechaUltimoAcceso());
                log.info("tp.getIdSesionTramitacion() => " + tp.getIdSesionTramitacion());
                log.info("tp.getTipo() => " + tp.getTipo());
                log.info("tp.getVersionSistra() => " + tp.getVersionSistra());
                log.info("tp.getUrl() => " + tp.getUrl());
                log.info("tp.isPendiente() => " + tp.isPendiente());
                log.info("tp.getNumero() => " + tp.getNumero());
                x++;
            }*/
        }
        return tramits;

    }

    private Client getClientBasicAuthenticator() throws Exception {

        final String username = getPropertyRequired(SISTRA2_PROPERTY_BASE + "user");
        final String password = getPropertyRequired(SISTRA2_PROPERTY_BASE + "pass");

        return ClientBuilder.newClient().register(new BasicAuthenticator(username, password))
                .register(EntityLoggingFilter.class).register(JsonbConfigurator.class);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "sistra_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage/sistra_reactjs_main.js";

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

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- A C C E S O T I Q U E T S I S T R A 2 ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String OBTENER_TIQUET = "tiquetAcceso";

    public void obtenerTiquetSistra2(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        String tramitID = request.getParameter("tramite");
        log.error("Obtenint tiquet accès Sistra 2: TRAMITEID => " + tramitID);
        try {

            if (isDevelopment()) {
                log.info("==================  OBTENER TIQUET ACCESO ==========================");
                log.info("REQUEST TRAMITE: " + tramitID);
                log.info("Nombre: " + userData.getName());
                log.info("Apellido1: " + userData.getSurname1());
                log.info("Apellido2: " + userData.getSurname2());
                log.info("Nif: " + userData.getAdministrationID());
                log.info("MetodoAutentication: " + userData.getAuthenticationMethod());
                log.info("Qaa: " + userData.getQaa());
                log.info("=====================================================================");
            }

            String url = obtenerTiquetAccesoSistra2(tramitID, userData);
            response.sendRedirect(url);

        } catch (Exception e) {
            log.error("Error obtenint tiquet accès Sistra 2: " + e.getMessage(), e);
        }
    }

    public String obtenerTiquetAccesoSistra2(String idSesionTramitacion, UserData userData) throws Exception {

        Client client = getClientBasicAuthenticator();

        RInfoTicketAcceso infoTicket = new RInfoTicketAcceso();
        infoTicket.setIdSesionTramitacion(idSesionTramitacion);

        RUsuarioAutenticadoInfo usuarioInfo = new RUsuarioAutenticadoInfo();
        usuarioInfo.setNombre(userData.getName());
        usuarioInfo.setApellido1(userData.getSurname1());
        usuarioInfo.setApellido2(userData.getSurname2());
        usuarioInfo.setEmail("");
        usuarioInfo.setUsername(userData.getAdministrationID());
        usuarioInfo.setNif(userData.getAdministrationID());
        usuarioInfo.setAutenticacion("c");
        usuarioInfo.setMetodoAutenticacion(userData.getAuthenticationMethod());
        usuarioInfo.setQaa("" + userData.getQaa());

        UserDataRepresentative reprentative = userData.getRepresentative();
        if (reprentative != null) {

            log.info("\n\n\n XXXXXXXXXXXXX PASSA PER REPRESENTATIVE \n\n\n");

            RRepresentanteInfo representant = new RRepresentanteInfo();

            representant.setNombre(reprentative.getName());
            representant.setApellido1(reprentative.getSurname1());
            representant.setApellido2(reprentative.getSurname2());
            representant.setEmail(null);
            representant.setNif(reprentative.getAdministrationID());

            usuarioInfo.setRepresentanteInfo(representant);
        }

        infoTicket.setUsuarioAutenticadoInfo(usuarioInfo);

        String url = client.target(getPropertyRequired(SISTRA2_PROPERTY_BASE + "url") + "/ticketAcceso")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(infoTicket, MediaType.APPLICATION_JSON), String.class);

        // XYZ ZZZ
        log.info("URL TIQUET: " + url);

        if (isDevelopment()) {
            log.info("URL TIQUET: " + url);
        }

        return url;
    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-sistra.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return SISTRA_PROPERTY_BASE;
    }

    @Override
    public String getDetalleTitle(Locale locale) {
        return getTraduccio("detalletitle", locale);
    }

    @Override
    public String getEntidad() throws Exception {
        return getPropertyRequired(SISTRA_PROPERTY_BASE + "entidad");
    }

}
