package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.UsuariJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariBeanValidator 
      extends AbstractBeanValidator<UsuariJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.IIdiomaManager __idiomaManager;

  protected final es.caib.carpeta.model.dao.IUsuariManager __usuariManager;


  public final UsuariValidator<UsuariJPA> _validator;


  public UsuariBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IIdiomaManager __idiomaManager,
     es.caib.carpeta.model.dao.IUsuariManager __usuariManager) { 
    this.__entitatManager = __entitatManager;
    this.__idiomaManager = __idiomaManager;
    this.__usuariManager = __usuariManager;
    _validator = new UsuariValidator<UsuariJPA>();
  }

  public UsuariBeanValidator(UsuariValidator<UsuariJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IIdiomaManager __idiomaManager,
     es.caib.carpeta.model.dao.IUsuariManager __usuariManager) {
    this.__entitatManager = __entitatManager;
    this.__idiomaManager = __idiomaManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariJPA> _bvr_ = new BeanValidatorResult<UsuariJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __idiomaManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
