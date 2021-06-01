package es.caib.carpeta.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.persistence.PluginJPA;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginLogicaService extends es.caib.carpeta.ejb.PluginService {
  
    public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginLogicaEJB!es.caib.carpeta.logic.PluginLogicaService";
  
    public boolean deleteOfCache(Long pluginID);
  
    public void clearCache();

    @Override
    public PluginJPA findByPrimaryKey(Long _ID_);

    @Override
    public PluginJPA findByPrimaryKey(long _ID_);
    
    public boolean deleteFull(Long pluginID) throws I18NException;

}
