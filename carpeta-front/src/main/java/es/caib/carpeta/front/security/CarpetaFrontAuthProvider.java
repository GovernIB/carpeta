package es.caib.carpeta.front.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.caib.carpeta.front.utils.SesionHttp;


@Component
public class CarpetaFrontAuthProvider implements AuthenticationProvider {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;
    
    @Autowired
    private SesionHttp sesionHttp;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesLogicaEjb;
    
    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    protected UtilitiesForFrontLogicaService utilsEjb;
    

    @Override
    public Authentication authenticate(Authentication authentication) {

        log.info("Dentro de CarpetaFront AuthProvider");
        
        // Obtiene user/pass
        final String usuario = authentication.getName();
        final String passwd = authentication.getCredentials().toString();

        log.info("Usuario: " + usuario);

        UsuarioClave usuarioClave = null;

        try {

            usuarioClave = securityService.validarTicketAutentificacion(passwd);
          
            if (usuarioClave != null) {
            	
            	// Primer miram a veure si hi ha una entitat a sessio
            	long entitatID = sesionHttp.getEntitatID();
            	
            	if (entitatID < 1L) {
            		// Recuperam les entitats associades a l'usuari si no hi ha entitatID a sessió
	            	List<UsuariEntitatJPA> usuariEntitats = utilsEjb.getEntitatsByNIF(usuarioClave.getNif());
	            	if (usuariEntitats != null && usuariEntitats.size() > 0) {
	            		entitatID = usuariEntitats.get(0).getEntitatID();
	            	}
	            	
	            	if (entitatID < 1L) {
		            	//Agafam l'entitat per defecte
		                PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
		                String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
		                EntitatJPA entitat = utilsEjb.getEntitat(defaultEntityCode);
		                entitatID = entitat.getEntitatID();
		            }
            	}

            	accesLogicaEjb.crearAcces(usuarioClave, TIPUS_ACCES_LOGIN_AUTENTICAT, entitatID, null, 
            			new Timestamp(new Date().getTime()), Locale.getDefault().getLanguage(),
                        sesionHttp.getIpAddress(), true, sesionHttp.getIdSessio());
            }

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
