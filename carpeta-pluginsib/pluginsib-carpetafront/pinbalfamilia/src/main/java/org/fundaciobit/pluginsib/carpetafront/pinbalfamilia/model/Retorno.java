package org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Retorno")
public class Retorno {
	
	private ArrayList<Estado> estado = new ArrayList<>();
	
	private ArrayList<TituloFamiliaNumerosaRetorno> tituloFamiliaNumerosa = new ArrayList<>();
	
	private ArrayList<ListaBeneficiariosRetorno> beneficiarios = new ArrayList<>();
	
	public Retorno(){}

	@XmlElement(name="Estado")
	public ArrayList<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ArrayList<Estado> estado) {
		this.estado = estado;
	}

	@XmlElement(name="TituloFamiliaNumerosaRetorno")
	public ArrayList<TituloFamiliaNumerosaRetorno> getTituloFamiliaNumerosa() {
		return tituloFamiliaNumerosa;
	}

	public void setTituloFamiliaNumerosa(ArrayList<TituloFamiliaNumerosaRetorno> tituloFamiliaNumerosa) {
		this.tituloFamiliaNumerosa = tituloFamiliaNumerosa;
	}

	@XmlElement(name="ListaBeneficiariosRetorno")
	public ArrayList<ListaBeneficiariosRetorno> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(ArrayList<ListaBeneficiariosRetorno> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
}
