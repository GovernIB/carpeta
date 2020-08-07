package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Enlace;
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
public interface EnlaceService extends DAO<Enlace, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/EnlaceEJB!es.caib.carpeta.ejb.EnlaceService";

   /**
    * Crea un enlace asociada a la entidad indicada
    *
    * @param enlace enlace.
    * @param entidadId entidad a la que se asocia.
    * @return el enlace creado.
    */
   Enlace crearEnlace(Enlace enlace, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos las enlace de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de enlaces de la entidad
    */
   List<Enlace> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de enlace de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de enlaces de la entidad.
    */
   Long countAllByEntidad(Long entidadId);
}
