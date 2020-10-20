
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.jpa.EnllazJPAManager;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class EnllazEJB extends EnllazJPAManager implements EnllazLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Enllaz instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Enllaz create(Enllaz instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Enllaz update(Enllaz instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public EnllazJPA findByPrimaryKey(Long _ID_) {
    return (EnllazJPA)super.findByPrimaryKey(_ID_);
  }

}
