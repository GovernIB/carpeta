package es.caib.carpeta.utils;


import org.apache.log4j.Logger;

import static es.caib.carpeta.utils.CarpetaConstantes.CARPETA_PROPERTY_BASE;

public class Propiedad {

    protected static final Logger log = Logger.getLogger(Propiedad.class);

    // Propiedades de REGWEB3
    public static String getHostRegWeb3() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "regweb3.host");
    }
    public static String getApiRegWeb3() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "regweb3.api");
    }
    public static String getEntidadRegWeb3() { return System.getProperty(CARPETA_PROPERTY_BASE + "regweb3.entidad");
    }
    public static String getUserRegWeb3() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "regweb3.user");
    }
    public static String getPassRegWeb3() {
        return System.getProperty(CARPETA_PROPERTY_BASE + "regweb3.pass");
    }


    // Propiedades de LOGINIB


    // Propiedades de SISTRA2


}
