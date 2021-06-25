package org.fundaciobit.pluginsib.carpetafront.pinbalayudas.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Estado")
public class Estado {

	private String codigoEstado; 
	
	private String codigoEstadoSecundario;
	
	private String literalError;
	
	public Estado() {}

	@XmlElement(name="CodigoEstado")
	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	@XmlElement(name="CodigoEstadoSecundario")
	public String getCodigoEstadoSecundario() {
		return codigoEstadoSecundario;
	}

	public void setCodigoEstadoSecundario(String codigoEstadoSecundario) {
		this.codigoEstadoSecundario = codigoEstadoSecundario;
	}

	@XmlElement(name="LiteralError")
	public String getLiteralError() {
		return literalError;
	}

	public void setLiteralError(String literalError) {
		this.literalError = literalError;
	};
	
	
	
}
