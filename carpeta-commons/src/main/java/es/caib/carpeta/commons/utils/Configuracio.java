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

    static {
        //matches the property name as defined in the system-properties element in WildFly
        String propertyFile = System.getProperty("es.caib.carpeta.properties.path");
        File file = new File(propertyFile);

        try {
            properties.load(new FileInputStream(file));
        } catch (
           IOException e) {
            LOG.error("No es pot carregar el fitxer de propietats", e);
        }

    }




    public static String getProperty(String key) {

        return (String) properties.get(key);

    }


    public static String getProperty(String key, String def) {

        if(properties.get(key) != null) {
            return (String) properties.get(key);
        }else {
            return def;
        }
    }

    public  static boolean isDesenvolupament() {

        return Boolean.parseBoolean(getProperty(CARPETA_PROPERTY_BASE + "development"));
    }

    public static boolean isCAIB() {
        return Boolean.getBoolean(getProperty(CARPETA_PROPERTY_BASE + "iscaib"));
    }

    public  static String getAppUrl() { return getProperty(CARPETA_PROPERTY_BASE + "url");
    }

    public  static String getAppEmail() { return getProperty(CARPETA_PROPERTY_BASE + "email.from");
    }

    public  static String getAppName() {
        return getProperty(CARPETA_PROPERTY_BASE + "name","carpeta");
    }

    public  static String getDefaultLanguage() {
        return getProperty(CARPETA_PROPERTY_BASE  + "defaultlanguage", "ca");
    }

    public  static byte[] getEncryptKey() {
        return getProperty(CARPETA_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
    }

    public  static Long getMaxUploadSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public  static Long getMaxFitxerAdaptatSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }

}
