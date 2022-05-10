package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.NotificacioAppJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class NotificacioAppForm extends CarpetaBaseForm {
  
  private NotificacioAppJPA notificacioApp;
  
  public NotificacioAppForm() {
  }
  
  public NotificacioAppForm(NotificacioAppForm __toClone) {
    super(__toClone);
      this.notificacioApp = __toClone.notificacioApp;
    this.listOfTraduccioForTitolID = __toClone.listOfTraduccioForTitolID;
    this.listOfTraduccioForMissatgeID = __toClone.listOfTraduccioForMissatgeID;
    this.listOfPluginForFrontPluginID = __toClone.listOfPluginForFrontPluginID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public NotificacioAppForm(NotificacioAppJPA notificacioApp, boolean nou) {
    super(nou);
    this.notificacioApp = notificacioApp;
  }
  
  public NotificacioAppJPA getNotificacioApp() {
    return notificacioApp;
  }
  public void setNotificacioApp(NotificacioAppJPA notificacioApp) {
    this.notificacioApp = notificacioApp;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForTitolID;

  public List<StringKeyValue> getListOfTraduccioForTitolID() {
    return this.listOfTraduccioForTitolID;
  }

  public void setListOfTraduccioForTitolID(List<StringKeyValue> listOfTraduccioForTitolID) {
    this.listOfTraduccioForTitolID = listOfTraduccioForTitolID;
  }



  private List<StringKeyValue> listOfTraduccioForMissatgeID;

  public List<StringKeyValue> getListOfTraduccioForMissatgeID() {
    return this.listOfTraduccioForMissatgeID;
  }

  public void setListOfTraduccioForMissatgeID(List<StringKeyValue> listOfTraduccioForMissatgeID) {
    this.listOfTraduccioForMissatgeID = listOfTraduccioForMissatgeID;
  }



  private List<StringKeyValue> listOfPluginForFrontPluginID;

  public List<StringKeyValue> getListOfPluginForFrontPluginID() {
    return this.listOfPluginForFrontPluginID;
  }

  public void setListOfPluginForFrontPluginID(List<StringKeyValue> listOfPluginForFrontPluginID) {
    this.listOfPluginForFrontPluginID = listOfPluginForFrontPluginID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
