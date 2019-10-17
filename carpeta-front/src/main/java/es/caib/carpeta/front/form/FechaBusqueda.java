package es.caib.carpeta.front.form;

import es.caib.carpeta.core.utils.DateUtils;

import java.util.Date;

public class FechaBusqueda {

    private Date fechaInicio;
    private Date fechaFin;

    public FechaBusqueda() {
        fechaInicio = DateUtils.sumarRestarDiasFecha(new Date(), -30);
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
