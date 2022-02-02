package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatosConvivencia {


    protected String error = "";       
    protected String dni = "";
    protected String nombre = "";
    protected String apellido1 = "";
    protected String apellido2 = "";
    protected String fecha = "";
    protected String codigo = "";
    protected String descripcion = "";
    protected String distrito = "";
	protected String seccion = "";
	protected String hoja = "";
	protected String fechaExpedicion = "";

	protected String via = "";
	protected String tipoVia = "";
	protected String numero = "";
	protected String kmt = "";
	protected String bloque = "";
	protected String portal = "";
	protected String escalera = "";
	protected String planta = "";
	protected String puerta = "";
	protected String codPostal = "";
	
	protected ArrayList<Persona> personas = new ArrayList<Persona>();
	
	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}
	
	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getKmt() {
		return kmt;
	}

	public void setKmt(String kmt) {
		this.kmt = kmt;
	}

	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDistrito() {
		return distrito;
	}
	
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	
	public String getSeccion() {
		return seccion;
	}
	
	public void setSeccion (String seccion) {
		this.seccion = seccion;
	}
	
	public String getHoja() {
		return hoja;
	}
	
	public void setHoja (String hoja) {
		this.hoja = hoja;
	}
	
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	
	public void setFechaExpedicion( String fecha) {
		this.fechaExpedicion = fecha;
	}
	
	public DatosConvivencia() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fecha = new Date();
		this.fecha = dateFormat.format(fecha);
	}

	public DatosConvivencia(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fecha = new Date();
		this.fecha = dateFormat.format(fecha);
	}
	
}
