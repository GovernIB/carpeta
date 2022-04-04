
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Ciutada;
import es.caib.carpeta.persistence.CiutadaJPA;
import es.caib.carpeta.persistence.CiutadaJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class CiutadaEJB extends CiutadaJPAManager implements CiutadaService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Ciutada instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Ciutada create(Ciutada instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Ciutada update(Ciutada instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public CiutadaJPA findByPrimaryKey(Long _ID_) {
        return (CiutadaJPA)super.findByPrimaryKey(_ID_);
    }

}
