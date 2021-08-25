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
public class PluginLoginMock implements IPluginLogin {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError,
            String language) throws Exception {
        log.info("Start authentication with MOCK");
        return urlCallBackLoginOk + "?" + Constants.TICKET_PARAM + "=" + System.currentTimeMillis();
    }

    @Override
    public LoginInfo validateAuthenticationTicket(String ticket) throws Exception {

//        log.info("Entramn a validar ... ");

        String username = "";
        String name = "Pep";
        String surname1 = "Fuster";
        String surname2 = "Gonella";

        String administrationID = Configuracio
                .getProperty("es.caib.carpeta.pluginsib.login.mock.administrationid");
        if (administrationID == null) {
            administrationID = "30000056Y";
        }
        String authenticationMethod = "None";

        // S'ha mogut la petició al issue "LoginIB no retorna informació del Provider de
        // l'autenticacio #310 "
        // TODO este valor no viene informado por LoginIB, tendriamos que hablar
        // con loginIb a ver si pueden enviarnoslo.
        String identityProvider = "Mock";

        boolean business = false;

        int qaa = 1;
        LoginInfoRepresentative representative = null;

        return new LoginInfo(username, name, surname1, surname2, administrationID,
                authenticationMethod, qaa, identityProvider, business, representative);
    }

    @Override
    public String logout(String urlCallBackLogout, String language) throws Exception {

        return urlCallBackLogout;
    }

}
