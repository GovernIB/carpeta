package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.net.URL;
import java.util.*;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.*;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandler;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandlerCertificate;

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

/**
 * @author jpernia
 * @author anadal
 */
public class ApoderaCarpetaFrontPlugin extends AbstractPinbalCarpetaFrontPlugin {

    public static final String APODERA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "apodera.";

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
            HttpServletRequest request, UserData userData, String administrationIDEncriptat,
            String parameter, IListenerLogCarpeta logCarpeta) throws Exception {

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
    public void requestCarpetaFront(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("ApoderaCarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(SERVEI_REST_SERVICE)) {

            consultaApoderamentsRestService(absolutePluginRequestPath, relativePluginRequestPath,
                    query, request, response, userData, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query,
                    request, response, userData, administrationEncriptedID, locale, isGet,
                    logCarpeta);
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

    public void index(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("text/html");

            String resource = "/webpage/apodera_index.html";

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

    public void consultaApoderamentsRestService(String absolutePluginRequestPath,
            String relativePluginRequestPath, String query, HttpServletRequest request,
            HttpServletResponse response, UserData userData, String administrationEncriptedID,
            Locale locale, boolean isGet) {

        try {

            // CIUTADÀ PODERDANT
            DatosPoderdanteCompletoType poderdant = new DatosPoderdanteCompletoType();

            String nifPoderdante = userData.getAdministrationID();
            boolean isEmpresa = userData.isBusiness();

            if(!isEmpresa) {
                PersonaFisicaType persona = new PersonaFisicaType();
                persona.setNombre(userData.getName());
                persona.setApellido1(userData.getSurname1());
                persona.setApellido2(userData.getSurname2());
                persona.setNifNie(userData.getAdministrationID());
                poderdant.setPersonaFisica(persona);
            } else{
                PersonaJuridicaType empresa = new PersonaJuridicaType();
                empresa.setRazonSocial(userData.getName());
                empresa.setNif(userData.getAdministrationID());
                poderdant.setPersonaJuridica(empresa);
            }

            List<DatosApoderamientoType> apoderaments = consultaInterna(nifPoderdante, null).getListaApoderamientos();

            int apoderamentsTotals;
            ArrayList<Apoderamiento> apos = new ArrayList<Apoderamiento>();

            if (apoderaments == null) {
                apoderaments = new ArrayList<DatosApoderamientoType>();
                apoderamentsTotals = 0;

            } else {

                apoderamentsTotals = apoderaments.size();


                // LLISTA APODERAMENTS
                for (DatosApoderamientoType apoderament : apoderaments) {

                    Apoderamiento apo = new Apoderamiento();

                    // TIPUS APODERAMENT
                    TipoApoderamiento ta = TipoApoderamiento.getTipoApoderamiento(
                            apoderament.getTipoApoderamiento().getTipoApod(),
                            apoderament.getTipoApoderamiento().getSubTipoApod());

                    String ts = "Tipo:" + apoderament.getTipoApoderamiento().getTipoApod() + " | Subtipo:"
                            + apoderament.getTipoApoderamiento().getSubTipoApod();

//                    if (ta == null) {
////                        apo.setTipus(getTraduccio(APODERA_RES_BUNDLE,"tipo.desconegut", locale) + " (" + ts + ")");
//                        apo.setTipus(getTraduccio(APODERA_RES_BUNDLE,"tipo.desconegut", locale));
//                    } else {
////                        apo.setTipus(ta.getDescripcion() + " (" + ts + ")");
//                        apo.setTipus(apoderament.getTipoApoderamiento().getTipoApod());
//                    }

                    apo.setTipus(apoderament.getTipoApoderamiento().getTipoApod());


                    // SUBTIPUS
                    apo.setSubtipus(apoderament.getTipoApoderamiento().getSubTipoApod());

                    // ESTAT
                    switch (apoderament.getEstado().substring(0,5)) {
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
                    DatosApoderadoCompletoType apoCompleto = apoderament.getDatosApoderado();
                    if (apoCompleto.getPersonaFisica() != null) {
                        PersonaFisicaType pf = apoCompleto.getPersonaFisica();
                        apo.setApoderado(pf.getNombre() + " " + pf.getApellido1() + " (" + pf.getNifNie() + ")" + " - " + getTraduccio(APODERA_RES_BUNDLE,"persona.fisica", locale));
                    }
                    if (apoCompleto.getPersonaJuridica() != null) {
                        PersonaJuridicaType pf = apoCompleto.getPersonaJuridica();
                        apo.setApoderado(pf.getRazonSocial() + " (" + pf.getNif() + ")" + " - " + getTraduccio(APODERA_RES_BUNDLE,"persona.juridica", locale));
                    }


                    // VIGÈNCIA APODERAMENT
                    if (apoderament.getPeriodoVigencia() != null) {
                        apo.setVigencia(apoderament.getPeriodoVigencia().getFechaInicio().substring(0,10) + " - "
                                + apoderament.getPeriodoVigencia().getFechaFin().substring(0,10));
                    }

                    // PROCEDIMENT
                    if(apoderament.getTipoApoderamiento().getListaProcedimientos() != null) {
                        Class<Procedimiento2> procediment = apoderament.getTipoApoderamiento().getListaProcedimientos().getDeclaredType();
                        apo.setProcediment(procediment.getName());
                    } else {
                        apo.setProcediment(null);
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

            infoApoderaments.put("poderdant", poderdant);
            infoApoderaments.put("apoderaments", apos);
            infoApoderaments.put("totalRegistres", apoderamentsTotals);
            infoApoderaments.put("urlApodera", urlApodera);

            Gson gson = new Gson();
            String json = gson.toJson(infoApoderaments);

            //log.info(json);

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

    protected ConsultaApoderamientosResponse consultaInterna(String nifPoderdante, String nifApoderado) throws Exception {

        String codAplicacion = getPropertyRequired(APODERA_PROPERTY_BASE + "codiApp");

        if (nifApoderado == null && nifPoderdante == null) {
            // TODO traduir
            throw new Exception("S'ha de definir com a mínim nifapoderado o nifpoderdante ");
        }

        // # CERTIFICATE Token

        String organisme_dir3 = getPropertyRequired(APODERA_PROPERTY_BASE + "organisme.dir3");
        String organisme_denominacio = getPropertyRequired(
                APODERA_PROPERTY_BASE + "organisme.denominacio");

        /*
         * ApoderaCarpetaFrontPlugin api = new ApoderaCarpetaFrontPlugin(endPoint,
         * auth_ks_Path, auth_ks_Type, auth_ks_Password, auth_ks_Alias,
         * auth_ks_Cert_Password);
         */

        DatosAuditoriaType datosAuditoriaType = new DatosAuditoriaType();
        datosAuditoriaType.setCodAplicacion(codAplicacion);

        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

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
        JAXBElement<Organismo> jaxbelementOrganismo = factory
                .createTipoApoderamientoTypeListaOrganismos(organismo);
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

                throw new Exception("Error cridant a la consulta de apoderaments: "
                        + errorType.getDesError() + " (CODI: " + errorType.getCodError() + ")");
            }

        }

//        List<DatosApoderamientoType> apoderamientos = response.getListaApoderamientos();
//
//        if (apoderamientos.size() == 0) {
//
//            log.error("No hi ha apoderaments per aquest usuari ...");
//
//        } else {
//
//            int i = 1;
//            for (DatosApoderamientoType d : apoderamientos) {
//
//                log.info("");
//                log.info(i + ".- Common Info=>  Estat:" + d.getEstado() + "\tcodiApoderaEXT:"
//                        + d.getCodApoderamientoEXT() + "\tcodiApoderaINT:"
//                        + d.getCodApoderamientoINT());
//
//                TipoApoderamiento ta = TipoApoderamiento.getTipoApoderamiento(
//                        d.getTipoApoderamiento().getTipoApod(),
//                        d.getTipoApoderamiento().getSubTipoApod());
//
//                String ts = "Tipo:" + d.getTipoApoderamiento().getTipoApod() + " | Subtipo:"
//                        + d.getTipoApoderamiento().getSubTipoApod();
//
//                if (ta == null) {
//                    log.info(i + ".- Tipo Apoderamiento => DESCONEGUT (" + ts + ")");
//                } else {
//                    log.info(i + ".- Tipo Apoderamiento => " + ta.getDescripcion() + " (" + ts
//                            + ")");
//                }
//
//                DatosApoderadoCompletoType apo = d.getDatosApoderado();
//                if (apo.getPersonaFisica() != null) {
//                    PersonaFisicaType pf = apo.getPersonaFisica();
//                    log.info(i + ".- Apoderat Perso. Fisica => " + pf.getNombre());
//
//                }
//                if (apo.getPersonaJuridica() != null) {
//                    PersonaJuridicaType pf = apo.getPersonaJuridica();
//                    log.info(i + ".- Apoderat Perso. Juridica => " + pf.getRazonSocial());
//
//                }
//
//                DatosPoderdanteCompletoType poderdante = d.getDatosPoderdante();
//
//                if (d.getPeriodoVigencia() != null) {
//                    log.info(i + ".- Vigencia => " + d.getPeriodoVigencia().getFechaInicio() + " - "
//                            + d.getPeriodoVigencia().getFechaFin());
//                }
//
//                i++;
//            }
//        }

        return response;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "apodera_reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response,
            UserData userData, String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition", "inline;filename=\""
                    + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

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
    private ConsultaAvanzadaPortType getApi() throws Exception {

        String endPoint = getPropertyRequired(APODERA_PROPERTY_BASE + "endpoint");
        String auth_ks_Path = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.path");
        String auth_ks_Type = getPropertyRequired(APODERA_PROPERTY_BASE + "authorization.ks.type");
        String auth_ks_Password = getPropertyRequired(
                APODERA_PROPERTY_BASE + "authorization.ks.password");
        String auth_ks_Alias = getPropertyRequired(
                APODERA_PROPERTY_BASE + "authorization.ks.cert.alias");
        String auth_ks_Cert_Password = getPropertyRequired(
                APODERA_PROPERTY_BASE + "authorization.ks.cert.password");

        // XYZ ZZZ TODO
        URL wsdlUrl = new URL(endPoint + "?wsdl");
        ConsultaAvanzadaService service = new ConsultaAvanzadaService(wsdlUrl);
        ConsultaAvanzadaPortType api = service.getConsultaAvanzadaPort();

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

        ClientHandler ch = new ClientHandlerCertificate(auth_ks_Path, auth_ks_Type,
                auth_ks_Password, auth_ks_Alias, auth_ks_Cert_Password);

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
            client.getInInterceptors().add(new LoggingInInterceptor());
            client.getOutInterceptors().add(new LoggingOutInterceptor());
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
