package es.caib.carpeta.api.externa.model;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Dades d'un accés
 * 
 * @author jagarcia
 *
 */

@Schema(name = "Acces")
public class AccesDTO {

	private String proveidor;
	private String metodeAutenticacio;
	private java.lang.Integer qaa;
	private Date data;
	private String idioma;
	private String entitat;
	private String tipus;
	private String plugin;
	
	public AccesDTO() {
	}

	public AccesDTO(String proveidor, String metodeAutenticacio, java.lang.Integer qaa, Date data, String idioma, String entitat, String tipus, String plugin) {
		this.proveidor = proveidor;
		this.metodeAutenticacio = metodeAutenticacio;
		this.qaa = qaa;
		this.data = data;
		this.idioma = idioma;
		this.entitat = entitat;
		this.tipus = tipus;
		this.plugin = plugin;
	}
	
	public String getProveidor() {
		return proveidor;
	}
	
	public void setProveidor(String proveidor) {
		this.proveidor = proveidor;
	}

	public String getMetodeAutenticacio() { 
		return metodeAutenticacio; 
	}

	public void setMetodeAutenticacio(String metodeAutenticacio) { 
		this.metodeAutenticacio = metodeAutenticacio; 
	}

	public java.lang.Integer getQaa() {
		return qaa;
	}
	
	public void setQaa(java.lang.Integer qaa) {
		this.qaa = qaa;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getIdioma() {
		return idioma;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public String getEntitat() {
		return entitat;
	}
	
	public void setEntitat(String entitat) {
		this.entitat = entitat;
	}
	
	public String getTipus() {
		 return tipus;
	}
	
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	
	public String getPlugin() {
		return plugin;
	}
	
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public String toString() {
		return "AccesDTO{"  
				+ "proveidor=" + proveidor
				+ "metodeAutenticacio=" + metodeAutenticacio
				+ "qaa=" + qaa
				+ "data=" + data
				+ "idioma=" + idioma
				+ "entitat=" + entitat
				+ "tipus=" + tipus
				+ "plugin=" + plugin
				+ "}";
	}
	
}
