package es.caib.carpeta.ejb;


import es.caib.carpeta.persistence.PropiedadGlobal;
import es.caib.carpeta.persistence.dao.DAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 22/06/2020R
 */

public interface PropiedadGlobalService extends DAO<PropiedadGlobal, Long> {



   public static final String JNDI_NAME = "java:app/carpeta-ejb/PropiedadGlobalEJB!es.caib.carpeta.ejb.PropiedadGlobalService";

   /**
    * Crea un propiedadGlobal asociada a la entidad indicada
    *
    * @param propiedadGlobal propiedadGlobal.
    * @param entidadId entidad a la que se asocia.
    * @return La propiedadGlobal creada.
    */
   PropiedadGlobal crearPropiedadGlobal(PropiedadGlobal propiedadGlobal, Long entidadId) throws I18NException;


   /**
    * Obtiene una lista de todas las propiedadGlobal de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de propiedadGlobals de la entidad
    */
   List<PropiedadGlobal> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de propiedadGlobal de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de propiedadGlobals de la entidad.
    */
   Long countAllByEntidad(Long entidadId);
}
