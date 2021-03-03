package es.caib.carpeta.front.pluginlogin;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * @author anadal
 *
 */
public interface IPluginLogin extends IPlugin {
    
    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError, String language) throws Exception ;
    
    
    public LoginInfo validateAuthenticationTicket(final String ticket) throws Exception;
    
    
    public String logout(String urlCallBackLogout, String language) throws Exception;
}
