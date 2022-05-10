package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.NotificacioAppJPA;
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
public class NotificacioAppBeanValidator 
      extends AbstractBeanValidator<NotificacioAppJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.INotificacioAppManager __notificacioAppManager;

  protected final es.caib.carpeta.model.dao.IPluginManager __pluginManager;

  protected final es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager;


  public final NotificacioAppValidator<NotificacioAppJPA> _validator;


  public NotificacioAppBeanValidator(es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.INotificacioAppManager __notificacioAppManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__notificacioAppManager = __notificacioAppManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new NotificacioAppValidator<NotificacioAppJPA>();
  }

  public NotificacioAppBeanValidator(NotificacioAppValidator<NotificacioAppJPA> _validator,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.INotificacioAppManager __notificacioAppManager,
     es.caib.carpeta.model.dao.IPluginManager __pluginManager,
     es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__notificacioAppManager = __notificacioAppManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(NotificacioAppJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<NotificacioAppJPA> _bvr_ = new BeanValidatorResult<NotificacioAppJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __notificacioAppManager, __pluginManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
