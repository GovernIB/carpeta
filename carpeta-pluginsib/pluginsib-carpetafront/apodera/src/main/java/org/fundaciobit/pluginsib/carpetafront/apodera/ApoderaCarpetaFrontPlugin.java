package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.net.URL;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;

import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ConsultaApoderamientosResponse;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ConsultaAvanzadaPortType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ConsultaAvanzadaService;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderadoCompletoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderadoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosApoderamientoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosAuditoriaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosConsultaApoderamientoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosConsultaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosPoderdanteCompletoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.DatosPoderdanteType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ErrorType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ObjectFactory;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.Organismo;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.Organismo2;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.OrganismoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.OrganismoType2;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PersonaFisicaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PersonaJuridicaType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.PeticionConsulta;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.Procedimiento2;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ProcedimientoType2;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.RespuestaConsulta;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.TipoApoderamientoCompletoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.TipoApoderamientoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.TramiteType2;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandler;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandlerCertificate;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractPinbalCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author jpernia
 * @author anadal
 */

public class ApoderaCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String APODERA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "apodera.";

    protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    protected static final SimpleDateFormat SDF_VIGENCIA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     *
     */
    public ApoderaCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public ApoderaCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public ApoderaCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafront.apodera";
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

//        log.info("APODERA getStartUrl( ); => " + startURL);
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

        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(SERVEI_REST_SERVICE)) {

            consultaApoderamentsRestService(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                    response, userData, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    /**
     * Mètode que retorna la icona del plugin
     *
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-apodera.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return APODERA_PROPERTY_BASE;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- INDEX ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String INDEX_HTML_PAGE = "apodera_index.html";

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage/apodera_index.html";

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

            String pathtoservei = absolutePluginRequestPath + "/" + SERVEI_REST_SERVICE;

            map.put("pathtoservei", pathtoservei);

            map.put("usuariNom", userData.getName());
            map.put("usuariDNI", userData.getAdministrationID());
            map.put("usuariMetode", userData.getAuthenticationMethod());

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            try {
                response.getWriter().println(generat);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            // XYZ ZZZ
            log.error("Error generant pàgina bàsica: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- SERVEI PAGE (Cridada a Apodera)
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String SESSIO_CACHE_APODERAMENTS_MAP_APODERA = "SESSIO_CACHE_APODERAMENT_NOTIB";

    protected static final String SERVEI_REST_SERVICE = "apodera";

    protected static final String APODERA_RES_BUNDLE = "carpetafrontapodera";

    public void consultaApoderamentsRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {
            String lang = request.getParameter("lang");

            if (lang != null) {
                locale = new Locale(lang);
            }

            // CIUTADÀ PODERDANT
            DatosPoderdanteCompletoType poderdant = new DatosPoderdanteCompletoType();

            String nif = userData.getAdministrationID();
            boolean isEmpresa = userData.isBusiness();

            if (!isEmpresa) {
                PersonaFisicaType persona = new PersonaFisicaType();
                persona.setNombre(userData.getName());
                persona.setApellido1(userData.getSurname1());
                persona.setApellido2(userData.getSurname2());
                persona.setNifNie(userData.getAdministrationID());
                poderdant.setPersonaFisica(persona);
            } else {
                PersonaJuridicaType empresa = new PersonaJuridicaType();
                empresa.setRazonSocial(userData.getName());
                empresa.setNif(userData.getAdministrationID());
                poderdant.setPersonaJuridica(empresa);
            }

            List<DatosApoderamientoType> apoderaments = new ArrayList<DatosApoderamientoType>();

            int totalComPoderdant = 0;
            {
                List<DatosApoderamientoType> comPoderdant = consultaInterna(nif, null).getListaApoderamientos();
                if (comPoderdant != null) {
                    totalComPoderdant = comPoderdant.size();
                    apoderaments.addAll(comPoderdant);
                }
            }
            int totalComApoderat = 0;
            {
                List<DatosApoderamientoType> comApoderat = consultaInterna(null, nif).getListaApoderamientos();
                if (comApoderat != null) {
                    totalComApoderat = comApoderat.size();
                    apoderaments.addAll(comApoderat);
                }
            }

            ArrayList<Apoderamiento> apos = new ArrayList<Apoderamiento>();

            {

                // LLISTA APODERAMENTS
                for (DatosApoderamientoType apoderament : apoderaments) {

                    Apoderamiento apo = new Apoderamiento();

                    // TIPUS APODERAMENT
                    TipoApoderamientoCompletoType tipusApoderament = apoderament.getTipoApoderamiento();
                    {
                        final String tipus = tipusApoderament.getTipoApod();
                        final String subtipus = tipusApoderament.getSubTipoApod();

                        apo.setTipus(tipus);

                        TipoApoderamiento ta = TipoApoderamiento.getTipoApoderamiento(tipus, subtipus);

                        if (ta == null) {
                            log.error("\n\n\n NO S'HA TROBAT TIPUS APODERAMENT TIPUS[" + tipus + "] - SUBTIPUS["
                                    + subtipus + "]\n\n\n");
                            apo.setSubtipus(getTraduccio(APODERA_RES_BUNDLE, "apoderatipo.desconegut", locale));
                        } else {
                            apo.setSubtipus(
                                    getTraduccio(APODERA_RES_BUNDLE, "apoderatipo." + tipus + subtipus, locale));
                        }

                    }

                    // ESTAT
                    switch (apoderament.getEstado().substring(0, 5)) {
                        case "Sin a":
                            apo.setEstat("1");
                        break;
                        case "Autor":
                            apo.setEstat("2");
                        break;
                        case "Revoc":
                            apo.setEstat("3");
                        break;
                        case "Renun":
                            apo.setEstat("4");
                        break;
                        case "Caduc":
                            apo.setEstat("5");
                        break;
                        case "Cance":
                            apo.setEstat("6");
                        break;
                        default:
                            apo.setEstat("0");
                    }

                    // NOM I DOCUMENT APODERAT
                    {
                        DatosApoderadoCompletoType apoCompleto = apoderament.getDatosApoderado();
                        if (apoCompleto.getPersonaFisica() != null) {
                            PersonaFisicaType pf = apoCompleto.getPersonaFisica();
                            apo.setApoderado(pf.getNombre() + " " + pf.getApellido1() + " (" + pf.getNifNie() + ")"
                                    + " - " + getTraduccio(APODERA_RES_BUNDLE, "persona.fisica", locale));
                        } else {
                            if (apoCompleto.getPersonaJuridica() != null) {
                                PersonaJuridicaType pf = apoCompleto.getPersonaJuridica();
                                apo.setApoderado(pf.getRazonSocial() + " (" + pf.getNif() + ")" + " - "
                                        + getTraduccio(APODERA_RES_BUNDLE, "persona.juridica", locale));
                            }
                        }
                    }

                    // NOM I DOCUMENT PODERDANTE
                    {
                        DatosPoderdanteCompletoType poderdanteCompleto = apoderament.getDatosPoderdante();
                        if (poderdanteCompleto.getPersonaFisica() != null) {
                            PersonaFisicaType pf = poderdanteCompleto.getPersonaFisica();
                            apo.setPoderdante(pf.getNombre() + " " + pf.getApellido1() + " (" + pf.getNifNie() + ")"
                                    + " - " + getTraduccio(APODERA_RES_BUNDLE, "persona.fisica", locale));
                        } else {
                            if (poderdanteCompleto.getPersonaJuridica() != null) {
                                PersonaJuridicaType pf = poderdanteCompleto.getPersonaJuridica();
                                apo.setPoderdante(pf.getRazonSocial() + " (" + pf.getNif() + ")" + " - "
                                        + getTraduccio(APODERA_RES_BUNDLE, "persona.juridica", locale));
                            }
                        }
                    }

                    // VIGÈNCIA APODERAMENT
                    if (apoderament.getPeriodoVigencia() != null) {
                        
                        String fechaFinFull = apoderament.getPeriodoVigencia().getFechaFin();
                        log.info("\n\n\n =>  ]]" + fechaFinFull + "[[");

                        try {
                          long dataFinalVigencia = SDF_VIGENCIA.parse(fechaFinFull).getTime();
                          apo.setDataFinalVigencia(dataFinalVigencia);
                        } catch(Throwable th) {
                            log.error("Error parsejant data Final vigència [ " + fechaFinFull + "]: " + th.getMessage(), th);
                            apo.setDataFinalVigencia(0);
                        }

                        apo.setVigencia(apoderament.getPeriodoVigencia().getFechaInicio().substring(0, 10) + " - "
                                + fechaFinFull.substring(0, 10).trim());
                    }

                    // TRAMITS
                    if (tipusApoderament.getListaTramites() != null) {

                        List<TramiteType2> tramiteList = tipusApoderament.getListaTramites().getValue().getTramite();

                        List<String> tramits = new ArrayList<String>();

                        for (TramiteType2 tt : tramiteList) {
                            if (tt.getCodTramite() == null) {
                                tramits.add(tt.getDescTramite());
                            } else {
                                tramits.add(tt.getCodTramite() + " - " + tt.getDescTramite());
                            }
                        }
                        apo.setTramits(tramits);
                    }

                    // PROCEDIMENT
                    if (tipusApoderament.getListaProcedimientos() != null) {
                        List<String> procs = new ArrayList<String>();
                        Procedimiento2 procediments = tipusApoderament.getListaProcedimientos().getValue();
                        for (ProcedimientoType2 procediment : procediments.getProcedimiento()) {
                            procs.add(procediment.getCodProcedimiento() + ": " + procediment.getNombreProcedimiento());
                        }
                        apo.setProcediments(procs);
                    } else {
                        apo.setProcediments(null);
                    }

                    // ORGANISMES
                    if (tipusApoderament.getListaOrganismos() != null) {
                        List<String> orgs = new ArrayList<String>();
                        Organismo2 organismes = tipusApoderament.getListaOrganismos().getValue();
                        for (OrganismoType2 organisme : organismes.getOrganismo()) {
                            if (organisme.getCodOrganismo().equals("Todas")) {
                                orgs.add(getTraduccio(APODERA_RES_BUNDLE, "organisme.totes", locale));
                                break;
                            } else {
                                orgs.add(organisme.getCodOrganismo() + ": " + organisme.getDenomOrganismo());
                            }
                        }
                        apo.setOrganismes(orgs);
                    }

                    // Afegim apoderament a la llista
                    apos.add(apo);

                }
            }

            Map<Long, DatosApoderamientoType> apoderamentsMap = (Map<Long, DatosApoderamientoType>) request.getSession()
                    .getAttribute(SESSIO_CACHE_APODERAMENTS_MAP_APODERA);

            if (apoderamentsMap == null) {
                apoderamentsMap = new HashMap<Long, DatosApoderamientoType>();
                request.getSession().setAttribute(SESSIO_CACHE_APODERAMENTS_MAP_APODERA, apoderamentsMap);
            }

            Map<String, Object> infoApoderaments = new HashMap<String, Object>();

            String urlApodera = getPropertyRequired(APODERA_PROPERTY_BASE + "url");
            
            
            
            Collections.sort(apos, new Comparator<Apoderamiento>() {
                @Override
                public int compare(Apoderamiento o1, Apoderamiento o2) {
                    return (int)(Math.signum(o2.getDataFinalVigencia() - o1.getDataFinalVigencia()));
                }
            });
            
            
            for (Apoderamiento apoderamiento : apos) {
                // XYZ ZZZ
                log.info(" =====> " + apoderamiento.getDataFinalVigencia() + " - " + apoderamiento.getVigencia());
            }
            

            infoApoderaments.put("poderdant", poderdant);
            infoApoderaments.put("apoderaments", apos);

            infoApoderaments.put("totalComPoderdant", totalComPoderdant);
            infoApoderaments.put("totalComApoderat", totalComApoderat);

            infoApoderaments.put("urlApodera", urlApodera);
            infoApoderaments.put("entitat", getProperty(APODERA_PROPERTY_BASE + "organisme.denominacio"));

            Gson gson = new Gson();
            String json = gson.toJson(infoApoderaments);

            log.info(json);

            try {

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                response.getWriter().write(json);

            } catch (IOException e) {
                log.error("Error obtenint writer: " + e.getMessage(), e);
            }

        } catch (Throwable e) {

            String msg = "Error llistant apoderaments: " + e.getMessage();
            log.error(msg, e);

            errorRest(msg, e, request, response, absolutePluginRequestPath, locale);

        }

    }

    protected ConsultaApoderamientosResponse consultaInterna(String nifPoderdante, String nifApoderado)
            throws Exception {

        String codAplicacion = getPropertyRequired(APODERA_PROPERTY_BASE + "codiApp");

        if (nifApoderado == null && nifPoderdante == null) {
            // TODO traduir
            throw new Exception("S'ha de definir com a mínim nifapoderado o nifpoderdante ");
        }

        // # CERTIFICATE Token

        String organisme_dir3 = getPropertyRequired(APODERA_PROPERTY_BASE + "organisme.dir3");
        String organisme_denominacio = getProperty(APODERA_PROPERTY_BASE + "organisme.denominacio");

        DatosAuditoriaType datosAuditoriaType = new DatosAuditoriaType();
        datosAuditoriaType.setCodAplicacion(codAplicacion);

        datosAuditoriaType.setTimestamp(Long.parseLong(SDF.format(new Date())));

        DatosApoderadoType datosApoderado = null;
        if (nifApoderado != null) {
            datosApoderado = new DatosApoderadoType();
            datosApoderado.setNifNieApoderado(nifApoderado);
        }

        DatosPoderdanteType datosPoderdante = null;
        if (nifPoderdante != null) {
            datosPoderdante = new DatosPoderdanteType();
            datosPoderdante.setNifNiePoderdante(nifPoderdante);
        }

        TipoApoderamientoType tipoApoderamiento = null;

        tipoApoderamiento = new TipoApoderamientoType();

        OrganismoType organismoType = new OrganismoType();

        organismoType.setCodOrganismo(organisme_dir3);
        organismoType.setDenomOrganismo(organisme_denominacio);

        Organismo organismo = new Organismo();
        organismo.getOrganismo().add(organismoType);
        ObjectFactory factory = new ObjectFactory();
        JAXBElement<Organismo> jaxbelementOrganismo = factory.createTipoApoderamientoTypeListaOrganismos(organismo);
        tipoApoderamiento.setListaOrganismos(jaxbelementOrganismo);
       

        DatosConsultaApoderamientoType dcat = new DatosConsultaApoderamientoType();

        dcat.setDatosApoderado(datosApoderado);
        dcat.setDatosPoderdante(datosPoderdante);
        dcat.setTipoApoderamiento(tipoApoderamiento);

        DatosConsultaType datosConsultaType = new DatosConsultaType();
        datosConsultaType.setTipoConsulta(false); // false => simple 0 || true => completa 1
        datosConsultaType.setDatosConsultaApoderamiento(dcat);


        PeticionConsulta peticio = new PeticionConsulta();
        peticio.setDatosAuditoriaType(datosAuditoriaType);
        peticio.setDatosConsultaType(datosConsultaType);

        // Cridada
        ConsultaApoderamientosResponse response = null;

        ConsultaAvanzadaPortType api = getApi();

        RespuestaConsulta resposta = api.consultaAvanzadaApoderamientos(peticio);

        response = resposta.getConsultaApoderamientosResponse();

        ErrorType errorType = response.getResultadoError();

        if (errorType != null) {

            if (!"0102".equals(errorType.getCodError())) {

                log.info("errorType::CodError => " + errorType.getCodError());
                log.info("errorType::DesError => " + errorType.getDesError());

                
                throw new Exception(getTraduccio(APODERA_RES_BUNDLE, "apodera.api.error", new Locale(Configuracio.getDefaultLanguage())) + errorType.getDesError()
                + " (CODI: " + errorType.getCodError() + ")");
  
            }

        }

        return response;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "apodera_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage/apodera_reactjs_main.js";

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
            log.error("Error llistant apoderaments XYZ ZZZ: " + e.getMessage(), e);
        }

    }

    /**
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    private ConsultaAvanzadaPortType getApi() throws Exception {

        String endPoint = getPropertyRequired(APODERA_PROPERTY_BASE + "endpoint");
        String auth_ks_Path = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.path");
        String auth_ks_Type = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.type");
        String auth_ks_Password = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.password");
        String auth_ks_Alias = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.cert.alias");
        String auth_ks_Cert_Password = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.cert.password");

        // XYZ ZZZ TODO
        URL wsdlUrl = new URL(endPoint + "?wsdl");
        ConsultaAvanzadaService service = new ConsultaAvanzadaService(wsdlUrl);
        ConsultaAvanzadaPortType api = service.getConsultaAvanzadaPort();

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

        ClientHandler ch = new ClientHandlerCertificate(auth_ks_Path, auth_ks_Type, auth_ks_Password, auth_ks_Alias,
                auth_ks_Cert_Password);

        ch.addSecureHeader(api);

        // Fixar timeout
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        Long ct = this.getConnectTimeout();
        if (ct != null) {
            httpClientPolicy.setConnectionTimeout(ct);
        }
        Long rt = this.getReadTimeout();
        if (rt != null) {
            httpClientPolicy.setReceiveTimeout(rt);
        }
        httpClientPolicy.setAllowChunking(false);

        Client client = ClientProxy.getClient(api);
        ((HTTPConduit) client.getConduit()).setClient(httpClientPolicy);

        // Print XML in and out
        if (isDebug()) {
            client.getInInterceptors().add(new org.apache.cxf.interceptor.LoggingInInterceptor());
            client.getOutInterceptors().add(new org.apache.cxf.interceptor.LoggingOutInterceptor());
        }

        return api;

    }

    public Long getConnectTimeout() {

        return getPropertyLong("connecttimeout");

    }

    public Long getReadTimeout() {
        return getPropertyLong("readtimeout");
    }

    protected Long getPropertyLong(String prop) {
        String ct = getProperty(APODERA_PROPERTY_BASE + prop);
        if (ct != null) {
            try {
                return Long.parseLong(ct);
            } catch (NumberFormatException e) {
                // TODO: handle exception
                log.error("Error parsejant numero ]" + ct + "[:" + e.getMessage(), e);
            }
        }
        return null;
    }

    public boolean isDebug() {
        return "true".equals(getProperty(APODERA_PROPERTY_BASE + "development"));
    }

}
