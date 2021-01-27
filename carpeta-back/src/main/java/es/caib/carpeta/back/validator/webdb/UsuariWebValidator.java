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
import es.caib.carpeta.persistence.validator.UsuariValidator;

import es.caib.carpeta.back.form.webdb.UsuariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.Usuari;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariWebValidator extends AbstractWebValidator<UsuariForm, Usuari>
     implements Validator, UsuariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariValidator<Usuari> validator = new UsuariValidator<Usuari>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaLocal idiomaEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.UsuariLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.UsuariLocal usuariEjb;



  public UsuariWebValidator() {
    super();    
  }
  
  @Override
  public Usuari getBeanOfForm(UsuariForm form) {
    return  form.getUsuari();
  }

  @Override
  public Class<UsuariForm> getClassOfForm() {
    return UsuariForm.class;
  }

  @Override
  public void validate(UsuariForm __form, Usuari __bean, Errors errors) {

    WebValidationResult<UsuariForm> wvr;
    wvr = new WebValidationResult<UsuariForm>(errors);

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


  public void validate(UsuariForm __form, Usuari __bean, Errors errors,
    WebValidationResult<UsuariForm> wvr, boolean isNou) {

    BeanValidatorResult<Usuari> __vr = new BeanValidatorResult<Usuari>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, idiomaEjb, usuariEjb);

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

  public UsuariValidator<Usuari> getValidator() {
    return validator;
  }

  public void setValidator(UsuariValidator<Usuari> validator) {
    this.validator = validator;
  }

}