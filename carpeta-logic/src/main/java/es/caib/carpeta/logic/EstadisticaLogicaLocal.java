package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.EstadisticaLocal;
import es.caib.carpeta.jpa.EstadisticaJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
import java.util.*;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/10/2020
 */
@Local
public interface EstadisticaLogicaLocal extends EstadisticaLocal {
   public static final String JNDI_NAME = "java:app/carpeta-logic/EstadisticaLogicaEJB!es.caib.carpeta.logic.EstadisticaLogicaLocal";

   public List<EstadisticaJPA> findEstadistica(Integer tipus, Long entitatID, Date data, Long pluginID)throws I18NException;

   public void crearEstadistica(Long entitatID, @NotNull int tipus, Long pluginID) throws I18NException;

   public void incrementarComptador(@NotNull EstadisticaJPA estadisticaJPA) throws I18NException;


}
