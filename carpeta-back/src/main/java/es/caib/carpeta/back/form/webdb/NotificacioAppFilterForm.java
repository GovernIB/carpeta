
package es.caib.carpeta.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.carpeta.back.form.CarpetaBaseFilterForm;

import es.caib.carpeta.model.fields.NotificacioAppFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class NotificacioAppFilterForm extends CarpetaBaseFilterForm implements NotificacioAppFields {

  private java.lang.Long notificacioAppIDDesde;

  public java.lang.Long getNotificacioAppIDDesde() {
    return this.notificacioAppIDDesde;
  }

  public void setNotificacioAppIDDesde(java.lang.Long notificacioAppIDDesde) {
    this.notificacioAppIDDesde = notificacioAppIDDesde;
  }


  private java.lang.Long notificacioAppIDFins;

  public java.lang.Long getNotificacioAppIDFins() {
    return this.notificacioAppIDFins;
  }

  public void setNotificacioAppIDFins(java.lang.Long notificacioAppIDFins) {
    this.notificacioAppIDFins = notificacioAppIDFins;
  }


  private java.lang.String codi;

  public java.lang.String getCodi() {
    return this.codi;
  }

  public void setCodi(java.lang.String codi) {
    this.codi = codi;
  }


  private java.lang.Long titolIDDesde;

  public java.lang.Long getTitolIDDesde() {
    return this.titolIDDesde;
  }

  public void setTitolIDDesde(java.lang.Long titolIDDesde) {
    this.titolIDDesde = titolIDDesde;
  }


  private java.lang.Long titolIDFins;

  public java.lang.Long getTitolIDFins() {
    return this.titolIDFins;
  }

  public void setTitolIDFins(java.lang.Long titolIDFins) {
    this.titolIDFins = titolIDFins;
  }


  private java.lang.Long missatgeIDDesde;

  public java.lang.Long getMissatgeIDDesde() {
    return this.missatgeIDDesde;
  }

  public void setMissatgeIDDesde(java.lang.Long missatgeIDDesde) {
    this.missatgeIDDesde = missatgeIDDesde;
  }


  private java.lang.Long missatgeIDFins;

  public java.lang.Long getMissatgeIDFins() {
    return this.missatgeIDFins;
  }

  public void setMissatgeIDFins(java.lang.Long missatgeIDFins) {
    this.missatgeIDFins = missatgeIDFins;
  }


  private java.lang.Long frontPluginIDDesde;

  public java.lang.Long getFrontPluginIDDesde() {
    return this.frontPluginIDDesde;
  }

  public void setFrontPluginIDDesde(java.lang.Long frontPluginIDDesde) {
    this.frontPluginIDDesde = frontPluginIDDesde;
  }


  private java.lang.Long frontPluginIDFins;

  public java.lang.Long getFrontPluginIDFins() {
    return this.frontPluginIDFins;
  }

  public void setFrontPluginIDFins(java.lang.Long frontPluginIDFins) {
    this.frontPluginIDFins = frontPluginIDFins;
  }


  private java.lang.String ajuda;

  public java.lang.String getAjuda() {
    return this.ajuda;
  }

  public void setAjuda(java.lang.String ajuda) {
    this.ajuda = ajuda;
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


  public NotificacioAppFilterForm() {
  }
  
  public NotificacioAppFilterForm(NotificacioAppFilterForm __toClone) {
    super(__toClone);
    this.notificacioAppIDDesde = __toClone.notificacioAppIDDesde;
    this.notificacioAppIDFins = __toClone.notificacioAppIDFins;
    this.codi = __toClone.codi;
    this.titolIDDesde = __toClone.titolIDDesde;
    this.titolIDFins = __toClone.titolIDFins;
    this.missatgeIDDesde = __toClone.missatgeIDDesde;
    this.missatgeIDFins = __toClone.missatgeIDFins;
    this.frontPluginIDDesde = __toClone.frontPluginIDDesde;
    this.frontPluginIDFins = __toClone.frontPluginIDFins;
    this.ajuda = __toClone.ajuda;
    this.entitatIDDesde = __toClone.entitatIDDesde;
    this.entitatIDFins = __toClone.entitatIDFins;
    this.mapOfTraduccioForTitolID = __toClone.mapOfTraduccioForTitolID;
    this.mapOfTraduccioForMissatgeID = __toClone.mapOfTraduccioForMissatgeID;
    this.mapOfPluginForFrontPluginID = __toClone.mapOfPluginForFrontPluginID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CODI }));
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
  private Map<String, String> mapOfTraduccioForTitolID;

  public Map<String, String> getMapOfTraduccioForTitolID() {
    return this.mapOfTraduccioForTitolID;
  }

  public void setMapOfTraduccioForTitolID(Map<String, String> mapOfTraduccioForTitolID) {
    this.mapOfTraduccioForTitolID = mapOfTraduccioForTitolID;
  }



  private Map<String, String> mapOfTraduccioForMissatgeID;

  public Map<String, String> getMapOfTraduccioForMissatgeID() {
    return this.mapOfTraduccioForMissatgeID;
  }

  public void setMapOfTraduccioForMissatgeID(Map<String, String> mapOfTraduccioForMissatgeID) {
    this.mapOfTraduccioForMissatgeID = mapOfTraduccioForMissatgeID;
  }



  private Map<String, String> mapOfPluginForFrontPluginID;

  public Map<String, String> getMapOfPluginForFrontPluginID() {
    return this.mapOfPluginForFrontPluginID;
  }

  public void setMapOfPluginForFrontPluginID(Map<String, String> mapOfPluginForFrontPluginID) {
    this.mapOfPluginForFrontPluginID = mapOfPluginForFrontPluginID;
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
