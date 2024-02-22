package org.fundaciobit.pluginsib.carpetafront.notib2client.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {
    
    protected Logger log = Logger.getLogger(this.getClass());
    
    public BooleanDeserializer() {
        super();
        log.info("Inicialiitzant BooleanDeserializer");
    }

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        log.info("*****************Entra al deserializer Boolean");
        String b = p.getValueAsString();

        if (b == null || b.trim().length() == 0) {
            log.info("*****************Surt del deserializerBoolean NULL");
            return null;
        } else {
            log.info("*****************Surt del deserializerBoolean");
            return b.toLowerCase().trim().equals("true");
        }

    }

}