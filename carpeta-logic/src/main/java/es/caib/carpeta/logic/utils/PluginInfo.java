package es.caib.carpeta.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class PluginInfo {

    long pluginID;

    String nom;

    String descripcio;

    public PluginInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PluginInfo(long pluginID, String nom, String descripcio) {
        super();
        this.nom = nom;
        this.descripcio = descripcio;
        this.pluginID = pluginID;
    }

    public long getPluginID() {
        return pluginID;
    }

    public void setPluginID(long pluginID) {
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

}
