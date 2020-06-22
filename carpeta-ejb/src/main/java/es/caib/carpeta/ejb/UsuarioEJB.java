package es.caib.carpeta.ejb;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.persistence.TipoUsuario;
import es.caib.carpeta.persistence.Usuario;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 *
 * Servicio EJB para gestionar {@link Usuario}. Le aplicamos el interceptor {@link Logged}, por
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
public class UsuarioEJB extends AbstractDAO<Usuario, Long> implements UsuarioService {


   @Override
   public Usuario crearUsuario(Usuario usuario) throws I18NException {

      //TODO  comprobar que existe en keycloack y obtener la info para crear el usuario.
      create(usuario);
      return usuario;

   }


   /**
    * Obté una llista dels procediments d'una unitat orgànica que compleixen un filtre.
    * La cadena de filtre es cerca dins els camps de tipus string: 
    * Si filter és <code>null</code> no aplica cap filtre.
    *
    * @param username identificador del usuari
    * @param nombre
    * @param apellido1 identificador de la unitat orgànica.
    * @param apellido2 identificador de la unitat orgànica.
    * @param tipoUsuario
    * @param filters          map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
    * @return llista de procediments de la unitat orgànica que compleixen el filtre.
    */
   @PermitAll
   public List<Usuario> busquedaUsuario(Map<String, Object> filters, String username,String nombre, String apellido1, String apellido2, TipoUsuario tipoUsuario) {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
      final Root<Usuario> root = cq.from(Usuario.class);
      cq.select(root);
      cq.where(
         cb.or(
            cb.equal(root.get("username"), username),
            cb.equal(root.get("nombre"), nombre),
            cb.equal(root.get("apellido1"), apellido1),
            cb.equal(root.get("apellido2"), apellido2),
            cb.equal(root.get("tipo"), tipoUsuario),
            getFilterPredicate(root, filters)
         )
      );

      TypedQuery<Usuario> query = entityManager.createQuery(cq);
      return query.getResultList();
   }




   @Override
   public void eliminarUsuario(Long id) throws I18NException {

      //TODO falta borrar los datos asociados
      delete(id);
   }

}
