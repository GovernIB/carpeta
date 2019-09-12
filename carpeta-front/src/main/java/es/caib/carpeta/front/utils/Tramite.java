package es.caib.carpeta.front.utils;

import java.util.Date;

public class Tramite {

    private String idTramite;
    private String idSesionTramitacion;
    private Integer sistra;
    private String descripcionTramite;
    private String idioma;
    private Date fechaInicio;
    private Date fechaUltimoAcceso;



    public String getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(String idTramite) {
        this.idTramite = idTramite;
    }

    public String getIdSesionTramitacion() {
        return idSesionTramitacion;
    }

    public void setIdSesionTramitacion(String idSesionTramitacion) {
        this.idSesionTramitacion = idSesionTramitacion;
    }

    public Integer getSistra() {
        return sistra;
    }

    public void setSistra(Integer sistra) {
        this.sistra = sistra;
    }

    public String getDescripcionTramite() {
        return descripcionTramite;
    }

    public void setDescripcionTramite(String descripcionTramite) {
        this.descripcionTramite = descripcionTramite;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }
}
