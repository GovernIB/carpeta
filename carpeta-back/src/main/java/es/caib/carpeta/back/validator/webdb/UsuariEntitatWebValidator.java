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
import es.caib.carpeta.persistence.validator.UsuariEntitatValidator;

import es.caib.carpeta.back.form.webdb.UsuariEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.UsuariEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariEntitatWebValidator extends AbstractWebValidator<UsuariEntitatForm, UsuariEntitat>
     implements Validator, UsuariEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariEntitatValidator<UsuariEntitat> validator = new UsuariEntitatValidator<UsuariEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.UsuariLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.UsuariLocal usuariEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public UsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public UsuariEntitat getBeanOfForm(UsuariEntitatForm form) {
    return  form.getUsuariEntitat();
  }

  @Override
  public Class<UsuariEntitatForm> getClassOfForm() {
    return UsuariEntitatForm.class;
  }

  @Override
  public void validate(UsuariEntitatForm __form, UsuariEntitat __bean, Errors errors) {

    WebValidationResult<UsuariEntitatForm> wvr;
    wvr = new WebValidationResult<UsuariEntitatForm>(errors);

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


  public void validate(UsuariEntitatForm __form, UsuariEntitat __bean, Errors errors,
    WebValidationResult<UsuariEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<UsuariEntitat> __vr = new BeanValidatorResult<UsuariEntitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, usuariEjb, usuariEntitatEjb);

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

  public UsuariEntitatValidator<UsuariEntitat> getValidator() {
    return validator;
  }

  public void setValidator(UsuariEntitatValidator<UsuariEntitat> validator) {
    this.validator = validator;
  }

}