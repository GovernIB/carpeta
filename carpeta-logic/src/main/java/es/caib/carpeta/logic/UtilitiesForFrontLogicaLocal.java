package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.Idioma;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UtilitiesForFrontLogicaLocal {

    public static final String JNDI_NAME = "java:app/carpeta-logic/UtilitiesForFrontLogicaEJB!es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal";

    public List<StringKeyValue> getEntitats(String language) throws I18NException;

    public List<Idioma> getIdiomes() throws I18NException;

    public List<PluginInfo> getFrontPlugins(String codiEntitat, String language) throws I18NException;

}
