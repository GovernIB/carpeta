package es.caib.carpeta.logic;

import es.caib.carpeta.jpa.AvisJPA;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.logic.utils.I18NLogicUtils;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author anadal
 * @author mgonzalez
 *
 */
@PermitAll
@Stateless
public class UtilitiesForFrontLogicaEJB implements UtilitiesForFrontLogicaLocal {
    
    protected Logger log = Logger.getLogger(this.getClass());

    @EJB(mappedName = es.caib.carpeta.ejb.EntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.EntitatLocal entitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.IdiomaLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.IdiomaLocal idiomaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaLocal.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaLocal pluginCarpetaFrontEjb;

    @EJB(mappedName = PluginEntitatLogicaLocal.JNDI_NAME)
    protected PluginEntitatLogicaLocal pluginEntitatLogicaEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.EnllazLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.EnllazLocal enllazEjb;

    @EJB(mappedName = AvisLogicaLocal.JNDI_NAME)
    protected AvisLogicaLocal avisEjb;

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

        List<Long> pluginsEntitat = pluginEntitatLogicaEjb.getPluginsEntitat(codiEntitat, true);

        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));

        List<PluginInfo> pluginsInfo = new ArrayList<PluginInfo>(plugins.size());

        for (Plugin plugin : plugins) {
            if(plugin.isActiu()) {
                PluginJPA p = (PluginJPA) plugin;

                ICarpetaFrontPlugin cfp = pluginCarpetaFrontEjb.getInstanceByPluginID(p.getPluginID());

                List<AvisJPA> avisos = avisEjb.findActiveByPluginID(p.getPluginID());

                // XYZ ZZZ TODO Ara hi pot haver més d'un avís actiu al mateix temps, només es mostra el de major gravetat
                Long gravetatAvis = (long) 0;
                String missatgeAvis = "";
                if (avisos.size() > 0) {
                    gravetatAvis = (long) avisos.get(0).getGravetat();
                    missatgeAvis = avisos.get(0).getDescripcio().getTraduccio(language).getValor();
                }

                pluginsInfo.add(new PluginInfo(String.valueOf(plugin.getPluginID()),
                        p.getNom().getTraduccio(language).getValor(),
                        p.getDescripcio().getTraduccio(language).getValor(),
                        String.valueOf(cfp.isReactComponent()), gravetatAvis, missatgeAvis));
            }
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
                new OrderBy(EnllazFields.ENLLAZID));
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

    @Override
    public List<AvisJPA> getAvisosByType(String codiEntitat, int avisType) throws I18NException {

       List<AvisJPA> avisos = avisEjb.findActiveAvisos(codiEntitat, avisType);
       return avisos;
    }
    
    
    @Override
    public Map<String, String> getSuportEntitat(String codiEntitat, String lang) throws I18NException {

        Map<String, String> suport = new HashMap<String, String>();

        Entitat entitat;
        {
            List<Entitat> entitats = entitatEjb.select(EntitatFields.CODI.equal(codiEntitat));

            if (entitats == null || entitats.size() == 0) {
                return suport;
            }
            entitat = entitats.get(0);
        }

        Locale loc = new Locale(lang.toLowerCase());

        String[][] valors = {
                { I18NLogicUtils.tradueix(loc, "entitat.suportWeb"), entitat.getSuportWeb() },
                { I18NLogicUtils.tradueix(loc, "entitat.suportTelefon"), entitat.getSuportTelefon() },
                { I18NLogicUtils.tradueix(loc, "entitat.suportEmail"), entitat.getSuportEmail() },
                { I18NLogicUtils.tradueix(loc, "entitat.suportFAQ"), entitat.getSuportFAQ() },
                { I18NLogicUtils.tradueix(loc, "entitat.suportqssi"), entitat.getSuportqssi() },
                { I18NLogicUtils.tradueix(loc, "entitat.suportautenticacio"), entitat.getSuportautenticacio() } 
           };

        for (int i = 0; i < valors.length; i++) {

            if (valors[i][1] != null) {
                suport.put(valors[i][0], valors[i][1]);
            }

        }

        return suport;

    }
    
    
    
    
    
    
    
    

}
