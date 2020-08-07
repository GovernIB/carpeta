package es.caib.carpeta.jpa.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.UsuariEntitatFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.UsuariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariEntitatValidator<I extends UsuariEntitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements UsuariEntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public UsuariEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IUsuariManager __usuariManager
    ,es.caib.carpeta.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuariid, entitatid)
      if (__vr.getFieldErrorCount(USUARIID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0) {
        java.lang.Long __usuariid = __target__.getUsuariID();
        java.lang.Long __entitatid = __target__.getEntitatID();
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIID.equal(__usuariid), ENTITATID.equal(__entitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuariid, entitatid)
      if (__vr.getFieldErrorCount(USUARIID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0) {
        java.lang.Long __usuariid = __target__.getUsuariID();
        java.lang.Long __entitatid = __target__.getEntitatID();
        java.lang.Long __usuarientitatid = __target__.getUsuariEntitatID();
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIID.equal(__usuariid), ENTITATID.equal(__entitatid), USUARIENTITATID.notEqual(__usuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.Long __usuariid = __target__.getUsuariID();
      Long __count_ = null;
      try { __count_ = __usuariManager.count(UsuariFields.USUARIID.equal(__usuariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)));
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