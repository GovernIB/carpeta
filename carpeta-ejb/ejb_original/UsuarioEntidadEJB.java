package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.UsuarioEntidad;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 *
 * Servicio EJB para gestionar {@link UsuarioEntidad}. Le aplicamos el
 * interceptor {@link Logged}, por tanto, todas las llamadas se loguearan.
 *
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 09/06/2020
 */

@Logged
@Stateless
@RolesAllowed(Constants.CAR_SUPER)
public class UsuarioEntidadEJB extends AbstractDAO<UsuarioEntidad, Long> implements UsuarioEntidadService {

	/**
	 * Obtiene una lista de todos los usuarioEntidad de una entidad
	 *
	 * @param entidadId identificador de la entidad
	 * @return lista de usuarioEntidad de la entidad
	 */
	@Override
	public List<UsuarioEntidad> findAllByEntidad(Long entidadId) {
		return findAllByEntidad(entidadId, null);
	}

	/**
	 * Obtiene el numero de usuarioEntidad de una unidad orgánica
	 *
	 * @param entidadId identificador de la entidad.
	 * @return número de usuarioEntidad de la entidad.
	 */
	@Override
	public Long countAllByEntidad(Long entidadId) {
		return countAllByEntidad(entidadId, null);
	}

	@Override
	@PermitAll
	public List<UsuarioEntidad> findAllByPersonaId(@NotNull long usuarioID) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioEntidad> cq = cb.createQuery(UsuarioEntidad.class);
		final Root<UsuarioEntidad> root = cq.from(UsuarioEntidad.class);
		cq.select(root);
		cq.where(cb.equal(root.get("usuario"), usuarioID));

		TypedQuery<UsuarioEntidad> query = entityManager.createQuery(cq);

		List<UsuarioEntidad> list = query.getResultList();

		return list;

	}

	/**
	 * Obtiene una lista de los usuarioEntidad de una entidad que cumplen un filtro.
	 * La cadena de filtro se busca dentro de los campos de tipo string Si filter es
	 * <code>null</code> no aplica ningún filtro.
	 *
	 * @param entidadId identificador de la entidad
	 * @param filters   map donde las claves son el nombre de atributo y el valor
	 *                  por el cual se ha de filtrar.
	 * @return llista de usuarioEntidad de la entidad que cumplen el filtro.
	 */
	@PermitAll
	public List<UsuarioEntidad> findAllByEntidad(Long entidadId, Map<String, Object> filters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioEntidad> cq = cb.createQuery(UsuarioEntidad.class);
		final Root<UsuarioEntidad> root = cq.from(UsuarioEntidad.class);
		cq.select(root);
		cq.where(cb.and(cb.equal(root.get("entidad").get("id"), entidadId), getFilterPredicate(root, filters)));

		TypedQuery<UsuarioEntidad> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	/**
	 * Obtiene el número de usuarioEntidad de una entidad que cumplen un filtro. Si
	 * filter es <code>null</code> no aplica ningún filtro.
	 *
	 * @param entidadId identificador de la entidad
	 * @param filters   map donde las claves son el nombre de atributo y el valor
	 *                  por el cual se ha de filtrar.
	 * @return número de usuarioEntidad de la entidad que cumplen el filtro.
	 */
	@PermitAll
	public long countAllByEntidad(Long entidadId, Map<String, Object> filters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UsuarioEntidad> root = cq.from(UsuarioEntidad.class);
		cq.select(cb.count(root));
		cq.where(cb.and(cb.equal(root.get("entidad").get("id"), entidadId), getFilterPredicate(root, filters)));

		TypedQuery<Long> query = entityManager.createQuery(cq);
		return query.getSingleResult();
	}

}
