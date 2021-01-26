package org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact;

import es.caib.carpeta.pinbal.ws.recobriment.client.SVDDGPCIWS02v3Client;
import es.caib.carpeta.pinbal.ws.recobriment.datosespecificos.SVDDGPCIWS02v3RespuestaDatosEspecificos;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
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
public class DadesPersonalsReactCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String DADESPERSONALS_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "dadespersonals.";

    /**
     *
     */
    public DadesPersonalsReactCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public DadesPersonalsReactCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public DadesPersonalsReactCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getTitle(Locale locale) {
        return getTraduccio("title", locale);
    }

    @Override
    public String getSubTitle(Locale locale) {
        return getTraduccio("subtitle", locale);
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontdadespersonalsreact";
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat) throws Exception {

        registerUserData(userData);

        String startURL = absolutePluginRequestPath + "/" + INDEX_HTML_PAGE;

        // String startURL = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

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
        } else if (query.startsWith(DOCUMENT_IDENTITAT_PAGE)) {

            documentidentitat(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);
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
        return getImageFromResource(locale, "/logo/logo-dadespersonalsreact.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return DADESPERSONALS_PROPERTY_BASE;
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

            String resource = "/webpage_dadespersonals/index.html";

            response.setHeader("Content-Disposition",
                    "inline;filename=\"" + java.net.URLEncoder.encode(INDEX_HTML_PAGE, "UTF-8") + "\"");

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("title", getTitle(locale));

            map.put("subtitle", getSubTitle(locale));

            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);

            // int pos = absolutePluginRequestPath.indexOf(INDEX_HTML_PAGE);

            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

            map.put("pathtojs", pathtojs);
            
            String pathtodocumentidentitat = absolutePluginRequestPath + "/" + DOCUMENT_IDENTITAT_PAGE;

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
    // ------------------- DOCUMENT IDENTITAT PAGE (Cridades a PInbal)       ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String DOCUMENT_IDENTITAT_PAGE = "documentidentitat";

    public void documentidentitat(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            DadesPolicia dadesPolicia = new DadesPolicia();

            try {
                
                String baseurl = getProperty(DADESPERSONALS_PROPERTY_BASE + "pinbal.baseurl");
                String username = getProperty(DADESPERSONALS_PROPERTY_BASE + "pinbal.username");
                String password = getProperty(DADESPERSONALS_PROPERTY_BASE + "pinbal.password");
                
                String app = "es.caib.carpeta.";
                System.setProperty(app + "pinbal.client.baseURL", baseurl);
                System.setProperty(app + "pinbal.client.username", username);
                System.setProperty(app + "pinbal.client.password", password);
                

                SVDDGPCIWS02v3Client client = new SVDDGPCIWS02v3Client(app);

                RespuestaClientAdapter<SVDDGPCIWS02v3RespuestaDatosEspecificos> result;
                
                
                String nif = getProperty(DADESPERSONALS_PROPERTY_BASE + "pinbal.testnif");
                String surname = getProperty(DADESPERSONALS_PROPERTY_BASE + "pinbal.testsurname");
                
                if (nif == null || surname == null) {
                  nif = userData.getAdministrationID();
                  surname = userData.getSurname1();
                }
                
                

                //result = client.peticionSincrona("30000056Y", "FUSTER");
                // result = client.peticionSincrona("41107605G", "JAUME");                
                result = client.peticionSincrona(nif.toUpperCase(), surname.toUpperCase());
                

                log.info("\n  Result OBject: " + result + "\n");
                log.info("\n  Result::getLiteralError: " + result.getLiteralError() + "\n");
                log.info("\n  Result::getLiteralErrorSec: " + result.getLiteralErrorSec() + "\n");

                SVDDGPCIWS02v3RespuestaDatosEspecificos rde;
                rde = result.getTransmisionesClient().get(0).getDatosEspecificos();

                es.caib.scsp.esquemas.SVDDGPCIWS02v3.respuesta.datosespecificos.Estado estat = rde.getRetorno()
                        .getEstado();

                log.info("\nEstado  " + estat.getCodigoEstado() + "\n");
                log.info("\nEstado Secundario  " + estat.getCodigoEstadoSecundario() + "\n");
                
                /*
                      Result::getLiteralError: Tramitada
                      Result::getLiteralErrorSec: null
                      Estado  00
                      Estado Secundario  null
                 */                
                if ("00".equals(estat.getCodigoEstado())) {
                    DatosTitular dt = rde.getRetorno().getDatosTitular();
                    SimpleDateFormat sdf_in = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat sdf_out = new SimpleDateFormat("dd/MM/yyyy");
                    
                    
                    dt.setFechaCaducidad(sdf_out.format(sdf_in.parse(dt.getFechaCaducidad())));
                    
                    dt.getDatosNacimiento().setFecha(sdf_out.format(sdf_in.parse(dt.getDatosNacimiento().getFecha())));
                    
                    dadesPolicia.setDatosTitular(dt);
                } else {
                    
                    String error = "Error amb Estado " + estat.getCodigoEstado() 
                      + " | Estado Secundario: " +  estat.getCodigoEstadoSecundario()
                      + " | Literal Error: " + result.getLiteralError()
                      + " | Literal Error Secundario: " + result.getLiteralErrorSec();
                    dadesPolicia.setError(error);
                }

            } catch (Throwable e) {

                dadesPolicia.setError("Error consultant Dades d'Identitat: " + e.getMessage());

                // TODO: handle exception
                e.printStackTrace();
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

            String resource = "/webpage_dadespersonals/reactjs_main.js";

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
