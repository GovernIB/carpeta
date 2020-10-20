
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.jpa.EstadisticaJPAManager;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
public class EstadisticaEJB extends EstadisticaJPAManager implements EstadisticaLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Estadistica instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Estadistica create(Estadistica instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Estadistica update(Estadistica instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public EstadisticaJPA findByPrimaryKey(Long _ID_) {
    return (EstadisticaJPA)super.findByPrimaryKey(_ID_);
  }

}
