package es.caib.carpeta.front.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.commons.utils.UsuarioClaveRepresentante;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;
import es.caib.carpeta.pluginsib.carpetafront.api.UserDataRepresentative;

/**
 * Serveix per a poder mostrar els plugins al front sense problemes de seguretat
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AbstractCarpetaFrontModuleController.PUBLIC_CONTEXTWEB)
public class CarpetaFrontModulePublicController extends AbstractCarpetaFrontModuleController {


    @Override
    public UserData getUserData(String administrationID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserData userData = null;

        if (authentication != null) {

            Object principal = authentication.getPrincipal();

            if (principal != null && principal instanceof UsuarioAutenticado) {

                UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) principal;                
                UsuarioClave uc = usuarioAutenticado.getUsuarioClave();
                
                UserDataRepresentative representative;
                {
                    UsuarioClaveRepresentante ucr = uc.getUsuarioClaveRepresentante(); 
                    if ( ucr == null) {
                        representative = null;
                    } else {
                        representative = new UserDataRepresentative(ucr.getNombre(), ucr.getApellido1(), ucr.getApellido2(), ucr.getNif());
                    
                    }
                }
               
                userData = new UserData(uc.getNombre(), uc.getApellido1(), uc.getApellido2(), uc.getNif(),
                        uc.getMetodoAutentificacion(), uc.getQaa(), uc.isEmpresa(), representative);
            }
        }

        return userData;
    }

    

}
