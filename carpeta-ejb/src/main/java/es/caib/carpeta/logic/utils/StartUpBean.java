package es.caib.carpeta.logic.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

/**
 * 
 * @author anadal
 *
 */
@Startup
@Singleton
public class StartUpBean {

    protected final Logger log = Logger.getLogger(this.getClass());

    @PostConstruct
    public void start() throws Exception {
        log.info("\n\n ---------------------------------\n"
                + "      PASSA PER STARTUP\n -----------------------------------\n\n");

    }
}
