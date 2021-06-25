package org.fundaciobit.pluginsib.carpetafront.pinbalayudas.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Retorno")
public class Retorno {
	
	private ArrayList<Estado> estado = new ArrayList<>();
	
	public Retorno(){}

	@XmlElement(name="Estado")
	public ArrayList<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}	
	

}
