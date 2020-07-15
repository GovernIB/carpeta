package es.caib.carpeta.commons.utils;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 
 * @author anadal
 *
 */
public class StringUtils {

    //private final Logger log = LoggerFactory.getLogger(StringUtils.class);


    /**
     * Comprueba si una cadena está vacia ("") o es null.
     * @param cadena
     * @return
     */
    public static boolean isEmpty(final String cadena) {
        return cadena == null || cadena.length() == 0;
    }

    /**
     * Comprueba si una cadena no está vacia ("") o es null.
     * @param cadena
     * @return
     */
    public static boolean isNotEmpty(final String cadena) {
        return !isEmpty(cadena);
    }

}
