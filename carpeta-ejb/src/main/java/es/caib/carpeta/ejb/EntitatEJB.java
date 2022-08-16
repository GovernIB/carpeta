
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.EntitatJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class EntitatEJB extends EntitatJPAManager implements EntitatService {

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Entitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Entitat create(Entitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Entitat update(Entitat instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Entitat instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        java.util.ArrayList<Long> fitxers = new java.util.ArrayList<Long>();
        fitxers.add(instance.getLogoCapBackID());
        fitxers.add(instance.getLogoPeuBackID());
        fitxers.add(instance.getLogoLateralFrontID());
        fitxers.add(instance.getIconID());
        fitxers.add(instance.getFitxerCssID());

        this.delete(instance);

        java.util.Set<Long> fitxersEsborrar = new java.util.HashSet<Long>();

        // Borram fitxers a BD
        for (Long f : fitxers) {
            if (f != null) {
                fitxerEjb.delete(f);
                fitxersEsborrar.add(f);
            }
        }

        // Borram fitxers fisic
        __tsRegistry.registerInterposedSynchronization(new es.caib.carpeta.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EntitatJPA findByPrimaryKey(Long _ID_) {
        return (EntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
