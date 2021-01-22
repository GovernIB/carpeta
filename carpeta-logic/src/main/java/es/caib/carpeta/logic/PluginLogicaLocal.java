package es.caib.carpeta.logic;

import javax.ejb.Local;

import es.caib.carpeta.jpa.PluginJPA;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginLogicaLocal extends es.caib.carpeta.ejb.PluginLocal {
  
    public static final String JNDI_NAME = "java:app/carpeta-logic/PluginLogicaEJB!es.caib.carpeta.logic.PluginLogicaLocal";
  
    public boolean deleteOfCache(Long pluginID);
  
    public void clearCache();

    @Override
    public PluginJPA findByPrimaryKey(Long _ID_);

    @Override
    public PluginJPA findByPrimaryKey(long _ID_);

}
