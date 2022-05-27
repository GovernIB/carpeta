package org.fundaciobit.pluginsib.carpetafront.apodera;

public class Apoderamiento {

    private String tipus;
    private String subtipus;
    private String estat;
    private String apoderado;
    private String vigencia;
    private String ambit;
    private String descEstat;
    private String procediment;
    private String organisme;

    public Apoderamiento() {
        super();
    }

    public Apoderamiento(String tipus, String subtipus, String estat, String apoderado, String vigencia, String ambit,
                         String descEstat, String procediment, String organisme) {
        super();
        this.tipus = tipus;
        this.subtipus = subtipus;
        this.estat = estat;
        this.apoderado = apoderado;
        this.vigencia = vigencia;
        this.ambit = ambit;
        this.descEstat = descEstat;
        this.procediment = procediment;
        this.organisme = organisme;
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

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {this.ambit = ambit; }

    public String getDescEstat() {
        return descEstat;
    }

    public void setDescEstat(String descEstat) {this.descEstat = descEstat; }

    public String getProcediment() {
        return procediment;
    }

    public void setProcediment(String procediment) {this.procediment = procediment; }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {this.organisme = organisme; }

}
