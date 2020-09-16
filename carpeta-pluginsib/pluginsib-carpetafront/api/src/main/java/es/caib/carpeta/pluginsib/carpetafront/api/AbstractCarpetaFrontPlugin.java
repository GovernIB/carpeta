package es.caib.carpeta.pluginsib.carpetafront.api;

import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractCarpetaFrontPlugin extends AbstractPluginFullUtilities implements ICarpetaFrontPlugin {

    /**
     * 
     */
    public AbstractCarpetaFrontPlugin() {
        super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public AbstractCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public AbstractCarpetaFrontPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- REQUEST GET-POST ---------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------


    /**
    * 
    */
    @Override
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, String administrationID,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        if (administrationID == null || administrationID.trim().length() == 0) {
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
                    + " No s'ha passat AdministrationID";

            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query, administrationID,
                    request, response, locale);

        } else if (query.startsWith(WEBRESOURCE)) {

            retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath, administrationID, query, request,
                    response, locale);

        } else {
            // XYZ Fer un missatges com toca
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca")) + " DESCONEGUT";
            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query, administrationID,
                    request, response, locale);
        }
    }

    // XYZ TODO Passat a pare web
    public static final String WEBRESOURCE = "webresource";

    /*
     * protected void getJavascriptCSS(HttpServletRequest request, String
     * absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
     * Locale languageUI) {
     * 
     * out.println("<script type=\"text/javascript\" src=\"" +
     * relativePluginRequestPath + WEBRESOURCE + "/js/jquery.js\"></script>");
     * out.println("<script type=\"text/javascript\" src=\"" +
     * relativePluginRequestPath + WEBRESOURCE + "/js/bootstrap.js\"></script>");
     * out.println("<link href=\"" + relativePluginRequestPath + WEBRESOURCE +
     * "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");
     * 
     * }
     */

}
