package org.fundaciobit.pluginsib.carpetafront.regweb32;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.regweb3.ws.api.v3.AsientoWs;
import es.caib.regweb3.ws.api.v3.FileContentWs;
import es.caib.regweb3.ws.api.v3.JustificanteWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 11/02/2021
 * @author anadal Independitzar de RegWeb32
 */

public abstract class Regweb32DetallRegistre extends AbstractCarpetaFrontPlugin {

    public static final String MIME_PDF = "application/pdf";

    public static final String VALIDEZ_DOCUMENTO_COPIA  = "1";
    public static final String VALIDEZ_DOCUMENTO_COPIA_ORIGINAL  = "3";
    public static final String VALIDEZ_DOCUMENTO_ORIGINAL  = "4";

    public Regweb32DetallRegistre() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public Regweb32DetallRegistre(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public Regweb32DetallRegistre(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- M E T O D E S A B S T R A C T E S ----------------
    // --------------------------------------------------------------------------------------

    public abstract String getDetalleTitle(Locale locale);

    public abstract boolean isDevelopment();

    public abstract RegWebAsientoRegistralWs getRegWebAsientoRegistralWsService() throws Exception;

    public abstract String getEntidad() throws Exception;

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            if (query.startsWith(Regweb32DetallRegistre.DETALL_REGISTRE_PAGE)) {

                detallDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, /* getEntidad(), getConcsvUrl(), */ locale, isGet);

            } else if (query.startsWith(JUSTIFICANT_REGISTRE_PAGE)) {

                justificantDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, /* getEntidad(), getConcsvUrl(), */ locale, isGet);
            } else if (query.startsWith(ANNEXE_REGISTRE_PAGE)) {

                annexeDeRegistre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, /* getEntidad(), getConcsvUrl(), */ locale, isGet);

            } else {

                super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request,
                        response, userData, administrationEncriptedID, locale, isGet);
            }
        } catch (Exception e) {
            log.error("Error detall registre: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DETALL DE REGISTRE ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DETALL_REGISTRE_PAGE = "detallRegistre32";

    public void detallDeRegistre(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID,  Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");

            String webpage = getDetallDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado,
                    userData.getAdministrationID(), locale, "");

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }

    }

    public String getDetallDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            String administrationID, Locale locale, String error) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();

        AsientoWs registre;

        if (isDevelopment()) {
            registre = getDetallRegistreDebug(numeroRegistroFormateado, administrationID, locale);
        } else {
            registre = getDetallRegistre(numeroRegistroFormateado, administrationID, locale);
        }

        InputStream input = this.getClass().getResourceAsStream("/webpage/detall32.html");

        String plantilla = IOUtils.toString(input, "UTF-8");

        // XYZ ZZZ
        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

        // XYZ ZZZ
        map.put("form_action", absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE);
        map.put("lang", locale.getLanguage());
        map.put("detalletitle", getDetalleTitle(locale));

        // Traduccions tipoInteresado
        String[] tipoInteresado = { "", getTraduccio("registro.tipoInteresado.1", locale),
                getTraduccio("registro.tipoInteresado.2", locale), getTraduccio("registro.tipoInteresado.3", locale) };

        map.put("tipoInteresado", tipoInteresado);

        // Traduccions idioma Registro
        String[] registroIdioma = { "", getTraduccio("registro.idioma.1", locale),
                getTraduccio("registro.idioma.2", locale) };

        map.put("registroIdioma", registroIdioma);


        Map<String, String> VALIDEZ_DOCUMENTAL_ANEXO = new HashMap<String, String>() {{
            put(VALIDEZ_DOCUMENTO_COPIA, getTraduccio("registro.anexo.validezdocumento.1",locale));
            put(VALIDEZ_DOCUMENTO_COPIA_ORIGINAL, getTraduccio("registro.anexo.validezdocumento.3",locale));
            put(VALIDEZ_DOCUMENTO_ORIGINAL, getTraduccio("registro.anexo.validezdocumento.4",locale));
        }};

        map.put("validezDocumento", VALIDEZ_DOCUMENTAL_ANEXO);

        String[] traduccions = { "registro.titulo.detalle", "registro.entrada", "registro.fecha", "registro.numero",
                "registro.oficina", "registro.destinatario", "registro.tipo.doc", "registro.extracto", "carpeta.idioma",
                "registro.presencial", "registro.codigoSia", "registro.justificante", "registro.interesados",
                "registro.interesado.nombre", "registro.interesado.documento", "registro.interesado.tipo",
                "registro.anexos", "registro.anexos.vacio", "registro.anexo.name", "registro.anexo.mime",
                "registro.anexo.size", "registro.anexo.file", "registro.anexo.validezdocumento","carpeta.descargar", "justificante.generar",
                "carpeta.catala", "carpeta.castella", "justificante.generando", "anexo.obtener", "registro.anexo.nodisponible" };

        for (String t : traduccions) {
            map.put(t.replace('.', '_'), getTraduccio(t, locale));
        }

        map.put("registro", registre);

        String urlDetalle = absolutePluginRequestPath + "/" + DETALL_REGISTRE_PAGE + "?numeroRegistroFormateado=";
        map.put("urlDetalle", urlDetalle);

        // Montamos la url de generación del justificante
        String urlJustificant = absolutePluginRequestPath + "/" + JUSTIFICANT_REGISTRE_PAGE
                + "?numeroRegistroFormateado=" + registre.getNumeroRegistro() + "&tipoRegistro="
                + registre.getTipoRegistro();
        map.put("urlJustificant", urlJustificant);

        // Montamos la url de obtención del anexo
        String urlAnnexe = absolutePluginRequestPath + "/" + ANNEXE_REGISTRE_PAGE + "?numeroRegistroFormateado="
                + registre.getNumeroRegistro() + "&idAnnexe=";
        map.put("urlAnnexe", urlAnnexe);


        // Montamos la url de descarga del justificante
        if (registre.getJustificante() != null) {
            FileContentWs justificantRegistre = getAnnexeRegistre(registre.getJustificante().getFileID(),locale);
            if (justificantRegistre.getUrl() != null) {
                map.put("justificanteUrl", justificantRegistre.getUrl());
            }else {
                //TODO Aqui deberiamos enviar el FileInfo para que se lo descargue
                map.put("justificantId", justificantRegistre.getFileInfoWs().getFileID());
            }

        }

        // Indicamos si se ha producido un error
        map.put("error", error);

        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        return generat;

    }

    private AsientoWs getDetallRegistreDebug(String numeroRegistroFormateado, String administrationID, Locale locale)
            throws Exception {

        // TODO Obtener idioma

        AsientoWs registro = getDetallRegistre(numeroRegistroFormateado, administrationID, locale);

        if (registro == null) {
            System.out.println(" REGISTRE NULL o EMPTY: " + registro);
        } else {
            System.out.println(" -------------  REGISTRE  -------------------");
            System.out.println("ar.getNumeroRegistroFormateado() => " + (registro.getNumeroRegistro()));
            System.out.println("ar.getResumen() => " + registro.getExtracto());
            System.out.println("ar.getFechaRegistro(); => " + registro.getFechaRegistro());
            System.out.println("ar.getUnidadTramitacionDestinoDenominacion() => " + registro.getDenominacionDestino());
            System.out.println(
                    "ar.getEntidadRegistralInicioDenominacion() => " + registro.getDenominacionOficinaOrigen());
            System.out.println("ar.getTipoDocumentacionFisicaCodigo() => " + registro.getTipoDocumetacionFisica());
            System.out.println("Anexos retornados => " + registro.getAnexos().size());

        }

        return registro;
    }

    public AsientoWs getDetallRegistre(String numeroRegistroFormateado, String administrationID, /* String entidad, */
            Locale locale) throws Exception {

        RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();

        AsientoWs registro = regWebAsientoRegistralWs.obtenerAsientoCiudadanoCarpeta(getEntidad(), administrationID,
                numeroRegistroFormateado, locale.getLanguage());

        return registro;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JUSTIFICANTE DE REGISTRE ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String JUSTIFICANT_REGISTRE_PAGE = "justificantRegistre";

    public void justificantDeRegistre(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
            String tipoRegistro = request.getParameter("tipoRegistro");

            getJustificantDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado,
                    Long.valueOf(tipoRegistro), userData.getAdministrationID(), /* entidad, concsvUrl, */ locale,
                    request, response);

        } catch (Exception e) {
            log.error("Error llistant registres: " + e.getMessage(), e);
        }

    }

    public void getJustificantDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            Long tipoRegistro, String administrationID, Locale locale, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Obtenim justificant
        JustificanteWs justificantRegistre = getJustificantRegistre(numeroRegistroFormateado, tipoRegistro, locale);

        // Indicam si s'ha produit un error en obtenció de l'annexe

        String errorJustificant = "";
        if (justificantRegistre == null) {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            errorJustificant = getTraduccio("justificante.error.generando", locale);

            String webpage = getDetallDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado,
                    administrationID, locale, errorJustificant);
            try {
                response.getWriter().println(webpage);
                response.flushBuffer();

            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }
        } else {

            obtenerContentType(MIME_PDF, response, "justificant_" + numeroRegistroFormateado + ".pdf", null,
                    justificantRegistre.getJustificante());
        }
    }

    public JustificanteWs getJustificantRegistre(String numeroRegistroFormateado, Long tipoRegistro, Locale locale)
            throws Exception {

        JustificanteWs justificante;

        try {
            RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();
            justificante = regWebAsientoRegistralWs.obtenerJustificante(getEntidad(), numeroRegistroFormateado,
                    tipoRegistro);

        } catch (Exception e) {
            justificante = null;
        }

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
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            String numeroRegistroFormateado = request.getParameter("numeroRegistroFormateado");
            String idAnnexe = request.getParameter("idAnnexe");

            getAnnexeDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado, Long.valueOf(idAnnexe),
                    userData.getAdministrationID(), locale, request, response);

        } catch (Exception e) {
            log.error("Error obtenint annexe: " + e.getMessage(), e);
        }

    }

    public void getAnnexeDeRegistrePage(String absolutePluginRequestPath, String numeroRegistroFormateado,
            Long idAnnexe, String administrationID, Locale locale,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Obtenim annexe
        FileContentWs fileContentWs = getAnnexeRegistre(idAnnexe, locale);

        // Indicam si s'ha produit un error en obtenció de l'annexe
        String errorAnnexe = "";
        if (fileContentWs == null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            errorAnnexe = getTraduccio("annexo.error.obtenent", locale);
            String webpage = getDetallDeRegistrePage(absolutePluginRequestPath, numeroRegistroFormateado,
                    administrationID, locale, errorAnnexe);

            try {
                response.getWriter().println(webpage);
                response.flushBuffer();

            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } else {

            obtenerContentType(fileContentWs.getFileInfoWs().getMime(), response,
                    fileContentWs.getFileInfoWs().getFilename(), null, fileContentWs.getData());
        }

    }

    public FileContentWs getAnnexeRegistre(Long idAnnexe, Locale locale) throws Exception {

        FileContentWs annexe;

        try {
            RegWebAsientoRegistralWs regWebAsientoRegistralWs = getRegWebAsientoRegistralWsService();
            annexe = regWebAsientoRegistralWs.obtenerAnexoCiudadano(getEntidad(), idAnnexe, locale.getLanguage());

        } catch (Exception e) {
            annexe = null;
        }

        return annexe;

    }

}
