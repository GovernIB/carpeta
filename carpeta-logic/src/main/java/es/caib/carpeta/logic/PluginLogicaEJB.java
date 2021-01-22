package es.caib.carpeta.logic;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.IPlugin;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;

import es.caib.carpeta.ejb.PluginEJB;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.model.entity.Plugin;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anadal
 *
 */
public class PluginLogicaEJB extends PluginEJB implements PluginLogicaLocal {

  private static final Map<Long, IPlugin> pluginsCache = new HashMap<Long, IPlugin>();

  @EJB(mappedName = es.caib.carpeta.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.TraduccioLocal traduccioEjb;
  
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


}
