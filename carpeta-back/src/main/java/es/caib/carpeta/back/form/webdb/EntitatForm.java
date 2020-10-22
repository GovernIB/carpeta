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
  
  
  private CommonsMultipartFile logoCapBackID;
  private boolean logoCapBackIDDelete;
  
  
  private CommonsMultipartFile logoPeuBackID;
  private boolean logoPeuBackIDDelete;
  
  
  private CommonsMultipartFile logoLateralFrontID;
  private boolean logoLateralFrontIDDelete;
  
  
  private CommonsMultipartFile iconID;
  private boolean iconIDDelete;
  
  
  private CommonsMultipartFile fitxerCssID;
  private boolean fitxerCssIDDelete;
  
  public EntitatForm() {
  }
  
  public EntitatForm(EntitatForm __toClone) {
    super(__toClone);
      this.entitat = __toClone.entitat;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfPluginForPluginLoginID = __toClone.listOfPluginForPluginLoginID;
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
  
  public CommonsMultipartFile getLogoCapBackID() {
    return logoCapBackID;
  }
  
   public void setLogoCapBackID(CommonsMultipartFile logoCapBackID) {
    this.logoCapBackID = logoCapBackID;
  }
  public boolean isLogoCapBackIDDelete() {
    return logoCapBackIDDelete;
  }
  
  public void setLogoCapBackIDDelete(boolean logoCapBackIDDelete) {
    this.logoCapBackIDDelete = logoCapBackIDDelete;
   }
  public CommonsMultipartFile getLogoPeuBackID() {
    return logoPeuBackID;
  }
  
   public void setLogoPeuBackID(CommonsMultipartFile logoPeuBackID) {
    this.logoPeuBackID = logoPeuBackID;
  }
  public boolean isLogoPeuBackIDDelete() {
    return logoPeuBackIDDelete;
  }
  
  public void setLogoPeuBackIDDelete(boolean logoPeuBackIDDelete) {
    this.logoPeuBackIDDelete = logoPeuBackIDDelete;
   }
  public CommonsMultipartFile getLogoLateralFrontID() {
    return logoLateralFrontID;
  }
  
   public void setLogoLateralFrontID(CommonsMultipartFile logoLateralFrontID) {
    this.logoLateralFrontID = logoLateralFrontID;
  }
  public boolean isLogoLateralFrontIDDelete() {
    return logoLateralFrontIDDelete;
  }
  
  public void setLogoLateralFrontIDDelete(boolean logoLateralFrontIDDelete) {
    this.logoLateralFrontIDDelete = logoLateralFrontIDDelete;
   }
  public CommonsMultipartFile getIconID() {
    return iconID;
  }
  
   public void setIconID(CommonsMultipartFile iconID) {
    this.iconID = iconID;
  }
  public boolean isIconIDDelete() {
    return iconIDDelete;
  }
  
  public void setIconIDDelete(boolean iconIDDelete) {
    this.iconIDDelete = iconIDDelete;
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



  private List<StringKeyValue> listOfPluginForPluginLoginID;

  public List<StringKeyValue> getListOfPluginForPluginLoginID() {
    return this.listOfPluginForPluginLoginID;
  }

  public void setListOfPluginForPluginLoginID(List<StringKeyValue> listOfPluginForPluginLoginID) {
    this.listOfPluginForPluginLoginID = listOfPluginForPluginLoginID;
  }



  
} // Final de Classe 
