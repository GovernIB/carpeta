package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.Acceso;
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
 *
 * Servicio EJB para gestionar {@link Acceso}. Le aplicamos el interceptor {@link Logged}, por
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
public class AccesoEJB extends AbstractDAO<Acceso, Long> implements AccesoService{

   @Override
   public Acceso crearAcceso(Acceso acceso, Long entidadId) throws I18NException {

      if(entidadId != null) {
         try {
            Entidad entidad = entityManager.getReference(Entidad.class, entidadId);
            acceso.setEntidad(entidad);
         } catch (EntityNotFoundException e) {
            throw new I18NException("entidad.noexisteix", String.valueOf(entidadId));
         }
      }
      entityManager.persist(acceso);
      return acceso;

   }

   /**
    * Obtiene una lista de todos los accesos de una entidad
    *
    * @param entidadId identificador de la entidad
    * @return lista de accesos de la entidad
    */
   @Override
   public List<Acceso> findAllByEntidad(Long entidadId) {
      return findAllByEntidad(entidadId, null);
   }

   /**
    * Obtiene el numero de accesos de una entidad
    *
    * @param entidadId identificador de la entidad.
    * @return número de accesos de la entidad.
    */
   @Override
   public Long countAllByEntidad(Long entidadId) {
      return countAllByEntidad(entidadId,null);
   }








   /**
    * Obtiene una lista de los accesos de una entidad que cumplen un filtro.
    * La cadena de filtro se busca dentro de los campos de tipo string
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return llista de accesos de la entidad que cumplen el filtro.
    */
   @PermitAll
   public List<Acceso> findAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Acceso> cq = cb.createQuery(Acceso.class);
      final Root<Acceso> root = cq.from(Acceso.class);
      cq.select(root);
      cq.where(
         cb.and(
            cb.equal(root.get("entidad").get("id"), entidadId),
            getFilterPredicate(root, filters)
         )
      );

      TypedQuery<Acceso> query = entityManager.createQuery(cq);
      return query.getResultList();
   }

   /**
    * Obtiene el número de accesos de una entidad que cumplen un filtro.
    * Si filter es <code>null</code> no aplica ningún filtro.
    *
    * @param entidadId identificador de la entidad
    * @param filters   map donde las claves son el nombre de atributo y el valor por el cual se ha de filtrar.
    * @return número de accesos de la entidad que cumplen el filtro.
    */
   @PermitAll
   public long countAllByEntidad(Long entidadId, Map<String, Object> filters) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      final Root<Acceso> root = cq.from(Acceso.class);
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
