
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseFilterForm;
import es.caib.carpeta.model.fields.EstadisticaFields;
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


  private java.lang.Long accesIDDesde;

  public java.lang.Long getAccesIDDesde() {
    return this.accesIDDesde;
  }

  public void setAccesIDDesde(java.lang.Long accesIDDesde) {
    this.accesIDDesde = accesIDDesde;
  }


  private java.lang.Long accesIDFins;

  public java.lang.Long getAccesIDFins() {
    return this.accesIDFins;
  }

  public void setAccesIDFins(java.lang.Long accesIDFins) {
    this.accesIDFins = accesIDFins;
  }


  private java.lang.Integer accioDesde;

  public java.lang.Integer getAccioDesde() {
    return this.accioDesde;
  }

  public void setAccioDesde(java.lang.Integer accioDesde) {
    this.accioDesde = accioDesde;
  }


  private java.lang.Integer accioFins;

  public java.lang.Integer getAccioFins() {
    return this.accioFins;
  }

  public void setAccioFins(java.lang.Integer accioFins) {
    this.accioFins = accioFins;
  }


  private java.lang.String element;

  public java.lang.String getElement() {
    return this.element;
  }

  public void setElement(java.lang.String element) {
    this.element = element;
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


  public EstadisticaFilterForm() {
  }
  
  public EstadisticaFilterForm(EstadisticaFilterForm __toClone) {
    super(__toClone);
    this.estadisticaIDDesde = __toClone.estadisticaIDDesde;
    this.estadisticaIDFins = __toClone.estadisticaIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.accesIDDesde = __toClone.accesIDDesde;
    this.accesIDFins = __toClone.accesIDFins;
    this.accioDesde = __toClone.accioDesde;
    this.accioFins = __toClone.accioFins;
    this.element = __toClone.element;
    this.dataEstadisticaDesde = __toClone.dataEstadisticaDesde;
    this.dataEstadisticaFins = __toClone.dataEstadisticaFins;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
    this.mapOfAccesForAccesID = __toClone.mapOfAccesForAccesID;
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



  private Map<String, String> mapOfAccesForAccesID;

  public Map<String, String> getMapOfAccesForAccesID() {
    return this.mapOfAccesForAccesID;
  }

  public void setMapOfAccesForAccesID(Map<String, String> mapOfAccesForAccesID) {
    this.mapOfAccesForAccesID = mapOfAccesForAccesID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
