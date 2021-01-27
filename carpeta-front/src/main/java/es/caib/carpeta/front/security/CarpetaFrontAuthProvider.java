package es.caib.carpeta.front.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;

import static es.caib.carpeta.commons.utils.Constants.TIPUS_ACCES_LOGIN_AUTENTICAT;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;


@Component
public class CarpetaFrontAuthProvider implements AuthenticationProvider {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesLogicaEjb;
    
    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    protected UtilitiesForFrontLogicaService utilsEjb;

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

            //Agafam l'entitat per defecte
            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            EntitatJPA entitat = utilsEjb.getEntitat(defaultEntityCode);
            long entitatID = entitat.getEntitatID();
            accesLogicaEjb.crearAcces(usuarioClave, TIPUS_ACCES_LOGIN_AUTENTICAT, entitatID, null, new Timestamp(new Date().getTime()), LocaleContextHolder.getLocale().getLanguage(), InetAddress.getLocalHost().getHostAddress(), true);


        } catch (Exception ie){
            log.error("S'ha produit un error : " + ie.getMessage(), ie);
        }


        UsuarioAutenticado usuarioAutenticado =  new UsuarioAutenticado();
        usuarioAutenticado.setUsuarioClave(usuarioClave);
        

        UsernamePasswordAuthenticationToken upat;
        upat = new UsernamePasswordAuthenticationToken(usuarioAutenticado, authentication.getCredentials(), usuarioAutenticado.getAuthorities());
        
        
        return upat;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
