package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.EnllazJPA;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;

import java.util.List;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EnllazBeanValidator 
      extends AbstractBeanValidator<EnllazJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEnllazManager __enllazManager;

  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final EnllazValidator<EnllazJPA> _validator;


  public EnllazBeanValidator(es.caib.carpeta.model.dao.IEnllazManager __enllazManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__enllazManager = __enllazManager;
    this.__entitatManager = __entitatManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new EnllazValidator<EnllazJPA>();
  }

  public EnllazBeanValidator(EnllazValidator<EnllazJPA> _validator,
     es.caib.carpeta.model.dao.IEnllazManager __enllazManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__enllazManager = __enllazManager;
    this.__entitatManager = __entitatManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EnllazJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EnllazJPA> _bvr_ = new BeanValidatorResult<EnllazJPA>();
    _validator.validate(_bvr_, target, isNou, __enllazManager, __entitatManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
