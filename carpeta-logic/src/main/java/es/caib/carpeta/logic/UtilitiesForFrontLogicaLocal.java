package es.caib.carpeta.logic;

import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.model.entity.Idioma;
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
public interface UtilitiesForFrontLogicaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/UtilitiesForFrontLogicaEJB!es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal";
    
    public EntitatJPA getEntitat(String codiEntitat) throws I18NException;

    public List<StringKeyValue> getEntitats(String language) throws I18NException;

    public List<Idioma> getIdiomes() throws I18NException;

    public List<PluginInfo> getFrontPlugins(String codiEntitat, String language) throws I18NException;

    public FileInfo getIconaPlugin(Long pluginID, String language) throws I18NException;

    public List<Enllaz> getEnllazosByType(String codiEntitat, String language, int enllazType) throws I18NException;
    
    public Fitxer getFileInfo(Long fitxerID) throws I18NException;
    
    public long getIconaEntitat(String codiEntitat) throws I18NException;
    
    public long getLogolateralEntitat(String codiEntitat) throws I18NException;

    public String getTexteInformatiuEntitat(String codiEntitat) throws I18NException;

    public List<AvisJPA> getAvisosByType(String codiEntitat, int avisType) throws I18NException;
    
    public Map<String, String> getSuportEntitat(String codiEntitat, String lang) throws I18NException;

}
