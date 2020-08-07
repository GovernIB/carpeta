package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.AccesJPA;
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
public class AccesBeanValidator 
      extends AbstractBeanValidator<AccesJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IAccesManager __accesManager;

  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;


  public final AccesValidator<AccesJPA> _validator;


  public AccesBeanValidator(es.caib.carpeta.model.dao.IAccesManager __accesManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager) { 
    this.__accesManager = __accesManager;
    this.__entitatManager = __entitatManager;
    _validator = new AccesValidator<AccesJPA>();
  }

  public AccesBeanValidator(AccesValidator<AccesJPA> _validator,
     es.caib.carpeta.model.dao.IAccesManager __accesManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager) {
    this.__accesManager = __accesManager;
    this.__entitatManager = __entitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AccesJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AccesJPA> _bvr_ = new BeanValidatorResult<AccesJPA>();
    _validator.validate(_bvr_, target, isNou, __accesManager, __entitatManager);
    return _bvr_.getErrors();
  }
}
