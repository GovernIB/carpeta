package es.caib.carpeta.ejb;


import es.caib.carpeta.commons.query.OrderBy;
import es.caib.carpeta.commons.query.OrderType;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Servicio EJB para gestionar {@link Entidad}. Le aplicamos el interceptor {@link Logged}, por
 *   tanto, todas las llamadas se loguearan.
 *
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 09/06/2020
 */

@Logged
@Stateless
@RolesAllowed(Constants.CAR_ADMIN)
public class EntidadEJB extends AbstractDAO<Entidad, Long> implements EntidadService {



   @Override
   public Entidad crearEntidad(Entidad entidad) throws I18NException {

         //TODO crear todos los datos asociados
         //creamos las propiedades globales por defecto
         //plugins
         create(entidad);
         return entidad;

   }

   @Override
   public Entidad findByCodigoDir3(String codigo) throws I18NException {
      Map<String, Object> filters = new HashMap<>();
      filters.put("codigoDir3", codigo);

      List<Entidad> entidadList = findFiltered(filters, new OrderBy("id"));
      if(entidadList!= null) {
         return entidadList.get(0);
      }else{
         return null;
      }
   }


   @Override
   public List<Entidad> findAll() throws I18NException {

      OrderBy orderBy = new OrderBy("nombre", OrderType.ASC);
      return findAll(orderBy);
   }




   @Override
   public void eliminarEntidad(Long id) throws I18NException {

      //TODO falta borrar los datos asociados
      delete(id);
   }






}
