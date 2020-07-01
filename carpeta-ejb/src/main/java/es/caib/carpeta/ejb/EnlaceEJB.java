package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Enlace;
import es.caib.carpeta.persistence.Entidad;
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
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 11/06/2020
 */
@Logged
@Stateless
@RolesAllowed(Constants.CAR_ADMIN)
public class EnlaceEJB extends AbstractDAO<Enlace, Long> implements EnlaceService{

   @Override
   public Enlace crearEnlace(Enlace enlace, Long entidadId) throws I18NException {

      if(entidadId != null) {
         try {
            Entidad entidad = entityManager.getReference(Entidad.class, entidadId);
            enlace.setEntidad(entidad);
         } catch (EntityNotFoundException e) {
            throw new I18NException("entidad.noexisteix", String.valueOf(entidadId));
         }
      }
      entityManager.persist(enlace);
      return enlace;

   }

   /**
    * Obtiene una lista de todas los enlace de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de enlace de la entidad
    */
   @Override
   public List<Enlace> findAllByEntidad(Long entidadId) {
      return findAllByEntidad(entidadId, null);
   }

   /**
    * Obtiene el numero de enlace de una unidad orgánica
    *
    * @param entidadId identificador de la entidad.
    * @return número de enlaces de la entidad.
    */
   @Override
   public Long countAllByEntidad(Long entidadId) {
      return countAllByEntidad(entidadId,null);
   }




   /**
    * Obtiene una lista de los enlaces de una entidad que cumplen un filtro.
    * La cadena de filtro se busca dentro de los campos de tipo string
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return llista de enlaces de la entidad que cumplen el filtro.
    */
   @PermitAll
   public List<Enlace> findAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Enlace> cq = cb.createQuery(Enlace.class);
      final Root<Enlace> root = cq.from(Enlace.class);
      cq.select(root);
      cq.where(
         cb.and(
            cb.equal(root.get("entidad").get("id"), entidadId),
            getFilterPredicate(root, filters)
         )
      );

      TypedQuery<Enlace> query = entityManager.createQuery(cq);
      return query.getResultList();
   }

   /**
    * Obtiene el número de enlaces de una entidad que cumplen un filtro.
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return número de enlaces de la entidad que cumplen el filtro.
    */
   @PermitAll
   public long countAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<Enlace> root = cq.from(Enlace.class);
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
