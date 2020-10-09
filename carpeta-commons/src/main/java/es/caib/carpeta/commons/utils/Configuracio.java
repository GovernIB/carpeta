package es.caib.carpeta.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final Properties properties = new Properties();


    public static void getFileProperties(){
        //matches the property name as defined in the system-properties element in WildFly
        String propertyFile = System.getProperty(Constants.CARPETA_PROPERTY_BASE + "properties.path");
        File file = new File(propertyFile);

        try {
            properties.load(new FileInputStream(file));
        } catch (
           IOException e) {
            LOG.error("No es pot carregar el fitxer de propietats", e);
        }

    }


    public static Properties getSystemAndFileProperties(){

        getFileProperties();
        properties.putAll(System.getProperties());

        return properties;
    }

    public static String getProperty(String key) {

        return (String) properties.get(key);

    }


    public  static String getProperty(String key, String def) {

        if(properties.get(key) != null) {
            return (String) properties.get(key);
        }else {
            return def;
        }
    }


    public static boolean isDesenvolupament() {

        return Boolean.parseBoolean(getProperty(CARPETA_PROPERTY_BASE + "development"));
    }

    public static boolean isCAIB() {
        return Boolean.getBoolean(getProperty(CARPETA_PROPERTY_BASE + "iscaib"));
    }

    public static String getAppUrl() { return getProperty(CARPETA_PROPERTY_BASE + "url");
    }

    public static String getAppEmail() { return getProperty(CARPETA_PROPERTY_BASE + "email.from");
    }

    public static String getAppName() {
        return getProperty(CARPETA_PROPERTY_BASE + "name","carpeta");
    }

    public static String getDefaultLanguage() {
        return getProperty(CARPETA_PROPERTY_BASE  + "defaultlanguage", "ca");
    }

    public static byte[] getEncryptKey() {
        return getProperty(CARPETA_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
    }

    public static Long getMaxUploadSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public static Long getMaxFitxerAdaptatSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }
    
    public static File getFilesDirectory() {
        String path = getProperty(CARPETA_PROPERTY_BASE + "filesdirectory");
        return new File(path);
    }


    public static String getLoginIBMethodAuth() { return getProperty(CARPETA_PROPERTY_LOGINIB + "metodos_auth");
    }

    public static String getLoginIBEntidad() { return getProperty(CARPETA_PROPERTY_LOGINIB + "entidad");
    }

    public static String getLoginIBAplicacion() { return getProperty(CARPETA_PROPERTY_LOGINIB + "aplicacion");
    }

    public static String getLoginIBUrlCallbackLogin() { return getProperty(CARPETA_PROPERTY_LOGINIB + "url_callback_login");
    }

    public static String getLoginIBUrlCallbackError() { return getProperty(CARPETA_PROPERTY_LOGINIB + "url_callback_error");
    }

    public static String getLoginIBUrlCallbackLogout() { return getProperty(CARPETA_PROPERTY_LOGINIB + "url_callback_logout");
    }

    public static String getLoginIBIdioma() { return getProperty(CARPETA_PROPERTY_LOGINIB + "idioma");
    }

    public static String getLoginIBNivelQAA() { return getProperty(CARPETA_PROPERTY_LOGINIB + "nivel_qaa");
    }

    public static String getLoginIBUser() { return getProperty(CARPETA_PROPERTY_LOGINIB + "user");
    }

    public static String getLoginIBPassword() { return getProperty(CARPETA_PROPERTY_LOGINIB + "pass");
    }

    public static String getLoginIBUrl() { return getProperty(CARPETA_PROPERTY_LOGINIB + "url");
    }



}
