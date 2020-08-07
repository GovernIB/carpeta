package es.caib.carpeta.back.controller.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.carpeta.back.controller.webdb.UsuariController;
import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;
/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariSuperAdminController extends UsuariController {
    

    @Override
    public String getTileForm() {
      return "usuariFormSuperAdmin";
    }

    @Override
    public String getTileList() {
      return "usuariListSuperAdmin";
    }


    @Override
    public String getSessionAttributeFilterForm() {
      return "UsuariSuperAdmin_FilterForm";
    }

}
