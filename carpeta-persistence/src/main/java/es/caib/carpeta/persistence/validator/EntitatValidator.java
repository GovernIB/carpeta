package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Entitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.model.fields.TraduccioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntitatValidator<I extends Entitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements EntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public EntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IPluginManager __pluginManager
    ,es.caib.carpeta.model.dao.ITraduccioManager __traduccioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOMID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIDIR3, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIDIR3)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIVA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIVA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,COLORMENU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COLORMENU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VERSIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,WEBENTITAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(WEBENTITAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATDESCFRONT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATDESCFRONT)));

    // Check size
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = __target__.getCodi();
      if (__codi!= null && __codi.length() > 30) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(CODI) == 0) {
      String val = __target__.getCodi();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z]{3,30}");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(CODI, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CODIDIR3) == 0) {
      java.lang.String __codidir3 = __target__.getCodiDir3();
      if (__codidir3!= null && __codidir3.length() > 255) {
        __vr.rejectValue(CODIDIR3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIDIR3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(COLORMENU) == 0) {
      java.lang.String __colormenu = __target__.getColorMenu();
      if (__colormenu!= null && __colormenu.length() > 100) {
        __vr.rejectValue(COLORMENU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COLORMENU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(VERSIO) == 0) {
      java.lang.String __versio = __target__.getVersio();
      if (__versio!= null && __versio.length() > 50) {
        __vr.rejectValue(VERSIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VERSIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(WEBENTITAT) == 0) {
      java.lang.String __webentitat = __target__.getWebEntitat();
      if (__webentitat!= null && __webentitat.length() > 255) {
        __vr.rejectValue(WEBENTITAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(WEBENTITAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATDESCFRONT) == 0) {
      java.lang.String __entitatdescfront = __target__.getEntitatDescFront();
      if (__entitatdescfront!= null && __entitatdescfront.length() > 4000) {
        __vr.rejectValue(ENTITATDESCFRONT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATDESCFRONT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(4000)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTWEB) == 0) {
      java.lang.String __suportweb = __target__.getSuportWeb();
      if (__suportweb!= null && __suportweb.length() > 255) {
        __vr.rejectValue(SUPORTWEB, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTWEB)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTTELEFON) == 0) {
      java.lang.String __suporttelefon = __target__.getSuportTelefon();
      if (__suporttelefon!= null && __suporttelefon.length() > 255) {
        __vr.rejectValue(SUPORTTELEFON, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTTELEFON)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTEMAIL) == 0) {
      java.lang.String __suportemail = __target__.getSuportEmail();
      if (__suportemail!= null && __suportemail.length() > 255) {
        __vr.rejectValue(SUPORTEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTFAQ) == 0) {
      java.lang.String __suportfaq = __target__.getSuportFAQ();
      if (__suportfaq!= null && __suportfaq.length() > 255) {
        __vr.rejectValue(SUPORTFAQ, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTFAQ)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTQSSI) == 0) {
      java.lang.String __suportqssi = __target__.getSuportqssi();
      if (__suportqssi!= null && __suportqssi.length() > 255) {
        __vr.rejectValue(SUPORTQSSI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTQSSI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUPORTAUTENTICACIO) == 0) {
      java.lang.String __suportautenticacio = __target__.getSuportautenticacio();
      if (__suportautenticacio!= null && __suportautenticacio.length() > 255) {
        __vr.rejectValue(SUPORTAUTENTICACIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTAUTENTICACIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(CONTEXT) == 0) {
      java.lang.String __context = __target__.getContext();
      if (__context!= null && __context.length() > 255) {
        __vr.rejectValue(CONTEXT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTEXT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(COMMIT) == 0) {
      java.lang.String __commit = __target__.getCommit();
      if (__commit!= null && __commit.length() > 255) {
        __vr.rejectValue(COMMIT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COMMIT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(AVISLEGAL) == 0) {
      java.lang.String __avislegal = __target__.getAvisLegal();
      if (__avislegal!= null && __avislegal.length() > 2147483647) {
        __vr.rejectValue(AVISLEGAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(AVISLEGAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(ACCESSIBILITAT) == 0) {
      java.lang.String __accessibilitat = __target__.getAccessibilitat();
      if (__accessibilitat!= null && __accessibilitat.length() > 2147483647) {
        __vr.rejectValue(ACCESSIBILITAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCESSIBILITAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
    if (__vr.getFieldErrorCount(LOGOCAPBACKID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOCAPBACKID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOCAPBACKID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOCAPBACKID)));
      }
    }

    if (__vr.getFieldErrorCount(LOGOPEUBACKID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOPEUBACKID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOPEUBACKID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOPEUBACKID)));
      }
    }

    if (__vr.getFieldErrorCount(LOGOLATERALFRONTID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOLATERALFRONTID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOLATERALFRONTID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOLATERALFRONTID)));
      }
    }

    if (__vr.getFieldErrorCount(ICONID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,ICONID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(ICONID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ICONID)));
      }
    }

      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(NOMID) == 0) {
      java.lang.Long __nomid = __target__.getNomID();
      Long __count_ = null;
      try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__nomid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(NOMID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nomid)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIOID) == 0) {
      java.lang.Long __descripcioid = __target__.getDescripcioID();
      if (__descripcioid != null ) {
        Long __count_ = null;
        try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__descripcioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(DESCRIPCIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__descripcioid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINLOGINID) == 0) {
      java.lang.Long __pluginloginid = __target__.getPluginLoginID();
      if (__pluginloginid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginloginid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINLOGINID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginloginid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(LOGINTEXTID) == 0) {
      java.lang.Long __logintextid = __target__.getLoginTextID();
      if (__logintextid != null ) {
        Long __count_ = null;
        try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__logintextid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(LOGINTEXTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__logintextid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}