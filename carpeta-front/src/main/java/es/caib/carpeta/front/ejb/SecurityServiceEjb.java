package es.caib.carpeta.front.ejb;

import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.commons.utils.UsuarioClave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SecurityServiceEjb implements SecurityService {

    @Autowired
    SecurityService securityService;


    @Override
    public String iniciarSesionAutentificacion(String URL_CALLBACK_LOGIN, 
            String URL_CALLBACK_ERROR, String IDIOMA) throws Exception {
        return securityService.iniciarSesionAutentificacion(URL_CALLBACK_LOGIN, 
                URL_CALLBACK_ERROR, IDIOMA);
    }

    @Override
    public UsuarioClave validarTicketAutentificacion(final String ticket) throws Exception {
        return securityService.validarTicketAutentificacion(ticket);
    }

    @Override
    public String iniciarSesionLogout(String URL_CALLBACK_LOGOUT, String IDIOMA) throws Exception {
        return securityService.iniciarSesionLogout(URL_CALLBACK_LOGOUT, IDIOMA);
    }
}
