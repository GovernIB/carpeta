package es.caib.carpeta.back.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;
import es.caib.carpeta.model.entity.Usuari;
import es.caib.carpeta.persistence.UsuariJPA;


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
    
    @Override
    public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        UsuariForm usuariForm;
        usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);
        
        Usuari usu = usuariForm.getUsuari();
        
        final String username = usu.getUsername();
        
        if (username.equals(usu.getNom())) {
            usuariForm.getReadOnlyFields().remove(NOM);
        }
        
        if (username.equals(usu.getLlinatge1())) {
            usuariForm.getReadOnlyFields().remove(LLINATGE1);
        }
        
        if (usu.getLlinatge2() == null) {
            usuariForm.getReadOnlyFields().remove(LLINATGE1);
            usuariForm.getReadOnlyFields().remove(LLINATGE2);
        }

        if (usu.getEmail() == null) {
            usuariForm.getReadOnlyFields().remove(EMAIL);
        }
        
        return usuariForm;
    }

}
