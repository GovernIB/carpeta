package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.commons.utils.StringUtils;
import es.caib.carpeta.ejb.LogCarpetaEJB;
import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.LogCarpetaFields;


/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
@Stateless
public class LogCarpetaLogicaEJB extends LogCarpetaEJB implements LogCarpetaLogicaService  {


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
    public void crearLog(String descripcio, int estat, int tipus, long temps, Throwable th, String error, String peticio, String entitatCodi, Long pluginID, String idSessio) {

        String exception = null;
        if(th != null) {
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            th.printStackTrace(pw);
            exception = sw.getBuffer().toString();
            if (StringUtils.isEmpty(error)) {
                error = th.getMessage();
            }
        }
        
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
        logCarpeta.setIdSessio(idSessio);

        try {
            create(logCarpeta);
        } catch(Throwable t) {

            log.error(" ==============================================" );
            log.error(" TIPUS EXCEPCIO: " + t.getClass());
            log.error(th.getMessage(), t);

        }


    }
    
    
    /*
     * Métode per registrar els errors que es produeixen als plugins
     */
    @Override
    public void crearLogCarpeta(String descripcio, String error, String peticio, String entitatCodi, Long pluginID, String idSessio){
    	
        LogCarpetaJPA logCarpeta = new LogCarpetaJPA();
        logCarpeta.setTipus(Constants.TIPUS_LOG_PLUGIN_FRONT);
        logCarpeta.setTemps(System.currentTimeMillis());
        logCarpeta.setDescripcio(descripcio);
        logCarpeta.setDataInici(new Timestamp(System.currentTimeMillis()));
        logCarpeta.setEstat(Constants.ESTAT_LOG_ERROR);
        logCarpeta.setExcepcio(null);
        logCarpeta.setError(error);
        logCarpeta.setPeticio(peticio);
        logCarpeta.setEntitatCodi(entitatCodi);
        logCarpeta.setPluginID(pluginID);
        logCarpeta.setIdSessio(idSessio);

        try {
            create(logCarpeta);
        } catch(Throwable t) {

            log.error(" ==============================================" );
            log.error(" TIPUS EXCEPCIO: " + t.getClass());

        }


    }
    
    @Override
    public void esborrarLog(long dies) throws I18NException {
    	
    	LocalDateTime avui = LocalDateTime.now(); 
    	avui.toLocalDate().minusDays(dies);
    
    	Where w = LogCarpetaFields.DATAINICI.greaterThanOrEqual(Timestamp.valueOf(avui));
    	
    	if (Configuracio.isDesenvolupament()){
    		List<LogCarpeta> llista = select(w);
    		for(LogCarpeta l : llista) {
    			log.info("Eliminam registre " + l.getLogID() + " de la entitat " + l.getEntitatCodi() + " amb data " + l.getDataInici());    		
    		}
    	}
    	
    	delete(w);
    	
    }






}
