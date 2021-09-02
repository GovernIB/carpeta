package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatosEspecificos")
public class DatosEspecificos {
	
	public DatosEspecificos() { super(); }
	
	private ArrayList<Respuesta> respuesta = new ArrayList<>();
	
	@XmlElement(name="Respuesta")
	public ArrayList<Respuesta> getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(ArrayList<Respuesta> respuesta) {
		this.respuesta = respuesta;
	}	
	
}
