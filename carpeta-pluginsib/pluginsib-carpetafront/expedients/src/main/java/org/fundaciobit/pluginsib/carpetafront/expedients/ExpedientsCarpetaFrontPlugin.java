package org.fundaciobit.pluginsib.carpetafront.expedients;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import es.caib.plugins.arxiu.api.ConsultaFiltre;
import es.caib.plugins.arxiu.api.ConsultaOperacio;
import es.caib.plugins.arxiu.api.ConsultaResultat;
import es.caib.plugins.arxiu.api.ContingutArxiu;
import es.caib.plugins.arxiu.api.ExpedientMetadades;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac.ProcedimentDto;
import org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac.ProcedimientosResponse;
import org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac.UnidadAdministrativa;
import org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac.UnidadesAdministrativasResponse;
import org.fundaciobit.pluginsib.core.utils.MetadataConstants;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author anadal
 */
public class ExpedientsCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String EXPEDIENTS_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "expedients.";
    public static final long ACTUALITZACIO_MAP_ENTITATS_MS = 48 * 60 * 60 * 1000;

    /**
     *
     */
    public ExpedientsCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     */
    public ExpedientsCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public ExpedientsCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontexpedients";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, String parameter,
            IListenerLogCarpeta logCarpeta) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        log.info("ExpedientsCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("ExpedientsCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("ExpedientsCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(URL_REST_SERVICE_CONSULTA_EXPEDIENTS)) {

            consultaRestExpedients(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    @Override
    public boolean isReactComponent() {
        return true;
    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-expedients.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return EXPEDIENTS_PROPERTY_BASE;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- INDEX ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "index.html";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage_expedients/expedients_index.html";

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

            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojs", pathtojs);

            /*
            map.put("usuariNom", userData.getName());
            map.put("usuariLlinatge1", userData.getSurname1()== null?"":userData.getSurname1());
            map.put("usuariLlinatge2", userData.getSurname2()== null?"":userData.getSurname2());
            map.put("usuariDNI", userData.getAdministrationID());
            map.put("usuariMetode", userData.getAuthenticationMethod());
            
            map.put("userData", json.toJson(userData));
            */

            String pathtoservei = absolutePluginRequestPath + "/" + URL_REST_SERVICE_CONSULTA_EXPEDIENTS;

            map.put("pathtoservei", pathtoservei);

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            try {
                response.getWriter().println(generat);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            // XYZ ZZZ
            log.error("Error retornant pàgina principal del Plguin " + this.getTitle(locale) + ": " + e.getMessage(),
                    e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------- JAVASCRIPT REACT -----------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "expedients_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_expedients/expedients_reactjs_main.js";

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

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- CONSULTA REST  --------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String URL_REST_SERVICE_CONSULTA_EXPEDIENTS = "consultarestexpedients";

    public void consultaRestExpedients(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, Boolean isGet) {

        try {

            /* Filtre número de registres per pàgina */
            int elementsPerPagina;
            try {
                elementsPerPagina = Integer.parseInt(request.getParameter("elementsperpagina"));
            } catch (NumberFormatException e) {
                log.error(e.getMessage(), e);
                elementsPerPagina = 5;
                try {
                    String elementsperpagina = getProperty(EXPEDIENTS_PROPERTY_BASE + "elementsperpagina");
                    if (elementsperpagina != null) {
                        elementsPerPagina = Integer.parseInt(elementsperpagina);
                    }
                } catch (Exception e2) {
                    log.error("Valor de la propietat " + getPropertyBase() + EXPEDIENTS_PROPERTY_BASE
                            + "elementsperpagina no es numèric.", e2);
                }
            }

            int pagina;
            try {
                pagina = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                pagina = 0;
            }

            String nif = userData.getAdministrationID();
            ExpedientConsulta consulta = new ExpedientConsulta(locale.getLanguage(), pagina, elementsPerPagina);

            String filtreNom = request.getParameter("filtreNom");
            String filtreCodiSia = request.getParameter("filtreCodiSia");
            String filtreEstat = request.getParameter("filtreEstat");
            String filtreDataInici = request.getParameter("filtreDataInici");
            String filtreDataFi = request.getParameter("filtreDataFi");

            ExpedientResposta resposta = getExpedientsPerAdministrationID(nif, consulta, locale,filtreNom,filtreCodiSia , filtreEstat,
                    filtreDataInici, filtreDataFi);

            Gson gson = new Gson();
            String json = gson.toJson(resposta);

            //            log.info(json);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            response.getWriter().write(json);

        } catch (Exception e) {

            log.error("Error llistant 'Els meus Expedients': " + e.getMessage(), e);
            errorRest(e.getMessage(), e, request, response, absolutePluginRequestPath, locale);

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------- PLUGIN ARXIU ---------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public IArxiuPlugin instanticatePluginArxiu() throws Exception {

        IArxiuPlugin pluginInstance = null;

        String classArxiu = getPropertyRequired(EXPEDIENTS_PROPERTY_BASE + "arxiuclass");

        Properties properties = this.getPluginProperties();

        final String base = this.getPropertyKeyBase() + EXPEDIENTS_PROPERTY_BASE;

        pluginInstance = (IArxiuPlugin) PluginsManager.instancePluginByClassName(classArxiu, base, properties);

        if (pluginInstance == null) {
            // XYZ ZZZ
            throw new Exception(
                    "No puc instanciar el Plugin d'Arxiu dins del Plugin de Carpeta Front d'Expedients (Classe => "
                            + classArxiu + ")");
        }

        return pluginInstance;

    }

    public ExpedientResposta getExpedientsPerAdministrationID(String nif, ExpedientConsulta consulta, Locale locale,
            String filtreNom, String filtreCodiSia, String filtreEstat, String filtreDataInici, String filtreDataFi) throws Exception {

        IArxiuPlugin arxiu = instanticatePluginArxiu();

        //Llistat de filtres
        List<ConsultaFiltre> filterList = new ArrayList<ConsultaFiltre>();

        //S'introdueix el primer filtre base (per DNI de l'usuari)
        {
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.ENI_INTERESADOS_EXP); // == "eni:interesados_exp"
            filter.setOperacio(ConsultaOperacio.CONTE);
            filter.setValorOperacio1(nif);
            filterList.add(filter);
        }
        
        //S'introdueixen la resta de filtres:
        //System.out.println("XYZ ZZZ filtreCodiSia: " + filtreCodiSia);
        //System.out.println("XYZ ZZZ filtreEstat: " + filtreEstat);
        //System.out.println("XYZ ZZZ filtreDataInici: " + filtreDataInici);
        //System.out.println("XYZ ZZZ filtreDataFi: "+ filtreDataFi);
        
        //XYZ ZZZ TODO Trobar metadada que filtri pel nom d'expedient.
        //Filtre Nom:
        /*if(filtreNom != null && !filtreNom.isBlank()){
            System.out.println("XYZ ZZZ FiltreNom2: " + filtreNom);
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.);
            filter.setOperacio(ConsultaOperacio.CONTE);
            filter.setValorOperacio1(filtreNom);
            filterList.add(filter);
        }*/
        
      //Filtre Codi SIA:
        if(filtreCodiSia != null && !filtreCodiSia.isBlank()){
            System.out.println("XYZ ZZZ FiltreNom2: " + filtreCodiSia);
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.ENI_ID_TRAMITE); // Codi SIA
            filter.setOperacio(ConsultaOperacio.IGUAL);
            filter.setValorOperacio1(filtreCodiSia);
            filterList.add(filter);
        }
        
        // filtreEstat
        if(filtreEstat != null && !filtreEstat.isBlank()){
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.ENI_ESTADO_EXP);
            filter.setOperacio(ConsultaOperacio.CONTE);
            filter.setValorOperacio1(filtreEstat);
            filterList.add(filter);
        }
       
      
        //XYZ ZZZ TODO Pareix que els filtres de dates no funcionen correctament. 
        //             Els parametres filtreDataInici i filtreDataFi estan en el format correcte?
        //             La logica de "Menor" "Major" pot funcionar de manera diferent? Invertir no funciona.
        /*
        //filtreDataInici
        if(filtreDataInici != null && !filtreDataInici.isBlank()){
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.ENI_FECHA_INICIO);
            filter.setOperacio(ConsultaOperacio.MENOR);
            filter.setValorOperacio1(filtreDataInici);
            filterList.add(filter);
        }
        
        // filtreDataFi
        if(filtreDataFi != null && !filtreDataFi.isBlank()){
            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(MetadataConstants.ENI_FECHA_INICIO);
            filter.setOperacio(ConsultaOperacio.MAJOR);
            filter.setValorOperacio1(filtreDataFi);
            filterList.add(filter);
        }
        */

        int i = 1;
        do {
            //Variables per extreure la info de cada filtre

            final String filtrePropertyBase = EXPEDIENTS_PROPERTY_BASE + "filtre." + i;
            final String meta = getProperty(filtrePropertyBase + ".metadada");

            if (meta == null || meta.trim().length() == 0) {
                break;
            }

            ConsultaFiltre filter = new ConsultaFiltre();
            filter.setMetadada(meta);
            filter.setOperacio(getTipusConsultaOperacio(getProperty(filtrePropertyBase + ".operacio")));
            filter.setValorOperacio1(getProperty(filtrePropertyBase + ".valor1"));
            filter.setValorOperacio2(getProperty(filtrePropertyBase + ".valor2"));
            filterList.add(filter);

            i++;

        } while (true);

        log.info("consulta.getPagina()  => " + consulta.getPagina());
        log.info("consulta.getElementsPerPagina()  => " + consulta.getElementsPerPagina());

        ConsultaResultat resultat = arxiu.expedientConsulta(filterList, consulta.getPagina(),
                consulta.getElementsPerPagina());

        List<ContingutArxiu> resultats = resultat.getResultats();

        //System.out.println("#resultats  => " + resultats.size());

        List<ExpedientInfo> expedients = new ArrayList<ExpedientInfo>();

        for (ContingutArxiu ac : resultats) {

            ExpedientMetadades em = ac.getExpedientMetadades();

            {

                ExpedientInfo ei = new ExpedientInfo();
                ei.setExpedientNom(ac.getNom());
                ei.setExpedientDesc(ac.getDescripcio());

                if (em != null) {

                    String codiSia = em.getClassificacio();
                    ei.setCodiSia(codiSia);
                    String nomProcediment = null;
                    try {
                        nomProcediment = this.findProcedimentAmbCodiSia(codiSia, consulta.getLanguage());
                    } catch (Exception e) {
                        log.error("Error en el procedient de Rolsac. No s'ha pogut obtenir el nom del procediment "
                                + codiSia + ": " + e.getMessage(), e);
                    }

                    if (nomProcediment == null || nomProcediment.trim().length() == 0) {
                        ei.setNomProcediment(getTraduccio("nomprocediment.sense", locale, codiSia));
                    } else {
                        ei.setNomProcediment(nomProcediment);
                    }

                    String expEstat = em.getEstat().toString();
                    if ("E01".equals(expEstat)) {
                        ei.setExpedientEstat(this.getTraduccio("estat.expedient.1", locale));
                    } else if ("E02".equals(expEstat)) {
                        ei.setExpedientEstat(this.getTraduccio("estat.expedient.2", locale));
                    } else if ("E03".equals(expEstat)) {
                        ei.setExpedientEstat(this.getTraduccio("estat.expedient.3", locale));
                    } /*else {
                        ei.setExpedientEstat(this.getTraduccio("estat.expedient.0", locale));
                    }*/

                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

                    ei.setExpedientObertura(df.format(em.getDataObertura()));

                    List<String> organsList = em.getOrgans();

                    if (organsList != null && organsList.size() != 0) {

                        List<String> organsNomList = new ArrayList<String>();

                        for (String organDir3 : organsList) {
                            organsNomList.add(getNomByDir3(organDir3, consulta.getLanguage()));
                        }
                        ei.setExpedientOrgans(organsNomList);
                    }
                    expedients.add(ei);
                }
            }
        }

        ExpedientResposta resposta = new ExpedientResposta();

        resposta.setElementsPerPagina(consulta.getElementsPerPagina());
        resposta.setPaginaActual(resultat.getPaginaActual());
        resposta.setRegistresRetornats(resultat.getNumRetornat());
        resposta.setTotalPagines(resultat.getNumPagines());
        resposta.setTotalRegistres(resultat.getNumRegistres());
        resposta.setExpedients(expedients);

        /*
        { 
            log.info("\n\n   ============ PAGINACIO ===============");
            log.info(" + resultat.getPaginaActual() => " + resposta.getPaginaActual() );
            log.info(" + resultat.getTotalElements() => " + resposta.getTotalRegistres() );
            log.info(" + resultat.getTotalPagines() => " + resposta.getTotalPagines());
            log.info(" + resultat.getElementsPerPagina() => " + resposta.getElementsPerPagina());
            log.info(" + resultat.getElementsRetornats() => " + resposta.getRegistresRetornats());
            log.info(" + resposta.getExpedients().size() => " + resposta.getExpedients().size());
        
            }
        */

        return resposta;

    }

    protected static TreeMap<String, TreeMap<String, String>> unidadesCache = new TreeMap<String, TreeMap<String, String>>();
    protected static TreeMap<String, Long> t_darrera_actualitzacio_dir3 = new TreeMap<String, Long>();

    public String getNomByDir3(String codiDir3, String language) {

        try {

            if (t_darrera_actualitzacio_dir3.get(language) == null) {
                t_darrera_actualitzacio_dir3.put(language, (long) 0);
            }
            long timeLastDir3Update = System.currentTimeMillis() - t_darrera_actualitzacio_dir3.get(language);

            TreeMap<String, String> unidadesCacheLng = unidadesCache.get(language);

            if (unidadesCacheLng == null || timeLastDir3Update > ACTUALITZACIO_MAP_ENTITATS_MS) {

                unidadesCacheLng = getNomUnidadesAdministrativas(language);

                t_darrera_actualitzacio_dir3.put(language, System.currentTimeMillis());

                unidadesCache.put(language, unidadesCacheLng);
            }

            String nom = unidadesCacheLng.get(codiDir3);

            if (nom == null) {
                nom = codiDir3;
            }

            return nom;

        } catch (Exception e) {

            log.error("Error esbrinant nom d´unidata amb codiDir3 " + codiDir3);

            return codiDir3;
        }

    }

    protected TreeMap<String, String> getNomUnidadesAdministrativas(String lang) throws Exception {

        final String servicio = "unidades_administrativas";

        final String params = "?lang=" + lang + "&filtroPaginacion={\"page\":\"1\",\"size\":\"3000\"}";

        TreeMap<String, String> map = new TreeMap<String, String>();

        UnidadesAdministrativasResponse response;
        response = callApiRolsac(servicio, params, UnidadesAdministrativasResponse.class);

        if (response != null) {

            List<UnidadAdministrativa> unidades = response.getResultado();

            for (UnidadAdministrativa ua : unidades) {

                if (ua.getCodigoDIR3() != null) {
                    map.put(ua.getCodigoDIR3(), ua.getNombre());
                }
            }
        }

        return map;

    }

    public String findProcedimentAmbCodiSia(String codiSia, String lang) throws Exception {
        /*  System.out.println("Consulta del procediment pel codi SIA (" +
            "codiSia=" + codiSia + ")");*/

        try {

            final String servicio = "procedimientos";

            final String params = "?lang=" + lang + "&filtro={\"codigoSia\":\"" + codiSia
                    + "\",\"estadoSia\":\"A\",\"buscarEnDescendientesUA\":\"1\"}";

            ProcedimientosResponse response;
            response = callApiRolsac(servicio, params, ProcedimientosResponse.class);

            if (response != null && response.getStatus().equals("200")) {
                if (response.getResultado() != null && !response.getResultado().isEmpty()) {
                    for (ProcedimentDto procediment : response.getResultado()) {
                        //System.out.println("Consulta del procediment pel codi SIA (" + procediment.getCodigoSia() + " ) " + procediment.getNombre());
                        return (String) procediment.getNombre();
                    }
                } else {

                    return null;
                }

            } else {
                return null;
                //          throw new SistemaExternException(
                //                  "No s'han pogut consultar el procediment de ROLSAC (" +
                //                  "codiSia=" + codiSia + "). Resposta rebuda amb el codi " + response.getStatus());
            }
            // "No s'ha trobat el procediment"
            return null;

        } catch (Exception ex) {
            throw new Exception(
                    "Error consultant el procediment de ROLSAC (" + "codiSia=" + codiSia + "): " + ex.getMessage(), ex);
        }

    }

    protected <T> T callApiRolsac(String servicio, String params, Class<T> classe)
            throws Exception, IOException, JsonParseException, JsonMappingException {
        T response;
        String url = getPropertyRequired(EXPEDIENTS_PROPERTY_BASE + "rolsac.url") + servicio;
        // XYZ ZZZ CACHE ???
        Client jerseyClient = null;
        if (jerseyClient == null) {
            jerseyClient = new Client();

            jerseyClient.setConnectTimeout(getIntegerValue(EXPEDIENTS_PROPERTY_BASE + "rolsac.connecttimeout"));
            jerseyClient.setReadTimeout(getIntegerValue(EXPEDIENTS_PROPERTY_BASE + "rolsac.readtimeout"));
        }
        final String username = getPropertyRequired(EXPEDIENTS_PROPERTY_BASE + "rolsac.username");
        final String password = getPropertyRequired(EXPEDIENTS_PROPERTY_BASE + "rolsac.password");
        jerseyClient.addFilter(new HTTPBasicAuthFilter(username, password));
        ClientResponse resp = jerseyClient.resource(url).accept("application/json").type("application/json")
                .post(ClientResponse.class, params);
        String json = resp.getEntity(String.class);

        //System.out.println("JSON " + json);
        //jerseyClient.addFilter(new LoggingFilter(System.out));
        ObjectMapper mapper = new ObjectMapper();
        // Permet rebre un sol objecte en el lloc a on hi hauria d'haver una llista.
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        // Mecanisme de deserialització dels enums
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        // Per a no serialitzar propietats amb valors NULL
        mapper.setSerializationInclusion(Include.NON_NULL);
        // No falla si hi ha propietats que no estan definides a l'objecte destí
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        response = mapper.readValue(json, TypeFactory.defaultInstance().constructType(classe));
        return response;
    }

    protected Integer getIntegerValue(String property) {

        String ctStr = getProperty(property);
        if (ctStr == null) {
            try {
                return Integer.parseInt(ctStr);
            } catch (Exception e) {

            }
        }
        return null;
    }

    private ConsultaOperacio getTipusConsultaOperacio(String operacio) {
        switch (operacio) {
            case "IGUAL":
                return ConsultaOperacio.IGUAL;
            case "CONTE":
                return ConsultaOperacio.CONTE;
            case "MENOR":
                return ConsultaOperacio.MENOR;
            case "MAJOR":
                return ConsultaOperacio.MAJOR;
            case "ENTRE":
                return ConsultaOperacio.ENTRE;
            case "NO_IGUAL":
                return ConsultaOperacio.NO_IGUAL;
            case "NO_CONTE":
                return ConsultaOperacio.NO_CONTE;
        }

        return null;

    }

}
