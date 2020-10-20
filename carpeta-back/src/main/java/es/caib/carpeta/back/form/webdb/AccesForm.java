package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.AccesJPA;
import org.fundaciobit.genapp.common.StringKeyValue;

import java.util.List;

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



  
} // Final de Classe 
