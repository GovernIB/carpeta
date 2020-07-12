package es.caib.carpeta.ejb;


import es.caib.carpeta.persistence.Entidad;
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
public interface EntidadService extends DAO<Entidad, Long> {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/EntidadEJB!es.caib.carpeta.ejb.EntidadService";


   /**
    * Crea una entidad y los datos asociados por defecto a la entidad
    *
    * @param entidad   entidad.
    * @return La entidad creada.
    */
   Entidad crearEntidad(Entidad entidad) throws I18NException;


   /**
    * Retorna la Entidad cuyo CódigoDir3 es el indicado por parámetro
    * @param codigo
    * @return
    * @throws I18NException
    */
   Entidad findByCodigoDir3(String codigo) throws I18NException;


   /**
    * Retorna todas las entidades ordenadas por nombre
    * @return
    * @throws I18NException
    */
   List<Entidad> findAll() throws I18NException;


   /**
    * Retorna las entidades con los atributos mínimos para mostrar en el listado
    *
    * En el caso de las entidades
    * @return
    * @throws I18NException
    */
   List<Entidad> findAllList() throws I18NException;




   /**
    * Elimina una Entidad y todos sus datos dependientes
    * @param id
    * @throws I18NException
    */
   void eliminarEntidad(Long id) throws I18NException;



}
