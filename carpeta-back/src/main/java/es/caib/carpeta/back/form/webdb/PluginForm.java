package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.PluginJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginForm extends CarpetaBaseForm {
  
  private PluginJPA plugin;
  
  public PluginForm() {
  }
  
  public PluginForm(PluginForm __toClone) {
    super(__toClone);
      this.plugin = __toClone.plugin;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfTraduccioForDescripcioID = __toClone.listOfTraduccioForDescripcioID;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
  }
  
  public PluginForm(PluginJPA plugin, boolean nou) {
    super(nou);
    this.plugin = plugin;
  }
  
  public PluginJPA getPlugin() {
    return plugin;
  }
  public void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  private List<StringKeyValue> listOfTraduccioForDescripcioID;

  public List<StringKeyValue> getListOfTraduccioForDescripcioID() {
    return this.listOfTraduccioForDescripcioID;
  }

  public void setListOfTraduccioForDescripcioID(List<StringKeyValue> listOfTraduccioForDescripcioID) {
    this.listOfTraduccioForDescripcioID = listOfTraduccioForDescripcioID;
  }



  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  
} // Final de Classe 
