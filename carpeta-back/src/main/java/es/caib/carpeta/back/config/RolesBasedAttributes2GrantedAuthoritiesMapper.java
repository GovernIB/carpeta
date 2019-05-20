package es.caib.carpeta.back.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.Attributes2GrantedAuthoritiesMapper;

import java.util.*;

/**
 * Created by Fundació BIT.
 *
 * @author earrivi
 * Define el mapeo entre roles  J2EE y los roles internos de la aplicacion
 */
public class RolesBasedAttributes2GrantedAuthoritiesMapper implements Attributes2GrantedAuthoritiesMapper {

    protected final Log log = LogFactory.getLog(getClass());

    @SuppressWarnings("rawtypes")
    private Map baseRoleMapping = new HashMap();

    public void setBaseRoleMapping(@SuppressWarnings("rawtypes") Map baseRoleMapping) {
        this.baseRoleMapping = baseRoleMapping;
    }

    @SuppressWarnings("rawtypes")
    public Collection<GrantedAuthority> getGrantedAuthorities(Collection<String> attributes) {
        log.info("---------- Dentro RolesBasedAttributes2GrantedAuthoritiesMapper ----------");
        log.info("Attributes size: " + attributes.size());
        List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>();
        for (String attribute: attributes) {
            log.info("Attribute: " + attribute);
            Object mapping = baseRoleMapping.get(attribute);
            log.info("Mapping: " + mapping);
            if (mapping != null) {
                if (mapping instanceof Collection) {
                    for (Object obj: (Collection)mapping) {
                        if (obj != null)
                            gaList.add(new SimpleGrantedAuthority(obj.toString()));
                    }
                } else if (mapping != null) {
                    gaList.add(new SimpleGrantedAuthority(mapping.toString()));
                }
            } else {
                gaList.add(new SimpleGrantedAuthority(attribute));
            }
        }

        return gaList;
    }
}
