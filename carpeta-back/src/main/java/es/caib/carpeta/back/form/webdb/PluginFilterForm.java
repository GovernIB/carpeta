
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.PluginFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PluginFilterForm extends CarpetaBaseFilterForm implements PluginFields {

  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
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


  private java.lang.Long titolLlargIDDesde;

  public java.lang.Long getTitolLlargIDDesde() {
    return this.titolLlargIDDesde;
  }

  public void setTitolLlargIDDesde(java.lang.Long titolLlargIDDesde) {
    this.titolLlargIDDesde = titolLlargIDDesde;
  }


  private java.lang.Long titolLlargIDFins;

  public java.lang.Long getTitolLlargIDFins() {
    return this.titolLlargIDFins;
  }

  public void setTitolLlargIDFins(java.lang.Long titolLlargIDFins) {
    this.titolLlargIDFins = titolLlargIDFins;
  }


  private java.lang.Long subtitolLlargIDDesde;

  public java.lang.Long getSubtitolLlargIDDesde() {
    return this.subtitolLlargIDDesde;
  }

  public void setSubtitolLlargIDDesde(java.lang.Long subtitolLlargIDDesde) {
    this.subtitolLlargIDDesde = subtitolLlargIDDesde;
  }


  private java.lang.Long subtitolLlargIDFins;

  public java.lang.Long getSubtitolLlargIDFins() {
    return this.subtitolLlargIDFins;
  }

  public void setSubtitolLlargIDFins(java.lang.Long subtitolLlargIDFins) {
    this.subtitolLlargIDFins = subtitolLlargIDFins;
  }


  private java.lang.String context;

  public java.lang.String getContext() {
    return this.context;
  }

  public void setContext(java.lang.String context) {
    this.context = context;
  }


  private java.lang.String classe;

  public java.lang.String getClasse() {
    return this.classe;
  }

  public void setClasse(java.lang.String classe) {
    this.classe = classe;
  }


  private java.lang.String propietats;

  public java.lang.String getPropietats() {
    return this.propietats;
  }

  public void setPropietats(java.lang.String propietats) {
    this.propietats = propietats;
  }


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  public PluginFilterForm() {
  }
  
  public PluginFilterForm(PluginFilterForm __toClone) {
    super(__toClone);
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.descripcioIDDesde = __toClone.descripcioIDDesde;
    this.descripcioIDFins = __toClone.descripcioIDFins;
    this.titolLlargIDDesde = __toClone.titolLlargIDDesde;
    this.titolLlargIDFins = __toClone.titolLlargIDFins;
    this.subtitolLlargIDDesde = __toClone.subtitolLlargIDDesde;
    this.subtitolLlargIDFins = __toClone.subtitolLlargIDFins;
    this.context = __toClone.context;
    this.classe = __toClone.classe;
    this.propietats = __toClone.propietats;
    this.tipusSelect = __toClone.tipusSelect;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfTraduccioForDescripcioID = __toClone.mapOfTraduccioForDescripcioID;
    this.mapOfTraduccioForTitolLlargID = __toClone.mapOfTraduccioForTitolLlargID;
    this.mapOfTraduccioForSubtitolLlargID = __toClone.mapOfTraduccioForSubtitolLlargID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
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



  private Map<String, String> mapOfTraduccioForTitolLlargID;

  public Map<String, String> getMapOfTraduccioForTitolLlargID() {
    return this.mapOfTraduccioForTitolLlargID;
  }

  public void setMapOfTraduccioForTitolLlargID(Map<String, String> mapOfTraduccioForTitolLlargID) {
    this.mapOfTraduccioForTitolLlargID = mapOfTraduccioForTitolLlargID;
  }



  private Map<String, String> mapOfTraduccioForSubtitolLlargID;

  public Map<String, String> getMapOfTraduccioForSubtitolLlargID() {
    return this.mapOfTraduccioForSubtitolLlargID;
  }

  public void setMapOfTraduccioForSubtitolLlargID(Map<String, String> mapOfTraduccioForSubtitolLlargID) {
    this.mapOfTraduccioForSubtitolLlargID = mapOfTraduccioForSubtitolLlargID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
