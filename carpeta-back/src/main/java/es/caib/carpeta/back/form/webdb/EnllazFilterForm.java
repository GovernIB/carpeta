
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.EnllazFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EnllazFilterForm extends CarpetaBaseFilterForm implements EnllazFields {

  private java.lang.Long enllazIDDesde;

  public java.lang.Long getEnllazIDDesde() {
    return this.enllazIDDesde;
  }

  public void setEnllazIDDesde(java.lang.Long enllazIDDesde) {
    this.enllazIDDesde = enllazIDDesde;
  }


  private java.lang.Long enllazIDFins;

  public java.lang.Long getEnllazIDFins() {
    return this.enllazIDFins;
  }

  public void setEnllazIDFins(java.lang.Long enllazIDFins) {
    this.enllazIDFins = enllazIDFins;
  }


  private java.lang.Integer tipusDesde;

  public java.lang.Integer getTipusDesde() {
    return this.tipusDesde;
  }

  public void setTipusDesde(java.lang.Integer tipusDesde) {
    this.tipusDesde = tipusDesde;
  }


  private java.lang.Integer tipusFins;

  public java.lang.Integer getTipusFins() {
    return this.tipusFins;
  }

  public void setTipusFins(java.lang.Integer tipusFins) {
    this.tipusFins = tipusFins;
  }


  private java.lang.Long nomIDDesde;

  public java.lang.Long getNomIDDesde() {
    return this.nomIDDesde;
  }

  public void setNomIDDesde(java.lang.Long nomIDDesde) {
    this.nomIDDesde = nomIDDesde;
  }


  private java.lang.Long nomIDFins;

  public java.lang.Long getNomIDFins() {
    return this.nomIDFins;
  }

  public void setNomIDFins(java.lang.Long nomIDFins) {
    this.nomIDFins = nomIDFins;
  }


  private java.lang.Long descripcioIDDesde;

  public java.lang.Long getDescripcioIDDesde() {
    return this.descripcioIDDesde;
  }

  public void setDescripcioIDDesde(java.lang.Long descripcioIDDesde) {
    this.descripcioIDDesde = descripcioIDDesde;
  }


  private java.lang.Long descripcioIDFins;

  public java.lang.Long getDescripcioIDFins() {
    return this.descripcioIDFins;
  }

  public void setDescripcioIDFins(java.lang.Long descripcioIDFins) {
    this.descripcioIDFins = descripcioIDFins;
  }


  private java.lang.Long urlIDDesde;

  public java.lang.Long getUrlIDDesde() {
    return this.urlIDDesde;
  }

  public void setUrlIDDesde(java.lang.Long urlIDDesde) {
    this.urlIDDesde = urlIDDesde;
  }


  private java.lang.Long urlIDFins;

  public java.lang.Long getUrlIDFins() {
    return this.urlIDFins;
  }

  public void setUrlIDFins(java.lang.Long urlIDFins) {
    this.urlIDFins = urlIDFins;
  }


  private java.lang.Long entitatIDDesde;

  public java.lang.Long getEntitatIDDesde() {
    return this.entitatIDDesde;
  }

  public void setEntitatIDDesde(java.lang.Long entitatIDDesde) {
    this.entitatIDDesde = entitatIDDesde;
  }


  private java.lang.Long entitatIDFins;

  public java.lang.Long getEntitatIDFins() {
    return this.entitatIDFins;
  }

  public void setEntitatIDFins(java.lang.Long entitatIDFins) {
    this.entitatIDFins = entitatIDFins;
  }


  private java.lang.Long seccioIDDesde;

  public java.lang.Long getSeccioIDDesde() {
    return this.seccioIDDesde;
  }

  public void setSeccioIDDesde(java.lang.Long seccioIDDesde) {
    this.seccioIDDesde = seccioIDDesde;
  }


  private java.lang.Long seccioIDFins;

  public java.lang.Long getSeccioIDFins() {
    return this.seccioIDFins;
  }

  public void setSeccioIDFins(java.lang.Long seccioIDFins) {
    this.seccioIDFins = seccioIDFins;
  }


  public EnllazFilterForm() {
  }
  
  public EnllazFilterForm(EnllazFilterForm __toClone) {
    super(__toClone);
    this.enllazIDDesde = __toClone.enllazIDDesde;
    this.enllazIDFins = __toClone.enllazIDFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.descripcioIDDesde = __toClone.descripcioIDDesde;
    this.descripcioIDFins = __toClone.descripcioIDFins;
    this.urlIDDesde = __toClone.urlIDDesde;
    this.urlIDFins = __toClone.urlIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.seccioIDDesde = __toClone.seccioIDDesde;
    this.seccioIDFins = __toClone.seccioIDFins;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfTraduccioForDescripcioID = __toClone.mapOfTraduccioForDescripcioID;
    this.mapOfTraduccioForUrlID = __toClone.mapOfTraduccioForUrlID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfSeccioForSeccioID = __toClone.mapOfSeccioForSeccioID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfTraduccioForNomID;

  public Map<String, String> getMapOfTraduccioForNomID() {
    return this.mapOfTraduccioForNomID;
  }

  public void setMapOfTraduccioForNomID(Map<String, String> mapOfTraduccioForNomID) {
    this.mapOfTraduccioForNomID = mapOfTraduccioForNomID;
  }



  private Map<String, String> mapOfTraduccioForDescripcioID;

  public Map<String, String> getMapOfTraduccioForDescripcioID() {
    return this.mapOfTraduccioForDescripcioID;
  }

  public void setMapOfTraduccioForDescripcioID(Map<String, String> mapOfTraduccioForDescripcioID) {
    this.mapOfTraduccioForDescripcioID = mapOfTraduccioForDescripcioID;
  }



  private Map<String, String> mapOfTraduccioForUrlID;

  public Map<String, String> getMapOfTraduccioForUrlID() {
    return this.mapOfTraduccioForUrlID;
  }

  public void setMapOfTraduccioForUrlID(Map<String, String> mapOfTraduccioForUrlID) {
    this.mapOfTraduccioForUrlID = mapOfTraduccioForUrlID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfSeccioForSeccioID;

  public Map<String, String> getMapOfSeccioForSeccioID() {
    return this.mapOfSeccioForSeccioID;
  }

  public void setMapOfSeccioForSeccioID(Map<String, String> mapOfSeccioForSeccioID) {
    this.mapOfSeccioForSeccioID = mapOfSeccioForSeccioID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
