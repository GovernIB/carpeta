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
import es.caib.carpeta.jpa.validator.PluginEntitatValidator;

import es.caib.carpeta.back.form.webdb.PluginEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.PluginEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginEntitatWebValidator extends AbstractWebValidator<PluginEntitatForm, PluginEntitat>
     implements Validator, PluginEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginEntitatValidator<PluginEntitat> validator = new PluginEntitatValidator<PluginEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PluginEntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginEntitatLocal pluginEntitatEjb;



  public PluginEntitatWebValidator() {
    super();    
  }
  
  @Override
  public PluginEntitat getBeanOfForm(PluginEntitatForm form) {
    return  form.getPluginEntitat();
  }

  @Override
  public Class<PluginEntitatForm> getClassOfForm() {
    return PluginEntitatForm.class;
  }

  @Override
  public void validate(PluginEntitatForm __form, PluginEntitat __bean, Errors errors) {

    WebValidationResult<PluginEntitatForm> wvr;
    wvr = new WebValidationResult<PluginEntitatForm>(errors);

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


  public void validate(PluginEntitatForm __form, PluginEntitat __bean, Errors errors,
    WebValidationResult<PluginEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<PluginEntitat> __vr = new BeanValidatorResult<PluginEntitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, pluginEjb, pluginEntitatEjb);

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

  public PluginEntitatValidator<PluginEntitat> getValidator() {
    return validator;
  }

  public void setValidator(PluginEntitatValidator<PluginEntitat> validator) {
    this.validator = validator;
  }

}