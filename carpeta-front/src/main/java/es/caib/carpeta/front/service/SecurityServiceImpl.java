package es.caib.carpeta.front.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.commons.utils.UsuarioClaveRepresentante;
import es.caib.carpeta.front.controller.InicioController;
import es.caib.carpeta.front.pluginlogin.IPluginLogin;
import es.caib.carpeta.front.pluginlogin.LoginInfo;
import es.caib.carpeta.front.pluginlogin.LoginInfoRepresentative;
import es.caib.carpeta.front.pluginlogin.PluginLoginLoginIB;
import es.caib.carpeta.front.pluginlogin.PluginLoginReactNative;

/**
 * 
 * @author anadal (Representant)
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    // protected PluginLoginLoginIB pluginLoginInternal = new PluginLoginLoginIB();

    private IPluginLogin pluginLoginInternal = null;
    
    private static final IPluginLogin pluginLoginReactNative = new PluginLoginReactNative();

    private IPluginLogin getPluginLogin() throws Exception {
        
        HttpServletRequest request;
        request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                        .getRequest();
        
        Boolean isReactNative = (Boolean)request.getSession().getAttribute(InicioController.SESSION_DO_LOGIN_WITH_REACTNATIVE);
        
        
        log.info("\n\nEntra a SecurityServiceImpl::getPluginLogin() => SESSION_DO_LOGIN_WITH_REACTNATIVE = " + isReactNative + "\n\n");
        
        
        if (isReactNative != null) {
            return pluginLoginReactNative;
        }

        if (pluginLoginInternal == null) {

            String pluginClass = Configuracio.getLoginClass();

            if (pluginClass == null) {
                pluginLoginInternal = new PluginLoginLoginIB();

            } else {

                Class<?> clazz = Class.forName(pluginClass);

                Object obj = clazz.getDeclaredConstructor().newInstance();

                pluginLoginInternal = (IPluginLogin) obj;

            }

        }

        return pluginLoginInternal;
    }

    @Override
    public String iniciarSesionAutentificacion(String urlCallBackLoginOk, String urCallBackLoginError, String language)
            throws Exception {

        log.info("\n\n\nUrl CALLBACK: " + urlCallBackLoginOk + "\nURL ERROR: " + urCallBackLoginError + "\n\n\n");

        return getPluginLogin().startAuthentication(urlCallBackLoginOk, urCallBackLoginError, language);

    }

    @Override
    public UsuarioClave validarTicketAutentificacion(final String ticket) throws Exception {

        LoginInfo loginInfo = getPluginLogin().validateAuthenticationTicket(ticket);

        LoginInfoRepresentative rep = loginInfo.getRepresentative();

        final UsuarioClaveRepresentante ucr;
        if (rep == null) {
            ucr = null;
        } else {
            ucr = new UsuarioClaveRepresentante(rep.getName(), rep.getSurname1(), rep.getSurname2(),
                    rep.getAdministrationID());
        }

        UsuarioClave usuarioClave = new UsuarioClave(loginInfo.getName(), loginInfo.getSurname1(),
                loginInfo.getSurname2(), loginInfo.getAdministrationID(), loginInfo.isBusiness(), loginInfo.getAuthenticationMethod(),
                loginInfo.getQaa(), loginInfo.getIdentityProvider(), ucr);

        return usuarioClave;

    }

    @Override
    public String iniciarSesionLogout(String urlCallBackLogout, String language) throws Exception {

        String urlLogout = getPluginLogin().logout(urlCallBackLogout, language);

        log.info("Url LOGOUT: " + urlLogout);

        return urlLogout;

    }

}
