package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.LogCarpetaJPA;
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
public class LogCarpetaBeanValidator 
      extends AbstractBeanValidator<LogCarpetaJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager;


  public final LogCarpetaValidator<LogCarpetaJPA> _validator;


  public LogCarpetaBeanValidator(es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager) { 
    this.__logCarpetaManager = __logCarpetaManager;
    _validator = new LogCarpetaValidator<LogCarpetaJPA>();
  }

  public LogCarpetaBeanValidator(LogCarpetaValidator<LogCarpetaJPA> _validator,
     es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager) {
    this.__logCarpetaManager = __logCarpetaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(LogCarpetaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<LogCarpetaJPA> _bvr_ = new BeanValidatorResult<LogCarpetaJPA>();
    _validator.validate(_bvr_, target, isNou, __logCarpetaManager);
    return _bvr_.getErrors();
  }
}
