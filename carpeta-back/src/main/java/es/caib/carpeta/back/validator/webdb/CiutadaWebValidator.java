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
import es.caib.carpeta.persistence.validator.CiutadaValidator;

import es.caib.carpeta.back.form.webdb.CiutadaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.Ciutada;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CiutadaWebValidator extends AbstractWebValidator<CiutadaForm, Ciutada>
     implements Validator, CiutadaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected CiutadaValidator<Ciutada> validator = new CiutadaValidator<Ciutada>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.CiutadaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.CiutadaService ciutadaEjb;



  public CiutadaWebValidator() {
    super();    
  }
  
  @Override
  public Ciutada getBeanOfForm(CiutadaForm form) {
    return  form.getCiutada();
  }

  @Override
  public Class<CiutadaForm> getClassOfForm() {
    return CiutadaForm.class;
  }

  @Override
  public void validate(CiutadaForm __form, Ciutada __bean, Errors errors) {

    WebValidationResult<CiutadaForm> wvr;
    wvr = new WebValidationResult<CiutadaForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(CiutadaForm __form, Ciutada __bean, Errors errors,
    WebValidationResult<CiutadaForm> wvr, boolean isNou) {

    BeanValidatorResult<Ciutada> __vr = new BeanValidatorResult<Ciutada>();
    validator.validate(__vr, __bean,
      isNou, ciutadaEjb);

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

  public CiutadaValidator<Ciutada> getValidator() {
    return validator;
  }

  public void setValidator(CiutadaValidator<Ciutada> validator) {
    this.validator = validator;
  }

}