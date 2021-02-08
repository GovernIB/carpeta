package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.EnllazJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class EnllazForm extends CarpetaBaseForm {
  
  private EnllazJPA enllaz;
  
  
  private CommonsMultipartFile logoID;
  private boolean logoIDDelete;
  
  public EnllazForm() {
  }
  
  public EnllazForm(EnllazForm __toClone) {
    super(__toClone);
      this.enllaz = __toClone.enllaz;
    this.listOfValuesForTipus = __toClone.listOfValuesForTipus;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfTraduccioForUrlID = __toClone.listOfTraduccioForUrlID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfSeccioForSeccioID = __toClone.listOfSeccioForSeccioID;
  }
  
  public EnllazForm(EnllazJPA enllaz, boolean nou) {
    super(nou);
    this.enllaz = enllaz;
  }
  
  public EnllazJPA getEnllaz() {
    return enllaz;
  }
  public void setEnllaz(EnllazJPA enllaz) {
    this.enllaz = enllaz;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  public CommonsMultipartFile getLogoID() {
    return logoID;
  }
  
   public void setLogoID(CommonsMultipartFile logoID) {
    this.logoID = logoID;
  }
  public boolean isLogoIDDelete() {
    return logoIDDelete;
  }
  
  public void setLogoIDDelete(boolean logoIDDelete) {
    this.logoIDDelete = logoIDDelete;
   }
  private List<StringKeyValue> listOfValuesForTipus;

  public List<StringKeyValue> getListOfValuesForTipus() {
    return this.listOfValuesForTipus;
  }

  public void setListOfValuesForTipus(List<StringKeyValue> listOfValuesForTipus) {
    this.listOfValuesForTipus = listOfValuesForTipus;
  }



  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  private List<StringKeyValue> listOfTraduccioForUrlID;

  public List<StringKeyValue> getListOfTraduccioForUrlID() {
    return this.listOfTraduccioForUrlID;
  }

  public void setListOfTraduccioForUrlID(List<StringKeyValue> listOfTraduccioForUrlID) {
    this.listOfTraduccioForUrlID = listOfTraduccioForUrlID;
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
