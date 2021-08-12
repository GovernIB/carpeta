package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RespuestaMovilidad")
public class RespuestaMovilidad {

	private int puntuacion; 
	
	private String factor;
	
	public RespuestaMovilidad() {}

	@XmlElement(name="Puntuacion")
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@XmlElement(name="Factor")
	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}
	
	

}
