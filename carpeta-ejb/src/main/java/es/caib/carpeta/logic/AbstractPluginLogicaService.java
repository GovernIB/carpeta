package es.caib.carpeta.logic;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.model.entity.Plugin;

/**
 * 
 * @author anadal
 */
public interface AbstractPluginLogicaService<I> extends PluginLogicaService {

    /**
     * Retorna tots els plugins actius del tipus I associats no associats a cap
     * entitat
     * 
     * @return llista de plugins
     * @throws I18NException si es produeix qualsevol error
     */
    public List<Plugin> getAllPlugins() throws I18NException;
    
    public List<Plugin> getAllPlugins(Where w) throws I18NException;

    public I getInstanceByPluginID(long pluginID) throws I18NException;
    
    public boolean existsInstanceForPluginID(long pluginID) throws I18NException;

    public List<I> getPluginInstancesBy(List<Long> filterByPluginID, List<String> filterByPluginCode)
            throws I18NException;

    public Where getWhere();

}
