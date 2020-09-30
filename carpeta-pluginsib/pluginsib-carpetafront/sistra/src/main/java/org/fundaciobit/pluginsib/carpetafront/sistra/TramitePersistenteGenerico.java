package org.fundaciobit.pluginsib.carpetafront.sistra;

import java.util.Date;

import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;

public class TramitePersistenteGenerico {
	
	private String idSesionTramitacion;
	private String idioma;
	private String idTramite;
	private int versionTramite;
	private String descripcionTramite;
	private Date fechaInicio;
	private Date fechaUltimoAcceso;
	private int versionSistra;
	
	public String getIdSesionTramitacion() {
		return idSesionTramitacion;
	}

	public void setIdSesionTramitacion(final String idSesionTramitacion) {
		this.idSesionTramitacion = idSesionTramitacion;
	}

	public String getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(final String idTramite) {
		this.idTramite = idTramite;
	}

	public int getVersionTramite() {
		return versionTramite;
	}

	public void setVersionTramite(final int versionTramite) {
		this.versionTramite = versionTramite;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getDescripcionTramite() {
		return descripcionTramite;
	}

	public void setDescripcionTramite(final String descripcionTramite) {
		this.descripcionTramite = descripcionTramite;
	}

	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(final Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(final String idioma) {
		this.idioma = idioma;
	}
	
	public int getVersionSistra() {
		return versionSistra;
	}
	
	public void setVersionSistra(final int versionSistra) {
		this.versionSistra = versionSistra;
	}
	
	public TramitePersistenteGenerico(RTramitePersistencia tramite, int versionSistra) {
		this.idSesionTramitacion = tramite.getIdSesionTramitacion();
		this.idioma = tramite.getIdioma();
		this.idTramite = tramite.getIdTramite();
		this.versionTramite = tramite.getVersionTramite();
		this.descripcionTramite = tramite.getDescripcionTramite();
		this.fechaInicio = tramite.getFechaInicio();
		this.fechaUltimoAcceso = tramite.getFechaUltimoAcceso();
		this.versionSistra = versionSistra;
	}
	
	public TramitePersistenteGenerico(TramitePersistente tramite, int versionSistra) {
		this.idSesionTramitacion = tramite.getIdSesionTramitacion();
		this.idioma = tramite.getIdioma();
		this.idTramite = tramite.getIdTramite();
		this.versionTramite = tramite.getVersionTramite();
		this.descripcionTramite = tramite.getDescripcionTramite();
		this.fechaInicio = tramite.getFechaInicio().toGregorianCalendar().getTime();
		this.fechaUltimoAcceso = tramite.getFechaUltimoAcceso().toGregorianCalendar().getTime();
		this.versionSistra = versionSistra;
	}
	
}
