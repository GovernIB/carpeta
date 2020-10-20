package es.caib.carpeta.logic;

import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.utils.Constants;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author anadal
 * @author mgonzalez
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

    @EJB(mappedName = es.caib.carpeta.ejb.PluginEntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginEntitatLocal pluginEntitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.EnllazLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.EnllazLocal enllazEjb;

    @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
    protected FitxerLogicaLocal fitxerLogicaEjb;

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

    @Override
    public EntitatJPA getEntitat(String codiEntitat) throws I18NException {

        List<Entitat> entitats = entitatEjb.select(EntitatFields.CODI.equal(codiEntitat));

        if (entitats == null || entitats.size() == 0) {
            return null;
        }

        EntitatJPA e = (EntitatJPA) entitats.get(0);

        return e;

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

        // XYZ ZZZ TODO Millorar amb una subquery
        List<Long> pluginsEntitat = pluginEntitatEjb.executeQuery(PluginEntitatFields.PLUGINID,
                new PluginEntitatQueryPath().ENTITAT().CODI().equal(codiEntitat));

        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));

        List<PluginInfo> pluginsInfo = new ArrayList<PluginInfo>(plugins.size());

        for (Plugin plugin : plugins) {
            PluginJPA p = (PluginJPA) plugin;

            ICarpetaFrontPlugin cfp = pluginCarpetaFrontEjb.getInstanceByPluginID(p.getPluginID());

            pluginsInfo.add(new PluginInfo(String.valueOf(plugin.getPluginID()),
                    p.getNom().getTraduccio(Constants.IDIOMA_CATALA).getValor(),
                    p.getNom().getTraduccio(Constants.IDIOMA_CASTELLA).getValor(),
                    p.getNom().getTraduccio(Constants.IDIOMA_ANGLES).getValor(),
                    p.getDescripcio().getTraduccio(Constants.IDIOMA_CATALA).getValor(),
                    p.getDescripcio().getTraduccio(Constants.IDIOMA_CASTELLA).getValor(),
                    p.getDescripcio().getTraduccio(Constants.IDIOMA_ANGLES).getValor(),
                    String.valueOf(cfp.isReactComponent())));
        }

        return pluginsInfo;
    }

    @Override
    public FileInfo getIconaPlugin(Long pluginID, String language) throws I18NException {

        ICarpetaFrontPlugin plugin = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
        FileInfo fi = plugin.getIcon(new Locale(language));
        return fi;

    }

    // es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL
    @Override
    public List<Enllaz> getEnllazosByType(String codiEntitat, String language, int enllazType) throws I18NException {

        EnllazQueryPath eqp = new EnllazQueryPath();

        List<Enllaz> enllazos = enllazEjb.select(Where.AND(eqp.ENTITAT().CODI().equal(codiEntitat),
                EnllazFields.TIPUS.equal(enllazType)), 
                new org.fundaciobit.genapp.common.query.OrderBy(EnllazFields.ENLLAZID));
        return enllazos;
    }

    @Override
    public Fitxer getFileInfo(Long fitxerID) throws I18NException {
        return fitxerLogicaEjb.findByPrimaryKey(fitxerID);
    }

    @Override
    public long getIconaEntitat(String codiEntitat) throws I18NException {
        return entitatEjb.executeQueryOne(EntitatFields.ICONID, EntitatFields.CODI.equal(codiEntitat));
    }

    @Override
    public long getLogolateralEntitat(String codiEntitat) throws I18NException {
        return entitatEjb.executeQueryOne(EntitatFields.LOGOLATERALFRONTID, EntitatFields.CODI.equal(codiEntitat));
    }

    @Override
    public String getTexteInformatiuEntitat(String codiEntitat) throws I18NException {
        return entitatEjb.executeQueryOne(EntitatFields.ENTITATDESCFRONT, EntitatFields.CODI.equal(codiEntitat));
    }

}
