package es.caib.carpeta.front.controller;


import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.front.controller.WebUIController.EnllazInfo;
import es.caib.carpeta.front.controller.WebUIController.SeccioInfo;
import es.caib.carpeta.logic.utils.PluginInfo;


/**
 * 
 * @author anadal
 *
 */
public class ItemInfo implements Comparable<ItemInfo> {

    public static final int TIPUS_REACT_PLUGIN_PUBLIC = -2;
    public static final int TIPUS_HTML_PLUGIN_PUBLIC = -1;
    public static final int TIPUS_REACT_PLUGIN = 0;
    public static final int TIPUS_HTML_PLUGIN = 1;
    public static final int TIPUS_ENLLAZ = 2;
    public static final int TIPUS_SECCIO = 3;
    public static final int TIPUS_PSEUDOPLUGIN = 4;
    public static final int TIPUS_ENUNCLIC = 7;

    protected String id;
    protected String nom;
    protected String descripcio;
    protected String context;
    protected int tipus;
    protected int gravetat;
    protected String missatge;
    protected String url;
    protected String urllogo;

    protected int order;

    public ItemInfo(String id, String nom, String descripcio, String context, int tipus, int gravetat, String missatge,
            String url, String urllogo, int order) {
        super();
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.context = context;
        this.tipus = tipus;
        this.gravetat = gravetat;
        this.missatge = missatge;
        this.url = url;
        this.urllogo = urllogo;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTipus() {
        return tipus;
    }

    public void setTipus(int tipus) {
        this.tipus = tipus;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrllogo() {
        return urllogo;
    }

    public void setUrllogo(String urllogo) {
        this.urllogo = urllogo;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public static ItemInfo createFromPluginInfo(PluginInfo p) {
        final int tipusPlugin = p.getTipusPlugin();
        final int tipus;
        if (p.isReactComponent()) {
            if (tipusPlugin == Constants.PLUGIN_TIPUS_FRONT_PRIVAT) {
                tipus =  TIPUS_REACT_PLUGIN;
            } else {
                tipus = TIPUS_REACT_PLUGIN_PUBLIC;
            }
        } else {
            if (tipusPlugin == Constants.PLUGIN_TIPUS_FRONT_PRIVAT) {
                tipus =  TIPUS_HTML_PLUGIN;
            } else {
                tipus = TIPUS_HTML_PLUGIN_PUBLIC;
            }
        }

        return new ItemInfo(p.getPluginID(), p.getNom(), p.getDescripcio(), p.getContext(),
                tipus,
                p.getGravetat() == null ? 0 : (int) (long) p.getGravetat(), p.getMissatge(), null,                        
                        "/pluginfront/pluginicon/" + p.getPluginID(), p.getOrder());

    }

    public static ItemInfo createFromSeccioInfo(SeccioInfo s) {
        // long seccioID, String nom, String descripcio, String context, String iconaID)
        // {

        return new ItemInfo(String.valueOf(s.getSeccioID()), s.getNom(), s.getDescripcio(), s.getContext(),
                TIPUS_SECCIO, 0, null, null, s.getIconaID(), s.getOrdre());
    }

    public static ItemInfo createFromEnllazInfo(EnllazInfo e) {

        return new ItemInfo(String.valueOf(e.getEnllazID()), e.getLabel(), e.getLabelDescription(), null, TIPUS_ENLLAZ,
                0, null, e.getUrl(), e.getUrllogo(), e.getOrdre());
    }

    public static ItemInfo createFromPseuDoPlugin(EnllazInfo e) {

        return new ItemInfo(String.valueOf(e.getEnllazID()), e.getLabel(), e.getLabelDescription(), null,
                TIPUS_PSEUDOPLUGIN, 0, null, e.getUrl(), e.getUrllogo(), e.getOrdre());
    }

    public static ItemInfo createFromEnunclic(EnllazInfo e) {

        return new ItemInfo(String.valueOf(e.getEnllazID()), e.getLabel(), e.getLabelDescription(), null,
                TIPUS_ENUNCLIC, 0, null, e.getUrl(), e.getUrllogo(), e.getOrdre());
    }

    @Override
    public int compareTo(ItemInfo o2) {
        return this.order - o2.order;
    }

}
