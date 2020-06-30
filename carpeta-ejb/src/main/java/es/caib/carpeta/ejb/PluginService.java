package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.Plugin;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 09/06/2020
 */
@Local
public interface PluginService extends DAO<Plugin, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEJB!es.caib.carpeta.ejb.PluginService";

   /**
    * Crea un plugin asociado a la entidad indicada
    *
    * @param plugin plugin.
    * @param entidadId entidad a la que se asocia.
    * @return La plugin creada.
    */
   Plugin crearPlugin(Plugin plugin, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todos los plugins de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de plugins de la entidad
    */
   List<Plugin> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de plugins de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de plugins de la entidad.
    */
   Long countAllByEntidad(Long entidadId);


}
