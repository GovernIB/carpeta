package org.fundaciobit.pluginsib.carpetafront.expedients;


import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ExpedientInfo {

    protected String codiSia;
    protected String nomProcediment;
    protected String expedientNom;
    protected String expedientDesc;
    protected String expedientEstat;
    protected List<String> expedientOrgans;
    protected String expedientObertura;

    public ExpedientInfo() {
        super();
    }

    public ExpedientInfo(String codiSia, String nomProcediment, String expedientNom, String expedientDesc,
            String expedientEstat, List<String> expedientOrgans, String expedientObertura) {
        super();
        this.codiSia = codiSia;
        this.nomProcediment = nomProcediment;
        this.expedientNom = expedientNom;
        this.expedientDesc = expedientDesc;
        this.expedientEstat = expedientEstat;
        this.expedientOrgans = expedientOrgans;
        this.expedientObertura = expedientObertura;
    }

    public String getExpedientDesc() {
        return expedientDesc;
    }

    public void setExpedientDesc(String expedientDesc) {
        this.expedientDesc = expedientDesc;
    }

    public String getCodiSia() {
        return codiSia;
    }

    public void setCodiSia(String codiSia) {
        this.codiSia = codiSia;
    }

    public String getNomProcediment() {
        return nomProcediment;
    }

    public void setNomProcediment(String nomProcediment) {
        this.nomProcediment = nomProcediment;
    }

    public String getExpedientNom() {
        return expedientNom;
    }

    public void setExpedientNom(String expedientNom) {
        this.expedientNom = expedientNom;
    }

    public String getExpedientEstat() {
        return expedientEstat;
    }

    public void setExpedientEstat(String expedientEstat) {
        this.expedientEstat = expedientEstat;
    }

    public List<String> getExpedientOrgans() {
        return expedientOrgans;
    }

    public void setExpedientOrgans(List<String> expedientOrgans) {
        this.expedientOrgans = expedientOrgans;
    }

    public String getExpedientObertura() {
        return expedientObertura;
    }

    public void setExpedientObertura(String expedientObertura) {
        this.expedientObertura = expedientObertura;
    }

}
