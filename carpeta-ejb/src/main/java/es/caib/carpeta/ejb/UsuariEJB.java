
package es.caib.carpeta.ejb;

import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.carpeta.model.entity.Usuari;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.persistence.UsuariJPAManager;

import es.caib.carpeta.utils.Constants;

@Stateless
public class UsuariEJB extends UsuariJPAManager implements UsuariLocal {

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public void delete(Usuari instance) {
		super.delete(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,Constants.CAR_USER})
	public Usuari create(Usuari instance) throws I18NException {
		return super.create(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
	public Usuari update(Usuari instance) throws I18NException {
		 return super.update(instance);
	}

  @Override
	@RolesAllowed({Constants.CAR_ADMIN,
        Constants.CAR_USER})
  public UsuariJPA findByPrimaryKey(Long _ID_) {
    return (UsuariJPA)super.findByPrimaryKey(_ID_);
  }

}
