package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.PluginEntitatEJB;
import es.caib.carpeta.jpa.PluginEntitatJPA;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.PluginEntitatFields;

/**
 * 
 * @author jagarcia
 *
 */
@Stateless
public class PluginEntitatLogicaEJB extends PluginEntitatEJB implements PluginEntitatLogicaLocal {

	@Override
	@PermitAll
	public List<PluginEntitatJPA> findAllByEntitatId(@NotNull long entitatId) throws I18NException {

		List<PluginEntitat> list = select(PluginEntitatFields.ENTITATID.equal(entitatId));

		List<PluginEntitatJPA> list2 = new ArrayList<PluginEntitatJPA>(list.size());
		for (PluginEntitat pluginEntitat : list) {
			list2.add((PluginEntitatJPA) pluginEntitat);
		}

		return list2;

	}

}
