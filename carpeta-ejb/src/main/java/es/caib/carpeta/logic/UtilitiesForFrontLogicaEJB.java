package es.caib.carpeta.logic;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.*;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.io.File;
import java.io.FileInputStream;
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
public class UtilitiesForFrontLogicaEJB implements UtilitiesForFrontLogicaService {

    protected Logger log = Logger.getLogger(this.getClass());

    @EJB(mappedName = es.caib.carpeta.ejb.EntitatService.JNDI_NAME)
    protected es.caib.carpeta.ejb.EntitatService entitatEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.carpeta.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatLogicaEjb;

    @EJB(mappedName = es.caib.carpeta.ejb.EnllazService.JNDI_NAME)
    protected es.caib.carpeta.ejb.EnllazService enllazEjb;

    @EJB(mappedName = AvisLogicaService.JNDI_NAME)
    protected AvisLogicaService avisEjb;

    @EJB(mappedName = FitxerLogicaService.JNDI_NAME)
    protected FitxerLogicaService fitxerLogicaEjb;

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
     * Retorna Entitats en l'idioma seleccionat
     */
    @Override
    public List<EntitatJPA> getEntitatsFull(String language) throws I18NException {

        List<Entitat> entitats = entitatEjb.select(EntitatFields.ACTIVA.equal(true));

        List<EntitatJPA> entities = new ArrayList<>(entitats.size());

        for (Entitat entitat : entitats) {
            entities.add((EntitatJPA) entitat);
        }

        return entities;

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
    public Long getFrontPluginIDByContext(String pluginContext) throws I18NException {

        Long pluginID = pluginCarpetaFrontEjb.executeQueryOne(PluginFields.PLUGINID,
                PluginFields.CONTEXT.equal(pluginContext));
        return pluginID;
    }

    @Override
    public List<PluginInfo> getFrontPlugins(String codiEntitat, String language, Long seccioID) throws I18NException {

        List<Long> pluginsEntitat = pluginEntitatLogicaEjb.getPluginsEntitat(codiEntitat, true, seccioID);

        List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));

        List<PluginInfo> pluginsInfo = new ArrayList<PluginInfo>(plugins.size());

        for (Plugin plugin : plugins) {
            if (plugin.isActiu()) {
                PluginJPA p = (PluginJPA) plugin;

                ICarpetaFrontPlugin cfp = pluginCarpetaFrontEjb.getInstanceByPluginID(p.getPluginID());

                List<Avis> avisos = avisEjb.findActiveByPluginID(p.getPluginID());

                // Ara hi pot haver més d'un avís actiu al mateix temps, només es mostra el de
                // major gravetat,
                // ja que cada tipus d'avis te una forma diferent de visualitzar el plugin al
                // Front
                Long gravetatAvis = (long) 0;
                String missatgeAvis = "";
                if (avisos.size() > 0) {
                    gravetatAvis = (long) avisos.get(0).getGravetat();
                    missatgeAvis = ((AvisJPA) avisos.get(0)).getDescripcio().getTraduccio(language).getValor();
                }

                pluginsInfo.add(new PluginInfo(String.valueOf(plugin.getPluginID()),
                        p.getNom().getTraduccio(language).getValor(),
                        p.getDescripcio().getTraduccio(language).getValor(), p.getContext(),
                        String.valueOf(cfp.isReactComponent()), gravetatAvis, missatgeAvis));
            }
        }

        return pluginsInfo;
    }

    @Override
    public PluginInfo getFrontPluginInfo(String language, Long pluginID) throws I18NException {

        Plugin plugin = pluginCarpetaFrontEjb.findByPrimaryKey(pluginID);

        PluginInfo pluginInfo;

        PluginJPA p = (PluginJPA) plugin;

        pluginInfo = new PluginInfo(String.valueOf(plugin.getPluginID()), p.getNom().getTraduccio(language).getValor(),
                p.getDescripcio().getTraduccio(language).getValor(), p.getContext(), "", 0L, "");

        return pluginInfo;
    }

    @Override
    public FileInfo getIconaPlugin(Long pluginID, String language) throws I18NException {

        FileInfo fi = null;

        // miram si el plugin té associat una icona.
        List<Plugin> pluginItem = pluginCarpetaFrontEjb.select(PluginFields.PLUGINID.equal(pluginID));
        if (pluginItem.size() > 0) {

            try {
                Fitxer f = pluginItem.get(0).getLogo();
                if (f != null) {
                    File file = FileSystemManager.getFile(f.getFitxerID());
                    if (file != null) {
                        FileInputStream fis = new FileInputStream(file);
                        fi = new FileInfo(f.getNom(), f.getMime(), org.apache.commons.io.IOUtils.toByteArray(fis));
                        fis.close();
                    }
                }

            } catch (Exception e) {
                log.error("getIconaPlugin - Error carregant fitxer.");
            }

        }

        // Si no té icona associat, miram el properties
        // Si no está definit a properties, posam el per defecte
        if (fi == null) {
            ICarpetaFrontPlugin plugin = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
            fi = plugin.getIcon(new Locale(language));
        }

        return fi;

    }

    // es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL
    @Override
    public List<Enllaz> getEnllazosByType(String codiEntitat, String language, int enllazType, Long seccioID)
            throws I18NException {

        EnllazQueryPath eqp = new EnllazQueryPath();

        Where w;
        if (seccioID == null) {
            w = EnllazFields.SECCIOID.isNull();
        } else {
            w = EnllazFields.SECCIOID.equal(seccioID);
        }

        List<Enllaz> enllazos = enllazEjb.select(
                Where.AND(eqp.ENTITAT().CODI().equal(codiEntitat), EnllazFields.TIPUS.equal(enllazType), w),
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
    public List<Avis> getAvisosByType(String codiEntitat, int avisType) throws I18NException {

        List<Avis> avisos = avisEjb.findActiveAvisos(codiEntitat, avisType);
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

        // Locale loc = new Locale(lang.toLowerCase());

        if (entitat.getSuportWeb() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_WEB), entitat.getSuportWeb());
        }
        if (entitat.getSuportTelefon() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_TELEFON), entitat.getSuportTelefon());
        }
        if (entitat.getSuportEmail() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_MAIL), entitat.getSuportEmail());
        }
        if (entitat.getSuportFAQ() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_FAQ), entitat.getSuportFAQ());
        }
        if (entitat.getSuportqssi() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_CONSULTA_TECNICA), entitat.getSuportqssi());
        }
        if (entitat.getSuportautenticacio() != null) {
            suport.put(String.valueOf(Constants.TIPUS_SUPORT_AUTENTICACIO), entitat.getSuportautenticacio());
        }

        return suport;

    }

    @Override
    public FileInfo getIconEntity(Long entityID) throws I18NException {

        FileInfo fi = null;

        // miram si l'entitat té associada una icona.
        List<Entitat> entitatItem = entitatEjb.select(EntitatFields.ENTITATID.equal(entityID));
        if (entitatItem.size() > 0) {

            try {
                Fitxer f = entitatItem.get(0).getIcon();
                if (f != null) {
                    File file = FileSystemManager.getFile(f.getFitxerID());
                    if (file != null) {
                        FileInputStream fis = new FileInputStream(file);
                        fi = new FileInfo(f.getNom(), f.getMime(), org.apache.commons.io.IOUtils.toByteArray(fis));
                        fis.close();
                    }
                }

            } catch (Exception e) {
                log.error("getIconaEntitat - Error carregant fitxer.");
            }

        }

        return fi;

    }

}
