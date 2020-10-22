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

    String reactComponent;

    int gravetat;
    String missatge;

    public PluginInfo() {
        super();
    }

    public PluginInfo(String pluginID, String nomCa, String nomEs, String nomEn, String descripcioCa,
            String descripcioEs, String descripcioEn, String reactComponent, int gravetat, String missatge) {
        super();
        this.nomCa = nomCa;
        this.nomEs = nomEs;
        this.nomEn = nomEn;
        this.descripcioCa = descripcioCa;
        this.descripcioEs = descripcioEs;
        this.descripcioEn = descripcioEn;
        this.pluginID = pluginID;
        this.reactComponent = reactComponent;
        this.gravetat = gravetat;
        this.missatge = missatge;
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

    public String isReactComponent() {
        return reactComponent;
    }

    public void setReactComponent(String reactComponent) {
        this.reactComponent = reactComponent;
    }

    public int getGravetat() {
        return gravetat;
    }

    public void setGravetat(int gravetat) {
        this.gravetat = gravetat;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

}
