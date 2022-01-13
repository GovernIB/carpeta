package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Via")
public class Via {

	private String codigo; 
	private String tipo;
	private String nombre;
	
	public Via() {}

	@XmlElement(name="Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlElement(name="Tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	
	
	
}
