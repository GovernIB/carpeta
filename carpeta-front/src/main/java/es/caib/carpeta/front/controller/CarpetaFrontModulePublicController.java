package es.caib.carpeta.front.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

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
        
        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
        UsuarioClave uc = usuarioAutenticado.getUsuarioClave();

        return new UserData(uc.getNombre(), uc.getApellido1(), uc.getApellido2(), uc.getNif(), uc.getMetodoAutentificacion(), uc.getQaa());
    }

}
