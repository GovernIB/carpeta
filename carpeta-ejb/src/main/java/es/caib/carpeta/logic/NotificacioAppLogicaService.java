package es.caib.carpeta.logic;

import javax.ejb.Local;
import es.caib.carpeta.ejb.NotificacioAppService;


/**
 * 
 * @author anadal
 * 
 */
@Local
public interface NotificacioAppLogicaService extends NotificacioAppService {
  
    public static final String JNDI_NAME = "java:app/carpeta-ejb/NotificacioAppLogicaEJB!es.caib.carpeta.logic.NotificacioAppLogicaService";

}

