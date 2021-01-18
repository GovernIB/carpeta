
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.AccesFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class AccesFilterForm extends CarpetaBaseFilterForm implements AccesFields {

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


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String llinatges;

  public java.lang.String getLlinatges() {
    return this.llinatges;
  }

  public void setLlinatges(java.lang.String llinatges) {
    this.llinatges = llinatges;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String ip;

  public java.lang.String getIp() {
    return this.ip;
  }

  public void setIp(java.lang.String ip) {
    this.ip = ip;
  }


  private java.lang.String proveidorIdentitat;

  public java.lang.String getProveidorIdentitat() {
    return this.proveidorIdentitat;
  }

  public void setProveidorIdentitat(java.lang.String proveidorIdentitat) {
    this.proveidorIdentitat = proveidorIdentitat;
  }


  private java.lang.String metodeAutenticacio;

  public java.lang.String getMetodeAutenticacio() {
    return this.metodeAutenticacio;
  }

  public void setMetodeAutenticacio(java.lang.String metodeAutenticacio) {
    this.metodeAutenticacio = metodeAutenticacio;
  }


  private java.lang.Integer qaaDesde;

  public java.lang.Integer getQaaDesde() {
    return this.qaaDesde;
  }

  public void setQaaDesde(java.lang.Integer qaaDesde) {
    this.qaaDesde = qaaDesde;
  }


  private java.lang.Integer qaaFins;

  public java.lang.Integer getQaaFins() {
    return this.qaaFins;
  }

  public void setQaaFins(java.lang.Integer qaaFins) {
    this.qaaFins = qaaFins;
  }


  private java.sql.Timestamp dataAccesDesde;

  public java.sql.Timestamp getDataAccesDesde() {
    return this.dataAccesDesde;
  }

  public void setDataAccesDesde(java.sql.Timestamp dataAccesDesde) {
    this.dataAccesDesde = dataAccesDesde;
  }


  private java.sql.Timestamp dataAccesFins;

  public java.sql.Timestamp getDataAccesFins() {
    return this.dataAccesFins;
  }

  public void setDataAccesFins(java.sql.Timestamp dataAccesFins) {
    this.dataAccesFins = dataAccesFins;
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


  private java.lang.String idioma;

  public java.lang.String getIdioma() {
    return this.idioma;
  }

  public void setIdioma(java.lang.String idioma) {
    this.idioma = idioma;
  }


  public AccesFilterForm() {
  }
  
  public AccesFilterForm(AccesFilterForm __toClone) {
    super(__toClone);
    this.accesIDDesde = __toClone.accesIDDesde;
    this.accesIDFins = __toClone.accesIDFins;
    this.tipusDesde = __toClone.tipusDesde;
    this.tipusFins = __toClone.tipusFins;
    this.nom = __toClone.nom;
    this.llinatges = __toClone.llinatges;
    this.nif = __toClone.nif;
    this.ip = __toClone.ip;
    this.proveidorIdentitat = __toClone.proveidorIdentitat;
    this.metodeAutenticacio = __toClone.metodeAutenticacio;
    this.qaaDesde = __toClone.qaaDesde;
    this.qaaFins = __toClone.qaaFins;
    this.dataAccesDesde = __toClone.dataAccesDesde;
    this.dataAccesFins = __toClone.dataAccesFins;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.idioma = __toClone.idioma;
    this.mapOfValuesForTipus = __toClone.mapOfValuesForTipus;
    this.mapOfValuesForPluginID = __toClone.mapOfValuesForPluginID;
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
