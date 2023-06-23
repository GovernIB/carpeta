
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.PreguntesFrequentsFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PreguntesFrequentsFilterForm extends CarpetaBaseFilterForm implements PreguntesFrequentsFields {

  private java.lang.Long preguntesFrequentsIDDesde;

  public java.lang.Long getPreguntesFrequentsIDDesde() {
    return this.preguntesFrequentsIDDesde;
  }

  public void setPreguntesFrequentsIDDesde(java.lang.Long preguntesFrequentsIDDesde) {
    this.preguntesFrequentsIDDesde = preguntesFrequentsIDDesde;
  }


  private java.lang.Long preguntesFrequentsIDFins;

  public java.lang.Long getPreguntesFrequentsIDFins() {
    return this.preguntesFrequentsIDFins;
  }

  public void setPreguntesFrequentsIDFins(java.lang.Long preguntesFrequentsIDFins) {
    this.preguntesFrequentsIDFins = preguntesFrequentsIDFins;
  }


  private java.lang.Long enunciatIDDesde;

  public java.lang.Long getEnunciatIDDesde() {
    return this.enunciatIDDesde;
  }

  public void setEnunciatIDDesde(java.lang.Long enunciatIDDesde) {
    this.enunciatIDDesde = enunciatIDDesde;
  }


  private java.lang.Long enunciatIDFins;

  public java.lang.Long getEnunciatIDFins() {
    return this.enunciatIDFins;
  }

  public void setEnunciatIDFins(java.lang.Long enunciatIDFins) {
    this.enunciatIDFins = enunciatIDFins;
  }


  private java.lang.Integer ordreDesde;

  public java.lang.Integer getOrdreDesde() {
    return this.ordreDesde;
  }

  public void setOrdreDesde(java.lang.Integer ordreDesde) {
    this.ordreDesde = ordreDesde;
  }


  private java.lang.Integer ordreFins;

  public java.lang.Integer getOrdreFins() {
    return this.ordreFins;
  }

  public void setOrdreFins(java.lang.Integer ordreFins) {
    this.ordreFins = ordreFins;
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


  private java.lang.String respostaCa;

  public java.lang.String getRespostaCa() {
    return this.respostaCa;
  }

  public void setRespostaCa(java.lang.String respostaCa) {
    this.respostaCa = respostaCa;
  }


  private java.lang.String respostaEs;

  public java.lang.String getRespostaEs() {
    return this.respostaEs;
  }

  public void setRespostaEs(java.lang.String respostaEs) {
    this.respostaEs = respostaEs;
  }


  public PreguntesFrequentsFilterForm() {
  }
  
  public PreguntesFrequentsFilterForm(PreguntesFrequentsFilterForm __toClone) {
    super(__toClone);
    this.preguntesFrequentsIDDesde = __toClone.preguntesFrequentsIDDesde;
    this.preguntesFrequentsIDFins = __toClone.preguntesFrequentsIDFins;
    this.enunciatIDDesde = __toClone.enunciatIDDesde;
    this.enunciatIDFins = __toClone.enunciatIDFins;
    this.ordreDesde = __toClone.ordreDesde;
    this.ordreFins = __toClone.ordreFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.respostaCa = __toClone.respostaCa;
    this.respostaEs = __toClone.respostaEs;
    this.mapOfTraduccioForEnunciatID = __toClone.mapOfTraduccioForEnunciatID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ENUNCIATID }));
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
  private Map<String, String> mapOfTraduccioForEnunciatID;

  public Map<String, String> getMapOfTraduccioForEnunciatID() {
    return this.mapOfTraduccioForEnunciatID;
  }

  public void setMapOfTraduccioForEnunciatID(Map<String, String> mapOfTraduccioForEnunciatID) {
    this.mapOfTraduccioForEnunciatID = mapOfTraduccioForEnunciatID;
  }



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
