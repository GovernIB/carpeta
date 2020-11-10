
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


  private java.lang.Long usuariIDDesde;

  public java.lang.Long getUsuariIDDesde() {
    return this.usuariIDDesde;
  }

  public void setUsuariIDDesde(java.lang.Long usuariIDDesde) {
    this.usuariIDDesde = usuariIDDesde;
  }


  private java.lang.Long usuariIDFins;

  public java.lang.Long getUsuariIDFins() {
    return this.usuariIDFins;
  }

  public void setUsuariIDFins(java.lang.Long usuariIDFins) {
    this.usuariIDFins = usuariIDFins;
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


  private java.lang.String ticketLoginIB;

  public java.lang.String getTicketLoginIB() {
    return this.ticketLoginIB;
  }

  public void setTicketLoginIB(java.lang.String ticketLoginIB) {
    this.ticketLoginIB = ticketLoginIB;
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


  public AuditoriaFilterForm() {
  }
  
  public AuditoriaFilterForm(AuditoriaFilterForm __toClone) {
    super(__toClone);
    this.auditoriaIDDesde = __toClone.auditoriaIDDesde;
    this.auditoriaIDFins = __toClone.auditoriaIDFins;
    this.dataAuditDesde = __toClone.dataAuditDesde;
    this.dataAuditFins = __toClone.dataAuditFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.ticketLoginIB = __toClone.ticketLoginIB;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfUsuariForUsuariID = __toClone.mapOfUsuariForUsuariID;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForPluginID = __toClone.mapOfValuesForPluginID;
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



  private Map<String, String> mapOfUsuariForUsuariID;

  public Map<String, String> getMapOfUsuariForUsuariID() {
    return this.mapOfUsuariForUsuariID;
  }

  public void setMapOfUsuariForUsuariID(Map<String, String> mapOfUsuariForUsuariID) {
    this.mapOfUsuariForUsuariID = mapOfUsuariForUsuariID;
  }



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




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
