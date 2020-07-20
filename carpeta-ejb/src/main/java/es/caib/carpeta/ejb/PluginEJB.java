package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Entidad;
import es.caib.carpeta.persistence.Plugin;
import es.caib.carpeta.persistence.dao.AbstractDAO;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 *
 * Servicio EJB para gestionar {@link Plugin}. Le aplicamos el interceptor {@link Logged}, por
 *   tanto, todas las llamadas se loguearan.
 *
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 09/06/2020
 */

@Logged
@Stateless
@RolesAllowed(Constants.CAR_SUPER)
public class PluginEJB extends AbstractDAO<Plugin, Long> implements PluginService{

   @Override
   public Plugin crearPlugin(Plugin plugin, Long entidadId) throws I18NException {

         if(entidadId != null) {
            try {
               Entidad entidad = entityManager.getReference(Entidad.class, entidadId);
               plugin.setEntidad(entidad);
            } catch (EntityNotFoundException e) {
               throw new I18NException("entidad.noexisteix", String.valueOf(entidadId));
            }
         }
         entityManager.persist(plugin);
         return plugin;

   }

   /**
    * Obtiene una lista de todos los plugins de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de plugins de la entidad
    */
   @Override
   public List<Plugin> findAllByEntidad(Long entidadId) {
      return findAllByEntidad(entidadId, null);
   }

   /**
    * Obtiene el numero de plugins de una unidad orgánica
    *
    * @param entidadId identificador de la entidad.
    * @return número de plugins de la entidad.
    */
   @Override
   public Long countAllByEntidad(Long entidadId) {
      return countAllByEntidad(entidadId,null);
   }








   /**
    * Obtiene una lista de los plugins de una entidad que cumplen un filtro.
    * La cadena de filtro se busca dentro de los campos de tipo string
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return llista de plugins de la entidad que cumplen el filtro.
    */
   @PermitAll
   public List<Plugin> findAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Plugin> cq = cb.createQuery(Plugin.class);
      final Root<Plugin> root = cq.from(Plugin.class);
      cq.select(root);
      cq.where(
         cb.and(
            cb.equal(root.get("entidad").get("id"), entidadId),
            getFilterPredicate(root, filters)
         )
      );

      TypedQuery<Plugin> query = entityManager.createQuery(cq);
      return query.getResultList();
   }

   /**
    * Obtiene el número de plugins de una entidad que cumplen un filtro.
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return número de plugins de la entidad que cumplen el filtro.
    */
   @PermitAll
   public long countAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<Plugin> root = cq.from(Plugin.class);
      cq.select(cb.count(root));
      cq.where(
         cb.and(
            cb.equal(root.get("entidad").get("id"), entidadId),
            getFilterPredicate(root, filters)
         )
      );

      TypedQuery<Long> query = entityManager.createQuery(cq);
      return query.getSingleResult();
   }
}
