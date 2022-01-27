package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Documentacion")
public class Documentacion {

	private String tipo; 
	
	private String valor;
	
	private String numSoporte;
	
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

	@XmlElement(name="NumSoporte")
	public String getNumSoporte() {
		return numSoporte;
	}

	public void setNumSoporte(String numSoporte) {
		this.numSoporte = numSoporte;
	}

}
