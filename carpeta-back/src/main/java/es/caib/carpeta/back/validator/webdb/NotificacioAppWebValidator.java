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
import es.caib.carpeta.persistence.validator.NotificacioAppValidator;

import es.caib.carpeta.back.form.webdb.NotificacioAppForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.NotificacioApp;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class NotificacioAppWebValidator extends AbstractWebValidator<NotificacioAppForm, NotificacioApp>
     implements Validator, NotificacioAppFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected NotificacioAppValidator<NotificacioApp> validator = new NotificacioAppValidator<NotificacioApp>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatService.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.NotificacioAppService.JNDI_NAME)
  protected es.caib.carpeta.ejb.NotificacioAppService notificacioAppEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PluginService.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioService traduccioEjb;



  public NotificacioAppWebValidator() {
    super();    
  }
  
  @Override
  public NotificacioApp getBeanOfForm(NotificacioAppForm form) {
    return  form.getNotificacioApp();
  }

  @Override
  public Class<NotificacioAppForm> getClassOfForm() {
    return NotificacioAppForm.class;
  }

  @Override
  public void validate(NotificacioAppForm __form, NotificacioApp __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(TITOLID);
_ignoreFields.add(MISSATGEID);
    WebValidationResult<NotificacioAppForm> wvr;
    wvr = new WebValidationResult<NotificacioAppForm>(errors, _ignoreFields);

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


  public void validate(NotificacioAppForm __form, NotificacioApp __bean, Errors errors,
    WebValidationResult<NotificacioAppForm> wvr, boolean isNou) {

  {
      es.caib.carpeta.persistence.NotificacioAppJPA __jpa;
      __jpa = (es.caib.carpeta.persistence.NotificacioAppJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getTitol();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.carpeta.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.carpeta.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.carpeta.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("notificacioApp.titol", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLID.fullName)}, null);
                errors.rejectValue("notificacioApp.titol.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("notificacioApp.titol", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getMissatge();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.carpeta.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.carpeta.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.carpeta.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("notificacioApp.missatge", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MISSATGEID.fullName)}, null);
                errors.rejectValue("notificacioApp.missatge.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MISSATGEID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("notificacioApp.missatge", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MISSATGEID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<NotificacioApp> __vr = new BeanValidatorResult<NotificacioApp>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, notificacioAppEjb, pluginEjb, traduccioEjb);

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

  public NotificacioAppValidator<NotificacioApp> getValidator() {
    return validator;
  }

  public void setValidator(NotificacioAppValidator<NotificacioApp> validator) {
    this.validator = validator;
  }

}