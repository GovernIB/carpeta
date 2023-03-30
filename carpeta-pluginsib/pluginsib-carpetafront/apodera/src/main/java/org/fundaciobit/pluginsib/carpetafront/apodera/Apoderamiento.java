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
    private int estat;
    private String estatNom;
    private String estatDesc;
    private String apoderado;
    private String poderdante;
    private String vigencia;
    private String ambit;

    private List<String> procediments;
    private List<String> organismes;
    private List<String> tramits;
    private transient long dataFinalVigencia;

    public Apoderamiento() {
        super();
    }

    public Apoderamiento(String tipus, String subtipus, int estat, String estatNom, String estatDesc, String apoderado,
            String poderdante, String vigencia, String ambit, List<String> procediments, List<String> organismes,
            List<String> tramits, long dataFinalVigencia) {
        super();
        this.tipus = tipus;
        this.subtipus = subtipus;
        this.estat = estat;
        this.estatNom = estatNom;
        this.estatDesc = estatDesc;

        this.apoderado = apoderado;
        this.poderdante = poderdante;
        this.vigencia = vigencia;
        this.ambit = ambit;
        this.estatDesc = estatDesc;
        this.procediments = procediments;
        this.organismes = organismes;
        this.tramits = tramits;
        this.dataFinalVigencia = dataFinalVigencia;
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

    public int getEstat() {
        return estat;
    }

    public void setEstat(int estat) {
        this.estat = estat;
    }

    public String getEstatNom() {
        return estatNom;
    }

    public void setEstatNom(String estatNom) {
        this.estatNom = estatNom;
    }

    public String getEstatDesc() {
        return estatDesc;
    }

    public void setEstatDesc(String estatDesc) {
        this.estatDesc = estatDesc;
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
