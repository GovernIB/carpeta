package es.caib.carpeta.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.carpeta.back.form.CarpetaBaseForm;
import es.caib.carpeta.persistence.SeccioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class SeccioForm extends CarpetaBaseForm {
  
  private SeccioJPA seccio;
  
  
  private CommonsMultipartFile iconaID;
  private boolean iconaIDDelete;
  
  public SeccioForm() {
  }
  
  public SeccioForm(SeccioForm __toClone) {
    super(__toClone);
      this.seccio = __toClone.seccio;
    this.listOfTraduccioForNomID = __toClone.listOfTraduccioForNomID;
    this.listOfTraduccioForDescripcioID = __toClone.listOfTraduccioForDescripcioID;
    this.listOfValuesForSeccioPareID = __toClone.listOfValuesForSeccioPareID;
  }
  
  public SeccioForm(SeccioJPA seccio, boolean nou) {
    super(nou);
    this.seccio = seccio;
  }
  
  public SeccioJPA getSeccio() {
    return seccio;
  }
  public void setSeccio(SeccioJPA seccio) {
    this.seccio = seccio;
  }
  
  java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio = null;

  public java.util.List<es.caib.carpeta.model.entity.Idioma> getIdiomesTraduccio() {
    return idiomesTraduccio;
  }

  public void setIdiomesTraduccio(java.util.List<es.caib.carpeta.model.entity.Idioma> idiomesTraduccio) {
    this.idiomesTraduccio = idiomesTraduccio;
  }
  
  public CommonsMultipartFile getIconaID() {
    return iconaID;
  }
  
   public void setIconaID(CommonsMultipartFile iconaID) {
    this.iconaID = iconaID;
  }
  public boolean isIconaIDDelete() {
    return iconaIDDelete;
  }
  
  public void setIconaIDDelete(boolean iconaIDDelete) {
    this.iconaIDDelete = iconaIDDelete;
   }
  private List<StringKeyValue> listOfTraduccioForNomID;

  public List<StringKeyValue> getListOfTraduccioForNomID() {
    return this.listOfTraduccioForNomID;
  }

  public void setListOfTraduccioForNomID(List<StringKeyValue> listOfTraduccioForNomID) {
    this.listOfTraduccioForNomID = listOfTraduccioForNomID;
  }



  private List<StringKeyValue> listOfTraduccioForDescripcioID;

  public List<StringKeyValue> getListOfTraduccioForDescripcioID() {
    return this.listOfTraduccioForDescripcioID;
  }

  public void setListOfTraduccioForDescripcioID(List<StringKeyValue> listOfTraduccioForDescripcioID) {
    this.listOfTraduccioForDescripcioID = listOfTraduccioForDescripcioID;
  }



  private List<StringKeyValue> listOfValuesForSeccioPareID;

  public List<StringKeyValue> getListOfValuesForSeccioPareID() {
    return this.listOfValuesForSeccioPareID;
  }

  public void setListOfValuesForSeccioPareID(List<StringKeyValue> listOfValuesForSeccioPareID) {
    this.listOfValuesForSeccioPareID = listOfValuesForSeccioPareID;
  }



  
} // Final de Classe 
