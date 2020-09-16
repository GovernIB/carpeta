package es.caib.carpeta.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class PluginInfo {

    String pluginID;

    String nomCa;
    String nomEs;
    String nomEn;

    String descripcioCa;
    String descripcioEs;
    String descripcioEn;

    public PluginInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PluginInfo(String pluginID, String nomCa, String nomEs, String nomEn, String descripcioCa, String descripcioEs, String descripcioEn) {
        super();
        this.nomCa = nomCa;
        this.nomEs = nomEs;
        this.nomEn = nomEn;
        this.descripcioCa = descripcioCa;
        this.descripcioEs = descripcioEs;
        this.descripcioEn = descripcioEn;
        this.pluginID = pluginID;
    }

    public String getPluginID() {
        return pluginID;
    }

    public void setPluginID(String pluginID) {
        this.pluginID = pluginID;
    }

    public String getNomCa() {
        return nomCa;
    }

    public void setNomCa(String nomCa) {
        this.nomCa = nomCa;
    }

    public String getNomEs() {
        return nomEs;
    }

    public void setNomEs(String nomEs) {
        this.nomEs = nomEs;
    }

    public String getNomEn() {
        return nomEn;
    }

    public void setNomEn(String nomEn) {
        this.nomEn = nomEn;
    }

    public String getDescripcioCa() {
        return descripcioCa;
    }

    public void setDescripcioCa(String descripcioCa) {
        this.descripcioCa = descripcioCa;
    }

    public String getDescripcioEs() {
        return descripcioEs;
    }

    public void setDescripcioEs(String descripcioEs) {
        this.descripcioEs = descripcioEs;
    }

    public String getDescripcioEn() {
        return descripcioEn;
    }

    public void setDescripcioEn(String descripcioEn) {
        this.descripcioEn = descripcioEn;
    }

}
