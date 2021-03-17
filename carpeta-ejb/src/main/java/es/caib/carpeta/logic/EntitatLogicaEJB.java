package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.caib.carpeta.ejb.AccesService;
import es.caib.carpeta.ejb.AvisService;
import es.caib.carpeta.ejb.EntitatEJB;
import es.caib.carpeta.ejb.FitxerService;
import es.caib.carpeta.ejb.PluginEntitatService;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.ejb.UsuariEntitatService;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.AvisFields;
import es.caib.carpeta.model.fields.EnllazFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.FitxerFields;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import es.caib.carpeta.model.fields.SeccioFields;
import es.caib.carpeta.model.fields.UsuariEntitatFields;
import es.caib.carpeta.persistence.EntitatJPA;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 
 * @author jagarcia
 *
 */

@Stateless(name = "EntitatLogicaEJB")
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaService, EntitatFields{
	
	@EJB(mappedName = UsuariEntitatService.JNDI_NAME)
	protected UsuariEntitatService usuariEntitatEjb;

	@EJB(mappedName = PluginEntitatService.JNDI_NAME)
	protected PluginEntitatService pluginEntitatEjb;
	
	@EJB(mappedName = EnllazLogicaService.JNDI_NAME)
	protected EnllazLogicaService enllazLogicaEjb;
	
	@EJB(mappedName = SeccioLogicaService.JNDI_NAME)
	protected SeccioLogicaService seccioLogicaEjb;
	
	@EJB(mappedName = AvisService.JNDI_NAME)
	protected AvisService avisEjb;
	
	@EJB(mappedName = PropietatGlobalService.JNDI_NAME)
	protected PropietatGlobalService propietatGlobalEjb;
	
	@EJB(mappedName = AccesService.JNDI_NAME)
	protected AccesService accesEjb;
	
	@EJB(mappedName = FitxerService.JNDI_NAME)
	protected FitxerService fitxersEjb;
	
	
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
		
		// Seccio BBDD + fitxers
		List<Seccio> seccions = seccioLogicaEjb.select(SeccioFields.ENTITATID.equal(entitat.getEntitatID()));
		for (Seccio s : seccions) {
			fitxers.addAll(seccioLogicaEjb.deleteFull(s,false));
		}
		
		// ENTITAT 
		Set<Long> fitxersEntitat = new HashSet<Long>();
		fitxersEntitat.add(entitat.getFitxerCssID());
		fitxersEntitat.add(entitat.getLogoCapBackID());
		fitxersEntitat.add(entitat.getLogoLateralFrontID());
		fitxersEntitat.add(entitat.getLogoPeuBackID());
		fitxersEntitat.add(entitat.getIconID());
		fitxersEntitat.removeAll(Collections.singleton(null));
		fitxers.addAll(fitxersEntitat);
		
		// eliminar la entitat de BBDD
		delete(entitat);
		
		// obligam a realitzar les transaccions abans de eliminar els fitxers per evitar errors de constraint #434 
		getEntityManager().flush();
		
		// Borram els fitxers de BBDD
		fitxersEjb.delete(FitxerFields.FITXERID.in(fitxersEntitat));
		
		// eliminar els fitxers físics
		if (deleteFiles) {
			fitxers.removeAll(Collections.singleton(null));
			if (!FileSystemManager.eliminarArxius(fitxers)) {
				log.error("Error esborrant fitxers de la entitat amb ID " + entitat.getEntitatID(), new Exception());
			}
		}
		
	}

	/** Cerca una entiat a través del seu coodi**/
	@Override
	public EntitatJPA findByCodi(String codiEntitat) throws I18NException {

		Where w1 = Where.AND(CODI.equal(codiEntitat),ACTIVA.equal(true));
		List<Entitat> entitats = select(w1);

		return entitats.size()>0?(EntitatJPA)entitats.get(0):null;
	}


	/** Cerca una entitat a través del seu codi DIR3**/
	@Override
	public EntitatJPA findByCodiDir3(String codiDir3Entitat) throws I18NException {

		Where w1 = Where.AND(CODIDIR3.equal(codiDir3Entitat),ACTIVA.equal(true));
		select(w1);
		List<Entitat> entitats = select(w1);
		return entitats.size()>0?(EntitatJPA)entitats.get(0):null;

	}
}
