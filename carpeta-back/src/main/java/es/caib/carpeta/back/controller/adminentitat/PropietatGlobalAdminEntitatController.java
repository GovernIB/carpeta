package es.caib.carpeta.back.controller.adminentitat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.carpeta.back.controller.superadmin.PropietatGlobalSuperAdminController;
import es.caib.carpeta.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.carpeta.back.form.webdb.PropietatGlobalForm;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdminEntitatController extends PropietatGlobalSuperAdminController {

    @Override
    protected boolean isSuperAdmin() {
        return true;
    }

}
