package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Numero")
public class Numero {

	private String calificador; 
	private String valor;
	
	public Numero() {}

	@XmlElement(name="Calificador")
	public String getCalificador() {
		return calificador;
	}

	public void setCalificador(String calificador) {
		this.calificador = calificador;
	}

	@XmlElement(name="Valor")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
