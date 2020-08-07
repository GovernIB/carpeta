
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.jpa.PluginJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class PluginEJB extends PluginJPAManager implements PluginLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Plugin instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Plugin create(Plugin instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Plugin update(Plugin instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public PluginJPA findByPrimaryKey(Long _ID_) {
    return (PluginJPA)super.findByPrimaryKey(_ID_);
  }

}
