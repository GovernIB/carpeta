package org.fundaciobit.pluginsib.carpetafront.regwebdetallcomponent;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoWs;
import es.caib.regweb3.ws.api.v3.FileContentWs;
import es.caib.regweb3.ws.api.v3.FileInfoWs;
import es.caib.regweb3.ws.api.v3.JustificanteWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Fundació BIT.
 *
 * @author jagarcia Date: 15/04/2021
 */

public abstract class RegwebDetallComponent extends AbstractCarpetaFrontPlugin {

    public static final String MIME_PDF = "application/pdf";

    public static final String RESOURCE_BUNDLE_NAME = "carpetafrontregwebdetallcomponent";

    public static final String VALIDEZ_DOCUMENTO_COPIA = "1";
    public static final String VALIDEZ_DOCUMENTO_COPIA_ORIGINAL = "3";
    public static final String VALIDEZ_DOCUMENTO_ORIGINAL = "4";

    public static final String REGWEB32_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "regweb32.";

    public RegwebDetallComponent() {
        super();
    }

    public RegwebDetallComponent(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    public RegwebDetallComponent(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- M E T O D E S                 A B S T R A C T E S ----------------
    // --------------------------------------------------------------------------------------

    public abstract String getDetalleTitle(Locale locale);

    public abstract boolean isDevelopment();

    public abstract String getEntidad() throws Exception;

    public abstract String getPropertyBase();

    protected abstract FileInfo getResourceIcon(Locale locale);

    public abstract boolean isReactComponent();

    @Override
    public String getResourceBundleName() {
        return RESOURCE_BUNDLE_NAME;
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
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        try {

            if (query.startsWith(DETALL_REACT_PAGE)) {
                detallDeRegistreJson(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet, logCarpeta);
            } else if (query.startsWith(JUSTIFICANT_REGISTRE_PAGE_JS)) {
                justificantDeRegistreJson(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, locale, isGet, logCarpeta);
            } else if (query.startsWith(JUSTIFICANT_REGISTRE_PAGE)) {
                justificantDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet, logCarpeta);
            } else if (query.startsWith(ANNEXE_REGISTRE_PAGE)) {
                annexeDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet, logCarpeta);

            } else {
                super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, locale, isGet, logCarpeta);
            }

        } catch (Exception e) {

            try {

                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath, locale);
                log.error("Error detall registre: " + e.getMessage(), e);

            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- C O N S U L T A  S E R V I C E ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DETALL_REACT_PAGE = "detallRegistreJson";

    public void detallDeRegistreJson(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData2,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String numeroRegistroFormateado = request.getParameter("numero");

        Map<String, String> dades = new HashMap<String, String>();

        AsientoWs registre;
        final String nif = userData2.getAdministrationID();
        log.info("detallregistre::nif: " + nif);

        try {

            if (isDevelopment()) {
                registre = getDetallRegistreDebug(numeroRegistroFormateado, nif, locale);
            } else {
                registre = getDetallRegistre(numeroRegistroFormateado, nif, locale);
            }

            if (registre != null) {
                Gson jsonRegistre = new Gson();
                dades.put("registre", jsonRegistre.toJson(registre));

                // Montamos la url de generación del justificante
                String urlGeneracioJustificant = absolutePluginRequestPath + "/" + JUSTIFICANT_REGISTRE_PAGE_JS
                        + "?numeroRegistroFormateado=" + registre.getNumeroRegistro() + "&tipoRegistro="
                        + registre.getTipoRegistro();

                dades.put("urlGeneracioJustificant", urlGeneracioJustificant);

                // Montamos la url de obtención del anexo
                String urlAnnexe = absolutePluginRequestPath + "/" + ANNEXE_REGISTRE_PAGE + "?numeroRegistroFormateado="
                        + registre.getNumeroRegistro() + "&idAnnexe=";
                dades.put("urlAnnexe", urlAnnexe);

                dades.put("justificanteUrl", "");
                dades.put("justificanteId", "");
                dades.put("justificantData", "");
                dades.put("justificantSenseGenerar", "");
                dades.put("justificantFileName", "");

                String errorJustificant = "";
                if (registre.getJustificante() != null) {

                    FileContentWs justificantRegistre = getAnnexeRegistre(registre.getJustificante().getFileID(),
                            locale);
                    if (justificantRegistre != null) {
                        // Si retorna URL, es descarrega d'arxiu
                        if (justificantRegistre.getUrl() != null) {
                            dades.put("justificanteUrl", justificantRegistre.getUrl());
                            dades.put("justificantFileName", justificantRegistre.getFileInfoWs().getName());
                        } else {
                            // Es genera a filesystem i ens retorna Data.
                            dades.put("justificanteId",
                                    String.valueOf(justificantRegistre.getFileInfoWs().getFileID()));
                            if (justificantRegistre.getData() != null) {
                                dades.put("justificantData",
                                        new String(Base64.getEncoder().encode(justificantRegistre.getData())));
                                dades.put("justificantFileName", justificantRegistre.getFileInfoWs().getFilename());
                            } else {
                                dades.put("justificanteId", "");
                                dades.put("justificantData", "");
                                dades.put("error",
                                        getTraduccio(RESOURCE_BUNDLE_NAME, "justificante.error.obteniendo", locale));
                            }
                        }
                    } else {
                        errorJustificant = getTraduccio(RESOURCE_BUNDLE_NAME, "justificante.error.obteniendo", locale);
                        dades.put("error", errorJustificant);
                    }
                } else {
                    dades.put("justificantSenseGenerar", "true");
                }

            }

        } catch (Exception e) {
            log.error("Error detallRegistreJson: " + e.getMessage());
            dades.put("error", e.getMessage());
        }

        dades.put("locale", locale.getLanguage());

        Gson json = new Gson();
        String generat = json.toJson(dades);

        //        log.info("Generat: " + generat);

        try {
            response.getWriter().println(generat);
            response.flushBuffer();

        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

    /**
     * @return
     * @throws Exception
     */
    public RegWebAsientoRegistralWs getRegWebAsientoRegistralWsService() throws Exception {

        final String regweb3Url = getPropertyRequired(REGWEB32_PROPERTY_BASE + "url");

        final String username = getPropertyRequired(REGWEB32_PROPERTY_BASE + "user");

        final String password = getPropertyRequired(REGWEB32_PROPERTY_BASE + "pass");

        final URL wsdl = new URL(regweb3Url + "?wsdl");

        RegWebAsientoRegistralWsService service = new RegWebAsientoRegistralWsService(wsdl);
        RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, regweb3Url);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, username);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, password);

        return api;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DETALL DE REGISTRE -----------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public AsientoWs getDetallRegistreDebug(String numeroRegistroFormateado, String administrationID, Locale locale)
            throws Exception {

        // TODO Obtener idioma

        AsientoWs registro = getDetallRegistre(numeroRegistroFormateado, administrationID, locale);

        if (registro == null) {
            log.info(" REGISTRE NULL o EMPTY: " + registro);
        } else {
            log.info(" -------------  REGISTRE  -------------------");
            log.info("ar.getNumeroRegistroFormateado() => " + (registro.getNumeroRegistro()));
            log.info("ar.getResumen() => " + registro.getExtracto());
            log.info("ar.getFechaRegistro(); => " + registro.getFechaRegistro());
            log.info("ar.getUnidadTramitacionDestinoDenominacion() => " + registro.getDenominacionDestino());
            log.info("ar.getEntidadRegistralInicioDenominacion() => " + registro.getDenominacionOficinaOrigen());
            log.info("ar.getTipoDocumentacionFisicaCodigo() => " + registro.getTipoDocumetacionFisica());
            log.info("Anexos retornados => " + registro.getAnexos().size());
            log.info("ar.descripcionEstado() => " + registro.getDescripcionEstado());
            log.info("ar.getExpone() => " + registro.getExpone());
            log.info("ar.getSolicita() => " + registro.getSolicita());

            log.info(" --------------- ANEXOS ---------------------");
            for (FileInfoWs annexo : registro.getAnexos()) {
                log.info("Anexo.isConfidencial => " + annexo.isConfidencial());
                log.info("Anexo.getName => " + annexo.getName());
                log.info("Anexo.getHash => " + annexo.getHash());
            }
        }
        return registro;
    }

    public AsientoWs getDetallRegistre(String numeroRegistroFormateado, String administrationID, Locale locale)
            throws Exception {

        RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();

        AsientoWs registro = regWebAsientoRegistralWs.obtenerAsientoCiudadanoCarpeta(getEntidad(), administrationID,
                numeroRegistroFormateado, locale.getLanguage());

        return registro;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JUSTIFICANTE DE REGISTRE JSON ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String JUSTIFICANT_REGISTRE_PAGE_JS = "justificantRegistreJson";

    public void justificantDeRegistreJson(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
        String tipoRegistro = request.getParameter("tipoRegistro");

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        try {

            Map<String, String> dades = new HashMap<String, String>();

            dades.put("numero", numeroRegistroFormateado);

            // Obtenim justificant
            JustificanteWs justificantRegistre;
            try {
                justificantRegistre = getJustificantRegistre(numeroRegistroFormateado, Long.valueOf(tipoRegistro),
                        locale);
            } catch (Exception e) {
                log.error("Error obtenint justificant: " + e.getMessage(), e);
                justificantRegistre = null;
            }

            if (justificantRegistre != null) {
                /*obtenerContentType(MIME_PDF, response, "justificant_" + numeroRegistroFormateado + ".pdf", null,
                        justificantRegistre.getJustificante());*/
                dades.put("justificantData",
                        new String(Base64.getEncoder().encode(justificantRegistre.getJustificante())));
                dades.put("justificantFilename", "justificant_" + numeroRegistroFormateado + ".pdf");
            } else {

                dades.put("error", getTraduccio(RESOURCE_BUNDLE_NAME, "justificante.error.generando", locale));
            }

            Gson json = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm").create();
            String generat = json.toJson(dades);

            log.info("Generat: " + generat);

            response.getWriter().println(generat);
            response.flushBuffer();

        } catch (Exception e) {

            try {

                StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEBDETALLCOMPONENT] Error descàrrega justificant").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("Registre: " + numeroRegistroFormateado).append("\n");
                peticio.append("Error: " + e.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEBDETALLCOMPONENT] Error justificant", peticio.toString(),
                        "[REGWEBDETALLCOMPONENT] Error justificant", request.getRequestedSessionId());

                log.error("Error obtenint justificant: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JUSTIFICANTE DE REGISTRE ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String JUSTIFICANT_REGISTRE_PAGE = "justificantRegistre";

    public void justificantDeRegistre(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
            String tipoRegistro = request.getParameter("tipoRegistro");

            getJustificantDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado,
                    Long.valueOf(tipoRegistro), userData.getAdministrationID(), locale, request, response, logCarpeta);

        } catch (Exception e) {

            try {
                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath, locale);
                log.error("Error obtenint justificant: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    public void getJustificantDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            Long tipoRegistro, String administrationID, Locale locale, HttpServletRequest request,
            HttpServletResponse response, IListenerLogCarpeta logCarpeta) throws Exception {

        // Obtenim justificant
        JustificanteWs justificantRegistre;
        try {
            justificantRegistre = getJustificantRegistre(numeroRegistroFormateado, tipoRegistro, locale);
        } catch (Exception e) {

            justificantRegistre = null;

            try {

                StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEBDETALLCOMPONENT] Error descàrrega justificant").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("Registre: " + numeroRegistroFormateado).append("\n");
                peticio.append("Error: " + e.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEBDETALLCOMPONENT] Error justificant", peticio.toString(),
                        "[REGWEBDETALLCOMPONENT] Error justificant", request.getRequestedSessionId());

                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath, locale);
                log.error("Error obtenint justificant: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

        // Indicam si s'ha produit un error en obtenció de l'annexe
        try {
            obtenerContentType(MIME_PDF, response, "justificant_" + numeroRegistroFormateado + ".pdf", null,
                    justificantRegistre.getJustificante());
        } catch (Exception ex) {

            try {

                StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEBDETALLCOMPONENT] Error justificant").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("Error: " + ex.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEBDETALLCOMPONENT] Error justificant", peticio.toString(),
                        "[REGWEBDETALLCOMPONENT] Error justificant", request.getRequestedSessionId());

                errorPage(ex.getLocalizedMessage(), ex, request, response, absolutePluginRequestPath, locale);
                log.error("Error detall registre: " + ex.getMessage(), ex);
            } catch (Exception ex2) {
                log.error("Error mostrant pàgina d'error: " + ex2.getMessage(), ex2);
            }
        }

    }

    public JustificanteWs getJustificantRegistre(String numeroRegistroFormateado, Long tipoRegistro, Locale locale)
            throws Exception {

        JustificanteWs justificante;

        RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();
        justificante = regWebAsientoRegistralWs.obtenerJustificante(getEntidad(), numeroRegistroFormateado,
                tipoRegistro);

        return justificante;

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- ANNEXE DE REGISTRE ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String ANNEXE_REGISTRE_PAGE = "annexeRegistre";

    public void annexeDeRegistre(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
            String idAnnexe = request.getParameter("idAnnexe");

            getAnnexeDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado, Long.valueOf(idAnnexe),
                    userData.getAdministrationID(), locale, request, response, logCarpeta);

        } catch (Exception e) {

            try {
                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath, locale);
                log.error("Error obtenint annexe: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

    }

    public void getAnnexeDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            Long idAnnexe, String administrationID, Locale locale, HttpServletRequest request,
            HttpServletResponse response, IListenerLogCarpeta logCarpeta) throws Exception {

        // Obtenim annexe
        FileContentWs fileContentWs;
        try {

            fileContentWs = getAnnexeRegistre(idAnnexe, locale);

        } catch (Exception e) {

            fileContentWs = null;

            try {

                StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEBDETALLCOMPONENT] Error descàrrega annex").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("IdAnnexe: " + idAnnexe).append("\n");
                peticio.append("Error: " + e.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEBDETALLCOMPONENT] Error annex", peticio.toString(),
                        "[REGWEBDETALLCOMPONENT] Error annex", request.getRequestedSessionId());

                errorPage(e.getLocalizedMessage(), e, request, response, absolutePluginRequestPath, locale);
                log.error("Error obtenint annexe: " + e.getMessage(), e);
            } catch (Exception e2) {
                log.error("Error mostrant pàgina d'error: " + e2.getMessage(), e2);
            }

        }

        // Indicam si s'ha produit un error en obtenció de l'annexe
        try {
            obtenerContentType(fileContentWs.getFileInfoWs().getMime(), response,
                    fileContentWs.getFileInfoWs().getFilename(), null, fileContentWs.getData());
        } catch (Exception ex) {

            try {

                StringBuilder peticio = new StringBuilder();
                peticio.append("[REGWEBDETALLCOMPONENT] Error annexe").append("\n");
                peticio.append("classe: ").append(getClass().getName()).append("\n");
                peticio.append("Error: " + ex.getMessage()).append("\n");
                logCarpeta.crearLogCarpeta("[REGWEBDETALLCOMPONENT] Error annexe", peticio.toString(),
                        "[REGWEBDETALLCOMPONENT] Error annexe", request.getRequestedSessionId());

                errorPage(ex.getLocalizedMessage(), ex, request, response, absolutePluginRequestPath, locale);
                log.error("Error detall registre: " + ex.getMessage(), ex);
            } catch (Exception ex2) {
                log.error("Error mostrant pàgina d'error: " + ex2.getMessage(), ex2);
            }
        }

    }

    public FileContentWs getAnnexeRegistre(Long idAnnexe, Locale locale) throws Exception {

        FileContentWs annexe;

        RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();
        annexe = regWebAsientoRegistralWs.obtenerAnexoCiudadano(getEntidad(), idAnnexe, locale.getLanguage());

        return annexe;

    }

}
