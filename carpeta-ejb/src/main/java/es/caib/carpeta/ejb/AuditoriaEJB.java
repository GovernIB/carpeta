
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Auditoria;
import es.caib.carpeta.persistence.AuditoriaJPA;
import es.caib.carpeta.persistence.AuditoriaJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class AuditoriaEJB extends AuditoriaJPAManager implements AuditoriaLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Auditoria instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Auditoria create(Auditoria instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Auditoria update(Auditoria instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public AuditoriaJPA findByPrimaryKey(Long _ID_) {
    return (AuditoriaJPA)super.findByPrimaryKey(_ID_);
  }

}
