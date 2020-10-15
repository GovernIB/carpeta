package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.LogCarpetaEJB;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
@Stateless
public class LogCarpetaLogicaEJB extends LogCarpetaEJB implements LogCarpetaLogicaLocal {

    @Override
    public LogCarpetaJPA crearLog(LogCarpetaJPA logCarpeta) throws I18NException {
        // TODO Validar !!!

        try {
            create(logCarpeta);
        } catch(Throwable th) {

            log.error(" ==============================================" );
            log.error(" TIPUS EXCEPCIO: " + th.getClass());
            log.error(th.getMessage(), th);

            if (th instanceof I18NException) {
                throw (I18NException)th;
            } else {
                throw new I18NException("comodi", th.getMessage());
            }

        }

        return logCarpeta;
    }


    public List<LogCarpetaJPA> findByEntidadByTipus(@NotNull Long entitatId, @NotNull Integer tipus)throws I18NException {

        List<LogCarpeta> logs = select(Where.AND(LogCarpetaFields.ENTITATID.equal(entitatId),
           LogCarpetaFields.TIPUS.equal(tipus))); ;

        if (logs == null || logs.size() == 0) {
            return null;
        } else {

            List<LogCarpetaJPA> list2 = new ArrayList<LogCarpetaJPA>(logs.size());
            for (LogCarpeta logCarpeta : logs) {
                list2.add((LogCarpetaJPA) logCarpeta);
            }

            return list2;

        }
    }






}
