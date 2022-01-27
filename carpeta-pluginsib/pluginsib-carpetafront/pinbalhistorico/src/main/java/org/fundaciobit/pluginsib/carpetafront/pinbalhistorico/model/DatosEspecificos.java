package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatosEspecificos")
public class DatosEspecificos {
	
	public DatosEspecificos() { super(); }
	
	private ArrayList<Estado> estado = new ArrayList<>();
	
	private ArrayList<Resultado> resultado = new ArrayList<>();
	
	@XmlElement(name="Estado")
	public ArrayList<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}
	
	@XmlElement(name="Resultado")
	public ArrayList<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(ArrayList<Resultado> resultado) {
		this.resultado = resultado;
	}
	
}
