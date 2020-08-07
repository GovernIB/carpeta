package es.caib.carpeta.utils;

import java.io.File;

import es.caib.carpeta.commons.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    public static boolean isCAIB() {
        return Boolean.getBoolean(CARPETA_PROPERTY_BASE + "iscaib");
    }

    public static File getFilesDirectory() {
        String path = System.getProperty(CARPETA_PROPERTY_BASE + "filesdirectory");
        return new File(path);
    }

    public static boolean isDesenvolupament() {
        return Boolean.getBoolean(CARPETA_PROPERTY_BASE + "development");
    }

    public static String getAppUrl() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "url");
    }

    public static String getAppEmail() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "email.from");
    }

    public static String getAppName() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "name", "Carpeta");
    }

    public static String getDefaultLanguage() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "defaultlanguage", "ca");
    }

    public static byte[] getEncryptKey() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
    }

    public static Long getMaxUploadSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public static Long getMaxFitxerAdaptatSizeInBytes() {
        return Long.getLong(CARPETA_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }

}
