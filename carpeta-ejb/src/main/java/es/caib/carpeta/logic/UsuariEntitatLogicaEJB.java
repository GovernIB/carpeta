package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.UsuariEntitatEJB;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.model.entity.UsuariEntitat;
import es.caib.carpeta.model.fields.UsuariEntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariEntitatLogicaEJB extends UsuariEntitatEJB implements UsuariEntitatLogicaService {

	@Override
	@PermitAll
	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException {

		List<UsuariEntitat> list = select(UsuariEntitatFields.USUARIID.equal(usuarioID));

		List<UsuariEntitatJPA> list2 = new ArrayList<UsuariEntitatJPA>(list.size());
		for (UsuariEntitat usuariEntitat : list) {
			list2.add((UsuariEntitatJPA) usuariEntitat);
		}

		return list2;

	}

	@Override
	@PermitAll
	public List<UsuariEntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException {

		TypedQuery<UsuariEntitatJPA> query = getEntityManager().createQuery(
				"select ue from UsuariEntitatJPA ue "
				+ "join fetch ue.entitat "
				+ "where ue.usuariID = :usuarioID", UsuariEntitatJPA.class);
		query.setParameter("usuarioID", usuarioID);
		return query.getResultList();

	}

}
