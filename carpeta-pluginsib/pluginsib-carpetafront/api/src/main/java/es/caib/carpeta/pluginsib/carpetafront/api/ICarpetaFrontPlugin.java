package es.caib.carpeta.pluginsib.carpetafront.api;

import org.fundaciobit.pluginsib.core.IPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 
 * 
 * @author anadal
 */
public interface ICarpetaFrontPlugin extends IPlugin {

    public static final String CARPETAFRONT_PROPERTY_BASE = IPLUGINSIB_BASE_PROPERTIES + "carpetafront.";
    
    public void setTitlesInfo(TitlesInfo titlesInfo);
    
    public TitlesInfo getTitlesInfo();

    public String getTitle(Locale locale);

    public String getSubTitle(Locale locale);

    public BasicServiceInformation existsInformation(UserData userData) throws Exception;

    public String getStartUrl(String absolutePluginRequestPath, String relativePluginRequestPath,
            HttpServletRequest request, UserData userdata, String administrationEncriptedID) throws Exception;

    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, String administrationID, String administrationEncriptedID, Locale locale, boolean isGet)
            throws Exception;

    public FileInfo getIcon(Locale locale);
    
    public boolean isReactComponent();
}
