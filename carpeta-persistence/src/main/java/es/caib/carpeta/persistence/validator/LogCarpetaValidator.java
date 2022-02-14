package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.LogCarpeta;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.LogCarpetaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class LogCarpetaValidator<I extends LogCarpeta>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements LogCarpetaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public LogCarpetaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DESCRIPCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAINICI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAINICI)));

    // Check size
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = __target__.getDescripcio();
      if (__descripcio!= null && __descripcio.length() > 2000) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATCODI) == 0) {
      java.lang.String __entitatcodi = __target__.getEntitatCodi();
      if (__entitatcodi!= null && __entitatcodi.length() > 9) {
        __vr.rejectValue(ENTITATCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
      }
    }

    if (__vr.getFieldErrorCount(PETICIO) == 0) {
      java.lang.String __peticio = __target__.getPeticio();
      if (__peticio!= null && __peticio.length() > 255) {
        __vr.rejectValue(PETICIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ERROR) == 0) {
      java.lang.String __error = __target__.getError();
      if (__error!= null && __error.length() > 2147483647) {
        __vr.rejectValue(ERROR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ERROR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(EXCEPCIO) == 0) {
      java.lang.String __excepcio = __target__.getExcepcio();
      if (__excepcio!= null && __excepcio.length() > 2147483647) {
        __vr.rejectValue(EXCEPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXCEPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(IDSESSIO) == 0) {
      java.lang.String __idsessio = __target__.getIdSessio();
      if (__idsessio!= null && __idsessio.length() > 2147483647) {
        __vr.rejectValue(IDSESSIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDSESSIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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