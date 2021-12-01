package es.caib.carpeta.logic;

import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal
 * @author mgonzalez
 *
 */
@Local
public interface UtilitiesForFrontLogicaService {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/UtilitiesForFrontLogicaEJB!es.caib.carpeta.logic.UtilitiesForFrontLogicaService";
    
    public EntitatJPA getEntitat(String codiEntitat) throws I18NException;

    public List<StringKeyValue> getEntitats(String language) throws I18NException;

    public List<EntitatJPA> getEntitatsFull(String language) throws I18NException;

    public List<Idioma> getIdiomes() throws I18NException;
    
    public Long getFrontPluginIDByContext(String pluginContext) throws I18NException;
    
    public PluginInfo getFrontPluginInfo(String language, long pluginID, long entitatID) throws I18NException;
    
    public PluginInfo getFrontPluginInfoByContext(String language, String pluginContext, long entitatID) throws I18NException;

    public List<PluginInfo> getFrontPlugins(Long entitatID, String language, Long seccioID, boolean autenticat) throws I18NException;

    public FileInfo getIconaPlugin(Long pluginID, String language) throws I18NException;

    public List<Enllaz> getEnllazosByType(String codiEntitat, String language, int enllazType, Long seccioID) throws I18NException;
    
    public Fitxer getFileInfo(Long fitxerID) throws I18NException;
    
    public Long getCustomCssEntitat(String codiEntitat) throws I18NException;
    
    public long getIconaEntitat(String codiEntitat) throws I18NException;
    
    public long getLogolateralEntitat(String codiEntitat) throws I18NException;

    public String getTexteInformatiuEntitat(String codiEntitat) throws I18NException;

    public List<Avis> getAvisosByType(String codiEntitat, int avisType) throws I18NException;
    
    public Map<String, String> getSuportEntitat(String codiEntitat, String lang) throws I18NException;

    public FileInfo getIconEntity(Long codiEntitat) throws I18NException;
    
    public List<UsuariEntitatJPA> getEntitatsByNIF(String nif) throws I18NException;

    public List<PreguntesFrequents> getFaqsByEntity(String codiEntitat, String language) throws I18NException;

}
