package es.caib.carpeta.back.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/usuarifullview")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariPersonaFullViewCommonController extends UsuariPersonaCommonController {
    


    @Override
    public String getTileForm() {
        return "usuariFullViewFormCommon";
    }

}
