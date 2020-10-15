package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.LogCarpetaJPA;
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
public class LogCarpetaBeanValidator 
      extends AbstractBeanValidator<LogCarpetaJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager;

  protected final es.caib.carpeta.model.dao.IPluginManager __pluginManager;


  public final LogCarpetaValidator<LogCarpetaJPA> _validator;


  public LogCarpetaBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager) { 
    this.__entitatManager = __entitatManager;
    this.__logCarpetaManager = __logCarpetaManager;
    this.__pluginManager = __pluginManager;
    _validator = new LogCarpetaValidator<LogCarpetaJPA>();
  }

  public LogCarpetaBeanValidator(LogCarpetaValidator<LogCarpetaJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager) {
    this.__entitatManager = __entitatManager;
    this.__logCarpetaManager = __logCarpetaManager;
    this.__pluginManager = __pluginManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(LogCarpetaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<LogCarpetaJPA> _bvr_ = new BeanValidatorResult<LogCarpetaJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __logCarpetaManager, __pluginManager);
    return _bvr_.getErrors();
  }
}
