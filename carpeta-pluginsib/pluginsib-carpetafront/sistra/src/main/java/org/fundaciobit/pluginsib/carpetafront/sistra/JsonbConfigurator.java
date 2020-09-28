package org.fundaciobit.pluginsib.carpetafront.sistra;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ext.ContextResolver;

public class JsonbConfigurator implements ContextResolver<Jsonb> {

    private final Jsonb jsonb;

    public JsonbConfigurator() {
        JsonbConfig config = new JsonbConfig()
                .withDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", null);
        
        jsonb = JsonbBuilder.newBuilder()
                .withConfig(config).build();
    }

    @Override
    public Jsonb getContext(Class<?> type) {
        return jsonb;
    }
}