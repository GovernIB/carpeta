package es.caib.carpeta.front.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.front.pluginlogin.LoginInfo;
import es.caib.carpeta.front.pluginlogin.PluginLoginLoginIB;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    protected PluginLoginLoginIB pluginLogin = new PluginLoginLoginIB();

    @Override
    public String iniciarSesionAutentificacion(String urlCallBackLoginOk, String urCallBackLoginError,
            String language) throws Exception {

        return pluginLogin.startAuthentication(urlCallBackLoginOk, urCallBackLoginError, language);

    }

    @Override
    public UsuarioClave validarTicketAutentificacion(final String ticket) throws Exception {

        LoginInfo loginInfo = pluginLogin.validateAuthenticationTicket(ticket);

        UsuarioClave usuarioClave = new UsuarioClave();
        usuarioClave.setNombre(loginInfo.getName());
        usuarioClave.setApellido1(loginInfo.getSurname1());
        usuarioClave.setApellido2(loginInfo.getSurname2());
        usuarioClave.setNif(loginInfo.getAdministrationID());
        usuarioClave.setMetodoAutentificacion(loginInfo.getAuthenticationMethod());
        usuarioClave.setQaa(loginInfo.getQaa());
        usuarioClave.setProveedorDeIdentidad(loginInfo.getIdentityProvider()); // clave

        return usuarioClave;

    }

    @Override
    public String iniciarSesionLogout(String urlCallBackLogout, String language) throws Exception {

        String urlLogout = pluginLogin.logout(urlCallBackLogout, language);

        log.info("Url LOGOUT: " + urlLogout);

        return urlLogout;

    }

}
