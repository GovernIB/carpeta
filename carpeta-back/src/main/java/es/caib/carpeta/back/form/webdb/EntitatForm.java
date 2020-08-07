package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.jpa.EntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EntitatForm extends CarpetaBaseForm {
  
  private EntitatJPA entitat;
  
  
  private CommonsMultipartFile logoMenuID;
  private boolean logoMenuIDDelete;
  
  
  private CommonsMultipartFile logoPeuID;
  private boolean logoPeuIDDelete;
  
  
  private CommonsMultipartFile fitxerCssID;
  private boolean fitxerCssIDDelete;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
  }
  
  public EntitatForm(EntitatJPA entitat, boolean nou) {
    super(nou);
    this.entitat = entitat;
  }
  
  public EntitatJPA getEntitat() {
    return entitat;
  }
  public void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  public CommonsMultipartFile getLogoMenuID() {
    return logoMenuID;
  }
  
   public void setLogoMenuID(CommonsMultipartFile logoMenuID) {
    this.logoMenuID = logoMenuID;
  }
  public boolean isLogoMenuIDDelete() {
    return logoMenuIDDelete;
  }
  
  public void setLogoMenuIDDelete(boolean logoMenuIDDelete) {
    this.logoMenuIDDelete = logoMenuIDDelete;
   }
  public CommonsMultipartFile getLogoPeuID() {
    return logoPeuID;
  }
  
   public void setLogoPeuID(CommonsMultipartFile logoPeuID) {
    this.logoPeuID = logoPeuID;
  }
  public boolean isLogoPeuIDDelete() {
    return logoPeuIDDelete;
  }
  
  public void setLogoPeuIDDelete(boolean logoPeuIDDelete) {
    this.logoPeuIDDelete = logoPeuIDDelete;
   }
  public CommonsMultipartFile getFitxerCssID() {
    return fitxerCssID;
  }
  
   public void setFitxerCssID(CommonsMultipartFile fitxerCssID) {
    this.fitxerCssID = fitxerCssID;
  }
  public boolean isFitxerCssIDDelete() {
    return fitxerCssIDDelete;
  }
  
  public void setFitxerCssIDDelete(boolean fitxerCssIDDelete) {
    this.fitxerCssIDDelete = fitxerCssIDDelete;
   }
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  
} // Final de Classe 
