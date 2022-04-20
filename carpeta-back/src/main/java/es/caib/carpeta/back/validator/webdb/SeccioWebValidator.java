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
import es.caib.carpeta.persistence.validator.SeccioValidator;

import es.caib.carpeta.back.form.webdb.SeccioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.carpeta.model.entity.Seccio;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SeccioWebValidator extends AbstractWebValidator<SeccioForm, Seccio>
     implements Validator, SeccioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected SeccioValidator<Seccio> validator = new SeccioValidator<Seccio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatService.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.SeccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.SeccioService seccioEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioService traduccioEjb;



  public SeccioWebValidator() {
    super();    
  }
  
  @Override
  public Seccio getBeanOfForm(SeccioForm form) {
    return  form.getSeccio();
  }

  @Override
  public Class<SeccioForm> getClassOfForm() {
    return SeccioForm.class;
  }

  @Override
  public void validate(SeccioForm __form, Seccio __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
_ignoreFields.add(DESCRIPCIOID);
    WebValidationResult<SeccioForm> wvr;
    wvr = new WebValidationResult<SeccioForm>(errors, _ignoreFields);

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


  public void validate(SeccioForm __form, Seccio __bean, Errors errors,
    WebValidationResult<SeccioForm> wvr, boolean isNou) {

  {
      es.caib.carpeta.persistence.SeccioJPA __jpa;
      __jpa = (es.caib.carpeta.persistence.SeccioJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getNom();
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
                errors.rejectValue("seccio.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("seccio.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("seccio.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.persistence.TraduccioJPA tradJPA = __jpa.getDescripcio();
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
                errors.rejectValue("seccio.descripcio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
                errors.rejectValue("seccio.descripcio.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("seccio.descripcio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
      }
    }

  }
    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile iconaID = ((SeccioForm)__form).getIconaID();
      if (iconaID == null || iconaID.isEmpty()) {
        errors.rejectValue(get(ICONAID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(ICONAID)) },
          null);
      }

    }
    BeanValidatorResult<Seccio> __vr = new BeanValidatorResult<Seccio>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, seccioEjb, traduccioEjb);

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

  public SeccioValidator<Seccio> getValidator() {
    return validator;
  }

  public void setValidator(SeccioValidator<Seccio> validator) {
    this.validator = validator;
  }

}