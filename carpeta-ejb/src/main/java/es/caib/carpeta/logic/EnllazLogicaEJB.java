package es.caib.carpeta.logic;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.EnllazEJB;
import es.caib.carpeta.logic.utils.CleanFilesSynchronization;
import es.caib.carpeta.model.entity.Enllaz;

/**
 * 
 * @author jagarcia
 * @author anadal
 *
 */
@Stateless
public class EnllazLogicaEJB extends EnllazEJB implements EnllazLogicaService {

    @Resource(mappedName = "java:comp/TransactionSynchronizationRegistry")
    protected TransactionSynchronizationRegistry transactionSynchronizationRegistry;

    @EJB(mappedName = FitxerLogicaService.JNDI_NAME)
    protected FitxerLogicaService fitxersEjb;

    @Override
    public Set<Long> deleteFullRecursive(Enllaz enllaz) throws I18NException {

        delete(enllaz);

        Set<Long> fitxers = new HashSet<Long>();

        fitxers.add(enllaz.getLogoID());
        fitxersEjb.delete(enllaz.getLogoID());

        return fitxers;

    }

    @Override
    public void deleteFull(Enllaz enllaz) throws I18NException {

        Set<Long> filesToDelete = deleteFullRecursive(enllaz);

        transactionSynchronizationRegistry
                .registerInterposedSynchronization(new CleanFilesSynchronization(filesToDelete));

    }

}
