package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.PreguntesFrequents;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.PreguntesFrequentsFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.TraduccioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PreguntesFrequentsValidator<I extends PreguntesFrequents>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PreguntesFrequentsFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PreguntesFrequentsValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IPreguntesFrequentsManager __preguntesFrequentsManager
    ,es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ENUNCIATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENUNCIATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ORDRE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORDRE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,RESPOSTACA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTACA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,RESPOSTAES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTAES)));

    // Check size
    if (__vr.getFieldErrorCount(RESPOSTACA) == 0) {
      java.lang.String __respostaca = __target__.getRespostaCa();
      if (__respostaca!= null && __respostaca.length() > 2147483647) {
        __vr.rejectValue(RESPOSTACA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTACA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(RESPOSTAES) == 0) {
      java.lang.String __respostaes = __target__.getRespostaEs();
      if (__respostaes!= null && __respostaes.length() > 2147483647) {
        __vr.rejectValue(RESPOSTAES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTAES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    if (__vr.getFieldErrorCount(ENUNCIATID) == 0) {
      java.lang.Long __enunciatid = __target__.getEnunciatID();
      Long __count_ = null;
      try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__enunciatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENUNCIATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__enunciatid)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.Long __entitatid = __target__.getEntitatID();
      Long __count_ = null;
      try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}