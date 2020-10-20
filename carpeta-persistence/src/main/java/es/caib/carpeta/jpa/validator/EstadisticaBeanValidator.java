package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.jpa.EstadisticaJPA;
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
public class EstadisticaBeanValidator 
      extends AbstractBeanValidator<EstadisticaJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.IAccesManager __accesManager;

  protected final es.caib.carpeta.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.carpeta.model.dao.IEstadisticaManager __estadisticaManager;


  public final EstadisticaValidator<EstadisticaJPA> _validator;


  public EstadisticaBeanValidator(es.caib.carpeta.model.dao.IAccesManager __accesManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IEstadisticaManager __estadisticaManager) { 
    this.__accesManager = __accesManager;
    this.__entitatManager = __entitatManager;
    this.__estadisticaManager = __estadisticaManager;
    _validator = new EstadisticaValidator<EstadisticaJPA>();
  }

  public EstadisticaBeanValidator(EstadisticaValidator<EstadisticaJPA> _validator,
     es.caib.carpeta.model.dao.IAccesManager __accesManager,
     es.caib.carpeta.model.dao.IEntitatManager __entitatManager,
     es.caib.carpeta.model.dao.IEstadisticaManager __estadisticaManager) {
    this.__accesManager = __accesManager;
    this.__entitatManager = __entitatManager;
    this.__estadisticaManager = __estadisticaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstadisticaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstadisticaJPA> _bvr_ = new BeanValidatorResult<EstadisticaJPA>();
    _validator.validate(_bvr_, target, isNou, __accesManager, __entitatManager, __estadisticaManager);
    return _bvr_.getErrors();
  }
}
