package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.LogCarpetaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
public interface LogCarpetaLogicaLocal extends LogCarpetaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/LogCarpetaLogicaEJB!es.caib.carpeta.logic.LogCarpetaLogicaLocal";

    void crearLog(String descripcio, int estat, int tipus, long temps, Throwable th, String error, String peticio, Long entidadID, Long pluginID ) throws I18NException;
}
