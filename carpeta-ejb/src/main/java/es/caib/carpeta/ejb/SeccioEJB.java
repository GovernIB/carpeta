
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.persistence.SeccioJPA;
import es.caib.carpeta.persistence.SeccioJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class SeccioEJB extends SeccioJPAManager implements SeccioService {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Seccio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Seccio create(Seccio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Seccio update(Seccio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public SeccioJPA findByPrimaryKey(Long _ID_) {
    return (SeccioJPA)super.findByPrimaryKey(_ID_);
  }

}
