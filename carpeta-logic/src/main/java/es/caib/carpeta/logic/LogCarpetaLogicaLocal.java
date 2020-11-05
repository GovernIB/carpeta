package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.LogCarpetaLocal;
import es.caib.carpeta.jpa.LogCarpetaJPA;

import java.util.*;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */
@Local
public interface LogCarpetaLogicaLocal extends LogCarpetaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/LogCarpetaLogicaEJB!es.caib.carpeta.logic.LogCarpetaLogicaLocal";

    public List<LogCarpetaJPA> findByEntidadByTipus(@NotNull Long entitatId, @NotNull Integer tipus)throws I18NException;

    void crearLog(String descripcio, int estat, int tipus, long temps, Throwable th, String error, String peticio, Long entidadID, Long pluginID ) throws I18NException;
}
