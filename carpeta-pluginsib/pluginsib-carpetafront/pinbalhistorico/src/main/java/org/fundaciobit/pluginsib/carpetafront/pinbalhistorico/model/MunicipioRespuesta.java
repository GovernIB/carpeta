package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MunicipioRespuesta")
public class MunicipioRespuesta {

	private String codigo; 
	private String nombre;
	
	public MunicipioRespuesta() {}

	@XmlElement(name="Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlElement(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public MunicipioRespuesta(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
}
