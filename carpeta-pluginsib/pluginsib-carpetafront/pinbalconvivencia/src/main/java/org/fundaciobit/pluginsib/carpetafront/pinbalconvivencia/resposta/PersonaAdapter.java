package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class PersonaAdapter extends TypeAdapter<Persona>{

	private PeriodoInscripcionAdapter periodoInscripcionAdapter = new PeriodoInscripcionAdapter();
	
	@Override
	public void write(JsonWriter out, Persona value) throws IOException {
				
		out.beginObject();
		out.name("nombre").value(value.getNombre());
		out.name("apellido1").value(value.getApellido1());
		out.name("apellido2").value(value.getApellido2());
		out.name("fechanacimiento").value(value.getFechaNacimiento());
		out.name("tipodocumentacion").value(value.getTipoDocumentacion());
		out.name("documentacion").value(value.getDocumentacion());
		out.name("nia").value(value.getNia());
		
		out.name("periodosInscripcion").beginArray();
		
		for (PeriodoInscripcion item: value.getPeriodosInscripcion()) {
			periodoInscripcionAdapter.write(out, item);
		}
	
		out.endArray().endObject();
		
	}

	@Override
	public Persona read(JsonReader in) throws IOException {
		return null;
	}
	
}
