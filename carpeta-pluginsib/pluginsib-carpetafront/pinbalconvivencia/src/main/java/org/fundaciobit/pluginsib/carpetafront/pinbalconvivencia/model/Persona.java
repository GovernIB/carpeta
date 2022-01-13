package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Persona")
public class Persona {

	private String nombre;
	private String particula1;
	private String apellido1;
	private String particula2;
	private String apellido2;
	private Date fechaNacimiento;
	private ArrayList<Documentacion> documentacion = new ArrayList<Documentacion>();
	private String nia;
	private ArrayList<PeriodoInscripcion> periodoInscripcion = new ArrayList<PeriodoInscripcion>();
	
	public Persona() {}

	@XmlElement(name="PeriodoInscripcion")
	public ArrayList<PeriodoInscripcion> getPeriodoInscripcion() {
		return periodoInscripcion;
	}

	public void setPeriodoInscripcion(ArrayList<PeriodoInscripcion> periodoInscripcion) {
		this.periodoInscripcion = periodoInscripcion;
	}

	@XmlElement(name="NIA")
	public String getNia() {
		return nia;
	}

	public void setNia(String nia) {
		this.nia = nia;
	}

	@XmlElement(name="Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement(name="Particula1")
	public String getParticula1() {
		return particula1;
	}

	public void setParticula1(String particula1) {
		this.particula1 = particula1;
	}

	@XmlElement(name="Apellido1")
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@XmlElement(name="Particula2")
	public String getParticula2() {
		return particula2;
	}

	public void setParticula2(String particula2) {
		this.particula2 = particula2;
	}

	@XmlElement(name="Apellido2")
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@XmlElement(name="FechaNacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@XmlElement(name="Documentacion")
	public ArrayList<Documentacion> getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(ArrayList<Documentacion> documentacion) {
		this.documentacion = documentacion;
	}
	
}
