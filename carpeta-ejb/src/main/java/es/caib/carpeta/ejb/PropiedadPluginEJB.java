package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Plugin;
import es.caib.carpeta.persistence.PropiedadPlugin;
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

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 22/06/2020
 */
@Logged
@Stateless
@RolesAllowed(Constants.CAR_ADMIN)
public class PropiedadPluginEJB extends AbstractDAO<PropiedadPlugin, Long> implements PropiedadPluginService{

   /**
    * Crea una propiedadPlugin dependiente de un plugin.
    *
    * @param propiedadPlugin      propiedadPlugin.
    * @param pluginId identificador del plugin
    * @return La propiedadPlugin creada.
    */
   @Override
   public PropiedadPlugin create(PropiedadPlugin propiedadPlugin, Long pluginId) throws I18NException {
      try {
         Plugin plugin = entityManager.getReference(Plugin.class, pluginId);
         propiedadPlugin.setPlugin(plugin);
         entityManager.persist(propiedadPlugin);
         return propiedadPlugin;
      } catch (EntityNotFoundException e) {
         throw new I18NException("plugin.noexisteix", String.valueOf(pluginId));
      }
   }


   /**
    * Obtiene una lista de las propiedadesPlugin de un plugin.
    * La cadena de filtro se busca dentro de los campos de tipo string
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador del plugin
    * @return lista de propiedadPlugin del plugin.
    */
   @PermitAll
   public List<PropiedadPlugin> findAllByPlugin(Long entidadId) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<PropiedadPlugin> cq = cb.createQuery(PropiedadPlugin.class);
      final Root<PropiedadPlugin> root = cq.from(PropiedadPlugin.class);
      cq.select(root);
      cq.where(
         cb.and(
            cb.equal(root.get("plugin").get("id"), entidadId),
            getFilterPredicate(root, null)
         )
      );

      TypedQuery<PropiedadPlugin> query = entityManager.createQuery(cq);
      return query.getResultList();
   }

   /**
    * Obtiene el número de propiedadPlugin de un plugin.
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la plugin
    * @return número de propiedadPlugin de la plugin que cumplen el filtro.
    */
   @PermitAll
   public Long countAllByPlugin(Long entidadId) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<PropiedadPlugin> root = cq.from(PropiedadPlugin.class);
      cq.select(cb.count(root));
      cq.where(
         cb.and(
            cb.equal(root.get("plugin").get("id"), entidadId),
            getFilterPredicate(root, null)
         )
      );

      TypedQuery<Long> query = entityManager.createQuery(cq);
      return query.getSingleResult();
   }


}
