package es.caib.carpeta.front.config;

import es.caib.carpeta.core.service.SecurityService;
import es.caib.carpeta.core.utils.UsuarioClave;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class CarpetaAuthProvider implements AuthenticationProvider {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        log.info("Dentro de CarpetaAuthProvider");
        // Obtiene user/pass
        final String usuario = authentication.getName();
        final String passwd = authentication.getCredentials().toString();

        log.info("Usuario: " + usuario);
        log.info("Password: " + passwd);

        UsuarioClave usuarioClave = null;

        try {
            usuarioClave = securityService.validarTicketAutentificacion(passwd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Imposible autenticar usuario: " + usuario);
        }

        UsuarioAutenticado usuarioAutenticado =  new UsuarioAutenticado();
        usuarioAutenticado.setUsuarioClave(usuarioClave);

        return new UsernamePasswordAuthenticationToken(usuarioAutenticado, authentication.getCredentials(), usuarioAutenticado.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
