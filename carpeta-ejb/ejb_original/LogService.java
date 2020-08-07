package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Log;
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
public interface LogService extends DAO<Log, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/LogEJB!es.caib.carpeta.ejb.LogService";

   /**
    * Crea un log asociado a la entidad indicada
    *
    * @param log log.
    * @param entidadId entidad a la que se asocia.
    * @return La log creada.
    */
   Log crearLog(Log log, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos los logs de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de logs de la entidad
    */
   List<Log> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de logs de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de logs de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
