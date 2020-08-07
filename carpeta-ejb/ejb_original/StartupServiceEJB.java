package es.caib.carpeta.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * EJB únic que s'executa a la inicialització.
 *
 * @author areus
 */
@Singleton
@Startup
public class StartupServiceEJB {

    private static final Logger LOG = LoggerFactory.getLogger(StartupServiceEJB.class);

    /**
     * Executat a l'inici de l'aplicació.
     */
    @PostConstruct
    private void init() {
        // Aquí es podrien llegir les opcions de configuració, i comprovar que tots els paràmetres necessaris hi són,
        // o fixar els valors per defecte pels que no hi siguin, programar timers no persistents, ...
        LOG.info("Inici del mòdul EJB");
    }

    /**
     * Executat quan s'atura l'aplicació.
     */
    @PreDestroy
    private void destroy() {
        LOG.info("Aturada del mòdul EJB");
    }
}
