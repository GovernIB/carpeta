package es.caib.carpeta.logic;

import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
public interface LogCarpetaLogicaLocal {

    LogCarpeta createFull(LogCarpeta logCarpeta) throws I18NException;
}
