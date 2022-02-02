package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta;

public class PeriodoInscripcion {

	protected String desde = "";
	protected String codigoVariacion = "";
	protected String causaVariacion = "";
	protected String descripcion = "";
	public String getDesde() {
		return desde;
	}
	public void setDesde(String desde) {
		this.desde = desde;
	}
	public String getCodigoVariacion() {
		return codigoVariacion;
	}
	public void setCodigoVariacion(String codigoVariacion) {
		this.codigoVariacion = codigoVariacion;
	}
	public String getCausaVariacion() {
		return causaVariacion;
	}
	public void setCausaVariacion(String causaVariacion) {
		this.causaVariacion = causaVariacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public PeriodoInscripcion() {
		super();
	}
	
	public PeriodoInscripcion(String desde, String codigoVariacion, String causaVariacion, String descripcion) {
		super();
		this.desde = desde;
		this.codigoVariacion = codigoVariacion;
		this.causaVariacion = causaVariacion;
		this.descripcion = descripcion;
	}
}
