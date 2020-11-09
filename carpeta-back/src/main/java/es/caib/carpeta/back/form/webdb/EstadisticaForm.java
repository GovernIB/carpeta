package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.EstadisticaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EstadisticaForm extends CarpetaBaseForm {
  
  private EstadisticaJPA estadistica;
  
  public EstadisticaForm() {
  }
  
  public EstadisticaForm(EstadisticaForm __toClone) {
    super(__toClone);
      this.estadistica = __toClone.estadistica;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForPluginID = __toClone.listOfValuesForPluginID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public EstadisticaForm(EstadisticaJPA estadistica, boolean nou) {
    super(nou);
    this.estadistica = estadistica;
  }
  
  public EstadisticaJPA getEstadistica() {
    return estadistica;
  }
  public void setEstadistica(EstadisticaJPA estadistica) {
    this.estadistica = estadistica;
  }
  
  
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForPluginID;

  public List<StringKeyValue> getListOfValuesForPluginID() {
    return this.listOfValuesForPluginID;
  }

  public void setListOfValuesForPluginID(List<StringKeyValue> listOfValuesForPluginID) {
    this.listOfValuesForPluginID = listOfValuesForPluginID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
