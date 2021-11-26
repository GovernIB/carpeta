package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.PreguntesFrequentsJPA;
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
public class PreguntesFrequentsBeanValidator 
      extends AbstractBeanValidator<PreguntesFrequentsJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.IPreguntesFrequentsManager __preguntesFrequentsManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final PreguntesFrequentsValidator<PreguntesFrequentsJPA> _validator;


  public PreguntesFrequentsBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPreguntesFrequentsManager __preguntesFrequentsManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__preguntesFrequentsManager = __preguntesFrequentsManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new PreguntesFrequentsValidator<PreguntesFrequentsJPA>();
  }

  public PreguntesFrequentsBeanValidator(PreguntesFrequentsValidator<PreguntesFrequentsJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPreguntesFrequentsManager __preguntesFrequentsManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__preguntesFrequentsManager = __preguntesFrequentsManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PreguntesFrequentsJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PreguntesFrequentsJPA> _bvr_ = new BeanValidatorResult<PreguntesFrequentsJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __preguntesFrequentsManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
