package es.caib.carpeta.logic;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.utils.EjbManager;

/**
 * Created by Fundacio BIT.
 * 
 * @author jagarcia
 *
 */
@Startup
@Singleton
public class SchedulerLogicaEJB implements SchedulerLogicaService {
	
	protected final Logger log = Logger.getLogger(getClass());

	@EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
	protected LogCarpetaLogicaService logCarpetaLogicaEjb;
	
	@EJB(mappedName = PropietatGlobalService.JNDI_NAME)
	protected PropietatGlobalService propietatGlobalEjb;
	
	@Resource
	public TimerService timerService;	
	public void setTimerService (TimerService timerService) {
		this.timerService = timerService;
	}
	
	
	@PostConstruct
	public void init() {
		
		try {
			
			String hora = EjbManager.getEsborrarLogsHora(propietatGlobalEjb);
			
			if (hora != null && Integer.valueOf(hora) >= 0 && Integer.valueOf(hora) <= 23) {
				
				ScheduleExpression expression = new ScheduleExpression();
		        expression.dayOfWeek("Sun,Mon,Tue,Wed,Thu,Fri,Sat");
		        expression.hour(hora);
		        expression.minute(0);
		        expression.second(0);

		        if(Configuracio.isDesenvolupament()) {
		        	log.info("---------------------------  SCHEDULER STARTUP ------------------------ ");		
		        	log.info("> > > SCHEDULER TIMER: " + expression.toString());	
		        }
		        
		        TimerConfig config = new TimerConfig();
				config.setInfo("Esborrar Logs");
				config.setPersistent(false);
				
				timerService.createCalendarTimer(expression, config);
			}else {
				log.warn("Les propietats globals hora i numdies no están definides per la programació del scheduler per esborrar logs.");		
			}
		} catch(I18NException e) {
			log.error("Error obtenint les propietats globals per esborrarlogs:" + e.getMessage());
		}
	   
	}
	
	
	@Timeout
    public void execute(Timer timer) {
		
        try {
   			
   			Long dies = EjbManager.getEsborrarLogsDies(propietatGlobalEjb);
   			if (dies != null) {
   				if(Configuracio.isDesenvolupament()) {
   					log.info("SchedulerLogicaEJB: Execució del mètode per Esborrarlogs dels darrers " + dies + " dies." );
   				}
   				logCarpetaLogicaEjb.esborrarLog(dies);
   			}
   			
   		}catch(Exception e) {
   			log.error("Error esborrant els logs desde SchedulerLogicaEJB: " + e.getMessage());
   		}
    }
}
