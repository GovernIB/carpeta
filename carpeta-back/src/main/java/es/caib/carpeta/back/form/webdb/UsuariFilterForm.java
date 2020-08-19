
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.UsuariFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariFilterForm extends CarpetaBaseFilterForm implements UsuariFields {

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


  private java.lang.String username;

  public java.lang.String getUsername() {
    return this.username;
  }

  public void setUsername(java.lang.String username) {
    this.username = username;
  }


  private java.lang.String nom;

  public java.lang.String getNom() {
    return this.nom;
  }

  public void setNom(java.lang.String nom) {
    this.nom = nom;
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


  private java.lang.String email;

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }


  private java.lang.String nif;

  public java.lang.String getNif() {
    return this.nif;
  }

  public void setNif(java.lang.String nif) {
    this.nif = nif;
  }


  private java.lang.Long darreraEntitatDesde;

  public java.lang.Long getDarreraEntitatDesde() {
    return this.darreraEntitatDesde;
  }

  public void setDarreraEntitatDesde(java.lang.Long darreraEntitatDesde) {
    this.darreraEntitatDesde = darreraEntitatDesde;
  }


  private java.lang.Long darreraEntitatFins;

  public java.lang.Long getDarreraEntitatFins() {
    return this.darreraEntitatFins;
  }

  public void setDarreraEntitatFins(java.lang.Long darreraEntitatFins) {
    this.darreraEntitatFins = darreraEntitatFins;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
  }


  public UsuariFilterForm() {
  }
  
  public UsuariFilterForm(UsuariFilterForm __toClone) {
    super(__toClone);
    this.usuariIDDesde = __toClone.usuariIDDesde;
    this.usuariIDFins = __toClone.usuariIDFins;
    this.username = __toClone.username;
    this.nom = __toClone.nom;
    this.llinatge1 = __toClone.llinatge1;
    this.llinatge2 = __toClone.llinatge2;
    this.email = __toClone.email;
    this.nif = __toClone.nif;
    this.darreraEntitatDesde = __toClone.darreraEntitatDesde;
    this.darreraEntitatFins = __toClone.darreraEntitatFins;
    this.idiomaID = __toClone.idiomaID;
    this.mapOfEntitatForDarreraEntitat = __toClone.mapOfEntitatForDarreraEntitat;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { USERNAME ,NOM ,LLINATGE1 ,LLINATGE2 ,EMAIL ,NIF }));
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
  private Map<String, String> mapOfEntitatForDarreraEntitat;

  public Map<String, String> getMapOfEntitatForDarreraEntitat() {
    return this.mapOfEntitatForDarreraEntitat;
  }

  public void setMapOfEntitatForDarreraEntitat(Map<String, String> mapOfEntitatForDarreraEntitat) {
    this.mapOfEntitatForDarreraEntitat = mapOfEntitatForDarreraEntitat;
  }



  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
