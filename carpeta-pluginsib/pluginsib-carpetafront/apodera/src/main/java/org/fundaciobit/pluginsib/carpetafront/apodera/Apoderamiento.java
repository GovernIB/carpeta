package org.fundaciobit.pluginsib.carpetafront.apodera;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class Apoderamiento {

    private String tipus;
    private String subtipus;
    private String estat;
    private String apoderado;
    private String poderdante;
    private String vigencia;
    private String ambit;
    private String descEstat;
    private List<String> procediments;
    private List<String> organismes;
    private List<String> tramits;
    private transient long dataFinalVigencia;

    public Apoderamiento() {
        super();
    }

    public Apoderamiento(String tipus, String subtipus, String estat, String apoderado, String poderdante,
            String vigencia, String ambit, String descEstat, List<String> procediments, List<String> organismes,
            List<String> tramits) {
        super();
        this.tipus = tipus;
        this.subtipus = subtipus;
        this.estat = estat;
        this.apoderado = apoderado;
        this.poderdante = poderdante;
        this.vigencia = vigencia;
        this.ambit = ambit;
        this.descEstat = descEstat;
        this.procediments = procediments;
        this.organismes = organismes;
        this.tramits = tramits;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getSubtipus() {
        return subtipus;
    }

    public void setSubtipus(String subtipus) {
        this.subtipus = subtipus;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getApoderao() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getPoderdante() {
        return poderdante;
    }

    public void setPoderdante(String poderdante) {
        this.poderdante = poderdante;
    }

    public String getApoderado() {
        return apoderado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }

    public String getDescEstat() {
        return descEstat;
    }

    public void setDescEstat(String descEstat) {
        this.descEstat = descEstat;
    }

    public List<String> getProcediments() {
        return procediments;
    }

    public void setProcediments(List<String> procediments) {
        this.procediments = procediments;
    }

    public List<String> getOrganismes() {
        return organismes;
    }

    public void setOrganismes(List<String> organismes) {
        this.organismes = organismes;
    }

    public List<String> getTramits() {
        return tramits;
    }

    public void setTramits(List<String> tramits) {
        this.tramits = tramits;
    }

    public long getDataFinalVigencia() {
        return dataFinalVigencia;
    }

    public void setDataFinalVigencia(long dataFinalVigencia) {
        this.dataFinalVigencia = dataFinalVigencia;
    }

}
