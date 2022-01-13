package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ClaveHojaPadronal")
public class ClaveHojaPadronal {

	private String distrito;
	
	private String seccion;
	
	private String hoja;
	
	public ClaveHojaPadronal() {}

	@XmlElement(name="Distrito")
	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	@XmlElement(name="Seccion")
	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	@XmlElement(name="Hoja")
	public String getHoja() {
		return hoja;
	}

	public void setHoja(String hoja) {
		this.hoja = hoja;
	}
	
}
