package es.caib.carpeta.front.form;

import java.util.Date;

public class FechaBusqueda {

    private Date fechaInicio;
    private Date fechaFin;

    public FechaBusqueda() {
        fechaInicio = new Date();
        fechaFin = new Date();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
