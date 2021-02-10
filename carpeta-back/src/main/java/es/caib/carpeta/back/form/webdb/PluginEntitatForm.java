package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.PluginEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PluginEntitatForm extends CarpetaBaseForm {
  
  private PluginEntitatJPA pluginEntitat;
  
  public PluginEntitatForm() {
  }
  
  public PluginEntitatForm(PluginEntitatForm __toClone) {
    super(__toClone);
      this.pluginEntitat = __toClone.pluginEntitat;
    this.listOfPluginForPluginID = __toClone.listOfPluginForPluginID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfSeccioForSeccioID = __toClone.listOfSeccioForSeccioID;
  }
  
  public PluginEntitatForm(PluginEntitatJPA pluginEntitat, boolean nou) {
    super(nou);
    this.pluginEntitat = pluginEntitat;
  }
  
  public PluginEntitatJPA getPluginEntitat() {
    return pluginEntitat;
  }
  public void setPluginEntitat(PluginEntitatJPA pluginEntitat) {
    this.pluginEntitat = pluginEntitat;
  }
  
  
  private List<StringKeyValue> listOfPluginForPluginID;

  public List<StringKeyValue> getListOfPluginForPluginID() {
    return this.listOfPluginForPluginID;
  }

  public void setListOfPluginForPluginID(List<StringKeyValue> listOfPluginForPluginID) {
    this.listOfPluginForPluginID = listOfPluginForPluginID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfSeccioForSeccioID;

  public List<StringKeyValue> getListOfSeccioForSeccioID() {
    return this.listOfSeccioForSeccioID;
  }

  public void setListOfSeccioForSeccioID(List<StringKeyValue> listOfSeccioForSeccioID) {
    this.listOfSeccioForSeccioID = listOfSeccioForSeccioID;
  }



  
} // Final de Classe 
