package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.AuditoriaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AuditoriaForm extends CarpetaBaseForm {
  
  private AuditoriaJPA auditoria;
  
  public AuditoriaForm() {
  }
  
  public AuditoriaForm(AuditoriaForm __toClone) {
    super(__toClone);
      this.auditoria = __toClone.auditoria;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForUsername = __toClone.listOfValuesForUsername;
    this.listOfValuesForEntitatID = __toClone.listOfValuesForEntitatID;
    this.listOfValuesForPluginID = __toClone.listOfValuesForPluginID;
  }
  
  public AuditoriaForm(AuditoriaJPA auditoria, boolean nou) {
    super(nou);
    this.auditoria = auditoria;
  }
  
  public AuditoriaJPA getAuditoria() {
    return auditoria;
  }
  public void setAuditoria(AuditoriaJPA auditoria) {
    this.auditoria = auditoria;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForUsername;

  public List<StringKeyValue> getListOfValuesForUsername() {
    return this.listOfValuesForUsername;
  }

  public void setListOfValuesForUsername(List<StringKeyValue> listOfValuesForUsername) {
    this.listOfValuesForUsername = listOfValuesForUsername;
  }



  private List<StringKeyValue> listOfValuesForEntitatID;

  public List<StringKeyValue> getListOfValuesForEntitatID() {
    return this.listOfValuesForEntitatID;
  }

  public void setListOfValuesForEntitatID(List<StringKeyValue> listOfValuesForEntitatID) {
    this.listOfValuesForEntitatID = listOfValuesForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForPluginID;

  public List<StringKeyValue> getListOfValuesForPluginID() {
    return this.listOfValuesForPluginID;
  }

  public void setListOfValuesForPluginID(List<StringKeyValue> listOfValuesForPluginID) {
    this.listOfValuesForPluginID = listOfValuesForPluginID;
  }



  
} // Final de Classe 
