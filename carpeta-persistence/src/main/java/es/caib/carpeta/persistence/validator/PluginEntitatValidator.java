package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.PluginEntitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.PluginFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PluginEntitatValidator<I extends PluginEntitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PluginEntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PluginEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IPluginManager __pluginManager
    ,es.caib.carpeta.model.dao.IPluginEntitatManager __pluginEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));

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

      // Check Unique MULTIPLE for (pluginid, entitatid)
      if (__vr.getFieldErrorCount(PLUGINID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0) {
        java.lang.Long __pluginid = __target__.getPluginID();
        java.lang.Long __entitatid = __target__.getEntitatID();
        Long __count_ = null;
        try { __count_ = __pluginEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(PLUGINID.equal(__pluginid), ENTITATID.equal(__entitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(PLUGINID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));
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

      // Check Unique MULTIPLE for (pluginid, entitatid)
      if (__vr.getFieldErrorCount(PLUGINID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0 && __vr.getFieldErrorCount(PLUGINENTITATID) == 0) {
        java.lang.Long __pluginid = __target__.getPluginID();
        java.lang.Long __entitatid = __target__.getEntitatID();
        java.lang.Long __pluginentitatid = __target__.getPluginEntitatID();
        Long __count_ = null;
        try { __count_ = __pluginEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(PLUGINID.equal(__pluginid), ENTITATID.equal(__entitatid), PLUGINENTITATID.notEqual(__pluginentitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(PLUGINID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(PLUGINID) == 0) {
      java.lang.Long __pluginid = __target__.getPluginID();
      Long __count_ = null;
      try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PLUGINID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)));
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