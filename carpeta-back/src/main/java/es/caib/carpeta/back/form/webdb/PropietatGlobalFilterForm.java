
package es.caib.carpeta.back.form.webdb;

import es.caib.carpeta.back.form.CarpetaBaseFilterForm;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
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
public class PropietatGlobalFilterForm extends CarpetaBaseFilterForm implements PropietatGlobalFields {

  private java.lang.Long propietaGlobalIDDesde;

  public java.lang.Long getPropietaGlobalIDDesde() {
    return this.propietaGlobalIDDesde;
  }

  public void setPropietaGlobalIDDesde(java.lang.Long propietaGlobalIDDesde) {
    this.propietaGlobalIDDesde = propietaGlobalIDDesde;
  }


  private java.lang.Long propietaGlobalIDFins;

  public java.lang.Long getPropietaGlobalIDFins() {
    return this.propietaGlobalIDFins;
  }

  public void setPropietaGlobalIDFins(java.lang.Long propietaGlobalIDFins) {
    this.propietaGlobalIDFins = propietaGlobalIDFins;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.String value;

  public java.lang.String getValue() {
    return this.value;
  }

  public void setValue(java.lang.String value) {
    this.value = value;
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


  public PropietatGlobalFilterForm() {
  }
  
  public PropietatGlobalFilterForm(PropietatGlobalFilterForm __toClone) {
    super(__toClone);
    this.propietaGlobalIDDesde = __toClone.propietaGlobalIDDesde;
    this.propietaGlobalIDFins = __toClone.propietaGlobalIDFins;
    this.codi = __toClone.codi;
    this.value = __toClone.value;
    this.descripcio = __toClone.descripcio;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
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
