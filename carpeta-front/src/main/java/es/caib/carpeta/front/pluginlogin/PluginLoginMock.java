package es.caib.carpeta.front.pluginlogin;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author anadal
 *
 */
public class PluginLoginMock implements IPluginLogin  {
    
    
     private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError, String language)
            throws Exception {
        
        return urlCallBackLoginOk + "?" + Constants.TICKET_PARAM + "=" + System.currentTimeMillis();
    }

    @Override
    public LoginInfo validateAuthenticationTicket(String ticket) throws Exception {
        
//        log.info("Entramn a validar ... ");
        final LoginInfo loginInfo = new LoginInfo();

        loginInfo.setUsername("");
        loginInfo.setName("Pep");
        loginInfo.setSurname1("Fuster");
        loginInfo.setSurname2("Gonella");

        String dni = Configuracio.getProperty("es.caib.carpeta.pluginsib.login.mock.administrationid");
        if (dni == null) {
          loginInfo.setAdministrationID("30000056Y");
        } else {
          loginInfo.setAdministrationID(dni);
        }
        loginInfo.setAuthenticationMethod("None");
        loginInfo.setQaa(1);
        // S'ha mogut la petició al issue "LoginIB no retorna informació del Provider de l'autenticacio #310 "
        //  TODO este valor no viene informado por LoginIB, tendriamos que hablar
        // con loginIb a ver si pueden enviarnoslo.
        loginInfo.setIdentityProvider("Mock"); 
        return loginInfo;
    }

    @Override
    public String logout(String urlCallBackLogout, String language) throws Exception {

        return urlCallBackLogout;
    }

}
