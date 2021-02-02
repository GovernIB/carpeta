package es.caib.carpeta.back.controller.adminentitat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.carpeta.back.form.webdb.AccesFilterForm;
import es.caib.carpeta.back.form.webdb.AccesForm;

import es.caib.carpeta.back.controller.superadmin.AccesSuperAdminController;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 26/01/2021
 */

@Controller
@RequestMapping(value = "/adminentitat/acces")
@SessionAttributes(types = { AccesForm.class, AccesFilterForm.class })
public class AccesAdminEntitatController extends AccesSuperAdminController {

    @Override
    protected boolean isSuperAdmin() {
        return false;
    }


}
