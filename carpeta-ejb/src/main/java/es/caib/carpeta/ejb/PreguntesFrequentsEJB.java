
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.PreguntesFrequents;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;
import es.caib.carpeta.persistence.PreguntesFrequentsJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class PreguntesFrequentsEJB extends PreguntesFrequentsJPAManager implements PreguntesFrequentsService {

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PreguntesFrequents instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PreguntesFrequents create(PreguntesFrequents instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PreguntesFrequents update(PreguntesFrequents instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(PreguntesFrequents instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        java.util.ArrayList<Long> fitxers = new java.util.ArrayList<Long>();
        fitxers.add(instance.getFitxer1ID());
        fitxers.add(instance.getFitxer2ID());
        fitxers.add(instance.getFitxer3ID());

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
    public PreguntesFrequentsJPA findByPrimaryKey(Long _ID_) {
        return (PreguntesFrequentsJPA)super.findByPrimaryKey(_ID_);
    }

}
