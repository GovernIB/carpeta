package es.caib.carpeta.logic;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.EnllazEJB;
import es.caib.carpeta.model.entity.Enllaz;

/**
 * 
 * @author jagarcia
 *
 */
@Stateless
public class EnllazLogicaEJB extends EnllazEJB implements EnllazLogicaLocal{
	
	@EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
	protected FitxerLogicaLocal fitxersEjb;
	
	@Override
	public Set<Long> deleteFull(Enllaz enllaz, boolean deleteFiles) throws I18NException{
		
		delete(enllaz);
		
		fitxersEjb.delete(enllaz.getLogoID());
		
		Set<Long> fitxers = new HashSet<Long>();
		fitxers.add(enllaz.getLogoID());
		
		if (deleteFiles) {
			if(!FileSystemManager.eliminarArxius(fitxers)) {
				log.error("Error esborrant fitxers del enllaz ID " + enllaz.getEnllazID(), new Exception());
			}
		}
		return fitxers;
		
	}

}
