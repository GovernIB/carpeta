package es.caib.carpeta.back.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;

import javax.ejb.EJB;

import org.apache.log4j.Logger;

/*
 * Created by Fundació BIT
 * 
 * @author jagarcia
 * Date: 29/03/2021
 */

@EnableScheduling
@Component
public class CarpetaScheduler {
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
	protected LogCarpetaLogicaService logCarpetaLogicaEjb;
	
	@EJB(mappedName = PropietatGlobalService.JNDI_NAME)
	protected PropietatGlobalService propietatGlobalEjb;
	
	@Scheduled(cron = "0 0 23 * * *")
	public void esborrarLogs() {
		try {
			
			long dies = EjbManager.getEsborrarLogs(propietatGlobalEjb);
			logCarpetaLogicaEjb.esborrarLog(dies);
			
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	

}
