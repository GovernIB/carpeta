package es.caib.carpeta.logic;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_ENTRADA_BACK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_BACK;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariJPA;
//import es.caib.carpeta.back.utils.CarpetaPluginsManager;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.fields.EntitatFields;
import java.util.List;
import java.util.Properties;

/**
 * Created by Fundació BIT.
 *
 * Aquest EJB és un EJB d'utilitat per al procés d'autenticació al backoffice a on s'empran diferents EJB que s'agrupen aquí.
 * Issue #249
 *
 * @author mgonzalez
 * @author anadal (userInformation)
 * Date: 11/01/2021
 */
@Stateless
public class AuthenticationLogicaEJB implements AuthenticationLogicaService {
    
    protected Logger log = Logger.getLogger(AuthenticationLogicaEJB.class);

    @EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
    protected UsuariEntitatLogicaService usuariEntitatLogicaEjb;

    @EJB(mappedName = UsuariLogicaService.JNDI_NAME)
    protected UsuariLogicaService usuariLogicaEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logCarpetaLogicaEjb;



    @Override
    public void crearLog(String descripcio, Long temps, String peticio, Throwable th, String error,String entitatCodi) {
       logCarpetaLogicaEjb.crearLog(descripcio, ESTAT_LOG_ERROR, TIPUS_LOG_AUTENTICACIO_BACK,
               System.currentTimeMillis() - temps, th, error,
               peticio, entitatCodi, null);
    }

    @Override
    public void crearAuditoria(Long entitatID,String username) throws I18NException {
        auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ENTRADA_BACK, entitatID, username, "");
    }

    @Override
    public UsuariJPA findByUsername(String username) throws I18NException{
        return usuariLogicaEjb.findByUsername(username);
    }

    @Override
    public Long executeQueryOne(String defaultEntityCode) throws I18NException{
        return entitatLogicaEjb.executeQueryOne(EntitatFields.ENTITATID,EntitatFields.CODI.equal(defaultEntityCode));
    }

    @Override
    public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException{
        return usuariLogicaEjb.crearUsuari(usuario);
    }

    @Override
    public UsuariEntitatJPA create(UsuariEntitatJPA usuariEntitat) throws I18NException{
        return (UsuariEntitatJPA)usuariEntitatLogicaEjb.create(usuariEntitat);
    }

    @Override
    public List<UsuariEntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException{
      return usuariEntitatLogicaEjb.findAllByUsuariIdWithEntitat(usuarioID);
    }
    
    
    

    public static final String USERINFORMATION_PLUGIN_KEY = Constants.CARPETA_PROPERTY_BASE + "userinformationplugin";

    public static IUserInformationPlugin userInformationPlugin = null;

    public IUserInformationPlugin getUserInformationPluginInstance() throws EJBException { 
        if (userInformationPlugin == null) {
            
            Properties props = Configuracio.getFilesProperties();

            String className = props.getProperty(USERINFORMATION_PLUGIN_KEY);
            Object pluginInstance;
            try {
                pluginInstance = PluginsManager.instancePluginByClassName(className, Constants.CARPETA_PROPERTY_BASE, props);
                
                if (pluginInstance == null) {
                  log.error("pluginInstance RETORNAT és null ");
                }
                
            } catch (Exception th) {
                String msg = "Error no controlat instanciant Plugin de userInformation: " + th.getMessage();
                log.error(msg, th);
                pluginInstance = null;
                throw new EJBException("plugin.donotinstantiateplugin", th);
            }
            
            if (pluginInstance == null) {
                throw new EJBException("plugin.donotinstantiateplugin");
            }
            userInformationPlugin = (IUserInformationPlugin) pluginInstance;
        }
        return userInformationPlugin;
    }
    
    
}
