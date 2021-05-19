package es.caib.carpeta.logic;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.persistence.TraduccioMapJPA;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;

import java.util.Map;

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

    @Override
    public TitlesInfo getTitlesInfo(long pluginID) {

        PluginJPA plugin = (PluginJPA) this.findByPrimaryKey(pluginID);

        TitlesInfo titlesInfo = new TitlesInfo();
        // ============ TÍTOLS !!!!
        {
            Map<String, TraduccioMapJPA> titles;
            if (plugin.getTitolLlargID() == null) {
                titles = plugin.getNomTraduccions();
            } else {
                titles = plugin.getTitolLlargTraduccions();
            }
            for (String lang : titles.keySet()) {
                titlesInfo.getTitlesByLang().put(lang, titles.get(lang).getValor());
            }
        }
        // ============ SUBTÍTOLS !!!!
        {
            Map<String, TraduccioMapJPA> subtitles;
            if (plugin.getSubtitolLlargID() == null) {
                subtitles = plugin.getDescripcioTraduccions();
            } else {
                subtitles = plugin.getSubtitolLlargTraduccions();
            }

            for (String lang : subtitles.keySet()) {
                titlesInfo.getSubtitlesByLang().put(lang, subtitles.get(lang).getValor());
            }
        }

        return titlesInfo;

    }

}
