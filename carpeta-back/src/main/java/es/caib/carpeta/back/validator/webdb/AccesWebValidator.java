package es.caib.carpeta.back.validator.webdb;

import es.caib.carpeta.back.form.webdb.AccesForm;
import es.caib.carpeta.jpa.validator.AccesValidator;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.fields.AccesFields;
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
public class AccesWebValidator extends AbstractWebValidator<AccesForm, Acces>
     implements Validator, AccesFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AccesValidator<Acces> validator = new AccesValidator<Acces>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.AccesLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AccesLocal accesEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;



  public AccesWebValidator() {
    super();    
  }
  
  @Override
  public Acces getBeanOfForm(AccesForm form) {
    return  form.getAcces();
  }

  @Override
  public Class<AccesForm> getClassOfForm() {
    return AccesForm.class;
  }

  @Override
  public void validate(AccesForm __form, Acces __bean, Errors errors) {

    WebValidationResult<AccesForm> wvr;
    wvr = new WebValidationResult<AccesForm>(errors);

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


  public void validate(AccesForm __form, Acces __bean, Errors errors,
    WebValidationResult<AccesForm> wvr, boolean isNou) {

    BeanValidatorResult<Acces> __vr = new BeanValidatorResult<Acces>();
    validator.validate(__vr, __bean,
      isNou, accesEjb, entitatEjb);

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

  public AccesValidator<Acces> getValidator() {
    return validator;
  }

  public void setValidator(AccesValidator<Acces> validator) {
    this.validator = validator;
  }

}