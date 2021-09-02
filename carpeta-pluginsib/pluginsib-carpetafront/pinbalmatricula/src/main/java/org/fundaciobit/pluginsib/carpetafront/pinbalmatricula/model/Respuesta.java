package org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Respuesta")
public class Respuesta {

	private ArrayList<DatosAlumno> datosAlumno = new ArrayList<>();
	private String codRespuesta;
	private String descRespuesta;
	private String cursoMatriculaVigente;
	private String cursoMatriculaFutura;
	private String fechaProceso;

	
	public Respuesta(){}

	@XmlElement(name="DatosAlumno")
	public ArrayList<DatosAlumno> getDatosAlumno() {
		return datosAlumno;
	}

	public void setDatosAlumno(ArrayList<DatosAlumno> datosAlumno) {
		this.datosAlumno = datosAlumno;
	}

	@XmlElement(name="CodRespuesta")
	public String getCodRespuesta() {
		return codRespuesta;
	}
	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}

	@XmlElement(name="DescRespuesta")
	public String getDescRespuesta() {
		return descRespuesta;
	}
	public void setDescRespuesta(String descRespuesta) {
		this.descRespuesta = descRespuesta;
	}

	@XmlElement(name="CursoMatriculaVigente")
	public String getCursoMatriculaVigente() {
		return cursoMatriculaVigente;
	}
	public void setCursoMatriculaVigente(String cursoMatriculaVigente) {
		this.cursoMatriculaVigente = cursoMatriculaVigente;
	}

	@XmlElement(name="CursoMatriculaFutura")
	public String getCursoMatriculaFutura() {
		return cursoMatriculaFutura;
	}
	public void setDCursoMatriculaFutura(String cursoMatriculaFutura) {
		this.cursoMatriculaFutura = cursoMatriculaFutura;
	}

	@XmlElement(name="FechaProceso")
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

}
