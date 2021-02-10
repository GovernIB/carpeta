package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.PluginEntitatJPA;
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
public class PluginEntitatBeanValidator 
      extends AbstractBeanValidator<PluginEntitatJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.IPluginManager __pluginManager;

  protected final es.caib.carpeta.model.dao.IPluginEntitatManager __pluginEntitatManager;

  protected final es.caib.carpeta.model.dao.ISeccioManager __seccioManager;


  public final PluginEntitatValidator<PluginEntitatJPA> _validator;


  public PluginEntitatBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.IPluginEntitatManager __pluginEntitatManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__pluginEntitatManager = __pluginEntitatManager;
    this.__seccioManager = __seccioManager;
    _validator = new PluginEntitatValidator<PluginEntitatJPA>();
  }

  public PluginEntitatBeanValidator(PluginEntitatValidator<PluginEntitatJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.IPluginEntitatManager __pluginEntitatManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager) {
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__pluginEntitatManager = __pluginEntitatManager;
    this.__seccioManager = __seccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PluginEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PluginEntitatJPA> _bvr_ = new BeanValidatorResult<PluginEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __pluginManager, __pluginEntitatManager, __seccioManager);
    return _bvr_.getErrors();
  }
}
