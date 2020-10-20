package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.UsuariEntitatJPA;
import org.fundaciobit.genapp.common.StringKeyValue;

import java.util.List;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatForm extends CarpetaBaseForm {
  
  private UsuariEntitatJPA usuariEntitat;
  
  public UsuariEntitatForm() {
  }
  
  public UsuariEntitatForm(UsuariEntitatForm __toClone) {
    super(__toClone);
      this.usuariEntitat = __toClone.usuariEntitat;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public UsuariEntitatForm(UsuariEntitatJPA usuariEntitat, boolean nou) {
    super(nou);
    this.usuariEntitat = usuariEntitat;
  }
  
  public UsuariEntitatJPA getUsuariEntitat() {
    return usuariEntitat;
  }
  public void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }
  
  
  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
