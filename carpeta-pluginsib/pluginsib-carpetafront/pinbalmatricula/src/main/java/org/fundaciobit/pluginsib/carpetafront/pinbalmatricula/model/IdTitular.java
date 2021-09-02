package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="IdTitular")
public class IdTitular {

	private String tipoDocumentacion;
	private String documentacion;

	@XmlElement(name="TipoDocumentacion")
	public String getTipoDocumentacion() {
		return tipoDocumentacion;
	}
	public void setTipoDocumentacion(String tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}
	
	@XmlElement(name="Documentacion")
	public String getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

}
