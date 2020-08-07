package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Aviso;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/06/2020
 */

@Local
public interface AvisoService extends DAO<Aviso, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/AvisoEJB!es.caib.carpeta.ejb.AvisoService";

   /**
    * Crea un aviso asociado a la entidad indicada
    *
    * @param aviso aviso.
    * @param entidadId entidad a la que se asocia.
    * @return La aviso creada.
    */
   Aviso crearAviso(Aviso aviso, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos los avisos de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de avisos de la entidad
    */
   List<Aviso> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de avisos de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de avisos de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
