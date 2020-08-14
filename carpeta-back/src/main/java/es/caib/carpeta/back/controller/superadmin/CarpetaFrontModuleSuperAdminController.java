package es.caib.carpeta.back.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.carpeta.back.controller.common.AbstractCarpetaFrontModuleController;


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
    public boolean isPublic() {
        return false;
    }
  
}
