package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DatosNacimiento")
public class DatosNacimiento {
	
	private String localidad; 
	private String provincia;
	private String fecha;
	
	public DatosNacimiento() {}

	@XmlElement(name="Localidad")
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@XmlElement(name="Provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@XmlElement(name="Fecha")
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
