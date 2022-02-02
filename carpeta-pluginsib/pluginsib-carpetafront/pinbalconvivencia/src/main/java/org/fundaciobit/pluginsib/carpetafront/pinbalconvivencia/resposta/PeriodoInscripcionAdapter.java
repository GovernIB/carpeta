package org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.resposta;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class PeriodoInscripcionAdapter extends TypeAdapter<PeriodoInscripcion> {

	@Override
	public void write(JsonWriter out, PeriodoInscripcion value) throws IOException {
		out.beginObject();
		out.name("desde");
		out.value(value.getDesde());
		out.name("causaVariacion");
		out.value(value.getCausaVariacion());
		out.name("codigoVariacion");
		out.value(value.getCodigoVariacion());
		out.name("descripcion");
		out.value(value.getDescripcion());
		out.endObject();
	}

	@Override
	public PeriodoInscripcion read(JsonReader in) throws IOException {
		return null;
	}
}
