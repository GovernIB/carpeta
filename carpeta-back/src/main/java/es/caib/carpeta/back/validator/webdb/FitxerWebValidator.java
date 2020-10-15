package es.caib.carpeta.back.validator.webdb;

import es.caib.carpeta.back.form.webdb.FitxerForm;
import es.caib.carpeta.jpa.validator.FitxerValidator;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.model.fields.FitxerFields;
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
public class FitxerWebValidator extends AbstractWebValidator<FitxerForm, Fitxer>
     implements Validator, FitxerFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FitxerValidator<Fitxer> validator = new FitxerValidator<Fitxer>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.FitxerLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.FitxerLocal fitxerEjb;



  public FitxerWebValidator() {
    super();    
  }
  
  @Override
  public Fitxer getBeanOfForm(FitxerForm form) {
    return  form.getFitxer();
  }

  @Override
  public Class<FitxerForm> getClassOfForm() {
    return FitxerForm.class;
  }

  @Override
  public void validate(FitxerForm __form, Fitxer __bean, Errors errors) {

    WebValidationResult<FitxerForm> wvr;
    wvr = new WebValidationResult<FitxerForm>(errors);

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


  public void validate(FitxerForm __form, Fitxer __bean, Errors errors,
    WebValidationResult<FitxerForm> wvr, boolean isNou) {

    BeanValidatorResult<Fitxer> __vr = new BeanValidatorResult<Fitxer>();
    validator.validate(__vr, __bean,
      isNou, fitxerEjb);

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

  public FitxerValidator<Fitxer> getValidator() {
    return validator;
  }

  public void setValidator(FitxerValidator<Fitxer> validator) {
    this.validator = validator;
  }

}