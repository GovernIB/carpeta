package es.caib.carpeta.ejb;

import es.caib.carpeta.persistence.PropiedadPlugin;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 22/06/2020
 */
@Local
public interface PropiedadPluginService extends DAO<PropiedadPlugin, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/PropiedadPluginEJB!es.caib.carpeta.ejb.PropiedadPluginService";

   /**
    * Crea una propiedadPlugin dependiente de un plugin.
    *
    * @param propiedadPlugin      propiedadPlugin.
    * @param pluginId identificador del plugin
    * @return La propiedadPlugin creada.
    */
   PropiedadPlugin create(PropiedadPlugin propiedadPlugin, Long pluginId) throws I18NException;


   /**
    * Obtiene una lista de todos las propiedadPlugins de un plugin
    *
    * @param pluginId identificador de la plugin
    * @return lista de usuarioPlugins de la plugin
    */
   List<PropiedadPlugin> findAllByPlugin(Long pluginId);

   /**
    * Obtiene el numero de propiedadPlugin de un plugin
    *
    * @param pluginId identificador del plugin.
    * @return número de propiedadesPlugin del plugin.
    */
   Long countAllByPlugin(Long pluginId);
}
