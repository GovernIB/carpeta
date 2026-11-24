package es.caib.carpeta.front.pluginlogin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.pluginsib.core.v3.IPluginIB;

/**
 * 
 * @author anadal
 *
 */
public interface IPluginLogin extends IPluginIB {

    /**
     * 
     * @param urlCallBackLoginOk
     * @param urCallBackLoginError
     * @param language
     * @return
     * @throws Exception
     */
    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError, String language)
            throws Exception;

    /**
     * 
     * @param ticket
     * @return
     * @throws Exception
     */
    public LoginInfo validateAuthenticationTicket(final String ticket) throws Exception;

    /**
     * 
     * @param urlCallBackLogout
     * @param language
     * @return
     * @throws Exception
     */
    public String logout(String urlCallBackLogout, String language) throws Exception;

    public String getError(HttpServletRequest request, String locale) throws Exception;



}
