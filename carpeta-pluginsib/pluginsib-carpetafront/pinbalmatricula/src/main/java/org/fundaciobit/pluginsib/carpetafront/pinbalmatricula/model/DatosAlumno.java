package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="DatosAlumno")
public class DatosAlumno {
	
	private IdTitular idTitular;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;
	
	@XmlElement(name="IdTitular")
	public IdTitular getIdTitular() {
		return idTitular;
	}
	public void setIdTitular(IdTitular idTitular) {
		this.idTitular = idTitular;
	}

	@XmlElement(name="Nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement(name="Apellido1")
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	@XmlElement(name="Apellido2")
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	@XmlElement(name="FechaNacimiento")
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

}
