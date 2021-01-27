package es.caib.carpeta.back.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.carpeta.back.controller.common.AbstractCarpetaFrontModuleController;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;


/**
 * Serveix per a que SuperAdmin pugui fer proves del Plugins de CarpetaFront
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AbstractCarpetaFrontModuleController.PLUGINFRONT_PRIVATE_CONTEXTWEB)
public class CarpetaFrontModuleSuperAdminController extends AbstractCarpetaFrontModuleController {


    @Override
    public UserData getUserData(String administrationID) {
        
        UsuariJPA persona = LoginInfo.getInstance().getUsuariPersona();
        
        final String authenticationMethod = null;
        final String qaa = null;
        return new UserData(persona.getNom(), persona.getLlinatge1(), persona.getLlinatge2(), persona.getNif(), authenticationMethod, qaa);
    }
  
}
