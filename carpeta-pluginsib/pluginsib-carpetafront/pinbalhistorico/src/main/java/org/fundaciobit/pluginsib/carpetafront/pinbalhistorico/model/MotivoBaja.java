package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MotivoBaja")
public class MotivoBaja {

	private String codigoVariacion; 
	
	private String causaVariacion;
	
	private String descripcion;
	
	public MotivoBaja() {}

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

	public MotivoBaja(String codigoVariacion, String causaVariacion, String descripcion) {
		super();
		this.codigoVariacion = codigoVariacion;
		this.causaVariacion = causaVariacion;
		this.descripcion = descripcion;
	}

}
