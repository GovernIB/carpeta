package org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ListaBeneficiariosRetorno")
public class ListaBeneficiariosRetorno {
	
	private ArrayList<BeneficiarioRetorno> beneficiarios;

	public ListaBeneficiariosRetorno() {
		super();
	}

	@XmlElement(name="BeneficiarioRetorno")
	public ArrayList<BeneficiarioRetorno> getBeneficiarios() {
		return beneficiarios;
	}

	public void setBeneficiarios(ArrayList<BeneficiarioRetorno> beneficiarios) {
		this.beneficiarios = beneficiarios;
	} 
	
	

}
