package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import es.caib.carpeta.ejb.AccesLocal;
import es.caib.carpeta.ejb.AvisLocal;
import es.caib.carpeta.ejb.EntitatEJB;
import es.caib.carpeta.ejb.FitxerLocal;
import es.caib.carpeta.ejb.PluginEntitatLocal;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.ejb.UsuariEntitatLocal;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.AvisFields;
import es.caib.carpeta.model.fields.EnllazFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.FitxerFields;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import es.caib.carpeta.model.fields.UsuariEntitatFields;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author jagarcia
 *
 */

@Stateless(name = "EntitatLogicaEJB")
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaLocal, EntitatFields{
	
	@EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
	protected UsuariEntitatLocal usuariEntitatEjb;

	@EJB(mappedName = PluginEntitatLocal.JNDI_NAME)
	protected PluginEntitatLocal pluginEntitatEjb;
	
	@EJB(mappedName = EnllazLogicaLocal.JNDI_NAME)
	protected EnllazLogicaLocal enllazLogicaEjb;
	
	@EJB(mappedName = AvisLocal.JNDI_NAME)
	protected AvisLocal avisEjb;
	
	@EJB(mappedName = PropietatGlobalLocal.JNDI_NAME)
	protected PropietatGlobalLocal propietatGlobalEjb;
	
	@EJB(mappedName = AccesLocal.JNDI_NAME)
	protected AccesLocal accesEjb;
	
	@EJB(mappedName = FitxerLocal.JNDI_NAME)
	protected FitxerLocal fitxersEjb;
	
	
	@Override
	public void deleteFull(Entitat entitat, boolean deleteFiles) throws I18NException {
		
		Set<Long> fitxers = new HashSet<Long>();
		
		// usuaris_entitat
		usuariEntitatEjb.delete(UsuariEntitatFields.ENTITATID.equal(entitat.getEntitatID()));
		
		// plugin_entitat
		pluginEntitatEjb.delete(PluginEntitatFields.ENTITATID.equal(entitat.getEntitatID()));
		
		// avisos
		avisEjb.delete(AvisFields.ENTITATID.equal(entitat.getEntitatID()));
		
		// propietats globals
		propietatGlobalEjb.delete(PropietatGlobalFields.ENTITATID.equal(entitat.getEntitatID()));
		
		// access
		accesEjb.delete(AccesFields.ENTITATID.equal(entitat.getEntitatID()));
		
		// enllazos BBDD + fitxers
		List<Enllaz> enllazos = enllazLogicaEjb.select(EnllazFields.ENTITATID.equal(entitat.getEntitatID()));
		for (Enllaz e : enllazos) {
			fitxers.addAll(enllazLogicaEjb.deleteFull(e,false));
		}
		
		// ENTITAT 
		Set<Long> fitxersEntitat = new HashSet<Long>();
		fitxersEntitat.add(entitat.getFitxerCssID());
		fitxersEntitat.add(entitat.getLogoCapBackID());
		fitxersEntitat.add(entitat.getLogoLateralFrontID());
		fitxersEntitat.add(entitat.getLogoPeuBackID());
		fitxersEntitat.add(entitat.getIconID());
		fitxers.addAll(fitxersEntitat);
		
		// eliminar la entitat de BBDD
		delete(entitat);
		
		// Borram els fitxers de BBDD
		fitxersEjb.delete(FitxerFields.FITXERID.in(fitxersEntitat));
		
		// eliminar els fitxers físics
		if (deleteFiles) {
			if (!FileSystemManager.eliminarArxius(fitxers)) {
				log.error("Error esborrant fitxers de la entitat amb ID " + entitat.getEntitatID(), new Exception());
			}
		}
			
	}

	/** Cerca una entiat a través del seu coodi**/
	@Override
	public EntitatJPA findByCodi(String codiEntitat) throws I18NException {

		TypedQuery<EntitatJPA> query = getEntityManager().createQuery(
				"select a from EntitatJPA a "
						+ "where a.codi = :codiEntitat and a.activa = true", EntitatJPA.class);
		query.setParameter("codiEntitat", codiEntitat);
		return query.getResultList().get(0);
	}


	/** Cerca una entitat a través del seu codi DIR3**/
	@Override
	public EntitatJPA findByCodiDir3(String codiDir3Entitat) throws I18NException {

		TypedQuery<EntitatJPA> query = getEntityManager().createQuery(
				"select a from EntitatJPA a "
						+ "where a.codiDir3 = :codiDir3Entitat and a.activa = true", EntitatJPA.class);
		query.setParameter("codiDir3Entitat", codiDir3Entitat);
		if(query.getResultList().size()>0){
			return query.getResultList().get(0);
		}else{
			return null;
		}

	}
}
