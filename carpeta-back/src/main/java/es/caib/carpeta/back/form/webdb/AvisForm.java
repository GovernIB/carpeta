package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.AvisJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AvisForm extends CarpetaBaseForm {
  
  private AvisJPA avis;
  
  public AvisForm() {
  }
  
  public AvisForm(AvisForm __toClone) {
    super(__toClone);
      this.avis = __toClone.avis;
    this.listOfTraduccioForDescripcioID = __toClone.listOfTraduccioForDescripcioID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfValuesForGravetat = __toClone.listOfValuesForGravetat;
    this.listOfPluginForPluginFrontID = __toClone.listOfPluginForPluginFrontID;
  }
  
  public AvisForm(AvisJPA avis, boolean nou) {
    super(nou);
    this.avis = avis;
  }
  
  public AvisJPA getAvis() {
    return avis;
  }
  public void setAvis(AvisJPA avis) {
    this.avis = avis;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForDescripcioID;

  public List<StringKeyValue> getListOfTraduccioForDescripcioID() {
    return this.listOfTraduccioForDescripcioID;
  }

  public void setListOfTraduccioForDescripcioID(List<StringKeyValue> listOfTraduccioForDescripcioID) {
    this.listOfTraduccioForDescripcioID = listOfTraduccioForDescripcioID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfValuesForGravetat;

  public List<StringKeyValue> getListOfValuesForGravetat() {
    return this.listOfValuesForGravetat;
  }

  public void setListOfValuesForGravetat(List<StringKeyValue> listOfValuesForGravetat) {
    this.listOfValuesForGravetat = listOfValuesForGravetat;
  }



  private List<StringKeyValue> listOfPluginForPluginFrontID;

  public List<StringKeyValue> getListOfPluginForPluginFrontID() {
    return this.listOfPluginForPluginFrontID;
  }

  public void setListOfPluginForPluginFrontID(List<StringKeyValue> listOfPluginForPluginFrontID) {
    this.listOfPluginForPluginFrontID = listOfPluginForPluginFrontID;
  }



  
} // Final de Classe 
