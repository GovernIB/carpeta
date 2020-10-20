
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseFilterForm;
import es.caib.carpeta.model.fields.AvisFields;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AvisFilterForm extends CarpetaBaseFilterForm implements AvisFields {

  private java.lang.Long avisIDDesde;

  public java.lang.Long getAvisIDDesde() {
    return this.avisIDDesde;
  }

  public void setAvisIDDesde(java.lang.Long avisIDDesde) {
    this.avisIDDesde = avisIDDesde;
  }


  private java.lang.Long avisIDFins;

  public java.lang.Long getAvisIDFins() {
    return this.avisIDFins;
  }

  public void setAvisIDFins(java.lang.Long avisIDFins) {
    this.avisIDFins = avisIDFins;
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


  private java.sql.Timestamp dataIniciDesde;

  public java.sql.Timestamp getDataIniciDesde() {
    return this.dataIniciDesde;
  }

  public void setDataIniciDesde(java.sql.Timestamp dataIniciDesde) {
    this.dataIniciDesde = dataIniciDesde;
  }


  private java.sql.Timestamp dataIniciFins;

  public java.sql.Timestamp getDataIniciFins() {
    return this.dataIniciFins;
  }

  public void setDataIniciFins(java.sql.Timestamp dataIniciFins) {
    this.dataIniciFins = dataIniciFins;
  }


  private java.sql.Timestamp dataFiDesde;

  public java.sql.Timestamp getDataFiDesde() {
    return this.dataFiDesde;
  }

  public void setDataFiDesde(java.sql.Timestamp dataFiDesde) {
    this.dataFiDesde = dataFiDesde;
  }


  private java.sql.Timestamp dataFiFins;

  public java.sql.Timestamp getDataFiFins() {
    return this.dataFiFins;
  }

  public void setDataFiFins(java.sql.Timestamp dataFiFins) {
    this.dataFiFins = dataFiFins;
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


  private java.lang.Integer gravetatDesde;

  public java.lang.Integer getGravetatDesde() {
    return this.gravetatDesde;
  }

  public void setGravetatDesde(java.lang.Integer gravetatDesde) {
    this.gravetatDesde = gravetatDesde;
  }


  private java.lang.Integer gravetatFins;

  public java.lang.Integer getGravetatFins() {
    return this.gravetatFins;
  }

  public void setGravetatFins(java.lang.Integer gravetatFins) {
    this.gravetatFins = gravetatFins;
  }


  private java.lang.Long pluginFrontIDDesde;

  public java.lang.Long getPluginFrontIDDesde() {
    return this.pluginFrontIDDesde;
  }

  public void setPluginFrontIDDesde(java.lang.Long pluginFrontIDDesde) {
    this.pluginFrontIDDesde = pluginFrontIDDesde;
  }


  private java.lang.Long pluginFrontIDFins;

  public java.lang.Long getPluginFrontIDFins() {
    return this.pluginFrontIDFins;
  }

  public void setPluginFrontIDFins(java.lang.Long pluginFrontIDFins) {
    this.pluginFrontIDFins = pluginFrontIDFins;
  }


  public AvisFilterForm() {
  }
  
  public AvisFilterForm(AvisFilterForm __toClone) {
    super(__toClone);
    this.avisIDDesde = __toClone.avisIDDesde;
    this.avisIDFins = __toClone.avisIDFins;
    this.descripcioIDDesde = __toClone.descripcioIDDesde;
    this.descripcioIDFins = __toClone.descripcioIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.dataFiDesde = __toClone.dataFiDesde;
    this.dataFiFins = __toClone.dataFiFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.gravetatDesde = __toClone.gravetatDesde;
    this.gravetatFins = __toClone.gravetatFins;
    this.pluginFrontIDDesde = __toClone.pluginFrontIDDesde;
    this.pluginFrontIDFins = __toClone.pluginFrontIDFins;
    this.mapOfTraduccioForDescripcioID = __toClone.mapOfTraduccioForDescripcioID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForGravetat = __toClone.mapOfValuesForGravetat;
    this.mapOfPluginForPluginFrontID = __toClone.mapOfPluginForPluginFrontID;
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
  private Map<String, String> mapOfTraduccioForDescripcioID;

  public Map<String, String> getMapOfTraduccioForDescripcioID() {
    return this.mapOfTraduccioForDescripcioID;
  }

  public void setMapOfTraduccioForDescripcioID(Map<String, String> mapOfTraduccioForDescripcioID) {
    this.mapOfTraduccioForDescripcioID = mapOfTraduccioForDescripcioID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfValuesForGravetat;

  public Map<String, String> getMapOfValuesForGravetat() {
    return this.mapOfValuesForGravetat;
  }

  public void setMapOfValuesForGravetat(Map<String, String> mapOfValuesForGravetat) {
    this.mapOfValuesForGravetat = mapOfValuesForGravetat;
  }



  private Map<String, String> mapOfPluginForPluginFrontID;

  public Map<String, String> getMapOfPluginForPluginFrontID() {
    return this.mapOfPluginForPluginFrontID;
  }

  public void setMapOfPluginForPluginFrontID(Map<String, String> mapOfPluginForPluginFrontID) {
    this.mapOfPluginForPluginFrontID = mapOfPluginForPluginFrontID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
