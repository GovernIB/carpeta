package es.caib.carpeta.back.controller.adminentitat;

import es.caib.carpeta.back.controller.superadmin.AvisSuperAdminController;
import es.caib.carpeta.back.form.webdb.AvisFilterForm;
import es.caib.carpeta.back.form.webdb.AvisForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/* 
 *  @author jagarcia
 */

@Controller
@RequestMapping(value = "/adminentitat/avis")
@SessionAttributes(types = { AvisForm.class, AvisFilterForm.class })
public class AvisAdminEntitatController extends AvisSuperAdminController {

    protected boolean isSuperAdmin() {
        return false;
    }
}