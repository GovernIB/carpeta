
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.EstadisticaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstadisticaFilterForm extends CarpetaBaseFilterForm implements EstadisticaFields {

  private java.lang.Long estadisticaIDDesde;

  public java.lang.Long getEstadisticaIDDesde() {
    return this.estadisticaIDDesde;
  }

  public void setEstadisticaIDDesde(java.lang.Long estadisticaIDDesde) {
    this.estadisticaIDDesde = estadisticaIDDesde;
  }


  private java.lang.Long estadisticaIDFins;

  public java.lang.Long getEstadisticaIDFins() {
    return this.estadisticaIDFins;
  }

  public void setEstadisticaIDFins(java.lang.Long estadisticaIDFins) {
    this.estadisticaIDFins = estadisticaIDFins;
  }


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  private java.sql.Timestamp dataEstadisticaDesde;

  public java.sql.Timestamp getDataEstadisticaDesde() {
    return this.dataEstadisticaDesde;
  }

  public void setDataEstadisticaDesde(java.sql.Timestamp dataEstadisticaDesde) {
    this.dataEstadisticaDesde = dataEstadisticaDesde;
  }


  private java.sql.Timestamp dataEstadisticaFins;

  public java.sql.Timestamp getDataEstadisticaFins() {
    return this.dataEstadisticaFins;
  }

  public void setDataEstadisticaFins(java.sql.Timestamp dataEstadisticaFins) {
    this.dataEstadisticaFins = dataEstadisticaFins;
  }


  private java.lang.Integer comptadorDesde;

  public java.lang.Integer getComptadorDesde() {
    return this.comptadorDesde;
  }

  public void setComptadorDesde(java.lang.Integer comptadorDesde) {
    this.comptadorDesde = comptadorDesde;
  }


  private java.lang.Integer comptadorFins;

  public java.lang.Integer getComptadorFins() {
    return this.comptadorFins;
  }

  public void setComptadorFins(java.lang.Integer comptadorFins) {
    this.comptadorFins = comptadorFins;
  }


  private java.util.List<java.lang.Long> pluginIDSelect;

  public java.util.List<java.lang.Long> getPluginIDSelect() {
    return this.pluginIDSelect;
  }

  public void setPluginIDSelect(java.util.List<java.lang.Long> pluginIDSelect) {
    this.pluginIDSelect = pluginIDSelect;
  }


  private java.util.List<java.lang.Long> entitatIDSelect;

  public java.util.List<java.lang.Long> getEntitatIDSelect() {
    return this.entitatIDSelect;
  }

  public void setEntitatIDSelect(java.util.List<java.lang.Long> entitatIDSelect) {
    this.entitatIDSelect = entitatIDSelect;
  }


  public EstadisticaFilterForm() {
  }
  
  public EstadisticaFilterForm(EstadisticaFilterForm __toClone) {
    super(__toClone);
    this.estadisticaIDDesde = __toClone.estadisticaIDDesde;
    this.estadisticaIDFins = __toClone.estadisticaIDFins;
    this.tipusSelect = __toClone.tipusSelect;
    this.dataEstadisticaDesde = __toClone.dataEstadisticaDesde;
    this.dataEstadisticaFins = __toClone.dataEstadisticaFins;
    this.comptadorDesde = __toClone.comptadorDesde;
    this.comptadorFins = __toClone.comptadorFins;
    this.pluginIDSelect = __toClone.pluginIDSelect;
    this.entitatIDSelect = __toClone.entitatIDSelect;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForPluginID = __toClone.mapOfValuesForPluginID;
    this.mapOfValuesForEntitatID = __toClone.mapOfValuesForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS ,DATAESTADISTICA ,ENTITATID }));
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



  private Map<String, String> mapOfValuesForPluginID;

  public Map<String, String> getMapOfValuesForPluginID() {
    return this.mapOfValuesForPluginID;
  }

  public void setMapOfValuesForPluginID(Map<String, String> mapOfValuesForPluginID) {
    this.mapOfValuesForPluginID = mapOfValuesForPluginID;
  }



  private Map<String, String> mapOfValuesForEntitatID;

  public Map<String, String> getMapOfValuesForEntitatID() {
    return this.mapOfValuesForEntitatID;
  }

  public void setMapOfValuesForEntitatID(Map<String, String> mapOfValuesForEntitatID) {
    this.mapOfValuesForEntitatID = mapOfValuesForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
