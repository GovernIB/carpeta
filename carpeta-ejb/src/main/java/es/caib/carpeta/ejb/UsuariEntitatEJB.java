
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.UsuariEntitatJPA;
import es.caib.carpeta.jpa.UsuariEntitatJPAManager;
import es.caib.carpeta.model.entity.UsuariEntitat;
import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class UsuariEntitatEJB extends UsuariEntitatJPAManager implements UsuariEntitatLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(UsuariEntitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public UsuariEntitat create(UsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public UsuariEntitat update(UsuariEntitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public UsuariEntitatJPA findByPrimaryKey(Long _ID_) {
    return (UsuariEntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
