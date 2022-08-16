
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.AvisJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class AvisEJB extends AvisJPAManager implements AvisService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Avis instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Avis create(Avis instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Avis update(Avis instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Avis instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public AvisJPA findByPrimaryKey(Long _ID_) {
        return (AvisJPA)super.findByPrimaryKey(_ID_);
    }

}
