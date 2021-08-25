package es.caib.carpeta.front.pluginlogin;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.commons.utils.UsuarioClaveRepresentante;
import es.caib.carpeta.front.controller.InicioController;
import es.caib.carpeta.front.controller.InicioController.ReactNativeLogin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author anadal
 *
 */
public class PluginLoginReactNative implements IPluginLogin {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String startAuthentication(String urlCallBackLoginOk, String urCallBackLoginError,
            String language) throws Exception {
        log.info("Start authentication with ReactNative");
        return urlCallBackLoginOk + "?" + Constants.TICKET_PARAM + "=" + System.currentTimeMillis();
    }

    @Override
    public LoginInfo validateAuthenticationTicket(String ticket) throws Exception {

//        log.info("Entramn a validar ... ");
        
        HttpServletRequest request;
        request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                        .getRequest();
        
        String loginCode = (String)request.getSession().getAttribute(InicioController.SESSION_LOGIN_CODE);
        
        log.error("PluginReactNative => loginCode = " + loginCode);
        
        ReactNativeLogin rnl = InicioController.getReactNativeLogin(loginCode);
        
        if (rnl == null) {
            log.error("PluginReactNative => ReactNativeLogin IS NULL !!!!!", new Exception());
            return null;
        }
        
        
        UsuarioClave uc = rnl.getUsuarioAutenticado().getUsuarioClave();
        
        if (uc == null) {
            log.error("PluginReactNative => UsuarioClave IS NULL !!!!!", new Exception());
            return null;
        }
        
        

        String identityProvider = "Reactnative Provider";
        
        
        LoginInfoRepresentative representative = null;
        
        UsuarioClaveRepresentante ucr =  uc.getUsuarioClaveRepresentante();
        if (ucr != null) {
            
            
            String name = ucr.getNombre();
            String surname1 = ucr.getApellido1();
            String surname2 = ucr.getApellido2();
            String administrationID = ucr.getNif();
            
            
          representative = new LoginInfoRepresentative(name, surname1, surname2, administrationID);
        }
        

        return new LoginInfo(uc.getNif(), uc.getNombre(), uc.getApellido1(), uc.getApellido2(),
                uc.getNif(), uc.getMetodoAutentificacion(), uc.getQaa(),
                identityProvider, uc.isEmpresa(), representative);
    }

    @Override
    public String logout(String urlCallBackLogout, String language) throws Exception {

        return urlCallBackLogout;
    }

}
