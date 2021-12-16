package es.caib.carpeta.front.pluginlogin;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        String json = Configuracio.getProperty("es.caib.carpeta.pluginsib.login.mock.logininfo");

        if (json == null || json.trim().length() == 0) {
            throw new Exception(
                    "No s'ha definit la propietat 'es.caib.carpeta.pluginsib.login.mock.logininfo'");
        }

        Gson gson = new GsonBuilder().create();

        try {
            LoginInfo logininfo = gson.fromJson(json, LoginInfo.class);

            return logininfo;
        } catch (Exception e) {
            String msg = "Error no controlat deserialitzant una estructura json de la classe LoginInfo: "
                    + e.getMessage();
            log.error(msg, e);
            throw new Exception(msg, e);
        }

    }

    @Override
    public String logout(String urlCallBackLogout, String language) throws Exception {

        return urlCallBackLogout;
    }

}
