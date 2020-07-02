package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Acceso;
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
public interface AccesoService extends DAO<Acceso, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/AccesoEJB!es.caib.carpeta.ejb.AccesoService";

   /**
    * Crea un acceso asociado a la entidad indicada
    *
    * @param acceso acceso.
    * @param entidadId entidad a la que se asocia.
    * @return La acceso creada.
    */
   Acceso crearAcceso(Acceso acceso, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos los accesos de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de accesos de la entidad
    */
   List<Acceso> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de accesos de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de accesos de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
