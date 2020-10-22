
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.jpa.IdiomaJPA;
import es.caib.carpeta.jpa.IdiomaJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class IdiomaEJB extends IdiomaJPAManager implements IdiomaLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Idioma instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Idioma create(Idioma instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Idioma update(Idioma instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public IdiomaJPA findByPrimaryKey(String _ID_) {
    return (IdiomaJPA)super.findByPrimaryKey(_ID_);
  }

}
