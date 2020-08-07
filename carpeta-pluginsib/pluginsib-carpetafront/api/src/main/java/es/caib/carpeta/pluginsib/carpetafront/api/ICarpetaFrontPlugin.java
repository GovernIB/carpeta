package es.caib.carpeta.pluginsib.carpetafront.api;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * 
 * @author anadal
 */
public interface ICarpetaFrontPlugin extends IPlugin {

    public static final String CARPETAFRONT_PROPERTY_BASE = IPLUGINSIB_BASE_PROPERTIES + "carpetafront.";

    public String getTitle(Locale locale);

    public String getSubTitle(Locale locale);

    public BasicServiceInformation existsInformation(UserData administrationID) throws Exception;

    public void requestGET(String absolutePluginRequestPath, String relativePluginRequestPath, String encriptedUserID,
            String query, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void requestPOST(String absolutePluginRequestPath, String relativePluginRequestPath, String encriptedUserID,
            String query, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
