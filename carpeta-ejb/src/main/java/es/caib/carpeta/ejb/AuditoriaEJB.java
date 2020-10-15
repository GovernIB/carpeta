
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.AuditoriaJPA;
import es.caib.carpeta.jpa.AuditoriaJPAManager;
import es.caib.carpeta.model.entity.Auditoria;
import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

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
