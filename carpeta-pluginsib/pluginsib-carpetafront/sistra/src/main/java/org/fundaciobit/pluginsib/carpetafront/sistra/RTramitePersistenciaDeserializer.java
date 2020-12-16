package org.fundaciobit.pluginsib.carpetafront.sistra;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import static javax.json.stream.JsonParser.Event;

public class RTramitePersistenciaDeserializer implements JsonbDeserializer<RTramitePersistencia> {
	
	private static final Logger logger = Logger.getLogger(RTramitePersistenciaDeserializer.class.getName());

    @Override
    public RTramitePersistencia deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
    	
        RTramitePersistencia tramite = new RTramitePersistencia();

        try {
        	
        	while (parser.hasNext()) {
                Event event = parser.next();
                if (event == Event.KEY_NAME) {
                    String keyName = parser.getString();
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
                            tramite.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(parser.getString()));
                        break;
                        case "fechaUltimoAcceso":                    	
                            tramite.setFechaUltimoAcceso(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(parser.getString()));
                        break;
                    }
                }
            }
        	
        }catch (ParseException e) {
        	logger.info("ERROR RTramitePersistencia: " + e.getMessage()); 
        }
        
        return tramite;
    }
}