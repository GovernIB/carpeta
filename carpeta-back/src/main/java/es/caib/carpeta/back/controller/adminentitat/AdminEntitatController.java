package es.caib.carpeta.back.controller.adminentitat;

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
@RequestMapping(value = "/adminentitat")
public class AdminEntitatController {

    protected final Logger log = Logger.getLogger(getClass());

    protected AuthenticationLogicaService authenticationLogicaEjb;

    @RequestMapping(value = "/buit")
    public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            authenticationLogicaEjb = EjbManager.getAuthenticationLogicaEJB();
            LoginInfo loginInfo = LoginInfo.getInstance();

            // Crea auditoria si hi ha canvi de rol a AdminEntitat
            final Long entitatID = loginInfo.getEntitatID();
            if (!Constants.ROLE_ADMIN.equals(WebUtils.getRequiredSessionAttribute(request,"rolBack"))) {
                authenticationLogicaEjb.crearAuditoria(entitatID, loginInfo.getUsuariPersona().getUsername());
                WebUtils.setSessionAttribute(request, "rolBack", Constants.ROLE_ADMIN);
                WebUtils.setSessionAttribute(request, "entitatAdmin", entitatID);
            }

            // Crea auditoria si hi ha canvi d'entitat pel rol AdminEntitat
            if (entitatID!= null && entitatID.equals(WebUtils.getSessionAttribute(request,"entitatAdmin"))) {
                authenticationLogicaEjb.crearAuditoria(entitatID, loginInfo.getUsuariPersona().getUsername());
                WebUtils.setSessionAttribute(request, "entitatAdmin", entitatID);
            }

        } catch (I18NException e) {
            log.error(e.getMessage());
        }

        ModelAndView mav = new ModelAndView("buitAdminEntitat");
        return mav;

    }

}
