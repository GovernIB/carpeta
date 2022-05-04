package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PreguntesFrequentsForm extends CarpetaBaseForm {
  
  private PreguntesFrequentsJPA preguntesFrequents;
  
  
  private CommonsMultipartFile fitxer1ID;
  private boolean fitxer1IDDelete;
  
  
  private CommonsMultipartFile fitxer2ID;
  private boolean fitxer2IDDelete;
  
  
  private CommonsMultipartFile fitxer3ID;
  private boolean fitxer3IDDelete;
  
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
  
  public CommonsMultipartFile getFitxer1ID() {
    return fitxer1ID;
  }
  
   public void setFitxer1ID(CommonsMultipartFile fitxer1ID) {
    this.fitxer1ID = fitxer1ID;
  }
  public boolean isFitxer1IDDelete() {
    return fitxer1IDDelete;
  }
  
  public void setFitxer1IDDelete(boolean fitxer1IDDelete) {
    this.fitxer1IDDelete = fitxer1IDDelete;
   }
  public CommonsMultipartFile getFitxer2ID() {
    return fitxer2ID;
  }
  
   public void setFitxer2ID(CommonsMultipartFile fitxer2ID) {
    this.fitxer2ID = fitxer2ID;
  }
  public boolean isFitxer2IDDelete() {
    return fitxer2IDDelete;
  }
  
  public void setFitxer2IDDelete(boolean fitxer2IDDelete) {
    this.fitxer2IDDelete = fitxer2IDDelete;
   }
  public CommonsMultipartFile getFitxer3ID() {
    return fitxer3ID;
  }
  
   public void setFitxer3ID(CommonsMultipartFile fitxer3ID) {
    this.fitxer3ID = fitxer3ID;
  }
  public boolean isFitxer3IDDelete() {
    return fitxer3IDDelete;
  }
  
  public void setFitxer3IDDelete(boolean fitxer3IDDelete) {
    this.fitxer3IDDelete = fitxer3IDDelete;
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
