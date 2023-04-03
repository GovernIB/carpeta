package org.fundaciobit.pluginsib.carpetafront.notificacionssistra;

import java.util.Date;

import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;

/**
 * 
 * @author anadal
 *
 */

public class NotificacioSistraItem {

    String descripcion;
    String url;
    Date fecha;

    public NotificacioSistraItem() {
    }

    public NotificacioSistraItem(ElementoExpediente src) {
        this.descripcion = src.getDescripcion();
        this.url = src.getUrl();
        this.fecha = src.getFecha().toGregorianCalendar().getTime();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
