
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.AvisJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class AvisEJB extends AvisJPAManager implements AvisLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Avis instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Avis create(Avis instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Avis update(Avis instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public AvisJPA findByPrimaryKey(Long _ID_) {
    return (AvisJPA)super.findByPrimaryKey(_ID_);
  }

}
