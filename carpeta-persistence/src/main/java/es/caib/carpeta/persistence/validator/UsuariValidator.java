package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Usuari;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.UsuariFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.IdiomaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariValidator<I extends Usuari>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements UsuariFields {

    protected final Logger log = Logger.getLogger(getClass());


  public UsuariValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.IEntitatManager __entitatManager
    ,es.caib.carpeta.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.carpeta.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USERNAME, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,LLINATGE1, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)));

    // Check size
    if (__vr.getFieldErrorCount(USERNAME) == 0) {
      java.lang.String __username = __target__.getUsername();
      if (__username!= null && __username.length() > 255) {
        __vr.rejectValue(USERNAME, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE1) == 0) {
      java.lang.String __llinatge1 = __target__.getLlinatge1();
      if (__llinatge1!= null && __llinatge1.length() > 255) {
        __vr.rejectValue(LLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGE2) == 0) {
      java.lang.String __llinatge2 = __target__.getLlinatge2();
      if (__llinatge2!= null && __llinatge2.length() > 255) {
        __vr.rejectValue(LLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(EMAIL) == 0) {
      java.lang.String __email = __target__.getEmail();
      if (__email!= null && __email.length() > 255) {
        __vr.rejectValue(EMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 255) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(USERNAME) == 0) {
        java.lang.String __username = __target__.getUsername();
        Long __count_ = null;
        try { __count_ = __usuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(USERNAME.equal(__username))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USERNAME, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__username)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)));
        }
      }

      if (__vr.getFieldErrorCount(NIF) == 0) {
        java.lang.String __nif = __target__.getNif();
        Long __count_ = null;
        try { __count_ = __usuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(USERNAME) == 0 && __vr.getFieldErrorCount(USUARIID) == 0) {
        java.lang.String __username = __target__.getUsername();
        java.lang.Long __usuariid = __target__.getUsuariID();
        Long __count_ = null;
        try { __count_ = __usuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(USERNAME.equal(__username), USUARIID.notEqual(__usuariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USERNAME, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__username)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USERNAME)));
        }
      }

      if (__vr.getFieldErrorCount(NIF) == 0 && __vr.getFieldErrorCount(USUARIID) == 0) {
        java.lang.String __nif = __target__.getNif();
        java.lang.Long __usuariid = __target__.getUsuariID();
        Long __count_ = null;
        try { __count_ = __usuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif), USUARIID.notEqual(__usuariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(DARRERAENTITAT) == 0) {
      java.lang.Long __darreraentitat = __target__.getDarreraEntitat();
      if (__darreraentitat != null ) {
        Long __count_ = null;
        try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__darreraentitat)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(DARRERAENTITAT, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__darreraentitat)));
        }
      }
    }

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      Long __count_ = null;
      try { __count_ = __idiomaManager.count(IdiomaFields.IDIOMAID.equal(__idiomaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(IDIOMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idioma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idiomaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__idiomaid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}