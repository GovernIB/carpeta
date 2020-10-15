package es.caib.carpeta.back.validator.webdb;

import es.caib.carpeta.back.form.webdb.AvisForm;
import es.caib.carpeta.jpa.validator.AvisValidator;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.fields.AvisFields;
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
public class AvisWebValidator extends AbstractWebValidator<AvisForm, Avis>
     implements Validator, AvisFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AvisValidator<Avis> validator = new AvisValidator<Avis>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.AvisLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AvisLocal avisEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.carpeta.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioLocal traduccioEjb;



  public AvisWebValidator() {
    super();    
  }
  
  @Override
  public Avis getBeanOfForm(AvisForm form) {
    return  form.getAvis();
  }

  @Override
  public Class<AvisForm> getClassOfForm() {
    return AvisForm.class;
  }

  @Override
  public void validate(AvisForm __form, Avis __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(DESCRIPCIOID);
    WebValidationResult<AvisForm> wvr;
    wvr = new WebValidationResult<AvisForm>(errors, _ignoreFields);

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


  public void validate(AvisForm __form, Avis __bean, Errors errors,
    WebValidationResult<AvisForm> wvr, boolean isNou) {

  {
      es.caib.carpeta.jpa.AvisJPA __jpa;
      __jpa = (es.caib.carpeta.jpa.AvisJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.carpeta.jpa.TraduccioJPA tradJPA = __jpa.getDescripcio();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.carpeta.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.carpeta.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.carpeta.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("avis.descripcio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
                errors.rejectValue("avis.descripcio.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("avis.descripcio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<Avis> __vr = new BeanValidatorResult<Avis>();
    validator.validate(__vr, __bean,
      isNou, avisEjb, entitatEjb, pluginEjb, traduccioEjb);

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

  public AvisValidator<Avis> getValidator() {
    return validator;
  }

  public void setValidator(AvisValidator<Avis> validator) {
    this.validator = validator;
  }

}