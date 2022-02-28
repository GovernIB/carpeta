package org.fundaciobit.pluginsib.carpetafront.notib;

import org.fundaciobit.pluginsib.carpetafront.notib.api.*;

import java.util.Date;

public class ComunicacioNotificacio extends Transmissio {

    private Transmissio transmissio;
    // Afegits
    private String tipus;
    private Date data;

    public ComunicacioNotificacio() {
        super();
    }

    public ComunicacioNotificacio(Transmissio transmissio, Date data, String tipus) {
        super();
        this.transmissio = transmissio;
        this.data = data;
        this.tipus = tipus;
    }

    public Transmissio getTransmissio() {
        return transmissio;
    }

    public void setTransmissio(Transmissio transmissio) {
        this.transmissio = transmissio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

}