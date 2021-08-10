package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Retorno")
public class Retorno {
	
	private ArrayList<Estado> estado = new ArrayList<>();
	
	private ArrayList<DatosAdicionalesTitularRespuesta> datosAdicionalesTitularRespuesta = new ArrayList<>();
	
	private ArrayList<CertificadoDatosDiscapacidad> certificadoDatosDiscapacidad = new ArrayList<>();
	
	public Retorno(){}

	@XmlElement(name="Estado")
	public ArrayList<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}

	@XmlElement(name="DatosAdicionalesTitularRespuesta")
	public ArrayList<DatosAdicionalesTitularRespuesta> getDatosAdicionalesTitularRespuesta() {
		return datosAdicionalesTitularRespuesta;
	}

	public void setDatosAdicionalesTitularRespuesta(ArrayList<DatosAdicionalesTitularRespuesta> datosAdicionalesTitularRespuesta) {
		this.datosAdicionalesTitularRespuesta = datosAdicionalesTitularRespuesta;
	}

	@XmlElement(name="CertificadoDatosDiscapacidad")
	public ArrayList<CertificadoDatosDiscapacidad> getCertificadoDatosDiscapacidad() {
		return certificadoDatosDiscapacidad;
	}

	public void setCertificadoDatosDiscapacidad(ArrayList<CertificadoDatosDiscapacidad> certificadoDatosDiscapacidad) {
		this.certificadoDatosDiscapacidad = certificadoDatosDiscapacidad;
	}
	
}
