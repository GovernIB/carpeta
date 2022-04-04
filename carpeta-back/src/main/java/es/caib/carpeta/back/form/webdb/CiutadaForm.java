package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.CiutadaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CiutadaForm extends CarpetaBaseForm {
  
  private CiutadaJPA ciutada;
  
  public CiutadaForm() {
  }
  
  public CiutadaForm(CiutadaForm __toClone) {
    super(__toClone);
      this.ciutada = __toClone.ciutada;
  }
  
  public CiutadaForm(CiutadaJPA ciutada, boolean nou) {
    super(nou);
    this.ciutada = ciutada;
  }
  
  public CiutadaJPA getCiutada() {
    return ciutada;
  }
  public void setCiutada(CiutadaJPA ciutada) {
    this.ciutada = ciutada;
  }
  
  
  
} // Final de Classe 
