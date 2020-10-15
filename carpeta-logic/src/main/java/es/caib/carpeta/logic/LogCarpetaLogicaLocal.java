package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.LogCarpetaLocal;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
public interface LogCarpetaLogicaLocal extends LogCarpetaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/LogCarpetaLogicaEJB!es.caib.carpeta.logic.LogCarpetaLogicaLocal";

    LogCarpetaJPA crearLog(LogCarpetaJPA logCarpeta) throws I18NException;
}
