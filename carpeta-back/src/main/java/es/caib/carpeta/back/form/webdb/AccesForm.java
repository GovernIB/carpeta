package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.AccesJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AccesForm extends CarpetaBaseForm {
  
  private AccesJPA acces;
  
  public AccesForm() {
  }
  
  public AccesForm(AccesForm __toClone) {
    super(__toClone);
      this.acces = __toClone.acces;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForPluginID = __toClone.listOfValuesForPluginID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public AccesForm(AccesJPA acces, boolean nou) {
    super(nou);
    this.acces = acces;
  }
  
  public AccesJPA getAcces() {
    return acces;
  }
  public void setAcces(AccesJPA acces) {
    this.acces = acces;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForPluginID;

  public List<StringKeyValue> getListOfValuesForPluginID() {
    return this.listOfValuesForPluginID;
  }

  public void setListOfValuesForPluginID(List<StringKeyValue> listOfValuesForPluginID) {
    this.listOfValuesForPluginID = listOfValuesForPluginID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
