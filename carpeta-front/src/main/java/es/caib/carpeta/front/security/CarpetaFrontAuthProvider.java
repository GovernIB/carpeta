package es.caib.carpeta.front.security;

import org.fundaciobit.genapp.common.i18n.I18NException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;

import java.util.*;

import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_OK;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_ESTAD_ENTRADA_FRONT_AUTENTICAT;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_FRONT;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.logic.AuditoriaLogicaLocal;
import es.caib.carpeta.logic.EstadisticaLogicaLocal;
import es.caib.carpeta.logic.LogCarpetaLogicaLocal;


@Component
public class CarpetaFrontAuthProvider implements AuthenticationProvider {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @EJB(mappedName = EstadisticaLogicaLocal.JNDI_NAME)
    protected EstadisticaLogicaLocal estadisticaLogicaEjb;

    @EJB(mappedName = AuditoriaLogicaLocal.JNDI_NAME)
    protected AuditoriaLogicaLocal auditoriaLogicaEjb;

    @EJB(mappedName = LogCarpetaLogicaLocal.JNDI_NAME)
    protected LogCarpetaLogicaLocal logCarpetaLogicaEjb;

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

            long temps = System.currentTimeMillis();
            StringBuilder peticio = new StringBuilder();

            peticio.append("Usuari Clave: ").append(usuarioClave.getNif()).append(System.getProperty("line.separator"));
            peticio.append("classe: ").append(getClass().getName()).append(System.getProperty("line.separator"));

            logCarpetaLogicaEjb.crearLog("Autenticació del Front de l'Usuari " + usuarioClave.getNif(), ESTAT_LOG_OK,TIPUS_LOG_AUTENTICACIO_FRONT,System.currentTimeMillis() - temps ,null,"",peticio.toString(),null,null);


            //Estadistica entrada al front autenticada
            List<EstadisticaJPA> estadisticas = estadisticaLogicaEjb.findEstadistica(TIPUS_ESTAD_ENTRADA_FRONT_AUTENTICAT,null,new Date(),null);

            if(estadisticas != null && !estadisticas.isEmpty()) {

                estadisticaLogicaEjb.incrementarComptador(estadisticas.get(0));
            }else{
                estadisticaLogicaEjb.crearEstadistica(null, TIPUS_ESTAD_ENTRADA_FRONT_AUTENTICAT,null);
            }

            //AUDITORIA
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ENTRADA_FRONT_AUTENTICAT,null,null,usuarioClave.getNif(),null);


        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Imposible autenticar usuario: " + usuario);
        }catch (I18NException ie){
            ie.printStackTrace();
            throw new BadCredentialsException("Imposible crear auditoria: " + usuario);
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
