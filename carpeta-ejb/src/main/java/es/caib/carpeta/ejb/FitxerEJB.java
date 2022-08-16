
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.persistence.FitxerJPA;
import es.caib.carpeta.persistence.FitxerJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class FitxerEJB extends FitxerJPAManager implements FitxerService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Fitxer instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Fitxer create(Fitxer instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Fitxer update(Fitxer instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Fitxer instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public FitxerJPA findByPrimaryKey(Long _ID_) {
        return (FitxerJPA)super.findByPrimaryKey(_ID_);
    }

}
