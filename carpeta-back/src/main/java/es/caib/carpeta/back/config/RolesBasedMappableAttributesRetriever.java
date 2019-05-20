package es.caib.carpeta.back.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.mapping.MappableAttributesRetriever;

import java.util.HashSet;
import java.util.Set;

public class RolesBasedMappableAttributesRetriever implements MappableAttributesRetriever {

    protected final Log log = LogFactory.getLog(getClass());

    private Set<String> defaultMappableAttributes;
    private Set<String> mappableAttributes = new HashSet<String>();


    public Set<String> getMappableAttributes() {
        refrescarMappableAttributes();
        return mappableAttributes;
    }

    public void setDefaultMappableAttributes(Set<String> defaultMappableAttributes) {

        log.info("Dentro setDefaultMappableAttributes: ");
        for (String defaultMappableAttribute : defaultMappableAttributes) {
            log.info("defaultMappableAttributes: " + defaultMappableAttribute);
        }
        this.defaultMappableAttributes = defaultMappableAttributes;
    }



    private void refrescarMappableAttributes() {

        log.info("---------- Dentro RolesBasedMappableAttributesRetriever ----------");
        mappableAttributes.clear();
        if (defaultMappableAttributes != null)
            mappableAttributes.addAll(defaultMappableAttributes);
    }
}
