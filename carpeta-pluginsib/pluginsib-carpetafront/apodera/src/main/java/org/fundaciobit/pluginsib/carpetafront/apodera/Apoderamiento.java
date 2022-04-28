package org.fundaciobit.pluginsib.carpetafront.apodera;

public class Apoderamiento {

    private String tipus;
    private String estat;
    private String apoderado;
    private String vigencia;

    public Apoderamiento() {
        super();
    }

    public Apoderamiento(String tipus, String estat, String apoderado, String vigencia) {
        super();
        this.tipus = tipus;
        this.estat = estat;
        this.apoderado = apoderado;
        this.vigencia = vigencia;

    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
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

}
