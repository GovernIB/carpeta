package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.ejb.PluginEntitatEJB;
import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PluginEntitatQueryPath;

/**
 * 
 * @author jagarcia
 * @author anadal
 */
@Stateless
public class PluginEntitatLogicaEJB extends PluginEntitatEJB implements PluginEntitatLogicaService {

	@Override
	@PermitAll
	public List<PluginEntitatJPA> findAllByEntitatId(@NotNull long entitatId) throws I18NException {

		List<PluginEntitat> list = select(PluginEntitatFields.ENTITATID.equal(entitatId), new OrderBy(PluginEntitatFields.ORDRE));

		List<PluginEntitatJPA> list2 = new ArrayList<PluginEntitatJPA>(list.size());
		for (PluginEntitat pluginEntitat : list) {
			list2.add((PluginEntitatJPA) pluginEntitat);
		}

		return list2;

	}

	/** Cerca plugins actius d'una entitat **/
	@Override
	public List<Long> getPluginsEntitat(String codiEntitat, boolean actiu, Long seccioID) throws I18NException {

	    /*
		TypedQuery<Long> query = getEntityManager().createQuery(
				"select a.pluginID from PluginEntitatJPA a "
						+ "where a.entitat.codi = :codiEntitat and a.actiu = :actiu",Long.class);
		query.setParameter("codiEntitat", codiEntitat);
		query.setParameter("actiu", actiu);

		return query.getResultList();
		*/
	    
	    PluginEntitatQueryPath peqp = new PluginEntitatQueryPath();
	    
	    Where w;
        if (seccioID == null) {
           w = PluginEntitatFields.SECCIOID.isNull(); 
        } else {
           w = PluginEntitatFields.SECCIOID.equal(seccioID);
        }
	    
	    
	    List<Long> pluginIDs = executeQuery(PluginEntitatFields.PLUGINID,
	            Where.AND(
	                    peqp.ENTITAT().CODI().equal(codiEntitat),
	                    PluginEntitatFields.ACTIU.equal(true),
	                    w
	                    ), new OrderBy(PluginEntitatFields.ORDRE));
	    
	    return pluginIDs;

	}
	
	/** Cerca tots els plugins associats a una entitat **/
	@Override
	public List<Long> getAllPluginsByEntitat(String codiEntitat) throws I18NException {
		
		TypedQuery<Long> query = getEntityManager().createQuery(
				"select a.pluginID from PluginEntitatJPA a "
						+ "where a.entitat.codi = :codiEntitat order by a." + PluginEntitatFields.ORDRE.javaName,Long.class);
		query.setParameter("codiEntitat", codiEntitat);

		return query.getResultList();
	}
	
}
