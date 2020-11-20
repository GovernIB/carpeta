package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.AuditoriaJPA;
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
public class AuditoriaBeanValidator 
      extends AbstractBeanValidator<AuditoriaJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IAuditoriaManager __auditoriaManager;

  protected final es.caib.carpeta.model.dao.IUsuariManager __usuariManager;


  public final AuditoriaValidator<AuditoriaJPA> _validator;


  public AuditoriaBeanValidator(es.caib.carpeta.model.dao.IAuditoriaManager __auditoriaManager,
     es.caib.carpeta.model.dao.IUsuariManager __usuariManager) { 
    this.__auditoriaManager = __auditoriaManager;
    this.__usuariManager = __usuariManager;
    _validator = new AuditoriaValidator<AuditoriaJPA>();
  }

  public AuditoriaBeanValidator(AuditoriaValidator<AuditoriaJPA> _validator,
     es.caib.carpeta.model.dao.IAuditoriaManager __auditoriaManager,
     es.caib.carpeta.model.dao.IUsuariManager __usuariManager) {
    this.__auditoriaManager = __auditoriaManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AuditoriaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AuditoriaJPA> _bvr_ = new BeanValidatorResult<AuditoriaJPA>();
    _validator.validate(_bvr_, target, isNou, __auditoriaManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
