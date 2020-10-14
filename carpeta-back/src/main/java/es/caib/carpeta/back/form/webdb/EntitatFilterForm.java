
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.EntitatFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EntitatFilterForm extends CarpetaBaseFilterForm implements EntitatFields {

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


  private java.lang.Long nomIDDesde;

  public java.lang.Long getNomIDDesde() {
    return this.nomIDDesde;
  }

  public void setNomIDDesde(java.lang.Long nomIDDesde) {
    this.nomIDDesde = nomIDDesde;
  }


  private java.lang.Long nomIDFins;

  public java.lang.Long getNomIDFins() {
    return this.nomIDFins;
  }

  public void setNomIDFins(java.lang.Long nomIDFins) {
    this.nomIDFins = nomIDFins;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.String codiDir3;

  public java.lang.String getCodiDir3() {
    return this.codiDir3;
  }

  public void setCodiDir3(java.lang.String codiDir3) {
    this.codiDir3 = codiDir3;
  }


  private java.lang.String colorMenu;

  public java.lang.String getColorMenu() {
    return this.colorMenu;
  }

  public void setColorMenu(java.lang.String colorMenu) {
    this.colorMenu = colorMenu;
  }


  private java.lang.String versio;

  public java.lang.String getVersio() {
    return this.versio;
  }

  public void setVersio(java.lang.String versio) {
    this.versio = versio;
  }


  private java.lang.String webEntitat;

  public java.lang.String getWebEntitat() {
    return this.webEntitat;
  }

  public void setWebEntitat(java.lang.String webEntitat) {
    this.webEntitat = webEntitat;
  }


  private java.lang.String entitatDescFront;

  public java.lang.String getEntitatDescFront() {
    return this.entitatDescFront;
  }

  public void setEntitatDescFront(java.lang.String entitatDescFront) {
    this.entitatDescFront = entitatDescFront;
  }


  private java.lang.String suportWeb;

  public java.lang.String getSuportWeb() {
    return this.suportWeb;
  }

  public void setSuportWeb(java.lang.String suportWeb) {
    this.suportWeb = suportWeb;
  }


  private java.lang.String suportTelefon;

  public java.lang.String getSuportTelefon() {
    return this.suportTelefon;
  }

  public void setSuportTelefon(java.lang.String suportTelefon) {
    this.suportTelefon = suportTelefon;
  }


  private java.lang.String suportEmail;

  public java.lang.String getSuportEmail() {
    return this.suportEmail;
  }

  public void setSuportEmail(java.lang.String suportEmail) {
    this.suportEmail = suportEmail;
  }


  private java.lang.Long pluginLoginIDDesde;

  public java.lang.Long getPluginLoginIDDesde() {
    return this.pluginLoginIDDesde;
  }

  public void setPluginLoginIDDesde(java.lang.Long pluginLoginIDDesde) {
    this.pluginLoginIDDesde = pluginLoginIDDesde;
  }


  private java.lang.Long pluginLoginIDFins;

  public java.lang.Long getPluginLoginIDFins() {
    return this.pluginLoginIDFins;
  }

  public void setPluginLoginIDFins(java.lang.Long pluginLoginIDFins) {
    this.pluginLoginIDFins = pluginLoginIDFins;
  }


  private java.lang.String context;

  public java.lang.String getContext() {
    return this.context;
  }

  public void setContext(java.lang.String context) {
    this.context = context;
  }


  private java.lang.String commit;

  public java.lang.String getCommit() {
    return this.commit;
  }

  public void setCommit(java.lang.String commit) {
    this.commit = commit;
  }


  public EntitatFilterForm() {
  }
  
  public EntitatFilterForm(EntitatFilterForm __toClone) {
    super(__toClone);
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.nomIDDesde = __toClone.nomIDDesde;
    this.nomIDFins = __toClone.nomIDFins;
    this.codi = __toClone.codi;
    this.codiDir3 = __toClone.codiDir3;
    this.colorMenu = __toClone.colorMenu;
    this.versio = __toClone.versio;
    this.webEntitat = __toClone.webEntitat;
    this.entitatDescFront = __toClone.entitatDescFront;
    this.suportWeb = __toClone.suportWeb;
    this.suportTelefon = __toClone.suportTelefon;
    this.suportEmail = __toClone.suportEmail;
    this.pluginLoginIDDesde = __toClone.pluginLoginIDDesde;
    this.pluginLoginIDFins = __toClone.pluginLoginIDFins;
    this.context = __toClone.context;
    this.commit = __toClone.commit;
    this.mapOfTraduccioForNomID = __toClone.mapOfTraduccioForNomID;
    this.mapOfPluginForPluginLoginID = __toClone.mapOfPluginForPluginLoginID;
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
  private Map<String, String> mapOfTraduccioForNomID;

  public Map<String, String> getMapOfTraduccioForNomID() {
    return this.mapOfTraduccioForNomID;
  }

  public void setMapOfTraduccioForNomID(Map<String, String> mapOfTraduccioForNomID) {
    this.mapOfTraduccioForNomID = mapOfTraduccioForNomID;
  }



  private Map<String, String> mapOfPluginForPluginLoginID;

  public Map<String, String> getMapOfPluginForPluginLoginID() {
    return this.mapOfPluginForPluginLoginID;
  }

  public void setMapOfPluginForPluginLoginID(Map<String, String> mapOfPluginForPluginLoginID) {
    this.mapOfPluginForPluginLoginID = mapOfPluginForPluginLoginID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
