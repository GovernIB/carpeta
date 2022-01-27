package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Resultado")
public class Resultado {
	
	private String nombre;
	private String particula1;
	private String apellido1;
	private String particula2;
	private String apellido2;
	
	// format: yyyy-MM-dd
	private String fechaNacimiento;
	
	private ArrayList<Documentacion> documentacion = new ArrayList<Documentacion>(); 
	
	private ArrayList<Domicilio> historicoDomicilios = new ArrayList<Domicilio>();
	
	private String fechaExpedicion;
	
	public Resultado() {}

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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@XmlElement(name="Documentacion")
	public ArrayList<Documentacion> getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(ArrayList<Documentacion> documentacion) {
		this.documentacion = documentacion;
	}

	@XmlElement(name="HistoricoDomicilios")
	public ArrayList<Domicilio> getHistoricoDomicilios() {
		return historicoDomicilios;
	}

	public void setHistoricoDomicilios(ArrayList<Domicilio> historicoDomicilios) {
		this.historicoDomicilios = historicoDomicilios;
	}

	@XmlElement(name="FechaExpedicion")
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

}
