package org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


/**
 * @author anadal
 */
public class DadesPersonalsReactCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    //public static final String REACTEXAMPLE_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE + "dadespersonalsreact.";

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
        
        //String startURL = absolutePluginRequestPath + "/" + REACT_JS_PAGE;

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationID: " + userData.getAdministrationID());
        log.info("Regweb3CarpetaFrontPlugin::requestCarpetaFront => administrationEncriptedID: "
                + administrationEncriptedID);

        if (query.startsWith(INDEX_HTML_PAGE)) {

            index(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(REACT_JS_PAGE)) {

            reactjs(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
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
    public FileInfo getIcon(Locale locale) {

        InputStream input;

        FileInfo fileInfo = null;
        try {

            // Agafa la icona del resource
            input = this.getClass().getResourceAsStream("/logo/logo-dadespersonalsreact.png");
            if (input != null) {
                fileInfo = new FileInfo("logo-dadespersonalsreact.png", "image/png", IOUtils.toByteArray(input));
            }

        } catch (Exception e) {
            log.error("Error llegint recurs : /logo/logo-dadespersonalsreact.png: " + e.getMessage(), e);

        }
        return fileInfo;

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
            
            response.setHeader( "Content-Disposition",
                    "inline;filename=\""+ java.net.URLEncoder.encode(INDEX_HTML_PAGE,"UTF-8") + "\"");

            response.setCharacterEncoding("utf-8");

            InputStream input = this.getClass().getResourceAsStream(resource);

            String plantilla = IOUtils.toString(input, "UTF-8");

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("title", getTitle(locale));

            map.put("subtitle", getSubTitle(locale));
            
            
            log.info("absolutePluginRequestPath ==> " + absolutePluginRequestPath);
            
            
            //int pos =  absolutePluginRequestPath.indexOf(INDEX_HTML_PAGE); 
             
            String pathtojs = absolutePluginRequestPath + "/" + REACT_JS_PAGE;
            
            map.put("pathtojs", pathtojs);
            
            
            
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
    // ------------------- JAVASCRIPT REACT ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String REACT_JS_PAGE = "reactjs_main.js";

    public void reactjs(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        try {

            response.setContentType("application/javascript");
            
            response.setHeader( "Content-Disposition",
                    "inline;filename=\""+ java.net.URLEncoder.encode(REACT_JS_PAGE,"UTF-8") + "\"");


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

}
