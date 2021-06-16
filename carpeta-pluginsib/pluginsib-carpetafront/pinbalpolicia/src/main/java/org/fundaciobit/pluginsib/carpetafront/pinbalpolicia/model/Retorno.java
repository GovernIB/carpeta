package org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Retorno")
public class Retorno {
	
	private ArrayList<Estado> estado = new ArrayList<>();
	
	private ArrayList<DatosTitular> datosTitular = new ArrayList<>();
	
	public Retorno(){}

	@XmlElement(name="Estado")
	public ArrayList<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}

	@XmlElement(name="DatosTitular")
	public ArrayList<DatosTitular> getDatosTitular() {
		return datosTitular;
	}

	public void setDatosTitular(ArrayList<DatosTitular> datosTitular) {
		this.datosTitular = datosTitular;
	};
	
	

}
