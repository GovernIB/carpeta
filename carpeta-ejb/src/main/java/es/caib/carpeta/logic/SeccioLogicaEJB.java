package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import es.caib.carpeta.ejb.SeccioEJB;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.model.fields.SeccioFields;
import es.caib.carpeta.persistence.SeccioJPA;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Fundació BIT.
 *
 * @author anadal
 */
@Stateless
public class SeccioLogicaEJB extends SeccioEJB implements SeccioLogicaService {

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;
    
    @EJB(mappedName = FitxerLogicaService.JNDI_NAME)
	protected FitxerLogicaService fitxersEjb;
    
    
    @PermitAll
    @Override
    public SeccioJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }
    
    
    @PermitAll
    @Override
    public SeccioJPA findByContext(String context) throws I18NException {

        List<Seccio> list = select(CONTEXT.equal(context));
        
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return (SeccioJPA)list.get(0);
        }
        
        
    }
    

    @Override
    public List<Seccio> findByEntity(long entitatID, Long seccioPareID) throws I18NException {
        
        Where w;
        if (seccioPareID == null) {
           w = SECCIOPAREID.isNull(); 
        } else {
           w = SECCIOPAREID.equal(seccioPareID);
        }        
        return select(Where.AND(ENTITATID.equal(entitatID), w, ACTIVA.equal(true)), new OrderBy(SeccioFields.ORDRE));
    }
    
    @Override
    public Set<Long> deleteFull(Seccio seccio, boolean deleteFiles) throws I18NException {
    	
    	delete(seccio);
		
		fitxersEjb.delete(seccio.getIconaID());
		
		Set<Long> fitxers = new HashSet<Long>();
		fitxers.add(seccio.getIconaID());
		
		if (deleteFiles) {
			if(!FileSystemManager.eliminarArxius(fitxers)) {
				log.error("Error esborrant fitxers de la seccio ID " + seccio.getSeccioID(), new Exception());
			}
		}
		return fitxers;
    	
    }

}
