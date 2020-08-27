package es.caib.carpeta.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.model.entity.Plugin;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.IPlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

/**
 *
 * @author anadal
 *
 */
public abstract class AbstractPluginLogicaEJB<I extends IPlugin> extends PluginLogicaEJB
        implements AbstractPluginLogicaLocal<I> {

    protected abstract int getTipusDePlugin();

    protected abstract String getName();

    @Override
    public List<Plugin> getAllPlugins() throws I18NException {
        return getAllPlugins(null); 
    }
    
    
    @Override
    public List<Plugin> getAllPlugins(Where w) throws I18NException {
        Where where;
        if (w == null) {
            where = Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));
        } else {
            where = Where.AND(w, TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));
        }
        return select(where);
    }
    

    @Override
    public Where getWhere() {
        return Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true)

        // TODO Elegim plugin entre les genèriques o entre els específics per l'entitat
        // Where.OR(ENTITATID.isNull(), ENTITATID.equal(entitatID))
        );
    }

    @Override
    public I getInstanceByPluginID(long pluginID) throws I18NException {

        IPlugin pluginInstance = getPluginFromCache(pluginID);

        if (pluginInstance == null) {

            PluginJPA plugin = (PluginJPA) findByPrimaryKey(pluginID);

            if (plugin == null) {
                return null;
            }

            Properties prop = new Properties();

            if (plugin.getPropietats() != null && plugin.getPropietats().trim().length() != 0) {
                try {
                    prop.load(new StringReader(plugin.getPropietats()));
                } catch (Exception e) {
                    // TODO Crec que no es cridarà mai
                    e.printStackTrace();
                }
            }

            pluginInstance = (IPlugin) PluginsManager.instancePluginByClassName(plugin.getClasse(),
                    es.caib.carpeta.commons.utils.Constants.CARPETA_PROPERTY_BASE, prop);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiate", getName() + " (" + plugin.getClasse() + ")");
            }

            addPluginToCache(pluginID, pluginInstance);

        }
        return (I) pluginInstance;

    }


    @Override
    public List<I> getPluginInstancesBy(List<Long> filterByPluginID, List<String> filterByPluginCode)
            throws I18NException {

        List<I> plugins = new ArrayList<I>();

        Where where = Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));

        if (filterByPluginID != null && filterByPluginID.size() != 0) {
            where = Where.AND(where, PLUGINID.in(filterByPluginID));
        }

        // TODO XYZ pendent afegir camp codi dins plugin
        // if (filterByPluginCode != null && filterByPluginCode.size() != 0) {
        // where = Where.AND(where, CODI.in(filterByPluginID));
        // }

        List<Plugin> modulsdefirma = select(where);

        for (Plugin mf : modulsdefirma) {
            plugins.add(getInstanceByPluginID(mf.getPluginID()));
        }

        return plugins;

    }
    
  
}
