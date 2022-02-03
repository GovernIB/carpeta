package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

import es.caib.carpeta.ejb.SeccioEJB;
import es.caib.carpeta.logic.utils.CleanFilesSynchronization;
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

    @Resource(mappedName = "java:comp/TransactionSynchronizationRegistry")
    protected TransactionSynchronizationRegistry transactionSynchronizationRegistry;

    @PermitAll
    @Override
    public SeccioJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @PermitAll
    @Override
    public SeccioJPA findByContext(String context) throws I18NException {

        List<Seccio> list = select(CONTEXTE.equal(context));

        if (list == null || list.size() == 0) {
            return null;
        } else {
            return (SeccioJPA) list.get(0);
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
    public void deleteFull(Seccio seccio) throws I18NException {

        Set<Long> filesToDelete = deleteFullRecursive(seccio);

        transactionSynchronizationRegistry
                .registerInterposedSynchronization(new CleanFilesSynchronization(filesToDelete));

    }

    @Override
    public Set<Long> deleteFullRecursive(Seccio seccio) throws I18NException {

        this.delete(seccio);

        Set<Long> fitxers = new HashSet<Long>();
        
        fitxersEjb.delete(seccio.getIconaID());        
        fitxers.add(seccio.getIconaID());

        // Si hi hagues dependències de FK's que també s'haguessin d'esborrar
        // fitxers.add(XXXXEJB.deleteFullRecursive(seccio.getFKObject());

        return fitxers;

    }

    

}
