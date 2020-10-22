package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.AuditoriaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class AuditoriaForm extends CarpetaBaseForm {
  
  private AuditoriaJPA auditoria;
  
  public AuditoriaForm() {
  }
  
  public AuditoriaForm(AuditoriaForm __toClone) {
    super(__toClone);
      this.auditoria = __toClone.auditoria;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfUsuariForUsuariID = __toClone.listOfUsuariForUsuariID;
  }
  
  public AuditoriaForm(AuditoriaJPA auditoria, boolean nou) {
    super(nou);
    this.auditoria = auditoria;
  }
  
  public AuditoriaJPA getAuditoria() {
    return auditoria;
  }
  public void setAuditoria(AuditoriaJPA auditoria) {
    this.auditoria = auditoria;
  }
  
  
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfUsuariForUsuariID;

  public List<StringKeyValue> getListOfUsuariForUsuariID() {
    return this.listOfUsuariForUsuariID;
  }

  public void setListOfUsuariForUsuariID(List<StringKeyValue> listOfUsuariForUsuariID) {
    this.listOfUsuariForUsuariID = listOfUsuariForUsuariID;
  }



  
} // Final de Classe 
