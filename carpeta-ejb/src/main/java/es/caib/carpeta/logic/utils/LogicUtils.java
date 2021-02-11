package es.caib.carpeta.logic.utils;

import es.caib.carpeta.commons.utils.StaticVersion;

import org.apache.log4j.Logger;
import java.util.*;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

	protected static Logger log = Logger.getLogger(LogicUtils.class);

	public static String getVersio() {
		return StaticVersion.VERSION + (es.caib.carpeta.commons.utils.Configuracio.isCAIB() ? "-caib" : "");
	}


	public static Date novaDataSenseHora(){

		Date date = new Date();                      // timestamp now
		Calendar cal = Calendar.getInstance();       // get calendar instance
		cal.setTime(date);                           // set cal to date
		cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
		cal.set(Calendar.MINUTE, 0);                 // set minute in hour
		cal.set(Calendar.SECOND, 0);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0);            // set millis in second
		return  cal.getTime();

	}

	public static Date llevarHoraData(Date date){

		Calendar cal = Calendar.getInstance();       // get calendar instance
		cal.setTime(date);                           // set cal to date
		cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
		cal.set(Calendar.MINUTE, 0);                 // set minute in hour
		cal.set(Calendar.SECOND, 0);                 // set second in minute
		cal.set(Calendar.MILLISECOND, 0);            // set millis in second
		return  cal.getTime();

	}


}
