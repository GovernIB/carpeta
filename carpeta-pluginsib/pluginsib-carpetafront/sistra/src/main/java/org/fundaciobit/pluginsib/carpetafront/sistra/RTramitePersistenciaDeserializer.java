package org.fundaciobit.pluginsib.carpetafront.sistra;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

import org.apache.log4j.Logger;

import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


import static javax.json.stream.JsonParser.Event;

public class RTramitePersistenciaDeserializer implements JsonbDeserializer<RTramitePersistencia> {
	
	private static final org.apache.log4j.Logger logger = Logger.getLogger(RTramitePersistenciaDeserializer.class);

    @Override
    public RTramitePersistencia deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
    	
        RTramitePersistencia tramite = new RTramitePersistencia();

        try {
        	
        	while (parser.hasNext()) {
                Event event = parser.next();
                if (event == Event.KEY_NAME) {
                    String keyName = parser.getString();
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
                    
                    parser.next();
                   switch (keyName) {
                        case "idTramite":
                            tramite.setIdTramite(parser.getString());
                        break;
                        case "descripcionTramite":
                            tramite.setDescripcionTramite(parser.getString());
                        break;
                        case "idioma":
                            tramite.setIdioma(parser.getString());
                        break;
                        case "idSesionTramitacion":
                            tramite.setIdSesionTramitacion(parser.getString());
                        break;
                        case "versionTramite":
                            tramite.setVersionTramite(parser.getInt());
                        break;
                        case "fechaInicio":
                            tramite.setFechaInicio(dateFormat.parse(parser.getString()));
                        break;
                        case "fechaUltimoAcceso":                    	
                            tramite.setFechaUltimoAcceso(dateFormat.parse(parser.getString()));
                        break;
                    }
                }
            }
        	
        }catch (ParseException e) {
        	logger.error("ERROR RTramitePersistencia: " + e.getMessage()); 
        }
        
        return tramite;
    }
}
