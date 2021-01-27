package es.caib.carpeta.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.carpeta.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.carpeta.persistence.validator.LogCarpetaValidator;

import es.caib.carpeta.back.form.webdb.LogCarpetaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.LogCarpeta;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class LogCarpetaWebValidator extends AbstractWebValidator<LogCarpetaForm, LogCarpeta>
     implements Validator, LogCarpetaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected LogCarpetaValidator<LogCarpeta> validator = new LogCarpetaValidator<LogCarpeta>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.LogCarpetaLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.LogCarpetaLocal logCarpetaEjb;



  public LogCarpetaWebValidator() {
    super();    
  }
  
  @Override
  public LogCarpeta getBeanOfForm(LogCarpetaForm form) {
    return  form.getLogCarpeta();
  }

  @Override
  public Class<LogCarpetaForm> getClassOfForm() {
    return LogCarpetaForm.class;
  }

  @Override
  public void validate(LogCarpetaForm __form, LogCarpeta __bean, Errors errors) {

    WebValidationResult<LogCarpetaForm> wvr;
    wvr = new WebValidationResult<LogCarpetaForm>(errors);

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


  public void validate(LogCarpetaForm __form, LogCarpeta __bean, Errors errors,
    WebValidationResult<LogCarpetaForm> wvr, boolean isNou) {

    BeanValidatorResult<LogCarpeta> __vr = new BeanValidatorResult<LogCarpeta>();
    validator.validate(__vr, __bean,
      isNou, logCarpetaEjb);

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

  public LogCarpetaValidator<LogCarpeta> getValidator() {
    return validator;
  }

  public void setValidator(LogCarpetaValidator<LogCarpeta> validator) {
    this.validator = validator;
  }

}