package es.caib.carpeta.pluginsib.carpetafront.api;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
    public final String getTitle(Locale locale) {
        
        String tipus = "title";
        String lang = locale.getLanguage();
        
        return getTitolSubtitol(tipus, lang);
    }
    
    
    @Override
    public final String getSubTitle(Locale locale) {
        
        String tipus = "subtitle";
        String lang = locale.getLanguage();
        
        return getTitolSubtitol(tipus, lang);
        
    }

    protected String getTitolSubtitol(String tipus, String lang) {
        String value = getProperty(getPropertyKeyBase() + tipus + "." + lang);
        
        if (value == null) {
            if (getTitlesInfo() != null) {
                if ("title".equals(tipus)) {
                    value = getTitlesInfo().getTitlesByLang().get(lang);
                } else {
                    value = getTitlesInfo().getSubtitlesByLang().get(lang); 
                }
            } 
        }
        
        if (value == null) {
            value="Algun error ha provocat que no es pogues obtenir el " 
                + tipus + " de la informació de títols/subtitols passada a traves del mètode setTitlesInfo(). "
                + " També pot definir la propietat [" + getPropertyBase() + tipus + "." + lang + "]";
        }
        
        return value;
    }

   
    @Override
    public boolean isPublic() {
        return false;
    }


    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- CACHE DE USERDATA --------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------






    protected static class InternalUserData extends UserData {

        public final long startDate;

        public InternalUserData(String name, String surname1, String surname2, String administrationID,
                String authenticationMethod, int qaa) {
            super(name, surname1, surname2, administrationID, authenticationMethod, qaa);
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
    
    
    
    
    

    private final Map<String, InternalUserData> userDataMap = new HashMap<String, AbstractCarpetaFrontPlugin.InternalUserData>();

    protected void registerUserData(UserData userData) {
        if (userData != null) {
            synchronized (userDataMap) {
                cleanOldUserData();
                userDataMap.put(userData.getAdministrationID(), new InternalUserData(userData));
    
            }
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
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {
        if (administrationID == null || administrationID.trim().length() == 0) {
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
                    + " No s'ha passat AdministrationID";

            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query, administrationID,
                    request, response, locale);
        } else {

            UserData userData = getUserData(administrationID);

            if (userData == null && !isPublic()) {
                String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
                        + ": No tenim informació de l´usuari amb AdministrationID [" + administrationID
                        + "]. Recordi a cridar  registerUserData() en el mètode getStartUrl()";

                requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
                        administrationID, request, response, locale);
            } else {

                requestCarpetaFront(absolutePluginRequestPath, relativePluginRequestPath, query, request, response,
                        userData, administrationEncriptedID, locale, isGet, logCarpeta);

            }

        }

    }

    /**
    * 
    */
    public void requestCarpetaFront(String absolutePluginRequestPath, String relativePluginRequestPath, String query,
            HttpServletRequest request, HttpServletResponse response, UserData userData,
            String administrationEncriptedID, Locale locale, boolean isGet, IListenerLogCarpeta logCarpeta) {
        
        String administrationID;
        if (userData == null) {
            administrationID = null;
        } else {
            administrationID = userData.getAdministrationID();
        }
        

        if (query.startsWith(WEBRESOURCE)) {

            retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath, administrationID,
                    query, request, response, locale);

        } else if (query.startsWith(WEBRESOURCECOMMON)) {

            retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath, administrationID,
                    query, request, response, locale);

        } else {
            // XYZ Fer un missatges com toca
            String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca")) + " DESCONEGUT";
            requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
                    administrationID, request, response, locale);
        }
    }

    // XYZ TODO Passat a pare web
    public static final String WEBRESOURCE = "webresource";

    public static final String WEBRESOURCECOMMON = "webresourcecommon";

    public static final String LOGORESOURCE = "logo";

    @Override
    public boolean isReactComponent() {
        return false;
    }

    public abstract String getPropertyBase();

    protected abstract FileInfo getResourceIcon(Locale locale);
    
    
    protected void esperaPage(String absolutePluginRequestPath, HttpServletResponse response, Locale locale,
            String rutaDesti) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        
        InputStream input = this.getClass().getResourceAsStream("/" + WEBRESOURCECOMMON + "/templates/espera.html");
        String plantilla = IOUtils.toString(input, "UTF-8");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resources", absolutePluginRequestPath + "/" + WEBRESOURCECOMMON);
        map.put("ruta_desti", rutaDesti);            
        
        
        String webpage = TemplateEngine.processExpressionLanguage(plantilla, map, locale);

        response.getWriter().println(webpage);
        response.flushBuffer();
    }

    /**
     * Mètode que retorna la icona del plugin
     * 
     * @param locale
     * @return
     */
    @Override
    public final FileInfo getIcon(Locale locale) {

        String logo = getProperty(getPropertyBase() + "logo");
        FileInfo fileInfo;
        try {

            if (logo == null || logo.trim().length() == 0) {
                
                
                fileInfo = getResourceIcon(locale);
            } else {
                logo = logo.trim();
                if ("EMPTY".equals(logo)) {
                    fileInfo = null;
                } else if (logo.toLowerCase().startsWith("http")) {
                    // URL
                    fileInfo = downloadImagePNG(logo);
                } else {
                    // FILE
                    fileInfo = imageFromFile(logo);
                }

            }

        } catch (Exception e) {
            log.error("Error llegint imatge " + logo + ": " + e.getMessage(), e);
            fileInfo = null;
        }
        return fileInfo;
    }

    protected FileInfo getImageFromResource(Locale locale, String resourcePath, String mime) {
        
        FileInfo fileInfo;
        
        try {
            
        
        
        InputStream input;
        
        if (resourcePath == null) {
            log.error("No s'ha definit getResourceLogoPNG() per aquest plugin " + this.getTitle(locale));
            fileInfo = null;
        } else {

            // Agafa la icona del resource
            input = this.getClass().getResourceAsStream(resourcePath);
            if (input == null) {
                fileInfo = null;
            } else {
                fileInfo = new FileInfo(resourcePath.replace('/', '_').replace('\\', '_'), mime,
                        IOUtils.toByteArray(input));
            }

        }
        
        } catch (Exception e) {
            log.error("Error llegint imatge " + resourcePath + " des de resources: " + e.getMessage(), e);
            fileInfo = null;
        }
        return fileInfo;
    }

    protected FileInfo imageFromFile(String sourceUrl) throws Exception {
        File f = new File(sourceUrl);

        InputStream imageReader = new FileInputStream(f);
        ByteArrayOutputStream imageWriter = new ByteArrayOutputStream();
        try {
            int readByte;

            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        } finally {
            imageReader.close();
        }

        return new FileInfo(f.getName(), "image/png", imageWriter.toByteArray());

    }

    protected FileInfo downloadImagePNG(String sourceUrl) throws Exception {
        URL imageUrl = new URL(sourceUrl);

        InputStream imageReader = new BufferedInputStream(imageUrl.openStream());
        ByteArrayOutputStream imageWriter = new ByteArrayOutputStream();
        {
            int readByte;

            while ((readByte = imageReader.read()) != -1) {
                imageWriter.write(readByte);
            }
        }

        return new FileInfo(sourceUrl.replace(':', '_').replace('/', '_'), "image/png", imageWriter.toByteArray());

    }

}
