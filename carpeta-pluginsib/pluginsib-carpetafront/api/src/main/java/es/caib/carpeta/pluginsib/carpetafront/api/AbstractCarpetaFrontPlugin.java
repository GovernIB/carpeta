package es.caib.carpeta.pluginsib.carpetafront.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
    // ------------------- CACHE DE USERDATA --------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------

    public static class InternalUserData extends UserData {

        public final long startDate;

        public InternalUserData(String name, String surname1, String surname2, String administrationID, 
                String authenticationMethod, String qaa) {
            super(name, surname1, surname2, administrationID,  authenticationMethod, qaa);
            this.startDate = System.currentTimeMillis();
        }

        public InternalUserData(UserData ud) {
            super(ud.name, ud.surname1, ud.surname2, ud.administrationID, ud.authenticationMethod, ud.qaa);
            this.startDate = System.currentTimeMillis();
        }

        public InternalUserData(String administrationID) {
            super(administrationID);
            this.startDate = System.currentTimeMillis();
        }

    }

    private static final Map<String, InternalUserData> userDataMap = new HashMap<String, AbstractCarpetaFrontPlugin.InternalUserData>();

    protected void registerUserData(UserData userData) {
        synchronized (userDataMap) {
            cleanOldUserData();
            userDataMap.put(userData.getAdministrationID(), new InternalUserData(userData));

        }

    }

    protected UserData getUserData(String administrationID) {
        synchronized (userDataMap) {
            cleanOldUserData();
            return userDataMap.get(administrationID);
        }

    }

    // 4 Hores
    protected long SESSION_EXPIRED = 4 * 60 * 60 * 1000;

    protected void cleanOldUserData() {

        // 4 hores esborram
        Set<String> itemsToDelete = new HashSet<String>();

        long now = System.currentTimeMillis();

        for (InternalUserData iud : userDataMap.values()) {
            if (iud.startDate + SESSION_EXPIRED < now) {
                itemsToDelete.add(iud.getAdministrationID());
            }
        }

        for (String nif : itemsToDelete) {
            userDataMap.remove(nif);
        }

    }

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- REQUEST GET-POST ---------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------

    @Override
    public final void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath,
            String query, HttpServletRequest request, HttpServletResponse response, String administrationID,
            String administrationEncriptedID, Locale locale, boolean isGet) {
        if (administrationID == null || administrationID.trim().length() == 0) {
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
                    + " No s'ha passat AdministrationID";

            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query, administrationID,
                    request, response, locale);
        } else {

            UserData userData = getUserData(administrationID);

            if (userData == null) {
                String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
                        + ": No tenim informació de l´usuari amb AdministrationID [" + administrationID
                        + "]. Recordi a cridar  registerUserData() en el mètode getStartUrl()";

                requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
                        administrationID, request, response, locale);
            } else {

                requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet);

            }

        }

    }

    /**
    * 
    */
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet) {

        if (query.startsWith(WEBRESOURCE)) {

            retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath, userData.getAdministrationID(),
                    query, request, response, locale);

        } else {
            // XYZ Fer un missatges com toca
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca")) + " DESCONEGUT";
            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
                    userData.getAdministrationID(), request, response, locale);
        }
    }

    // XYZ TODO Passat a pare web
    public static final String WEBRESOURCE = "webresource";

    public static final String LOGORESOURCE = "logo";

    @Override
    public boolean isReactComponent() {
        return false;
    }

}
