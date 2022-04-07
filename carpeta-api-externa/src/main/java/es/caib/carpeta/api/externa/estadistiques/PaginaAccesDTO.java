package es.caib.carpeta.api.externa.estadistiques;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import io.swagger.v3.oas.annotations.media.Schema;

//import org.eclipse.microprofile.openapi.annotations.media.Schema;

/*
 * Classe necessària per generar correctament la documentació OpenAPI, atès que els resultats
 * amb genèrics no funcionen del tot bé.
 * 
 * És equivalent a un Pagina<AccesDTO> i només s'empra per a la documentació del API REST
 * 
 * @author jagarcia  
 */

@Schema(name = "PaginaAcces")
public class PaginaAccesDTO {
	
	private final List<AccesDTO> items;
	
	@JsonbCreator
	public PaginaAccesDTO(@JsonbProperty("items") List<AccesDTO> items) {
		Objects.requireNonNull(items, "items no pot ser null");
		this.items = Collections.unmodifiableList(items);
	}
	
	public List<AccesDTO> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		return "Pagina{" + 
				"items=" + items +
				'}';
	}
	

}
