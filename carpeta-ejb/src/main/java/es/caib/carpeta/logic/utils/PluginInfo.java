package es.caib.carpeta.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class PluginInfo {

    protected String pluginID;
    protected String nom;
    protected String descripcio;
    protected String context;
    //protected boolean reactComponent;
    protected Long gravetat;
    protected String missatge;
    protected int order;
    protected int tipusPlugin;

    public PluginInfo() {
        super();
    }

    public PluginInfo(String pluginID, String nom, String descripcio, String context, /*boolean reactComponent*/
            Long gravetat, String missatge, int order, int tipusPlugin) {
        super();
        this.nom = nom;
        this.descripcio = descripcio;
        this.context = context;
        this.pluginID = pluginID;
        //this.reactComponent = reactComponent;
        this.gravetat = gravetat;
        this.missatge = missatge;
        this.order = order;
        this.tipusPlugin = tipusPlugin;
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

    public int getTipusPlugin() {
        return tipusPlugin;
    }

    public void setTipusPlugin(int tipusPlugin) {
        this.tipusPlugin = tipusPlugin;
    }

}
