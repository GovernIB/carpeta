package es.caib.carpeta.back.controller.adminentitat;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import es.caib.carpeta.back.form.webdb.AuditoriaFilterForm;
import es.caib.carpeta.back.form.webdb.AuditoriaForm;

import es.caib.carpeta.back.controller.superadmin.AuditoriaSuperAdminController;


/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 26/01/2021
 */

@Controller
@RequestMapping(value = "/adminentitat/auditoria")
@SessionAttributes(types = { AuditoriaForm.class, AuditoriaFilterForm.class })
public class AuditoriaAdminEntitatController extends AuditoriaSuperAdminController {


    @Override
    protected boolean isSuperAdmin() {
        return false;
    }



}
