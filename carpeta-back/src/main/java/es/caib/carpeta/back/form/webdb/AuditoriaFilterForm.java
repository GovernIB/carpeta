
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


  private java.util.List<java.lang.Integer> tipusSelect;

  public java.util.List<java.lang.Integer> getTipusSelect() {
    return this.tipusSelect;
  }

  public void setTipusSelect(java.util.List<java.lang.Integer> tipusSelect) {
    this.tipusSelect = tipusSelect;
  }


  private java.lang.String objecte;

  public java.lang.String getObjecte() {
    return this.objecte;
  }

  public void setObjecte(java.lang.String objecte) {
    this.objecte = objecte;
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


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.util.List<java.lang.Long> entitatIDSelect;

  public java.util.List<java.lang.Long> getEntitatIDSelect() {
    return this.entitatIDSelect;
  }

  public void setEntitatIDSelect(java.util.List<java.lang.Long> entitatIDSelect) {
    this.entitatIDSelect = entitatIDSelect;
  }


  public AuditoriaFilterForm() {
  }
  
  public AuditoriaFilterForm(AuditoriaFilterForm __toClone) {
    super(__toClone);
    this.auditoriaIDDesde = __toClone.auditoriaIDDesde;
    this.auditoriaIDFins = __toClone.auditoriaIDFins;
    this.tipusSelect = __toClone.tipusSelect;
    this.objecte = __toClone.objecte;
    this.dataAuditDesde = __toClone.dataAuditDesde;
    this.dataAuditFins = __toClone.dataAuditFins;
    this.username = __toClone.username;
    this.entitatIDSelect = __toClone.entitatIDSelect;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForEntitatID = __toClone.mapOfValuesForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { DATAAUDIT ,USERNAME }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUS ,ENTITATID }));
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
