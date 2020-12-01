package es.caib.carpeta.jpa.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Auditoria;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.AuditoriaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class AuditoriaValidator<I extends Auditoria>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements AuditoriaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public AuditoriaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IAuditoriaManager __auditoriaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATAAUDIT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAAUDIT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    // Check size
    if (__vr.getFieldErrorCount(USERNAME) == 0) {
      java.lang.String __username = __target__.getUsername();
      if (__username!= null && __username.length() > 255) {
        __vr.rejectValue(USERNAME, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(USERNAME) == 0) {
      String val = __target__.getUsername();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("(1|2|3|4)");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(USERNAME, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)));
        }
      }
    }

    if (__vr.getFieldErrorCount(USUARICLAVE) == 0) {
      java.lang.String __usuariclave = __target__.getUsuariClave();
      if (__usuariclave!= null && __usuariclave.length() > 256) {
        __vr.rejectValue(USUARICLAVE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARICLAVE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}