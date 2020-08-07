package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Estadistica;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 10/06/2020
 */

@Local
public interface EstadisticaService extends DAO<Estadistica, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/EstadisticaEJB!es.caib.carpeta.ejb.EstadisticaService";

   /**
    * Crea un estadistica asociado a la entidad indicada
    *
    * @param estadistica estadistica.
    * @param entidadId entidad a la que se asocia.
    * @return La estadistica creada.
    */
   Estadistica crearEstadistica(Estadistica estadistica, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos los estadisticas de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de estadisticas de la entidad
    */
   List<Estadistica> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de estadisticas de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de estadisticas de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
