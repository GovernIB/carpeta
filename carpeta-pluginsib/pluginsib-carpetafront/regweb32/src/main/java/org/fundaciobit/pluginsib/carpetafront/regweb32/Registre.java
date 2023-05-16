package org.fundaciobit.pluginsib.carpetafront.regweb32;

import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public class Registre {

    private String numeroRegistro;

    private Date fechaRegistro;

    private String extracto;

    private String estado;

    private String denominacionDestino;

    public Registre() {

    }
    

    public Registre(String numeroRegistro, Date fechaRegistro, String extracto, String estado,
            String denominacionDestino) {
        super();
        this.numeroRegistro = numeroRegistro;
        this.fechaRegistro = fechaRegistro;
        this.extracto = extracto;
        this.estado = estado;
        this.denominacionDestino = denominacionDestino;
    }


    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getExtracto() {
        return extracto;
    }

    public void setExtracto(String extracto) {
        this.extracto = extracto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDenominacionDestino() {
        return denominacionDestino;
    }

    public void setDenominacionDestino(String denominacionDestino) {
        this.denominacionDestino = denominacionDestino;
    }

}
