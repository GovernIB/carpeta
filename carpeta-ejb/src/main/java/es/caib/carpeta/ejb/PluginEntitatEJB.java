
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.persistence.PluginEntitatJPAManager;

import es.caib.carpeta.commons.utils.Constants;

@Stateless
public class PluginEntitatEJB extends PluginEntitatJPAManager implements PluginEntitatService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(PluginEntitat instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginEntitat create(PluginEntitat instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginEntitat update(PluginEntitat instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(PluginEntitat instance, es.caib.carpeta.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PluginEntitatJPA findByPrimaryKey(Long _ID_) {
        return (PluginEntitatJPA)super.findByPrimaryKey(_ID_);
    }

}
