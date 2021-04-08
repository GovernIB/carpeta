package es.caib.carpeta.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class PluginInfo {

    String pluginID;
    String nom;
    String descripcio;
    String context;
    boolean reactComponent;
    Long gravetat;
    String missatge;
    int order;

    public PluginInfo() {
        super();
    }

    public PluginInfo(String pluginID, String nom, String descripcio, String context,
            boolean reactComponent, Long gravetat, String missatge, int order) {
        super();
        this.nom = nom;
        this.descripcio = descripcio;
        this.context = context;
        this.pluginID = pluginID;
        this.reactComponent = reactComponent;
        this.gravetat = gravetat;
        this.missatge = missatge;
        this.order = order;
    }

    public String getPluginID() {
        return pluginID;
    }

    public void setPluginID(String pluginID) {
        this.pluginID = pluginID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean isReactComponent() {
        return reactComponent;
    }

    public void setReactComponent(boolean reactComponent) {
        this.reactComponent = reactComponent;
    }

    public Long getGravetat() {
        return gravetat;
    }

    public void setGravetat(Long gravetat) {
        this.gravetat = gravetat;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

   

}
