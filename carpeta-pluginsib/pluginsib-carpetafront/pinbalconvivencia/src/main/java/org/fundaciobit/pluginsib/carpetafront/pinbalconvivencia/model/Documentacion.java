package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Documentacion")
public class Documentacion {

	private String tipo;
	private String valor;
	
	public Documentacion() {}

	@XmlElement(name="Tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@XmlElement(name="Valor")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	/*
	@XmlType(name = "Tipo")
	@XmlEnum
	public enum Tipo {
		NIF,
		DNI,
		NIE,
		PASAPORTE;
		
		public String value() {
	        return name();
	    }

	    public static Tipo fromValue(String v) {
	        return valueOf(v);
	    }
	}
	*/
	
}
