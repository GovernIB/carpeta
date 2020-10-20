package es.caib.carpeta.jpa.validator;

import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import es.caib.carpeta.model.fields.PluginFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Field;
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
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.ILogCarpetaManager __logCarpetaManager
    ,es.caib.carpeta.model.dao.IPluginManager __pluginManager) {

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

    if (__vr.getFieldErrorCount(PETICIO) == 0) {
      java.lang.String __peticio = __target__.getPeticio();
      if (__peticio!= null && __peticio.length() > 255) {
        __vr.rejectValue(PETICIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ERROR) == 0) {
      java.lang.String __error = __target__.getError();
      if (__error!= null && __error.length() > 2000) {
        __vr.rejectValue(ERROR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ERROR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }

    if (__vr.getFieldErrorCount(EXCEPCIO) == 0) {
      java.lang.String __excepcio = __target__.getExcepcio();
      if (__excepcio!= null && __excepcio.length() > 2147483647) {
        __vr.rejectValue(EXCEPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXCEPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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

    if (__vr.getFieldErrorCount(PLUGINID) == 0) {
      java.lang.Long __pluginid = __target__.getPluginID();
      if (__pluginid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}