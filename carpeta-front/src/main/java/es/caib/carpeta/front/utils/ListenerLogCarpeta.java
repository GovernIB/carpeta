package es.caib.carpeta.front.utils;

import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;

/**
 * 
 * @author jagarcia
 *
 */
public class ListenerLogCarpeta implements IListenerLogCarpeta {

	Long pluginID;
	String codiEntitat;
	
	LogCarpetaLogicaService logCarpetaLogicaService;
	
	public ListenerLogCarpeta(Long pluginID, String codiEntitat, LogCarpetaLogicaService logCarpetaLogicaService) {
		super();
		this.pluginID = pluginID;
		this.codiEntitat = codiEntitat;
		this.logCarpetaLogicaService = logCarpetaLogicaService;
	}

	@Override
	public void crearLogCarpeta(String descripcio, String error, String peticio, String idSessio) {
		
		this.logCarpetaLogicaService.crearLogCarpeta(descripcio, error, peticio, this.codiEntitat, this.pluginID, idSessio);

	}

}
