
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.SeccioFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SeccioFilterForm extends CarpetaBaseFilterForm implements SeccioFields {

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


  private java.lang.String context;

  public java.lang.String getContext() {
    return this.context;
  }

  public void setContext(java.lang.String context) {
    this.context = context;
  }


  private java.lang.Long seccioPareIDDesde;

  public java.lang.Long getSeccioPareIDDesde() {
    return this.seccioPareIDDesde;
  }

  public void setSeccioPareIDDesde(java.lang.Long seccioPareIDDesde) {
    this.seccioPareIDDesde = seccioPareIDDesde;
  }


  private java.lang.Long seccioPareIDFins;

  public java.lang.Long getSeccioPareIDFins() {
    return this.seccioPareIDFins;
  }

  public void setSeccioPareIDFins(java.lang.Long seccioPareIDFins) {
    this.seccioPareIDFins = seccioPareIDFins;
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


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
  }


  public SeccioFilterForm() {
  }
  
  public SeccioFilterForm(SeccioFilterForm __toClone) {
    super(__toClone);
    this.seccioIDDesde = __toClone.seccioIDDesde;
    this.seccioIDFins = __toClone.seccioIDFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.descripcioIDDesde = __toClone.descripcioIDDesde;
    this.descripcioIDFins = __toClone.descripcioIDFins;
    this.context = __toClone.context;
    this.seccioPareIDDesde = __toClone.seccioPareIDDesde;
    this.seccioPareIDFins = __toClone.seccioPareIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfTraduccioForDescripcioID = __toClone.mapOfTraduccioForDescripcioID;
    this.mapOfValuesForSeccioPareID = __toClone.mapOfValuesForSeccioPareID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
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



  private Map<String, String> mapOfValuesForSeccioPareID;

  public Map<String, String> getMapOfValuesForSeccioPareID() {
    return this.mapOfValuesForSeccioPareID;
  }

  public void setMapOfValuesForSeccioPareID(Map<String, String> mapOfValuesForSeccioPareID) {
    this.mapOfValuesForSeccioPareID = mapOfValuesForSeccioPareID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
