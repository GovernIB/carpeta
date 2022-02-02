package org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.resposta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.fundaciobit.pluginsib.carpetafront.pinbalhistorico.model.Domicilio;

public class DatosHistorico {
	
    protected String error = "";       
    protected String dni = "";
    protected String nombre = "";
    protected String apellido1 = "";
    protected String apellido2 = "";
    protected String fecha = "";
    protected String codigo = "";
    protected String descripcion = "";
	protected String fechaExpedicion = "";
	protected String numeroAnyos =  "";
	
	protected String tipoDocumentacion = "";
	protected String documentacion =  "";
	protected String numSoporte = "";
	protected String fechaNacimiento = "";
	
	protected ArrayList<Domicilio> historico = new ArrayList<Domicilio>();
	
	public void setHistorico(ArrayList<Domicilio> historico) {
		this.historico = historico;
	}
	
	public ArrayList<Domicilio> getHistorico() {
		return this.historico;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipoDocumentacion() {
		return tipoDocumentacion;
	}

	public void setTipoDocumentacion(String tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}

	public String getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

	public String getNumSoporte() {
		return numSoporte;
	}

	public void setNumSoporte(String numSoporte) {
		this.numSoporte = numSoporte;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	
	public void setFechaExpedicion( String fecha) {
		this.fechaExpedicion = fecha;
	}
	
	public String getNumeroAnyos() {
		return numeroAnyos;
	}
	
	public void setNumeroAnyos( String numeroAnyos ) {
		this.numeroAnyos = numeroAnyos;
	}
	
	public DatosHistorico() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fecha = new Date();
		this.fecha = dateFormat.format(fecha);
	}

	public DatosHistorico(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fecha = new Date();
		this.fecha = dateFormat.format(fecha);
	}
	
}
