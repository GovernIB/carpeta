
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.EntitatJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class EntitatEJB extends EntitatJPAManager implements EntitatLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Entitat instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Entitat create(Entitat instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Entitat update(Entitat instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public EntitatJPA findByPrimaryKey(Long _ID_) {
    return (EntitatJPA)super.findByPrimaryKey(_ID_);
  }

}
