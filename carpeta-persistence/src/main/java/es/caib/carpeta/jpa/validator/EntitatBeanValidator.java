package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.EntitatJPA;
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
public class EntitatBeanValidator 
      extends AbstractBeanValidator<EntitatJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.IPluginManager __pluginManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final EntitatValidator<EntitatJPA> _validator;


  public EntitatBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new EntitatValidator<EntitatJPA>();
  }

  public EntitatBeanValidator(EntitatValidator<EntitatJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntitatJPA> _bvr_ = new BeanValidatorResult<EntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __pluginManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
