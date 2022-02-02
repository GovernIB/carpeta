package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Domicilio")
public class Domicilio {

	private ArrayList<ClaveHojaPadronal> claveHojaPadronal = new ArrayList<ClaveHojaPadronal>();
	private ArrayList<ProvinciaRespuesta> provinciaRespuesta = new ArrayList<ProvinciaRespuesta>();
	private ArrayList<MunicipioRespuesta> municipioRespuesta = new ArrayList<MunicipioRespuesta>();
	private ArrayList<EntColectiva> entColectiva = new ArrayList<EntColectiva>();
	private ArrayList<EntSingular> entSingular = new ArrayList<EntSingular>();
	private ArrayList<Nucleo> nucleo = new ArrayList<Nucleo>();
	private String codUnidadPoblacional;
	private ArrayList<Direccion> direccion = new ArrayList<Direccion>();
	private String desde;
	private ArrayList<MotivoInscripcion> motivoInscripcion = new ArrayList<MotivoInscripcion>();
	private String hasta;
	private ArrayList<MotivoBaja> motivoBaja = new ArrayList<MotivoBaja>();
	
	@XmlElement(name="ClaveHojaPadronal")
	public ArrayList<ClaveHojaPadronal> getClaveHojaPadronal() {
		return claveHojaPadronal;
	}

	public void setClaveHojaPadronal(ArrayList<ClaveHojaPadronal> claveHojaPadronal) {
		this.claveHojaPadronal = claveHojaPadronal;
	}

	@XmlElement(name="ProvinciaRespuesta")
	public ArrayList<ProvinciaRespuesta> getProvinciaRespuesta() {
		return provinciaRespuesta;
	}

	public void setProvinciaRespuesta(ArrayList<ProvinciaRespuesta> provinciaRespuesta) {
		this.provinciaRespuesta = provinciaRespuesta;
	}

	@XmlElement(name="MunicipioRespuesta")
	public ArrayList<MunicipioRespuesta> getMunicipioRespuesta() {
		return municipioRespuesta;
	}

	public void setMunicipioRespuesta(ArrayList<MunicipioRespuesta> municipioRespuesta) {
		this.municipioRespuesta = municipioRespuesta;
	}

	@XmlElement(name="EntColectiva")
	public ArrayList<EntColectiva> getEntColectiva() {
		return entColectiva;
	}

	public void setEntColectiva(ArrayList<EntColectiva> entColectiva) {
		this.entColectiva = entColectiva;
	}

	@XmlElement(name="EntSingular")
	public ArrayList<EntSingular> getEntSingular() {
		return entSingular;
	}

	public void setEntSingular(ArrayList<EntSingular> entSingular) {
		this.entSingular = entSingular;
	}

	@XmlElement(name="Nucleo")
	public ArrayList<Nucleo> getNucleo() {
		return nucleo;
	}

	public void setNucleo(ArrayList<Nucleo> nucleo) {
		this.nucleo = nucleo;
	}

	@XmlElement(name="CodUnidadPoblacional")
	public String getCodUnidadPoblacional() {
		return codUnidadPoblacional;
	}

	public void setCodUnidadPoblacional(String codUnidadPoblacional) {
		this.codUnidadPoblacional = codUnidadPoblacional;
	}

	@XmlElement(name="Direccion")
	public ArrayList<Direccion> getDireccion() {
		return direccion;
	}

	public void setDireccion(ArrayList<Direccion> direccion) {
		this.direccion = direccion;
	}

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

	@XmlElement(name="Hasta")
	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	@XmlElement(name="MotivoBaja")
	public ArrayList<MotivoBaja> getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(ArrayList<MotivoBaja> motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	
	public Domicilio() {}

}
