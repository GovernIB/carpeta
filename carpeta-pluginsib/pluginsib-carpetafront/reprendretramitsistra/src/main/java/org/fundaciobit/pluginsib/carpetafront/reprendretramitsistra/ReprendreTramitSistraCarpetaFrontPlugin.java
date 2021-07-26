package org.fundaciobit.pluginsib.carpetafront.reprendretramitsistra;

import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.BasicServiceInformation;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * 
 * @author anadal
 */
public class ReprendreTramitSistraCarpetaFrontPlugin extends AbstractCarpetaFrontPlugin {

    public static final String REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE = CARPETAFRONT_PROPERTY_BASE
            + "reprendretramitsistra.";

    /**
     * 
     */
    public ReprendreTramitSistraCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public ReprendreTramitSistraCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public ReprendreTramitSistraCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception {
        return null;
    }

    @Override
    public String getResourceBundleName() {
        return "carpetafrontreprendretramitsistra";
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userData, String administrationIDEncriptat, 
            String parameter, IListenerLogCarpeta logCarpeta)
            throws Exception {

        super.registerUserData(userData);

        String startURL;

        if (parameter == null) {
            startURL = absolutePluginRequestPath + "/" + SHOW_FORM_TRAMITID;
        } else {
            startURL = absolutePluginRequestPath + "/" + TRAMITACIO_ANONIMA_PARAMETRE + "/" + parameter;
        }

        log.info(" getStartUrl( ); => " + startURL);
        return startURL;
    }

    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {

        if (isDevelopment()) {
            log.info("ReprendreTramitSistraCarpetaFrontPlugin::requestCarpetaFront => query: ]" + query + "[");
        }

        if (query.startsWith(SHOW_FORM_TRAMITID)) {

            mostrarForm(absolutePluginRequestPath, relativePluginRequestPath, query, request, response, userData,
                    administrationEncriptedID, locale, isGet);

        } else if (query.startsWith(TRAMITACIO_ANONIMA_PARAMETRE)) {

            tramitacioAnonimaParametre(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet);

        } else {

            super.requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                    userData, administrationEncriptedID, locale, isGet, logCarpeta);
        }

    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- SHOW FORM TRAMIT----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    protected static final String SHOW_FORM_TRAMITID = "showformtramit";

    public void mostrarForm(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, Boolean isGet) {

        String clau = request.getParameter("clau");

        internalMostrarForm(absolutePluginRequestPath, request, response, administrationEncriptedID, locale, isGet,
                clau);
    }

    protected void internalMostrarForm(String absolutePluginRequestPath, HttpServletRequest request,
            HttpServletResponse response, String administrationEncriptedID, Locale locale, Boolean isGet, String clau) {

        final boolean esParametre;
        if (isGet == null) {
            esParametre = true;
            isGet = false;
        } else {
            esParametre = false;
        }

        log.info("internalMostrarForm:: esParametre => " + esParametre);
        log.info("internalMostrarForm:: isGet => " + isGet);

        try {

            Map<String, Object> map = new HashMap<String, Object>();

            if (!isGet) {
                if (clau == null || clau.isEmpty()) {
                    // "No s'ha introduït la clau de tramitació"
                    map.put("missatgeError", getTraduccio("error.noclau", locale) );
                } else {
                    try {

                        BackofficeFacade backofficeFacade = getBackofficeFacade();
                        String urlAnonimo = backofficeFacade.obtenerUrlAccesoAnonimo(clau);
                        
                        if (urlAnonimo == null) {
                        	map.put("missatgeError", getTraduccio("error.noclau", locale) );
                        }

                        map.put("id", clau);
                        map.put("url", urlAnonimo);
                    } catch (Exception e) {
                        String msg = "Error consultant la URL cap al tràmit: " + e.getMessage();
                        map.put("missatgeError", msg);
                        log.error(msg, e);
                    }

                }

            }

            // XYZ ZZZ
            map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);

            // XYZ ZZZ
            map.put("form_action", absolutePluginRequestPath + "/" + SHOW_FORM_TRAMITID);
            map.put("lang", locale.getLanguage());

            map.put("esParametre", esParametre);

            map.put("titol", getTitle(locale));
            map.put("subtitol", getSubTitle(locale));

            map.put("contextPath", request.getContextPath());

            String[] traduccions = { "label.reprendre", "label_info_finestra", 
                    "label_no_pipella", "label_continuar", "label_clau"};

            for (String t : traduccions) {
                map.put(t.replace('.', '_'), getTraduccio(t, locale));
            }

            InputStream input = this.getClass().getResourceAsStream("/webpage/reprendretramitsistra.html");
            String plantilla = IOUtils.toString(input, "UTF-8");

            String webpage = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");

            response.getWriter().println(webpage);
            response.flushBuffer();

        } catch (Exception e) {

            log.error("Error llistant tràmits: " + e.getMessage(), e);

            //errorPage(administrationEncriptedID, e, request, response, absolutePluginRequestPath, locale);
        }
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- SHOW FORM TRAMIT PARAMETRE ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public static final String TRAMITACIO_ANONIMA_PARAMETRE = "clau";

    public void tramitacioAnonimaParametre(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        // log.info(" query = ]" + query + "[");

        String clau = query.substring(query.lastIndexOf("/") + 1);
        // log.info(" clau = ]" + clau + "[");

        Boolean isGetNew = null; // indica que ve per paràmetre
        internalMostrarForm(absolutePluginRequestPath, request, response, administrationEncriptedID, locale, isGetNew,
                clau);
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------
    // ------------------- U T I L I T A T S ----------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public boolean isDevelopment() {
        return "true".equals(getProperty(REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE + "development"));
    }

    /* SISTRA 1 */

    private BackofficeFacade getBackofficeFacade() throws Exception {

        final String sistraUrl = getPropertyRequired(REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE + "url");
        final String username = getPropertyRequired(REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE + "user");
        final String password = getPropertyRequired(REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE + "pass");

        final URL wsdl = new URL(sistraUrl + "?wsdl");
        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade = service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, sistraUrl);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        return backofficeFacade;
    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public FileInfo getResourceIcon(Locale locale) {
        return getImageFromResource(locale, "/logo/logo-reprendretramitsistra.png", "image/png");
    }

    @Override
    public String getPropertyBase() {
        return REPRENDRE_TRAMIT_SISTRA_PROPERTY_BASE;
    }

}
