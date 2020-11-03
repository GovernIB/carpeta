
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


  private java.lang.Integer pluginIDDesde;

  public java.lang.Integer getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Integer pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Integer pluginIDFins;

  public java.lang.Integer getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Integer pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  public EstadisticaFilterForm() {
  }
  
  public EstadisticaFilterForm(EstadisticaFilterForm __toClone) {
    super(__toClone);
    this.estadisticaIDDesde = __toClone.estadisticaIDDesde;
    this.estadisticaIDFins = __toClone.estadisticaIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.dataEstadisticaDesde = __toClone.dataEstadisticaDesde;
    this.dataEstadisticaFins = __toClone.dataEstadisticaFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.comptadorDesde = __toClone.comptadorDesde;
    this.comptadorFins = __toClone.comptadorFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
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




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
