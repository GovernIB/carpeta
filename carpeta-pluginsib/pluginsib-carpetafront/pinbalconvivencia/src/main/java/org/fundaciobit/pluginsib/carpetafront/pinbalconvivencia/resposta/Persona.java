package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta;

import java.util.ArrayList;

public class Persona {

	protected String nombre = "";
	protected String apellido1 = "";
	protected String apellido2 = "";
	protected String fechaNacimiento = "";
	protected String tipoDocumentacion = "";
	protected String documentacion =  "";
	protected String nia = "";
	
	protected ArrayList<PeriodoInscripcion> periodosInscripcion = new ArrayList<PeriodoInscripcion>();
	
	public Persona() {
		
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getNia() {
		return nia;
	}

	public void setNia(String nia) {
		this.nia = nia;
	}

	public ArrayList<PeriodoInscripcion> getPeriodosInscripcion() {
		return periodosInscripcion;
	}

	public void setPeriodosInscripcion(ArrayList<PeriodoInscripcion> periodosInscripcion) {
		this.periodosInscripcion = periodosInscripcion;
	}

	public Persona(String nombre, String apellido1, String apellido2, String fechaNacimiento, String tipoDocumentacion,
			String documentacion, String nia, ArrayList<PeriodoInscripcion> periodosInscripcion) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumentacion = tipoDocumentacion;
		this.documentacion = documentacion;
		this.nia = nia;
		this.periodosInscripcion = periodosInscripcion;
	}
	
	
	
}
