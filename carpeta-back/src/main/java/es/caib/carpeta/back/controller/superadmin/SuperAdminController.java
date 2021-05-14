package es.caib.carpeta.back.controller.superadmin;

import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/")
public class SuperAdminController {

    protected final Logger log = Logger.getLogger(getClass());

    protected AuthenticationLogicaService authenticationLogicaEjb;

    @RequestMapping(value = "/buit")
    public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        try {
            authenticationLogicaEjb = EjbManager.getAuthenticationLogicaEJB();

            // Crea auditoria si hi ha canvi de rol a SuperAdmin
            if (!Constants.ROLE_SUPER.equals(WebUtils.getRequiredSessionAttribute(request,"rolBack"))) {
                LoginInfo loginInfo = LoginInfo.getInstance();
                authenticationLogicaEjb.crearAuditoria(null, loginInfo.getUsuariPersona().getUsername());
                WebUtils.setSessionAttribute(request, "rolBack", Constants.ROLE_SUPER);
            }

        } catch (I18NException e) {
            log.error(e.getMessage());
        }


        ModelAndView mav = new ModelAndView("buitSuperAdmin");
        return mav;

    }

    @RequestMapping(value = "/systemproperties")
    public ModelAndView systemProperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ModelAndView mav = new ModelAndView("systemproperties");
        return mav;

    }


}
