package es.caib.carpeta.logic;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.query.Where;

/**
 *
 * @author anadal
 *
 */
@Stateless
public class PluginDeCarpetaFrontLogicaEJB extends AbstractPluginLogicaEJB<ICarpetaFrontPlugin>
        implements PluginDeCarpetaFrontLogicaService {

    @Override
    protected String getName() {
        return "Plugin de Carpeta Front";
    }

    @Override
    protected Where getWhereTipusDePlugin() {

        return Where.OR(TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PRIVAT),
                TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PUBLIC),
                TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PUBLIC_I_PRIVAT));

    }

}
