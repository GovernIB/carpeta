package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MotivoInscripcion")
public class MotivoInscripcion {

	private String codigoVariacion; 
	
	private String causaVariacion;
	
	private String descripcion;
	
	public MotivoInscripcion() {}

	@XmlElement(name="CodigoVariacion")
	public String getCodigoVariacion() {
		return codigoVariacion;
	}

	public void setCodigoVariacion(String codigoVariacion) {
		this.codigoVariacion = codigoVariacion;
	}

	@XmlElement(name="CausaVariacion")
	public String getCausaVariacion() {
		return causaVariacion;
	}

	public void setCausaVariacion(String causaVariacion) {
		this.causaVariacion = causaVariacion;
	}

	@XmlElement(name="Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
