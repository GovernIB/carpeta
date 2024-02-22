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

public class DateTimeJodaDeserializer extends JsonDeserializer<DateTime>
{
    
    protected Logger log = Logger.getLogger(this.getClass());

    

    public DateTimeJodaDeserializer() {
        super();
        log.info("Inicialiitzant DateTimeJodaDeserializer");
        System.out.println("Inicialiitzant DateTimeJodaDeserializer");
    }

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        log.info("*****************Entra al deserializer");
        System.out.println("*****************Entra al deserializer");

        String date = p.getValueAsString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS-ZZZZ");
        Date dateTime;
        try {
            dateTime = sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new IOException(e);
        }
        DateTime dt = new DateTime(dateTime);
        log.info("*****************Surt del deserializer");
        System.out.println("*****************Surt del deserializer");
        return dt;
    }
    
}