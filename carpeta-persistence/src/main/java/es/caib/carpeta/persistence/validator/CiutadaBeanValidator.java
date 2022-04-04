package es.caib.carpeta.persistence.validator;

import es.caib.carpeta.persistence.CiutadaJPA;
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
public class CiutadaBeanValidator 
      extends AbstractBeanValidator<CiutadaJPA> {


  // EJB's
  protected final es.caib.carpeta.model.dao.ICiutadaManager __ciutadaManager;


  public final CiutadaValidator<CiutadaJPA> _validator;


  public CiutadaBeanValidator(es.caib.carpeta.model.dao.ICiutadaManager __ciutadaManager) { 
    this.__ciutadaManager = __ciutadaManager;
    _validator = new CiutadaValidator<CiutadaJPA>();
  }

  public CiutadaBeanValidator(CiutadaValidator<CiutadaJPA> _validator,
     es.caib.carpeta.model.dao.ICiutadaManager __ciutadaManager) {
    this.__ciutadaManager = __ciutadaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CiutadaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CiutadaJPA> _bvr_ = new BeanValidatorResult<CiutadaJPA>();
    _validator.validate(_bvr_, target, isNou, __ciutadaManager);
    return _bvr_.getErrors();
  }
}
