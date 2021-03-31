package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia;

import es.caib.carpeta.pinbal.ws.recobriment.datosespecificos.SVDDGPCIWS02v3RespuestaDatosEspecificos;
import es.caib.carpeta.pinbal.ws.recobriment.facade.SVDDGPCIWS02v3RecobrimentFacade;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.pinbal.ws.recobriment.Consentimiento;
import es.caib.pinbal.ws.recobriment.TipoDocumentacion;
import es.caib.scsp.esquemas.SVDDGPCIWS02v3.respuesta.datosespecificos.DatosTitular;
import es.caib.scsp.pinbal.ws.recobriment.facade.RespuestaClientAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * @author anadal
 */
public class PinbalPoliciaCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String PINBALPOLICIA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "pinbalpolicia.";

    /**
     *
     */
    public PinbalPoliciaCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public PinbalPoliciaCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public PinbalPoliciaCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    /*
    @Override
    public String getTitle(Locale locale) {
        return getTraduccio("title", locale);
    }

    @Override
    public String getSubTitle(Locale locale) {
        return getTraduccio("subtitle", locale);
    }
    */

    @Override
    public String getResourceBundleName() {
        return "carpetafrontpinbalpolicia";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationID: "
                + userData.getAdministrationID());
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
        } else if (query.startsWith(DOCUMENT_IDENTITAT_REST_SERVICE)) {

            documentIdentitatRestService(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);
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
        return getImageFromResource(locale, "/logo/logo-pinbalpolicia.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return PINBALPOLICIA_PROPERTY_BASE;
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

            String resource = "/webpage_pinbalpolicia/index.html";

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

            // int pos = absolutePluginRequestPath.indexOf(INDEX_HTML_PAGE);

            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojs", pathtojs);

            String pathtodocumentidentitat = absolutePluginRequestPath + "/" + DOCUMENT_IDENTITAT_REST_SERVICE;

            map.put("pathtodocumentidentitat", pathtodocumentidentitat);

            map.put("usuariNom", userData.getName());
            map.put("usuariLlinatge1", userData.getSurname1());
            map.put("usuariLlinatge2", userData.getSurname2());
            map.put("usuariDNI", userData.getAdministrationID());
            map.put("usuariMetode", userData.getAuthenticationMethod());

            String generat = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            try {
                response.getWriter().println(generat);
                response.flushBuffer();
            } catch (IOException e) {
                log.error("Error obtening writer: " + e.getMessage(), e);
            }

        } catch (Exception e) {
            // XYZ ZZZ
            log.error("Error llistant registres: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- DOCUMENT IDENTITAT PAGE (Cridades a PInbal)
    // ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DOCUMENT_IDENTITAT_REST_SERVICE = "documentidentitat";

    public void documentIdentitatRestService(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {


            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            DadesPolicia dadesPolicia = new DadesPolicia();

        try {
            final String PINBAL_PROPERTY_BASE = PINBALPOLICIA_PROPERTY_BASE + "pinbal.";

            // Estado

            final String codigoEstado = null;
            final String codigoEstadoSecundario = null;
            final String literalError = null;
            final String literalErrorSec = null;
            final Integer tiempoEstimadoRespuesta = null;

            // Atributos

            final String codigoCertificado = getPropertyRequired(PINBAL_PROPERTY_BASE + "codicertificat"); // "SVDDGPCIWS02";
            final String idPeticion = null;
            final String numElementos = "1";
            final String timeStamp = null;

            // Emisor (obtingut de la documentació SCSP del servei)

            final String nifEmisor = getPropertyRequired(PINBAL_PROPERTY_BASE + "nifemisor"); // "S2816015H";
            final String nombreEmisor = getPropertyRequired(PINBAL_PROPERTY_BASE + "nomemisor"); // "DGP";

            // Funcionario

            final String nifFuncionario = getPropertyRequired(PINBAL_PROPERTY_BASE + "niffuncionari"); // "43125996F";
            final String nombreCompletoFuncionario = getPropertyRequired(PINBAL_PROPERTY_BASE + "nomfuncionari"); // "Ana
                                                                                                                  // Bustos
                                                                                                                  // Nadal";
            final String seudonimo = null;

            // Procedimiento

            final String codProcedimiento = getPropertyRequired(PINBAL_PROPERTY_BASE + "codiprocediment"); // "CODSVDR_GBA_20121107";
            final String nombreProcedimiento = getPropertyRequired(PINBAL_PROPERTY_BASE + "nomprocediment"); // "PRUEBAS
                                                                                                             // DE
                                                                                                             // INTEGRACION
                                                                                                             // PARA
                                                                                                             // GOBIERNO
                                                                                                             // DE
                                                                                                             // BALEARES";

            // Solicitante
            final String codigoUnidadTramitadora = null;
            final Consentimiento consentimiento = Consentimiento.SI;
            final String finalidad = getPropertyRequired(PINBAL_PROPERTY_BASE + "finalitat"); // "Baremacions per el
                                                                                              // proces
                                                                                              // d'escolaritzacio";
            final String idExpediente = "";// "Q9WREU";
            final String identificadorSolicitante = getPropertyRequired(
                    PINBAL_PROPERTY_BASE + "identificadorsolicitant"); // "S0711001H";
            final String nombreSolicitante = getPropertyRequired(PINBAL_PROPERTY_BASE + "nomsolicitant"); // "Conselleria
                                                                                                          // d'Educació";
            final String unidadTramitadora = getPropertyRequired(PINBAL_PROPERTY_BASE + "unitattramitadora"); // "Servei
                                                                                                              // d'escolarització";

            // Transmision
            final String fechaGeneracion = "";
            final String idSolicitud = "";
            final String idTransmision = "";

            // Titular
            final String apellido1; // = "JAUME";
            final String apellido2 = "";
            final String documentacion; // = "41107605G";
            final String nombre = "";
            final String nombreCompleto = "";
            final TipoDocumentacion tipoDocumentacion = TipoDocumentacion.DNI;

            // Dades del Titular del DNI
            {
                String nif = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.testnif");
                String surname = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.testsurname");

                if (nif == null || surname == null) {
                    nif = userData.getAdministrationID();
                    surname = userData.getSurname1();
                }

                documentacion = nif.toUpperCase();
                apellido1 = surname.toUpperCase();
            }

            // Dades de connexio
            String baseurl = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.baseurl");
            String username = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.username");
            String password = getProperty(PINBALPOLICIA_PROPERTY_BASE + "pinbal.password");

            String app = "es.caib.carpeta.";
            System.setProperty(app + "pinbal.client.baseURL", baseurl);
            System.setProperty(app + "pinbal.client.username", username);
            System.setProperty(app + "pinbal.client.password", password);

            final SVDDGPCIWS02v3RecobrimentFacade facade;
            facade = new SVDDGPCIWS02v3RecobrimentFacade(app);

            RespuestaClientAdapter<SVDDGPCIWS02v3RespuestaDatosEspecificos> result;
            result = facade.peticionSincrona(codigoEstado, codigoEstadoSecundario, literalError, literalErrorSec,
                    tiempoEstimadoRespuesta, codigoCertificado, idPeticion, numElementos, timeStamp, nifEmisor,
                    nombreEmisor, nifFuncionario, nombreCompletoFuncionario, seudonimo, codProcedimiento,
                    nombreProcedimiento, codigoUnidadTramitadora, consentimiento, finalidad, idExpediente,
                    identificadorSolicitante, nombreSolicitante, unidadTramitadora, apellido1, apellido2, documentacion,
                    nombre, nombreCompleto, tipoDocumentacion, fechaGeneracion, idSolicitud, idTransmision);

            // SVDDGPCIWS02v3Client client = new SVDDGPCIWS02v3Client(app);

            // RespuestaClientAdapter<SVDDGPCIWS02v3RespuestaDatosEspecificos> result;

            // result = client.peticionSincrona("30000056Y", "FUSTER");
            // result = client.peticionSincrona("41107605G", "JAUME");
            // result = client.peticionSincrona(nif.toUpperCase(), surname.toUpperCase());

            // log.info("\n Result OBject: " + result + "\n");
            log.info("\n  Result::getLiteralError: " + result.getLiteralError() + "\n");
            log.info("\n  Result::getLiteralErrorSec: " + result.getLiteralErrorSec() + "\n");

            SVDDGPCIWS02v3RespuestaDatosEspecificos rde;
            rde = result.getTransmisionesClient().get(0).getDatosEspecificos();

            es.caib.scsp.esquemas.SVDDGPCIWS02v3.respuesta.datosespecificos.Estado estat = rde.getRetorno().getEstado();

            log.info("\nEstado  " + estat.getCodigoEstado() + "\n");
            log.info("\nEstado Secundario  " + estat.getCodigoEstadoSecundario() + "\n");

            /*
             * Result::getLiteralError: Tramitada Result::getLiteralErrorSec: null Estado 00
             * Estado Secundario null
             */
            if ("00".equals(estat.getCodigoEstado())) {
                DatosTitular dt = rde.getRetorno().getDatosTitular();
                SimpleDateFormat sdf_in = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf_out = new SimpleDateFormat("dd/MM/yyyy");

                dt.setFechaCaducidad(sdf_out.format(sdf_in.parse(dt.getFechaCaducidad())));

                dt.getDatosNacimiento().setFecha(sdf_out.format(sdf_in.parse(dt.getDatosNacimiento().getFecha())));
                
                

                dadesPolicia.setDatosTitular(dt);
            } else {

                String error = "Error amb Estado " + estat.getCodigoEstado() + " | Estado Secundario: "
                        + estat.getCodigoEstadoSecundario() + " | Literal Error: " + result.getLiteralError()
                        + " | Literal Error Secundario: " + result.getLiteralErrorSec();
                dadesPolicia.setError(error);
            }

        } catch (Throwable e) {

            String msg = "Error consultant Dades d'Identitat a la Policia: " + e.getMessage();

            dadesPolicia.setError(msg);

            log.error(msg, e);
        }

        Gson json = new Gson();

        String generat = json.toJson(dadesPolicia, DadesPolicia.class);
        log.info("\nDADES POLICIA JSON: " + generat + "\n");

        try {
            response.getWriter().println(generat);
            response.flushBuffer();
        } catch (IOException e) {
            log.error("Error obtening writer: " + e.getMessage(), e);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(REACT_JS_PAGE, "UTF-8") + "\"");

            String resource = "/webpage_pinbalpolicia/reactjs_main.js";

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

    public class DadesPolicia {

        protected String error = "";

        protected DatosTitular datosTitular = null;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public DatosTitular getDatosTitular() {
            return datosTitular;
        }

        public void setDatosTitular(DatosTitular datosTitular) {
            this.datosTitular = datosTitular;
        }

    }

}
