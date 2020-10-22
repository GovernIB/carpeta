package es.caib.carpeta.jpa.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Auditoria;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.AuditoriaFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.UsuariFields;

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
    ,es.caib.carpeta.model.dao.IAuditoriaManager __auditoriaManager
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ACCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAAUDIT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAAUDIT)));

    // Check size
    if (__vr.getFieldErrorCount(ELEMENT) == 0) {
      java.lang.String __element = __target__.getElement();
      if (__element!= null && __element.length() > 255) {
        __vr.rejectValue(ELEMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ELEMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.Long __entitatid = __target__.getEntitatID();
      if (__entitatid != null ) {
        Long __count_ = null;
        try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.Long __usuariid = __target__.getUsuariID();
      if (__usuariid != null ) {
        Long __count_ = null;
        try { __count_ = __usuariManager.count(UsuariFields.USUARIID.equal(__usuariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}