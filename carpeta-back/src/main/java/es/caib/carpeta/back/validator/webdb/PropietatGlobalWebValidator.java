package es.caib.carpeta.back.validator.webdb;

import es.caib.carpeta.back.form.webdb.PropietatGlobalForm;
import es.caib.carpeta.jpa.validator.PropietatGlobalValidator;
import es.caib.carpeta.model.entity.PropietatGlobal;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PropietatGlobalWebValidator extends AbstractWebValidator<PropietatGlobalForm, PropietatGlobal>
     implements Validator, PropietatGlobalFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PropietatGlobalValidator<PropietatGlobal> validator = new PropietatGlobalValidator<PropietatGlobal>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PropietatGlobalLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.PropietatGlobalLocal propietatGlobalEjb;



  public PropietatGlobalWebValidator() {
    super();    
  }
  
  @Override
  public PropietatGlobal getBeanOfForm(PropietatGlobalForm form) {
    return  form.getPropietatGlobal();
  }

  @Override
  public Class<PropietatGlobalForm> getClassOfForm() {
    return PropietatGlobalForm.class;
  }

  @Override
  public void validate(PropietatGlobalForm __form, PropietatGlobal __bean, Errors errors) {

    WebValidationResult<PropietatGlobalForm> wvr;
    wvr = new WebValidationResult<PropietatGlobalForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(PropietatGlobalForm __form, PropietatGlobal __bean, Errors errors,
    WebValidationResult<PropietatGlobalForm> wvr, boolean isNou) {

    BeanValidatorResult<PropietatGlobal> __vr = new BeanValidatorResult<PropietatGlobal>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, propietatGlobalEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PropietatGlobalValidator<PropietatGlobal> getValidator() {
    return validator;
  }

  public void setValidator(PropietatGlobalValidator<PropietatGlobal> validator) {
    this.validator = validator;
  }

}