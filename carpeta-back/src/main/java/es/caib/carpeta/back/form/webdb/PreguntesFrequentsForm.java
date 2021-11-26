package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PreguntesFrequentsForm extends CarpetaBaseForm {
  
  private PreguntesFrequentsJPA preguntesFrequents;
  
  public PreguntesFrequentsForm() {
  }
  
  public PreguntesFrequentsForm(PreguntesFrequentsForm __toClone) {
    super(__toClone);
      this.preguntesFrequents = __toClone.preguntesFrequents;
    this.listOfTraduccioForEnunciatID = __toClone.listOfTraduccioForEnunciatID;
    this.listOfTraduccioForRespostaID = __toClone.listOfTraduccioForRespostaID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public PreguntesFrequentsForm(PreguntesFrequentsJPA preguntesFrequents, boolean nou) {
    super(nou);
    this.preguntesFrequents = preguntesFrequents;
  }
  
  public PreguntesFrequentsJPA getPreguntesFrequents() {
    return preguntesFrequents;
  }
  public void setPreguntesFrequents(PreguntesFrequentsJPA preguntesFrequents) {
    this.preguntesFrequents = preguntesFrequents;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  private List<StringKeyValue> listOfTraduccioForEnunciatID;

  public List<StringKeyValue> getListOfTraduccioForEnunciatID() {
    return this.listOfTraduccioForEnunciatID;
  }

  public void setListOfTraduccioForEnunciatID(List<StringKeyValue> listOfTraduccioForEnunciatID) {
    this.listOfTraduccioForEnunciatID = listOfTraduccioForEnunciatID;
  }



  private List<StringKeyValue> listOfTraduccioForRespostaID;

  public List<StringKeyValue> getListOfTraduccioForRespostaID() {
    return this.listOfTraduccioForRespostaID;
  }

  public void setListOfTraduccioForRespostaID(List<StringKeyValue> listOfTraduccioForRespostaID) {
    this.listOfTraduccioForRespostaID = listOfTraduccioForRespostaID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
