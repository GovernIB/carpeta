package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DatosEspecificos")
public class DatosEspecificos {
	
	public DatosEspecificos() { super(); }
	
	private ArrayList<Retorno> retorno = new ArrayList<>();
	
	@XmlElement(name="Retorno")
	public ArrayList<Retorno> getRetorno() {
		return retorno;
	}

	public void setRetorno(ArrayList<Retorno> retorno) {
		this.retorno = retorno;
	}	
	
}
