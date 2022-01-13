package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PeriodoInscripcion")
public class PeriodoInscripcion {

	// format: yyyy-MM-dd
	private String desde; 
	
	private ArrayList<MotivoInscripcion> motivoInscripcion = new ArrayList<MotivoInscripcion>();
	
	public PeriodoInscripcion() {}

	@XmlElement(name="Desde")
	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	@XmlElement(name="MotivoInscripcion")
	public ArrayList<MotivoInscripcion> getMotivoInscripcion() {
		return motivoInscripcion;
	}

	public void setMotivoInscripcion(ArrayList<MotivoInscripcion> motivoInscripcion) {
		this.motivoInscripcion = motivoInscripcion;
	}

	
	
	
	
}
