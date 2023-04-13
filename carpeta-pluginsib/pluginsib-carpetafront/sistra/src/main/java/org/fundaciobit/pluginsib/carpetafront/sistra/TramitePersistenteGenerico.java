package org.fundaciobit.pluginsib.carpetafront.sistra;

import java.text.SimpleDateFormat;
import java.util.Locale;

import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RTramiteFinalizado;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;

/**
 * 
 * @author anadal
 *
 */
public class TramitePersistenteGenerico { 
    
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /* Atributs compartits */
    private String descripcionTramite;
    private String fechaInicio;
    private int versionSistra;
    private boolean mostraModal;

    /* Atributs inacabats */
    private String idSesionTramitacion;
    private String idioma;
    private String idTramite;
    private int versionTramite;
    private String fechaUltimoAcceso;

    /* Atributs acabats */
    private TipoElementoExpediente tipo;
    private boolean pendiente = false;
    private String url;
    private String numero;

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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(final String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcionTramite() {
        return descripcionTramite;
    }

    public void setDescripcionTramite(final String descripcionTramite) {
        this.descripcionTramite = descripcionTramite;
    }

    public String getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(final String fechaUltimoAcceso) {
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

    public TipoElementoExpediente getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoElementoExpediente tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isPendiente() {
        return this.pendiente;
    }

    public void setPendiente(Boolean pendiente) {
        this.pendiente = pendiente;
    }

    public Boolean esMostraModal() {
        return this.mostraModal;
    }

    public Boolean esRegistrado() {
        return this.tipo == TipoElementoExpediente.REGISTRO || !this.numero.isEmpty();
    }

    public void setMostraModal(Boolean mostraModal) {
        this.mostraModal = mostraModal;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public boolean isMostraModal() {
        return mostraModal;
    }

    public void setMostraModal(boolean mostraModal) {
        this.mostraModal = mostraModal;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public TramitePersistenteGenerico(RTramitePersistencia tramite, int versionSistra) {
        this.idSesionTramitacion = tramite.getIdSesionTramitacion();
        this.idioma = tramite.getIdioma();
        this.idTramite = tramite.getIdTramite();
        this.versionTramite = tramite.getVersionTramite();
        this.descripcionTramite = tramite.getDescripcionTramite();
        this.fechaInicio = SDF.format(tramite.getFechaInicio());
        this.fechaUltimoAcceso = SDF.format(tramite.getFechaUltimoAcceso());
        this.versionSistra = versionSistra;
        this.pendiente = true;
        this.url = "";
        this.tipo = null;
        this.mostraModal = false;
        this.numero = "";
    }

    public TramitePersistenteGenerico(RTramiteFinalizado tramite, int versionSistra) {
        this.idSesionTramitacion = tramite.getIdSesionTramitacion();
        this.idioma = tramite.getIdioma();
        this.idTramite = tramite.getIdTramite();
        this.versionTramite = tramite.getVersionTramite();
        this.descripcionTramite = tramite.getDescripcionTramite();
        this.fechaInicio = SDF.format(tramite.getFechaFin());
        this.fechaUltimoAcceso = SDF.format(tramite.getFechaFin());
        this.versionSistra = versionSistra;
        this.pendiente = false;
        this.url = "";
        this.tipo = null;
        this.mostraModal = false;
        this.numero = tramite.getNumeroRegistro();
    }

    public TramitePersistenteGenerico(TramitePersistente tramite, int versionSistra) {
        this.idSesionTramitacion = tramite.getIdSesionTramitacion();
        this.idioma = tramite.getIdioma();
        this.idTramite = tramite.getIdTramite();
        this.versionTramite = tramite.getVersionTramite();
        this.descripcionTramite = tramite.getDescripcionTramite();
        this.fechaInicio =  SDF.format(tramite.getFechaInicio().toGregorianCalendar().getTime());
        this.fechaUltimoAcceso = SDF.format(tramite.getFechaUltimoAcceso().toGregorianCalendar().getTime());
        this.versionSistra = versionSistra;
        this.pendiente = true;
        this.url = tramite.getUrlAcceso();
        this.tipo = null;
        this.mostraModal = false;
        this.numero = "";
    }

    public TramitePersistenteGenerico(ElementoExpediente tramite, int versionSistra) {
        this.idSesionTramitacion = "";
        this.idioma = "";
        this.idTramite = tramite.getUrl().substring(tramite.getUrl().indexOf("tramite=") + 8);
        this.versionTramite = 0;
        this.descripcionTramite = tramite.getDescripcion();
        this.fechaInicio = SDF.format(tramite.getFecha().toGregorianCalendar().getTime());
        this.fechaUltimoAcceso = SDF.format(tramite.getFecha().toGregorianCalendar().getTime());
        this.versionSistra = versionSistra;
        this.pendiente = tramite.isPendiente();
        this.url = tramite.getUrl();
        this.tipo = tramite.getTipo();
        this.mostraModal = false;
        this.numero = (tramite.getTipo() == TipoElementoExpediente.REGISTRO && tramite.getNumero() != null)
                ? (String) tramite.getNumero().getValue()
                : "";
    }

    public String toString() {
        return "{'idSesionTramitacion': " + this.idSesionTramitacion + "'idTramite':" + this.idTramite
                + "'descripcionTramite':" + this.descripcionTramite + "'url':" + this.url + "'numero':" + this.numero
                + "'versionSistra':" + this.versionSistra + "}";
    }

}
