package org.fundaciobit.pluginsib.carpetafront.notib;

import es.caib.notib.client.domini.consulta.TransmissioV2;

import java.util.Date;

public class ComunicacioNotificacio extends TransmissioV2 {

    private TransmissioV2 transmissio;
    // Afegits
    private String tipus;
    private Date data;

    public ComunicacioNotificacio() {
        super();
    }

    public ComunicacioNotificacio(TransmissioV2 transmissio, Date data, String tipus) {
        super();
        this.transmissio = transmissio;
        this.data = data;
        this.tipus = tipus;
    }

    public TransmissioV2 getTransmissio() {
        return transmissio;
    }

    public void setTransmissio(TransmissioV2 transmissio) {
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