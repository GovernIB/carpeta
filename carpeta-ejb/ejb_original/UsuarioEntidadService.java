package es.caib.carpeta.ejb;


import es.caib.carpeta.persistence.UsuarioEntidad;
import es.caib.carpeta.persistence.dao.DAO;


import javax.ejb.Local;

import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 09/06/2020
 */
@Local
public interface UsuarioEntidadService extends DAO<UsuarioEntidad, Long > {

   public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuarioEntidadEJB!es.caib.carpeta.ejb.UsuarioEntidadService";


   /**
    * Obtiene una lista de todos los usuarioEntidads de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de usuarioEntidads de la entidad
    */
   List<UsuarioEntidad> findAllByEntidad(Long entidadId);

   /**
    * Obtiene el numero de usuarioEntidad de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de usuarioEntidads de la entidad.
    */
   Long countAllByEntidad(Long entidadId);
   
   
   
   
   public List<UsuarioEntidad> findAllByPersonaId(@NotNull long usuarioID);

   
   

}
