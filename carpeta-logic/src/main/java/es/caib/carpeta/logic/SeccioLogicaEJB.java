package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import es.caib.carpeta.ejb.SeccioEJB;
import es.caib.carpeta.model.entity.Seccio;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author anadal
 */
@Stateless
public class SeccioLogicaEJB extends SeccioEJB implements SeccioLogicaService {

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @Override
    public List<Seccio> findByEntity(long entitatID, Long seccioPareID) throws I18NException {
        
        Where w;
        if (seccioPareID == null) {
           w = SECCIOPAREID.isNull(); 
        } else {
           w = SECCIOPAREID.equal(seccioPareID);
        }
        
        return select(Where.AND(ENTITATID.equal(entitatID), w, ACTIVA.equal(true)));
    }

}
