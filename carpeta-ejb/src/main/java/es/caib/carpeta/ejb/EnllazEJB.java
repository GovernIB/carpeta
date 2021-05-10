
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.persistence.EnllazJPA;
import es.caib.carpeta.persistence.EnllazJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class EnllazEJB extends EnllazJPAManager implements EnllazService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Enllaz instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Enllaz create(Enllaz instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Enllaz update(Enllaz instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public EnllazJPA findByPrimaryKey(Long _ID_) {
        return (EnllazJPA)super.findByPrimaryKey(_ID_);
    }

}
