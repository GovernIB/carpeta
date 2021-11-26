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
import es.caib.carpeta.persistence.validator.PreguntesFrequentsValidator;

import es.caib.carpeta.back.form.webdb.PreguntesFrequentsForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.PreguntesFrequents;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PreguntesFrequentsWebValidator extends AbstractWebValidator<PreguntesFrequentsForm, PreguntesFrequents>
     implements Validator, PreguntesFrequentsFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PreguntesFrequentsValidator<PreguntesFrequents> validator = new PreguntesFrequentsValidator<PreguntesFrequents>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatService.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PreguntesFrequentsService.JNDI_NAME)
  protected es.caib.carpeta.ejb.PreguntesFrequentsService preguntesFrequentsEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioService traduccioEjb;



  public PreguntesFrequentsWebValidator() {
    super();    
  }
  
  @Override
  public PreguntesFrequents getBeanOfForm(PreguntesFrequentsForm form) {
    return  form.getPreguntesFrequents();
  }

  @Override
  public Class<PreguntesFrequentsForm> getClassOfForm() {
    return PreguntesFrequentsForm.class;
  }

  @Override
  public void validate(PreguntesFrequentsForm __form, PreguntesFrequents __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(ENUNCIATID);
_ignoreFields.add(RESPOSTAID);
    WebValidationResult<PreguntesFrequentsForm> wvr;
    wvr = new WebValidationResult<PreguntesFrequentsForm>(errors, _ignoreFields);

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


  public void validate(PreguntesFrequentsForm __form, PreguntesFrequents __bean, Errors errors,
    WebValidationResult<PreguntesFrequentsForm> wvr, boolean isNou) {

  {
      es.caib.carpeta.persistence.PreguntesFrequentsJPA __jpa;
      __jpa = (es.caib.carpeta.persistence.PreguntesFrequentsJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getEnunciat();
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
                errors.rejectValue("preguntesFrequents.enunciat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(ENUNCIATID.fullName)}, null);
                errors.rejectValue("preguntesFrequents.enunciat.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(ENUNCIATID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("preguntesFrequents.enunciat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(ENUNCIATID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getResposta();
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
                errors.rejectValue("preguntesFrequents.resposta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(RESPOSTAID.fullName)}, null);
                errors.rejectValue("preguntesFrequents.resposta.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(RESPOSTAID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("preguntesFrequents.resposta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(RESPOSTAID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<PreguntesFrequents> __vr = new BeanValidatorResult<PreguntesFrequents>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, preguntesFrequentsEjb, traduccioEjb);

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

  public PreguntesFrequentsValidator<PreguntesFrequents> getValidator() {
    return validator;
  }

  public void setValidator(PreguntesFrequentsValidator<PreguntesFrequents> validator) {
    this.validator = validator;
  }

}