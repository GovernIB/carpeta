package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.LogCarpetaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class LogCarpetaForm extends CarpetaBaseForm {
  
  private LogCarpetaJPA logCarpeta;
  
  public LogCarpetaForm() {
  }
  
  public LogCarpetaForm(LogCarpetaForm __toClone) {
    super(__toClone);
      this.logCarpeta = __toClone.logCarpeta;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfPluginForPluginID = __toClone.listOfPluginForPluginID;
  }
  
  public LogCarpetaForm(LogCarpetaJPA logCarpeta, boolean nou) {
    super(nou);
    this.logCarpeta = logCarpeta;
  }
  
  public LogCarpetaJPA getLogCarpeta() {
    return logCarpeta;
  }
  public void setLogCarpeta(LogCarpetaJPA logCarpeta) {
    this.logCarpeta = logCarpeta;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfPluginForPluginID;

  public List<StringKeyValue> getListOfPluginForPluginID() {
    return this.listOfPluginForPluginID;
  }

  public void setListOfPluginForPluginID(List<StringKeyValue> listOfPluginForPluginID) {
    this.listOfPluginForPluginID = listOfPluginForPluginID;
  }



  
} // Final de Classe 
