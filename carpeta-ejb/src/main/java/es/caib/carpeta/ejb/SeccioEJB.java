
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.persistence.SeccioJPA;
import es.caib.carpeta.persistence.SeccioJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class SeccioEJB extends SeccioJPAManager implements SeccioService {

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Seccio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Seccio create(Seccio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Seccio update(Seccio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(Seccio instance,  FitxerService fitxerEjb)
            throws I18NException {

        java.util.ArrayList<Long> fitxers = new java.util.ArrayList<Long>();
        fitxers.add(instance.getIconaID());

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
    public SeccioJPA findByPrimaryKey(Long _ID_) {
        return (SeccioJPA)super.findByPrimaryKey(_ID_);
    }

}
