package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="DatosTitular")
public class DatosTitular {
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nacionalidad;
	private String sexo;
	private String nombrePadre;
	private String nombreMadre;
	private String fechaCaducidad;
	private ArrayList<DatosNacimiento> datosNacimiento = new ArrayList<>();
	
	@XmlElement(name="DatosNacimiento")
	public ArrayList<DatosNacimiento> getDatosNacimiento() {
		return datosNacimiento;
	}
	public void setDatosNacimiento(ArrayList<DatosNacimiento> datosNacimiento) {
		this.datosNacimiento = datosNacimiento;
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
	
	@XmlElement(name="Nacionalidad")
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad.replace("A�?A", "AÑA");
	}
	
	@XmlElement(name="Sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@XmlElement(name="NombrePadre")
	public String getNombrePadre() {
		return nombrePadre;
	}
	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}
	
	@XmlElement(name="NombreMadre")
	public String getNombremadre() {
		return nombreMadre;
	}
	public void setNombremadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}
	
	@XmlElement(name="FechaCaducidad")
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	

}
