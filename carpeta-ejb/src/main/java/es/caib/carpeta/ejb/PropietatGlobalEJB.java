
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.PropietatGlobal;
import es.caib.carpeta.persistence.PropietatGlobalJPA;
import es.caib.carpeta.persistence.PropietatGlobalJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class PropietatGlobalEJB extends PropietatGlobalJPAManager implements PropietatGlobalService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PropietatGlobal instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PropietatGlobal create(PropietatGlobal instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PropietatGlobal update(PropietatGlobal instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void deleteIncludingFiles(PropietatGlobal instance,  FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PropietatGlobalJPA findByPrimaryKey(Long _ID_) {
        return (PropietatGlobalJPA)super.findByPrimaryKey(_ID_);
    }

}
