package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.EstadisticaJPA;
import org.fundaciobit.genapp.common.StringKeyValue;

import java.util.List;

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
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfAccesForAccesID = __toClone.listOfAccesForAccesID;
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
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfAccesForAccesID;

  public List<StringKeyValue> getListOfAccesForAccesID() {
    return this.listOfAccesForAccesID;
  }

  public void setListOfAccesForAccesID(List<StringKeyValue> listOfAccesForAccesID) {
    this.listOfAccesForAccesID = listOfAccesForAccesID;
  }



  
} // Final de Classe 
