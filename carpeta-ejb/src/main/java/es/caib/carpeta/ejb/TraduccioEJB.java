
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Traduccio;
import es.caib.carpeta.persistence.TraduccioJPA;
import es.caib.carpeta.persistence.TraduccioJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class TraduccioEJB extends TraduccioJPAManager implements TraduccioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Traduccio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Traduccio create(Traduccio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Traduccio update(Traduccio instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Traduccio instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public TraduccioJPA findByPrimaryKey(Long _ID_) {
        return (TraduccioJPA)super.findByPrimaryKey(_ID_);
    }

}
