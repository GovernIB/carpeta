package es.caib.carpeta.logic.utils;

import org.apache.log4j.Logger;

import es.caib.carpeta.commons.utils.StaticVersion;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

	protected static Logger log = Logger.getLogger(LogicUtils.class);

	public static String getVersio() {
		return StaticVersion.VERSION + (es.caib.carpeta.utils.Configuracio.isCAIB() ? "-caib" : "");
	}

}
