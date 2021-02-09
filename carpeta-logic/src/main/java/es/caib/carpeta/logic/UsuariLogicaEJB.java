package es.caib.carpeta.logic;


import java.util.List;

import javax.annotation.security.PermitAll;


import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.ejb.UsuariEJB;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.model.entity.Usuari;
import es.caib.carpeta.model.fields.UsuariFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {

	
	@Override
	@PermitAll
	public UsuariJPA findByUsername(String username) throws I18NException {
		
		TypedQuery<UsuariJPA> query = getEntityManager().createQuery(
				"select u from UsuariJPA u "
				+ "join fetch u.entitat "
				+ "where u.username = :username", UsuariJPA.class);
		query.setParameter("username", username);
		
		List<UsuariJPA> resultats = query.getResultList();
		return (resultats.size() > 0) ? resultats.get(0) : null;
			
	}
	
	
	
	@Override
	@PermitAll
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, javax.ejb.EJBException {

		// TODO comprobar que existe en keycloack y obtener la info para crear el
		// usuario.
		try {
			create(usuario);	
		} catch(Throwable th) {
			
			log.error(" ==============================================" );
			log.error(" TIPUS EXCEPCIO: " + th.getClass());
			log.error(th.getMessage(), th);
			
			if (th instanceof I18NException) {
				throw (I18NException)th;
			} else {
				throw new I18NException("comodi", th.getMessage());
			}
			
		}
		
		return usuario;

	}
	


}

