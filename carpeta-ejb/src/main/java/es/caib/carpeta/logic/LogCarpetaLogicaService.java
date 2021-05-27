package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import es.caib.carpeta.ejb.LogCarpetaService;
import es.caib.carpeta.persistence.LogCarpetaJPA;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
@Local
public interface LogCarpetaLogicaService extends LogCarpetaService {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/LogCarpetaLogicaEJB!es.caib.carpeta.logic.LogCarpetaLogicaService";

    public List<LogCarpetaJPA> findByEntidadByTipus(@NotNull String entitatCodi, @NotNull Integer tipus)throws I18NException;

    public void crearLog(String descripcio, int estat, int tipus, long temps, Throwable th, String error, String peticio, String entitatCodi, Long pluginID);
    
    /* Métode per registrar els errors que es produeixen als plugins */
    public void crearLogCarpeta(String descripcio, String error, String peticio, String entitatCodi, Long pluginID);
    
    public void esborrarLog(long dies) throws I18NException;
}
