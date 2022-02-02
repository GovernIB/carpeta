package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Direccion")
public class Direccion {

	private ArrayList<Via> via = new ArrayList<Via>();
	private ArrayList<Numero> numero = new ArrayList<Numero>();
	private ArrayList<NumeroSuperior> numeroSuperior = new ArrayList<NumeroSuperior>();
	private String kmt;
	private String hmt;
	private String bloque;
	private String portal;
	private String escalera;
	private String planta;
	private String puerta;
	private String codigoPostal;
	
	public Direccion() {}

	@XmlElement(name="Via")
	public ArrayList<Via> getVia() {
		return via;
	}

	public void setVia(ArrayList<Via> via) {
		this.via = via;
	}

	@XmlElement(name="Numero")
	public ArrayList<Numero> getNumero() {
		return numero;
	}

	public void setNumero(ArrayList<Numero> numero) {
		this.numero = numero;
	}

	@XmlElement(name="NumeroSuperior")
	public ArrayList<NumeroSuperior> getNumeroSuperior() {
		return numeroSuperior;
	}

	public void setNumeroSuperior(ArrayList<NumeroSuperior> numeroSuperior) {
		this.numeroSuperior = numeroSuperior;
	}

	@XmlElement(name="Kmt")
	public String getKmt() {
		return kmt;
	}

	public void setKmt(String kmt) {
		this.kmt = kmt;
	}

	@XmlElement(name="Hmt")
	public String getHmt() {
		return hmt;
	}

	public void setHmt(String hmt) {
		this.hmt = hmt;
	}

	@XmlElement(name="Bloque")
	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	@XmlElement(name="Portal")
	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	@XmlElement(name="Escalera")
	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	@XmlElement(name="Planta")
	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	@XmlElement(name="Puerta")
	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	@XmlElement(name="CodPostal")
	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	
	
	
	
}
