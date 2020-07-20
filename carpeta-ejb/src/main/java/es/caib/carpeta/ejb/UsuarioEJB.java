package es.caib.carpeta.ejb;

import es.caib.carpeta.ejb.interceptor.Logged;
import es.caib.carpeta.ejb.utils.CarpetaPluginsManager;
import es.caib.carpeta.persistence.TipoUsuario;
import es.caib.carpeta.persistence.Usuario;
import es.caib.carpeta.persistence.dao.AbstractDAO;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import java.util.List;
import java.util.Map;

/**
 *
 * Servicio EJB para gestionar {@link Usuario}. Le aplicamos el interceptor
 * {@link Logged}, por tanto, todas las llamadas se loguearan.
 *
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 09/06/2020
 * @author anadal
 */

@Logged
@Stateless
@PermitAll
public class UsuarioEJB extends AbstractDAO<Usuario, Long> implements UsuarioService {

	@Override
	@PermitAll
	public Usuario crearUsuario(Usuario usuario) throws I18NException {

		// TODO comprobar que existe en keycloack y obtener la info para crear el
		// usuario.
		create(usuario);
		return usuario;

	}

	@Override
	@PermitAll
	public Usuario findByUsername(@NotNull String username) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		final Root<Usuario> root = cq.from(Usuario.class);
		cq.select(root);
		cq.where(cb.equal(root.get("username"), username));

		TypedQuery<Usuario> query = entityManager.createQuery(cq);

		List<Usuario> list = query.getResultList();

		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * Obté una llista dels procediments d'una unitat orgànica que compleixen un
	 * filtre. La cadena de filtre es cerca dins els camps de tipus string: Si
	 * filter és <code>null</code> no aplica cap filtre.
	 *
	 * @param username    identificador del usuari
	 * @param nombre
	 * @param apellido1   identificador de la unitat orgànica.
	 * @param apellido2   identificador de la unitat orgànica.
	 * @param tipoUsuario
	 * @param filters     map on les claus són el nom d'atribut i el valor pel qual
	 *                    s'ha de filtrar.
	 * @return llista de procediments de la unitat orgànica que compleixen el
	 *         filtre.
	 */
	@Override
	@PermitAll
	public List<Usuario> busquedaUsuario(Map<String, Object> filters, String username, String nombre, String apellido1,
			String apellido2, TipoUsuario tipoUsuario) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		final Root<Usuario> root = cq.from(Usuario.class);
		cq.select(root);
		cq.where(cb.or(cb.equal(root.get("username"), username), cb.equal(root.get("nombre"), nombre),
				cb.equal(root.get("apellido1"), apellido1), cb.equal(root.get("apellido2"), apellido2),
				cb.equal(root.get("tipo"), tipoUsuario), getFilterPredicate(root, filters)));

		TypedQuery<Usuario> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void eliminarUsuario(Long id) throws I18NException {

		// TODO falta borrar los datos asociados
		delete(id);
	}

	@Override
	@PermitAll
	public Usuario getUserInfoFromUserInformation(String username) throws I18NException, Exception {

		Usuario persona = null;
		IUserInformationPlugin plugin = CarpetaPluginsManager.getUserInformationPluginInstance();
		
		log.info("XYZ ZZZ ZZZ UserInformationPlugin => " + plugin);
		
		
		UserInfo info = plugin.getUserInfoByUserName(username);
		if (info != null) {
			persona = new Usuario();
			persona.setEmail(info.getEmail() == null ? "" : info.getEmail());

			// XYZ ZZZ
			// persona.setIdiomaID(Configuracio.getDefaultLanguage());
			final String nom, llinatge1, llinatge2;
			{
				nom = info.getName();

				llinatge1 = info.getSurname1();
				llinatge2 = info.getSurname2();
			}
			persona.setNombre(nom);
			persona.setApellido1(llinatge1);
			persona.setApellido2(llinatge2);

			persona.setTipo(TipoUsuario.PERSONA);
			persona.setUsername(username);
			// XYZ ZZZ ZZZ
			// persona.setNif(info.getAdministrationID().toUpperCase());
			persona.setIdioma("ca");
		}

		return persona;
	}

}
