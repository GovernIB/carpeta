
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseFilterForm;
import es.caib.carpeta.model.fields.LogCarpetaFields;
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
public class LogCarpetaFilterForm extends CarpetaBaseFilterForm implements LogCarpetaFields {

  private java.lang.Long logIDDesde;

  public java.lang.Long getLogIDDesde() {
    return this.logIDDesde;
  }

  public void setLogIDDesde(java.lang.Long logIDDesde) {
    this.logIDDesde = logIDDesde;
  }


  private java.lang.Long logIDFins;

  public java.lang.Long getLogIDFins() {
    return this.logIDFins;
  }

  public void setLogIDFins(java.lang.Long logIDFins) {
    this.logIDFins = logIDFins;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
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


  private java.lang.Integer estatDesde;

  public java.lang.Integer getEstatDesde() {
    return this.estatDesde;
  }

  public void setEstatDesde(java.lang.Integer estatDesde) {
    this.estatDesde = estatDesde;
  }


  private java.lang.Integer estatFins;

  public java.lang.Integer getEstatFins() {
    return this.estatFins;
  }

  public void setEstatFins(java.lang.Integer estatFins) {
    this.estatFins = estatFins;
  }


  private java.lang.Long tempsDesde;

  public java.lang.Long getTempsDesde() {
    return this.tempsDesde;
  }

  public void setTempsDesde(java.lang.Long tempsDesde) {
    this.tempsDesde = tempsDesde;
  }


  private java.lang.Long tempsFins;

  public java.lang.Long getTempsFins() {
    return this.tempsFins;
  }

  public void setTempsFins(java.lang.Long tempsFins) {
    this.tempsFins = tempsFins;
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


  private java.lang.String peticio;

  public java.lang.String getPeticio() {
    return this.peticio;
  }

  public void setPeticio(java.lang.String peticio) {
    this.peticio = peticio;
  }


  private java.lang.String error;

  public java.lang.String getError() {
    return this.error;
  }

  public void setError(java.lang.String error) {
    this.error = error;
  }


  private java.lang.String excepcio;

  public java.lang.String getExcepcio() {
    return this.excepcio;
  }

  public void setExcepcio(java.lang.String excepcio) {
    this.excepcio = excepcio;
  }


  public LogCarpetaFilterForm() {
  }
  
  public LogCarpetaFilterForm(LogCarpetaFilterForm __toClone) {
    super(__toClone);
    this.logIDDesde = __toClone.logIDDesde;
    this.logIDFins = __toClone.logIDFins;
    this.descripcio = __toClone.descripcio;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.estatDesde = __toClone.estatDesde;
    this.estatFins = __toClone.estatFins;
    this.tempsDesde = __toClone.tempsDesde;
    this.tempsFins = __toClone.tempsFins;
    this.dataIniciDesde = __toClone.dataIniciDesde;
    this.dataIniciFins = __toClone.dataIniciFins;
    this.peticio = __toClone.peticio;
    this.error = __toClone.error;
    this.excepcio = __toClone.excepcio;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForEstat = __toClone.mapOfValuesForEstat;
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



  private Map<String, String> mapOfPluginForPluginID;

  public Map<String, String> getMapOfPluginForPluginID() {
    return this.mapOfPluginForPluginID;
  }

  public void setMapOfPluginForPluginID(Map<String, String> mapOfPluginForPluginID) {
    this.mapOfPluginForPluginID = mapOfPluginForPluginID;
  }



  private Map<String, String> mapOfValuesForTipus;

  public Map<String, String> getMapOfValuesForTipus() {
    return this.mapOfValuesForTipus;
  }

  public void setMapOfValuesForTipus(Map<String, String> mapOfValuesForTipus) {
    this.mapOfValuesForTipus = mapOfValuesForTipus;
  }



  private Map<String, String> mapOfValuesForEstat;

  public Map<String, String> getMapOfValuesForEstat() {
    return this.mapOfValuesForEstat;
  }

  public void setMapOfValuesForEstat(Map<String, String> mapOfValuesForEstat) {
    this.mapOfValuesForEstat = mapOfValuesForEstat;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
