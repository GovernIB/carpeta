
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.CiutadaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CiutadaFilterForm extends CarpetaBaseFilterForm implements CiutadaFields {

  private java.lang.Long ciutadaIDDesde;

  public java.lang.Long getCiutadaIDDesde() {
    return this.ciutadaIDDesde;
  }

  public void setCiutadaIDDesde(java.lang.Long ciutadaIDDesde) {
    this.ciutadaIDDesde = ciutadaIDDesde;
  }


  private java.lang.Long ciutadaIDFins;

  public java.lang.Long getCiutadaIDFins() {
    return this.ciutadaIDFins;
  }

  public void setCiutadaIDFins(java.lang.Long ciutadaIDFins) {
    this.ciutadaIDFins = ciutadaIDFins;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.String llinatge1;

  public java.lang.String getLlinatge1() {
    return this.llinatge1;
  }

  public void setLlinatge1(java.lang.String llinatge1) {
    this.llinatge1 = llinatge1;
  }


  private java.lang.String llinatge2;

  public java.lang.String getLlinatge2() {
    return this.llinatge2;
  }

  public void setLlinatge2(java.lang.String llinatge2) {
    this.llinatge2 = llinatge2;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
  }


  private java.lang.String representantNif;

  public java.lang.String getRepresentantNif() {
    return this.representantNif;
  }

  public void setRepresentantNif(java.lang.String representantNif) {
    this.representantNif = representantNif;
  }


  private java.lang.String representantLlinatge1;

  public java.lang.String getRepresentantLlinatge1() {
    return this.representantLlinatge1;
  }

  public void setRepresentantLlinatge1(java.lang.String representantLlinatge1) {
    this.representantLlinatge1 = representantLlinatge1;
  }


  private java.lang.String representantLlinatge2;

  public java.lang.String getRepresentantLlinatge2() {
    return this.representantLlinatge2;
  }

  public void setRepresentantLlinatge2(java.lang.String representantLlinatge2) {
    this.representantLlinatge2 = representantLlinatge2;
  }


  private java.lang.String representantNom;

  public java.lang.String getRepresentantNom() {
    return this.representantNom;
  }

  public void setRepresentantNom(java.lang.String representantNom) {
    this.representantNom = representantNom;
  }


  private java.sql.Timestamp dataCreacioDesde;

  public java.sql.Timestamp getDataCreacioDesde() {
    return this.dataCreacioDesde;
  }

  public void setDataCreacioDesde(java.sql.Timestamp dataCreacioDesde) {
    this.dataCreacioDesde = dataCreacioDesde;
  }


  private java.sql.Timestamp dataCreacioFins;

  public java.sql.Timestamp getDataCreacioFins() {
    return this.dataCreacioFins;
  }

  public void setDataCreacioFins(java.sql.Timestamp dataCreacioFins) {
    this.dataCreacioFins = dataCreacioFins;
  }


  private java.lang.String mobileId;

  public java.lang.String getMobileId() {
    return this.mobileId;
  }

  public void setMobileId(java.lang.String mobileId) {
    this.mobileId = mobileId;
  }


  public CiutadaFilterForm() {
  }
  
  public CiutadaFilterForm(CiutadaFilterForm __toClone) {
    super(__toClone);
    this.ciutadaIDDesde = __toClone.ciutadaIDDesde;
    this.ciutadaIDFins = __toClone.ciutadaIDFins;
    this.nif = __toClone.nif;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.nom = __toClone.nom;
    this.representantNif = __toClone.representantNif;
    this.representantLlinatge1 = __toClone.representantLlinatge1;
    this.representantLlinatge2 = __toClone.representantLlinatge2;
    this.representantNom = __toClone.representantNom;
    this.dataCreacioDesde = __toClone.dataCreacioDesde;
    this.dataCreacioFins = __toClone.dataCreacioFins;
    this.mobileId = __toClone.mobileId;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NIF ,LLINATGE1 ,LLINATGE2 ,REPRESENTANTNIF ,REPRESENTANTLLINATGE1 ,MOBILEID }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOM ,EMPRESA }));
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

   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
