package es.caib.carpeta.back.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serveix per a que la passarela de firma pugui realitzar la seva feina sense 
 * problemes de seguretat
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AbstractCarpetaFrontModuleController.PUBLIC_CONTEXTWEB)
public class CarpetaFrontModuleControllerPublic extends AbstractCarpetaFrontModuleController {

    @Override
    public boolean isPublic() {
        return true;
    }

}
