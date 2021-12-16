package es.caib.carpeta.logic;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.IPlugin;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.caib.carpeta.ejb.AvisService;
import es.caib.carpeta.ejb.PluginEJB;
import es.caib.carpeta.ejb.PluginEntitatService;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.AvisFields;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import es.caib.carpeta.model.fields.PluginEntitatFields;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anadal
 *
 */
@Stateless
public class PluginLogicaEJB extends PluginEJB implements PluginLogicaService {

  private static final Map<Long, IPlugin> pluginsCache = new HashMap<Long, IPlugin>();

  @EJB(mappedName = es.caib.carpeta.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioService traduccioEjb;
  
  @EJB(mappedName = PluginEntitatService.JNDI_NAME)
  protected PluginEntitatService pluginEntitatEjb;
  
  @EJB(mappedName = AvisService.JNDI_NAME)
  protected AvisService avisEjb;
  
  @EJB(mappedName = es.caib.carpeta.ejb.LogCarpetaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.LogCarpetaService logCarpetaEjb;
  
  @EJB(mappedName = es.caib.carpeta.ejb.AccesService.JNDI_NAME)
  protected es.caib.carpeta.ejb.AccesService accesEjb;
  
  @Override
  public Plugin update(Plugin instance) throws I18NException {
    if (instance != null) {
      deleteOfCache(instance.getPluginID());
    }
    return super.update(instance);
  }

  
  @Override
  public boolean deleteOfCache(Long pluginID) {
    synchronized (pluginsCache) {
      IPlugin p = pluginsCache.remove(pluginID);
      return p != null;
    }
  }

  @Override
  public void delete(Plugin instance) {
    if (instance != null) {
      deleteOfCache(instance.getPluginID());

      // NOTA: Les traduccions s'esborren automàticament
      super.delete(instance);
    }
  }

  @Override
  public void clearCache() {
    synchronized (pluginsCache) {
      pluginsCache.clear();
    }
  }
  
  
  public void addPluginToCache(Long pluginID, IPlugin pluginInstance) { 
    synchronized (pluginsCache) {
      pluginsCache.put(pluginID, pluginInstance);  
    }
  }
  
  public IPlugin getPluginFromCache(Long pluginID) {
    synchronized (pluginsCache) {
      return  pluginsCache.get(pluginID);  
    }
  }

  @Override
  @PermitAll
  public PluginJPA findByPrimaryKey(Long _ID_) {
    return (PluginJPA) super.findByPrimaryKey(_ID_);
  }

  @Override
  @PermitAll
  public PluginJPA findByPrimaryKey(long _ID_) {
    return (PluginJPA) super.findByPrimaryKey(_ID_);
  }
  
  @Override
  @PermitAll
  public boolean deleteFull(Long pluginID) throws I18NException {

      // Eliminam si no está associat a cap entitat previament
      if (pluginEntitatEjb.count(PluginEntitatFields.PLUGINID.equal(pluginID)) < 1) {

          avisEjb.delete(AvisFields.PLUGINFRONTID.equal(pluginID));

          logCarpetaEjb.delete(LogCarpetaFields.PLUGINID.equal(pluginID));

          // Accesos: Per ara NO s'eliminen ???
          accesEjb.delete(AccesFields.PLUGINID.equal(pluginID));

          delete(pluginID);

          return true;
      }
      return false;
  }

}
