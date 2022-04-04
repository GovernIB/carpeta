package es.caib.carpeta.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.carpeta.model.entity.Ciutada;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.carpeta.model.fields.CiutadaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class CiutadaValidator<I extends Ciutada>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements CiutadaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public CiutadaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.carpeta.model.dao.ICiutadaManager __ciutadaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,EMPRESA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMPRESA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 100) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
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

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTNIF) == 0) {
      java.lang.String __representantnif = __target__.getRepresentantNif();
      if (__representantnif!= null && __representantnif.length() > 100) {
        __vr.rejectValue(REPRESENTANTNIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTNIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTLLINATGE1) == 0) {
      java.lang.String __representantllinatge1 = __target__.getRepresentantLlinatge1();
      if (__representantllinatge1!= null && __representantllinatge1.length() > 255) {
        __vr.rejectValue(REPRESENTANTLLINATGE1, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTLLINATGE1)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTLLINATGE2) == 0) {
      java.lang.String __representantllinatge2 = __target__.getRepresentantLlinatge2();
      if (__representantllinatge2!= null && __representantllinatge2.length() > 255) {
        __vr.rejectValue(REPRESENTANTLLINATGE2, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTLLINATGE2)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REPRESENTANTNOM) == 0) {
      java.lang.String __representantnom = __target__.getRepresentantNom();
      if (__representantnom!= null && __representantnom.length() > 255) {
        __vr.rejectValue(REPRESENTANTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(MOBILEID) == 0) {
      java.lang.String __mobileid = __target__.getMobileId();
      if (__mobileid!= null && __mobileid.length() > 255) {
        __vr.rejectValue(MOBILEID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOBILEID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (nif, representantnif)
      if (__vr.getFieldErrorCount(NIF) == 0 && __vr.getFieldErrorCount(REPRESENTANTNIF) == 0) {
        java.lang.String __nif = __target__.getNif();
        java.lang.String __representantnif = __target__.getRepresentantNif();
        Long __count_ = null;
        try { __count_ = __ciutadaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif), REPRESENTANTNIF.equal(__representantnif))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
            __vr.rejectValue(REPRESENTANTNIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__representantnif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTNIF)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (nif, representantnif)
      if (__vr.getFieldErrorCount(NIF) == 0 && __vr.getFieldErrorCount(REPRESENTANTNIF) == 0 && __vr.getFieldErrorCount(CIUTADAID) == 0) {
        java.lang.String __nif = __target__.getNif();
        java.lang.String __representantnif = __target__.getRepresentantNif();
        java.lang.Long __ciutadaid = __target__.getCiutadaID();
        Long __count_ = null;
        try { __count_ = __ciutadaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif), REPRESENTANTNIF.equal(__representantnif), CIUTADAID.notEqual(__ciutadaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
            __vr.rejectValue(REPRESENTANTNIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__representantnif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REPRESENTANTNIF)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}