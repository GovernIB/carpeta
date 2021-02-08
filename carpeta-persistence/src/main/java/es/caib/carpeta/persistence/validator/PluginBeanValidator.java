package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.PluginJPA;
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
public class PluginBeanValidator 
      extends AbstractBeanValidator<PluginJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IPluginManager __pluginManager;

  protected final es.caib.carpeta.model.dao.ISeccioManager __seccioManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final PluginValidator<PluginJPA> _validator;


  public PluginBeanValidator(es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__pluginManager = __pluginManager;
    this.__seccioManager = __seccioManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new PluginValidator<PluginJPA>();
  }

  public PluginBeanValidator(PluginValidator<PluginJPA> _validator,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ISeccioManager __seccioManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__pluginManager = __pluginManager;
    this.__seccioManager = __seccioManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PluginJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PluginJPA> _bvr_ = new BeanValidatorResult<PluginJPA>();
    _validator.validate(_bvr_, target, isNou, __pluginManager, __seccioManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
