package org.fundaciobit.pluginsib.carpetafront.apodera;

import org.fundaciobit.pluginsib.carpetafront.apodera.api.OrganismoType;
import org.fundaciobit.pluginsib.carpetafront.apodera.api.ProcedimientoType;

import java.util.List;

public class Apoderamiento {

    private String tipus;
    private String subtipus;
    private String estat;
    private String apoderado;
    private String vigencia;
    private String ambit;
    private String descEstat;
    private List<ProcedimientoType> procediments;
    private List<OrganismoType> organismes;

    public Apoderamiento() {
        super();
    }

    public Apoderamiento(String tipus, String subtipus, String estat, String apoderado, String vigencia, String ambit,
                         String descEstat, List<ProcedimientoType> procediments, List<OrganismoType> organismes) {
        super();
        this.tipus = tipus;
        this.subtipus = subtipus;
        this.estat = estat;
        this.apoderado = apoderado;
        this.vigencia = vigencia;
        this.ambit = ambit;
        this.descEstat = descEstat;
        this.procediments = procediments;
        this.organismes = organismes;
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

    public List<ProcedimientoType> getProcediments() {
        return procediments;
    }

    public void setProcediments(List<ProcedimientoType> procediments) {this.procediments = procediments; }

    public List<OrganismoType> getOrganismes() {
        return organismes;
    }

    public void setOrganismes(List<OrganismoType> organismes) {this.organismes = organismes; }

}
