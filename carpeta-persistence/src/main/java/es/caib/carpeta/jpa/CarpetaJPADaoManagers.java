package es.caib.carpeta.jpa;

import es.caib.carpeta.model.ICarpetaDaoManagers;
import es.caib.carpeta.model.dao.*;

import javax.persistence.EntityManager;

public final class CarpetaJPADaoManagers implements ICarpetaDaoManagers{

   private final AccesJPAManager car_acces;
   private final AuditoriaJPAManager car_auditoria;
   private final AvisJPAManager car_avis;
   private final EnllazJPAManager car_enllaz;
   private final EntitatJPAManager car_entitat;
   private final EstadisticaJPAManager car_estadistica;
   private final FitxerJPAManager car_fitxer;
   private final IdiomaJPAManager car_idioma;
   private final LogCarpetaJPAManager car_log;
   private final PluginJPAManager car_plugin;
   private final PluginEntitatJPAManager car_pluginentitat;
   private final PropietatGlobalJPAManager car_propietatglobal;
   private final TraduccioJPAManager car_traduccio;
   private final UsuariJPAManager car_usuari;
   private final UsuariEntitatJPAManager car_usuarientitat;

  public  CarpetaJPADaoManagers(EntityManager __em) {
    this.car_acces = new AccesJPAManager(__em);
    this.car_auditoria = new AuditoriaJPAManager(__em);
    this.car_avis = new AvisJPAManager(__em);
    this.car_enllaz = new EnllazJPAManager(__em);
    this.car_entitat = new EntitatJPAManager(__em);
    this.car_estadistica = new EstadisticaJPAManager(__em);
    this.car_fitxer = new FitxerJPAManager(__em);
    this.car_idioma = new IdiomaJPAManager(__em);
    this.car_log = new LogCarpetaJPAManager(__em);
    this.car_plugin = new PluginJPAManager(__em);
    this.car_pluginentitat = new PluginEntitatJPAManager(__em);
    this.car_propietatglobal = new PropietatGlobalJPAManager(__em);
    this.car_traduccio = new TraduccioJPAManager(__em);
    this.car_usuari = new UsuariJPAManager(__em);
    this.car_usuarientitat = new UsuariEntitatJPAManager(__em);
  }

	public IAccesManager getAccesManager() {
	  return this.car_acces;
	};

	public IAuditoriaManager getAuditoriaManager() {
	  return this.car_auditoria;
	};

	public IAvisManager getAvisManager() {
	  return this.car_avis;
	};

	public IEnllazManager getEnllazManager() {
	  return this.car_enllaz;
	};

	public IEntitatManager getEntitatManager() {
	  return this.car_entitat;
	};

	public IEstadisticaManager getEstadisticaManager() {
	  return this.car_estadistica;
	};

	public IFitxerManager getFitxerManager() {
	  return this.car_fitxer;
	};

	public IIdiomaManager getIdiomaManager() {
	  return this.car_idioma;
	};

	public ILogCarpetaManager getLogCarpetaManager() {
	  return this.car_log;
	};

	public IPluginManager getPluginManager() {
	  return this.car_plugin;
	};

	public IPluginEntitatManager getPluginEntitatManager() {
	  return this.car_pluginentitat;
	};

	public IPropietatGlobalManager getPropietatGlobalManager() {
	  return this.car_propietatglobal;
	};

	public ITraduccioManager getTraduccioManager() {
	  return this.car_traduccio;
	};

	public IUsuariManager getUsuariManager() {
	  return this.car_usuari;
	};

	public IUsuariEntitatManager getUsuariEntitatManager() {
	  return this.car_usuarientitat;
	};


}