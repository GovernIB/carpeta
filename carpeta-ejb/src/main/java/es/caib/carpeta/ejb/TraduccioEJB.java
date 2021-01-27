
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Traduccio;
import es.caib.carpeta.persistence.TraduccioJPA;
import es.caib.carpeta.persistence.TraduccioJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class TraduccioEJB extends TraduccioJPAManager implements TraduccioService {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Traduccio instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Traduccio create(Traduccio instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Traduccio update(Traduccio instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public TraduccioJPA findByPrimaryKey(Long _ID_) {
    return (TraduccioJPA)super.findByPrimaryKey(_ID_);
  }

}
