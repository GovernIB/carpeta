package es.caib.carpeta.model;

import es.caib.carpeta.model.dao.*;

public interface ICarpetaDaoManagers {
	public IAccesManager getAccesManager();
	public IAuditoriaManager getAuditoriaManager();
	public IAvisManager getAvisManager();
	public IEnllazManager getEnllazManager();
	public IEntitatManager getEntitatManager();
	public IEstadisticaManager getEstadisticaManager();
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public ILogCarpetaManager getLogCarpetaManager();
	public IPluginManager getPluginManager();
	public IPluginEntitatManager getPluginEntitatManager();
	public IPropietatGlobalManager getPropietatGlobalManager();
	public ISeccioManager getSeccioManager();
	public ITraduccioManager getTraduccioManager();
	public IUsuariManager getUsuariManager();
	public IUsuariEntitatManager getUsuariEntitatManager();

}