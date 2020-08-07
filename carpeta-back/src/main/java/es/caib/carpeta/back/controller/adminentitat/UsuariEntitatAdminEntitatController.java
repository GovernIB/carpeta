package es.caib.carpeta.back.controller.adminentitat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.carpeta.back.controller.superadmin.UsuariEntitatSuperAdminController;
import es.caib.carpeta.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariEntitatForm;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/usuarientitat")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatAdminEntitatController extends UsuariEntitatSuperAdminController {
  
    @Override
    public boolean isSuperAdmin() {
        return false;
    }
}
