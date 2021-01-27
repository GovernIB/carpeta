package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;

import es.caib.carpeta.commons.utils.StringUtils;
import es.caib.carpeta.ejb.LogCarpetaEJB;
import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.*;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
@Stateless
public class LogCarpetaLogicaEJB extends LogCarpetaEJB implements LogCarpetaLogicaService {


    @Override
    public List<LogCarpetaJPA> findByEntidadByTipus(@NotNull String entitatCodi, @NotNull Integer tipus)throws I18NException {

        List<LogCarpeta> logs = select(Where.AND(LogCarpetaFields.ENTITATCODI.equal(entitatCodi),
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


    @Override
    public void crearLog(String descripcio, int estat, int tipus, long temps, Throwable th, String error, String peticio, String entitatCodi, Long pluginID) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        if(th != null) {
            th.printStackTrace(pw);

            if (StringUtils.isEmpty(error)){
                error = th.getMessage();
            }
        }
        String exception = sw.getBuffer().toString();
        LogCarpetaJPA logCarpeta = new LogCarpetaJPA();
        logCarpeta.setTipus(tipus);
        logCarpeta.setTemps(temps);
        logCarpeta.setDescripcio(descripcio);
        logCarpeta.setDataInici(new Timestamp(System.currentTimeMillis()));
        logCarpeta.setEstat(estat);
        logCarpeta.setExcepcio(exception);
        logCarpeta.setError(error);
        logCarpeta.setPeticio(peticio);
        logCarpeta.setEntitatCodi(entitatCodi);
        logCarpeta.setPluginID(pluginID);


        try {
            create(logCarpeta);
        } catch(Throwable t) {

            log.error(" ==============================================" );
            log.error(" TIPUS EXCEPCIO: " + t.getClass());
            log.error(th.getMessage(), t);

        }


    }






}
