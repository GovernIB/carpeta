package es.caib.carpeta.logic;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;

import javax.ejb.Stateless;


/**
 *
 * @author anadal
 *
 */
@Stateless
public class PluginDeCarpetaFrontLogicaEJB extends AbstractPluginLogicaEJB<ICarpetaFrontPlugin>
        implements PluginDeCarpetaFrontLogicaLocal {

    @Override
    public int getTipusDePlugin() {
        return Constants.PLUGIN_TIPUS_FRONT;
    }

    @Override
    protected String getName() {
        return "Plugin de Carpeta Front";
    }

}
