package org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TituloFamiliaNumerosaRetorno")
public class TituloFamiliaNumerosaRetorno {

	private String codigoComunidadAutonoma; 
	
	private String numeroTitulo;
	
	/* G (General) | E (Especial) */
	/* Titulos anteriores a 2003: P(Primera), S(Segunda) o H (De honor) */
	private String categoria;
	
	private String tituloVigente;
	
	/* DD/MM/AAAA */
	private String fechaExpedicion;
	
	/* DD/MM/AAAA */
	private String fechaCaducidad;
	
	private String  numeroHijos;

	@XmlElement(name="CodigoComunidadAutonoma")
	public String getCodigoComunidadAutonoma() {
		return codigoComunidadAutonoma;
	}

	public void setCodigoComunidadAutonoma(String codigoComunidadAutonoma) {
		this.codigoComunidadAutonoma = codigoComunidadAutonoma;
	}

	@XmlElement(name="NumeroTitulo")
	public String getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

	@XmlElement(name="Categoria")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@XmlElement(name="TituloVigente")
	public String getTituloVigente() {
		return tituloVigente;
	}

	public void setTituloVigente(String tituloVigente) {
		this.tituloVigente = tituloVigente;
	}

	@XmlElement(name="FechaCaducidad")
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	@XmlElement(name="FechaExpedicion")
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	@XmlElement(name="NumeroHijos")
	public String getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(String numeroHijos) {
		this.numeroHijos = numeroHijos;
	} 
	
	public TituloFamiliaNumerosaRetorno() {
		super();
	}
	
}
