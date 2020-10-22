
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.jpa.LogCarpetaJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class LogCarpetaEJB extends LogCarpetaJPAManager implements LogCarpetaLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(LogCarpeta instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public LogCarpeta create(LogCarpeta instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public LogCarpeta update(LogCarpeta instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public LogCarpetaJPA findByPrimaryKey(Long _ID_) {
    return (LogCarpetaJPA)super.findByPrimaryKey(_ID_);
  }

}
