package es.caib.carpeta.core.utils;

import es.caib.carpeta.core.service.RegWeb3ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {

    private final Logger log = LoggerFactory.getLogger(RegWeb3ServiceImpl.class);


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
