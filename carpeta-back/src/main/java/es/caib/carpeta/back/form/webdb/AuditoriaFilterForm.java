
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.AuditoriaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AuditoriaFilterForm extends CarpetaBaseFilterForm implements AuditoriaFields {

  private java.lang.Long auditoriaIDDesde;

  public java.lang.Long getAuditoriaIDDesde() {
    return this.auditoriaIDDesde;
  }

  public void setAuditoriaIDDesde(java.lang.Long auditoriaIDDesde) {
    this.auditoriaIDDesde = auditoriaIDDesde;
  }


  private java.lang.Long auditoriaIDFins;

  public java.lang.Long getAuditoriaIDFins() {
    return this.auditoriaIDFins;
  }

  public void setAuditoriaIDFins(java.lang.Long auditoriaIDFins) {
    this.auditoriaIDFins = auditoriaIDFins;
  }


  private java.sql.Timestamp dataAuditDesde;

  public java.sql.Timestamp getDataAuditDesde() {
    return this.dataAuditDesde;
  }

  public void setDataAuditDesde(java.sql.Timestamp dataAuditDesde) {
    this.dataAuditDesde = dataAuditDesde;
  }


  private java.sql.Timestamp dataAuditFins;

  public java.sql.Timestamp getDataAuditFins() {
    return this.dataAuditFins;
  }

  public void setDataAuditFins(java.sql.Timestamp dataAuditFins) {
    this.dataAuditFins = dataAuditFins;
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


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String usuariClave;

  public java.lang.String getUsuariClave() {
    return this.usuariClave;
  }

  public void setUsuariClave(java.lang.String usuariClave) {
    this.usuariClave = usuariClave;
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


  public AuditoriaFilterForm() {
  }
  
  public AuditoriaFilterForm(AuditoriaFilterForm __toClone) {
    super(__toClone);
    this.auditoriaIDDesde = __toClone.auditoriaIDDesde;
    this.auditoriaIDFins = __toClone.auditoriaIDFins;
    this.dataAuditDesde = __toClone.dataAuditDesde;
    this.dataAuditFins = __toClone.dataAuditFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.username = __toClone.username;
    this.usuariClave = __toClone.usuariClave;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForUsername = __toClone.mapOfValuesForUsername;
    this.mapOfValuesForEntitatID = __toClone.mapOfValuesForEntitatID;
    this.mapOfValuesForPluginID = __toClone.mapOfValuesForPluginID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATAAUDIT ,USERNAME ,USUARICLAVE }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS ,ENTITATID ,PLUGINID }));
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



  private Map<String, String> mapOfValuesForUsername;

  public Map<String, String> getMapOfValuesForUsername() {
    return this.mapOfValuesForUsername;
  }

  public void setMapOfValuesForUsername(Map<String, String> mapOfValuesForUsername) {
    this.mapOfValuesForUsername = mapOfValuesForUsername;
  }



  private Map<String, String> mapOfValuesForEntitatID;

  public Map<String, String> getMapOfValuesForEntitatID() {
    return this.mapOfValuesForEntitatID;
  }

  public void setMapOfValuesForEntitatID(Map<String, String> mapOfValuesForEntitatID) {
    this.mapOfValuesForEntitatID = mapOfValuesForEntitatID;
  }



  private Map<String, String> mapOfValuesForPluginID;

  public Map<String, String> getMapOfValuesForPluginID() {
    return this.mapOfValuesForPluginID;
  }

  public void setMapOfValuesForPluginID(Map<String, String> mapOfValuesForPluginID) {
    this.mapOfValuesForPluginID = mapOfValuesForPluginID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
