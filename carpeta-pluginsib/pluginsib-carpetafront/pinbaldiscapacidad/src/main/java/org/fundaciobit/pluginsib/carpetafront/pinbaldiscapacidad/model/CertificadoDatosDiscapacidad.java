package org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CertificadoDatosDiscapacidad")
public class CertificadoDatosDiscapacidad {

	private String codigoComunidadAutonoma; 
	
	private String codigoProvincia;
	
	private String expediente;
	
	private String respuestaDependencia;
	
	private String respuestaAcompananteTPublico;
	
	private String gradoDiscapacidad;
	
	private String fechaEfectos;
	
	private String fechaRevision;
	
	private String validezPermanente;
	
	ArrayList<RespuestaMovilidad> respuestaMovilidad = new ArrayList<RespuestaMovilidad>();
	
	// FISICA, PSIQUICA o SENSORIAL
	private String tiposDiscapacidad;
	
	public CertificadoDatosDiscapacidad() {	}

	@XmlElement(name="CodigoComunidadAutonoma")
	public String getCodigoComunidadAutonoma() {
		return codigoComunidadAutonoma;
	}

	public void setCodigoComunidadAutonoma(String codigoComunidadAutonoma) {
		this.codigoComunidadAutonoma = codigoComunidadAutonoma;
	}

	@XmlElement(name="CodigoProvincia")
	public String getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	@XmlElement(name="Expediente")
	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	@XmlElement(name="RespuestaDependencia")
	public String getRespuestaDependencia() {
		return respuestaDependencia;
	}

	public void setRespuestaDependencia(String respuestaDependencia) {
		this.respuestaDependencia = respuestaDependencia;
	}

	@XmlElement(name="RespuestaAcompananteTPublico")
	public String getRespuestaAcompananteTPublico() {
		return respuestaAcompananteTPublico;
	}

	public void setRespuestaAcompananteTPublico(String respuestaAcompananteTPublico) {
		this.respuestaAcompananteTPublico = respuestaAcompananteTPublico;
	}

	@XmlElement(name="GradoDiscapacidad")
	public String getGradoDiscapacidad() {
		return gradoDiscapacidad;
	}

	public void setGradoDiscapacidad(String gradoDiscapacidad) {
		this.gradoDiscapacidad = gradoDiscapacidad;
	}

	@XmlElement(name="FechaEfectos")
	public String getFechaEfectos() {
		return fechaEfectos;
	}

	public void setFechaEfectos(String fechaEfectos) {
		this.fechaEfectos = fechaEfectos;
	}

	@XmlElement(name="FechaRevision")
	public String getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(String fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	@XmlElement(name="ValidezPermanente")
	public String getValidezPermanente() {
		return validezPermanente;
	}

	public void setValidezPermanente(String validezPermanente) {
		this.validezPermanente = validezPermanente;
	}

	@XmlElement(name="RespuestaMovilidad")
	public ArrayList<RespuestaMovilidad> getRespuestaMovilidad() {
		return respuestaMovilidad;
	}

	public void setRespuestaMovilidad(ArrayList<RespuestaMovilidad> respuestaMovilidad) {
		this.respuestaMovilidad = respuestaMovilidad;
	}

	@XmlElement(name="TiposDiscapacidad")
	public String getTiposDiscapacidad() {
		return tiposDiscapacidad;
	}

	public void setTiposDiscapacidad(String tiposDiscapacidad) {
		this.tiposDiscapacidad = tiposDiscapacidad;
	}

}
