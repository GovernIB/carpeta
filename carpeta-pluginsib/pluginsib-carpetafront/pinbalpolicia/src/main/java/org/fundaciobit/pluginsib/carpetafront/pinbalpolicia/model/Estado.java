package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Estado")
public class Estado {

	private String codigoEstado; 
	
	private String literalError;
	
	public Estado() {}

	@XmlElement(name="CodigoEstado")
	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	@XmlElement(name="LiteralError")
	public String getLiteralError() {
		return literalError;
	}

	public void setLiteralError(String literalError) {
		this.literalError = literalError;
	};
	
	
	
}
