package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DatosAdicionalesTitularRespuesta")
public class DatosAdicionalesTitularRespuesta {

	// DD/MM/AAAA
	private String fechaNacimiento;

	@XmlElement(name="FechaNacimiento")
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	} 
	
	public DatosAdicionalesTitularRespuesta() {
	}

}
