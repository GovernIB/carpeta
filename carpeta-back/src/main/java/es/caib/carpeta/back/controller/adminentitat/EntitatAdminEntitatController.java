package es.caib.carpeta.back.controller.adminentitat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.carpeta.back.controller.superadmin.EntitatSuperAdminController;
import es.caib.carpeta.back.form.webdb.EntitatFilterForm;
import es.caib.carpeta.back.form.webdb.EntitatForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.persistence.EntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatAdminEntitatController extends EntitatSuperAdminController {

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isSuperAdmin(){
        return false;
    }

    @Override
    public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);
        
        long currentEntitat = LoginInfo.getInstance().getEntitatID();
        if (entitatForm.getEntitat().getEntitatID() !=  currentEntitat) {
            log.error("S'ha intentat accedir a una entitat que no es la de l'Administrador d'Entitat !!!!");
            mav.setView(new RedirectView("/canviarPipella/superadmin", true));
            return entitatForm;
        }

        entitatForm.addReadOnlyField(NOMID);
        entitatForm.addReadOnlyField(CODI);
        entitatForm.addReadOnlyField(CODIDIR3);
        entitatForm.addReadOnlyField(ACTIVA);
        entitatForm.addReadOnlyField(VERSIO);

        entitatForm.setDeleteButtonVisible(false);
        entitatForm.setCancelButtonVisible(false);

        return entitatForm;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String mostrarLaMevaEntitat(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        java.lang.Long entitatID = LoginInfo.getInstance().getEntitatID();
        return "redirect:" + getContextWeb() + "/" + entitatID + "/edit";
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, EntitatForm entitatForm, Throwable __e) {
        if (__e == null) {
          return "redirect:" + getContextWeb() + "/edit";
        } else {
          return  getTileForm();
        }
      }
}
