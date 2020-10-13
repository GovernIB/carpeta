package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.LogCarpetaEJB;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
public class LogCarpetaLogicaEJB extends LogCarpetaEJB implements LogCarpetaLogicaLocal {

    @Override
    public LogCarpeta createFull(LogCarpeta logCarpeta) throws I18NException {
        // TODO Validar !!!

        return (LogCarpeta)super.create(logCarpeta);
    }




}
