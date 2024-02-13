package org.fundaciobit.pluginsib.carpetafront.notib;


import java.util.Date;

import org.fundaciobit.pluginsib.carpetafront.notib2client.model.TransmissioV2;
import org.joda.time.DateTime;

public class ComunicacioNotificacio extends TransmissioV2 {

    private TransmissioV2 transmissio;
    // Afegits
    private String tipus;
    private DateTime data;

    public ComunicacioNotificacio() {
        super();
    }

    public ComunicacioNotificacio(TransmissioV2 transmissio, DateTime data, String tipus) {
        super();
        this.transmissio = transmissio;
        this.data = data;
        this.tipus = tipus;
    }
    
    
    public ComunicacioNotificacio(TransmissioV2 transmissio, Date data, String tipus) {
        super();
        this.transmissio = transmissio;
        this.data = new DateTime(data);
        this.tipus = tipus;
    }

    public TransmissioV2 getTransmissio() {
        return transmissio;
    }

    public void setTransmissio(TransmissioV2 transmissio) {
        this.transmissio = transmissio;
    }

    public DateTime getData() {
        return data;
    }

    public void setData(DateTime data) {
        this.data = data;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

}