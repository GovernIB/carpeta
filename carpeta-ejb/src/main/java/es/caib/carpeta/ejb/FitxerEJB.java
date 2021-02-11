
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.persistence.FitxerJPA;
import es.caib.carpeta.persistence.FitxerJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class FitxerEJB extends FitxerJPAManager implements FitxerService {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Fitxer instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Fitxer create(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Fitxer update(Fitxer instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public FitxerJPA findByPrimaryKey(Long _ID_) {
    return (FitxerJPA)super.findByPrimaryKey(_ID_);
  }

}
