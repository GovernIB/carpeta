
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.persistence.PluginEntitatJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class PluginEntitatEJB extends PluginEntitatJPAManager implements PluginEntitatLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(PluginEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public PluginEntitat create(PluginEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public PluginEntitat update(PluginEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public PluginEntitatJPA findByPrimaryKey(Long _ID_) {
    return (PluginEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
