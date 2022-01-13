package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Resultado")
public class Resultado {

	private ArrayList<ClaveHojaPadronal> claveHojaPadronal = new ArrayList<ClaveHojaPadronal>();
	
	private ArrayList<Domicilio> domicilio = new ArrayList<Domicilio>();
	
	private ArrayList<Persona> personas = new ArrayList<Persona>();
	
	// format: yyyy-MM-dd
	private String fechaExpedicion; 
	
	public Resultado() {}

	@XmlElement(name="ClaveHojaPadronal")
	public ArrayList<ClaveHojaPadronal> getClaveHojaPadronal() {
		return claveHojaPadronal;
	}

	public void setClaveHojaPadronal(ArrayList<ClaveHojaPadronal> claveHojaPadronal) {
		this.claveHojaPadronal = claveHojaPadronal;
	}

	@XmlElement(name="Domicilio")
	public ArrayList<Domicilio> getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(ArrayList<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}

	@XmlElement(name="Personas")
	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	@XmlElement(name="FechaExpedicion")
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	
	
	
	
	
	
	
}
