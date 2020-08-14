package es.caib.carpeta.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.EntitatFields;

/**
 * 
 * @author anadal
 *
 */
@PermitAll
@Stateless
public class UtilitiesForFrontLogicaEJB implements UtilitiesForFrontLogicaLocal {

    @EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.IdiomaLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.IdiomaLocal idiomaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaLocal.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaLocal pluginCarpetaFrontEjb;

    /**
     * Retorna codi i nom en l'idioma seleccionat
     */
    @Override
    public List<StringKeyValue> getEntitats(String language) throws I18NException {

        List<Entitat> entitats = entitatEjb.select(EntitatFields.ACTIVA.equal(true));

        List<StringKeyValue> skv = new ArrayList<StringKeyValue>(entitats.size());

        for (Entitat entitat : entitats) {

            EntitatJPA e = (EntitatJPA) entitat;

            skv.add(new StringKeyValue(entitat.getCodi(), e.getNom().getTraduccio(language).getValor()));

        }

        return skv;

    }

    /**
     * Retorna codi i nom en l'idioma seleccionat
     */
    @Override
    public List<Idioma> getIdiomes() throws I18NException {

        List<Idioma> idiomes = idiomaEjb.select();

        return idiomes;

    }

    @Override
    public List<PluginInfo> getFrontPlugins(String codiEntitat, String language) throws I18NException {

        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins();

        List<PluginInfo> pluginsInfo = new ArrayList<PluginInfo>(plugins.size());

        for (Plugin plugin : plugins) {
            pluginsInfo.add(new PluginInfo(plugin.getPluginID(),
                    ((PluginJPA) plugin).getNom().getTraduccio(language).getValor(), ""));
            // XYZ ZZZ

            // ((PluginJPA)plugin).getDescripcio().getTraduccio(language).getValor()));
        }

        return pluginsInfo;
    }

}
