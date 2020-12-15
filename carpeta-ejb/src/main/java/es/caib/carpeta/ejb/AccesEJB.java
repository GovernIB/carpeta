
package es.caib.carpeta.ejb;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.jpa.AccesJPAManager;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.utils.Constants;

@Stateless
public class AccesEJB extends AccesJPAManager implements AccesLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Acces instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Acces create(Acces instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Acces update(Acces instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public AccesJPA findByPrimaryKey(Long _ID_) {
    return (AccesJPA)super.findByPrimaryKey(_ID_);
  }

}
