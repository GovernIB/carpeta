package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.SeccioJPA;
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
public class SeccioBeanValidator 
      extends AbstractBeanValidator<SeccioJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.ISeccioManager __seccioManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final SeccioValidator<SeccioJPA> _validator;


  public SeccioBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__seccioManager = __seccioManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new SeccioValidator<SeccioJPA>();
  }

  public SeccioBeanValidator(SeccioValidator<SeccioJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__seccioManager = __seccioManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SeccioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SeccioJPA> _bvr_ = new BeanValidatorResult<SeccioJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __seccioManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
