package es.caib.carpeta.logic;

import javax.ejb.Local;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 * Created by Fundacio BIT.
 * 
 * @author jagarcia
 *
 */


@Local
public interface SchedulerLogicaService {

	public static final String JNDI_NAME = "java:app/carpeta-ejb/SchedulerLogicaEJB!es.caib.carpeta.logic.SchedulerLogicaService";
	
	public void init();
	
	public void execute(Timer timer);
	
	public void setTimerService (TimerService timerService);
	
}
