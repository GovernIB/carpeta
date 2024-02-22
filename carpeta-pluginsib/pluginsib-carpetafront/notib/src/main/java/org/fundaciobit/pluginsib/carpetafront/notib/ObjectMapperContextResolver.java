package org.fundaciobit.pluginsib.carpetafront.notib;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.BooleanDeserializer;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.DateTimeJodaDeserializer;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    
    protected Logger log = Logger.getLogger(this.getClass());

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        log.info("\n\n\n Inicialitzant a ObjectMapperContextResolver \n\n\n ");
        this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private ObjectMapper createObjectMapper() {
        
        ObjectMapper mapper = new ObjectMapper();
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SimpleModule modul = new SimpleModule();
        modul.addDeserializer(DateTime.class, new DateTimeJodaDeserializer());
        modul.addDeserializer(Boolean.class, new BooleanDeserializer());
        mapper.registerModule(modul);
        
        return mapper;
    }
}