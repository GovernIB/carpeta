
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


  private java.lang.String nivellSeguretat;

  public java.lang.String getNivellSeguretat() {
    return this.nivellSeguretat;
  }

  public void setNivellSeguretat(java.lang.String nivellSeguretat) {
    this.nivellSeguretat = nivellSeguretat;
  }


  private java.lang.Integer resultatAutenticacioDesde;

  public java.lang.Integer getResultatAutenticacioDesde() {
    return this.resultatAutenticacioDesde;
  }

  public void setResultatAutenticacioDesde(java.lang.Integer resultatAutenticacioDesde) {
    this.resultatAutenticacioDesde = resultatAutenticacioDesde;
  }


  private java.lang.Integer resultatAutenticacioFins;

  public java.lang.Integer getResultatAutenticacioFins() {
    return this.resultatAutenticacioFins;
  }

  public void setResultatAutenticacioFins(java.lang.Integer resultatAutenticacioFins) {
    this.resultatAutenticacioFins = resultatAutenticacioFins;
  }


  private java.sql.Timestamp dataDarrerAccesDesde;

  public java.sql.Timestamp getDataDarrerAccesDesde() {
    return this.dataDarrerAccesDesde;
  }

  public void setDataDarrerAccesDesde(java.sql.Timestamp dataDarrerAccesDesde) {
    this.dataDarrerAccesDesde = dataDarrerAccesDesde;
  }


  private java.sql.Timestamp dataDarrerAccesFins;

  public java.sql.Timestamp getDataDarrerAccesFins() {
    return this.dataDarrerAccesFins;
  }

  public void setDataDarrerAccesFins(java.sql.Timestamp dataDarrerAccesFins) {
    this.dataDarrerAccesFins = dataDarrerAccesFins;
  }


  private java.lang.String idioma;

  public java.lang.String getIdioma() {
    return this.idioma;
  }

  public void setIdioma(java.lang.String idioma) {
    this.idioma = idioma;
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


  public AccesFilterForm() {
  }
  
  public AccesFilterForm(AccesFilterForm __toClone) {
    super(__toClone);
    this.accesIDDesde = __toClone.accesIDDesde;
    this.accesIDFins = __toClone.accesIDFins;
    this.nom = __toClone.nom;
    this.llinatges = __toClone.llinatges;
    this.nif = __toClone.nif;
    this.ip = __toClone.ip;
    this.proveidorIdentitat = __toClone.proveidorIdentitat;
    this.nivellSeguretat = __toClone.nivellSeguretat;
    this.resultatAutenticacioDesde = __toClone.resultatAutenticacioDesde;
    this.resultatAutenticacioFins = __toClone.resultatAutenticacioFins;
    this.dataDarrerAccesDesde = __toClone.dataDarrerAccesDesde;
    this.dataDarrerAccesFins = __toClone.dataDarrerAccesFins;
    this.idioma = __toClone.idioma;
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
