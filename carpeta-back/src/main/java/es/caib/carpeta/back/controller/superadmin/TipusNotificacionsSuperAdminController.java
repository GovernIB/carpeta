package es.caib.carpeta.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.NotificacioAppController;
import es.caib.carpeta.back.form.webdb.NotificacioAppFilterForm;
import es.caib.carpeta.back.form.webdb.NotificacioAppForm;
import es.caib.carpeta.persistence.NotificacioAppJPA;
import es.caib.carpeta.persistence.TraduccioJPA;
import es.caib.carpeta.persistence.TraduccioMapJPA;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/tipusnotificacions")
@SessionAttributes(types = { NotificacioAppForm.class, NotificacioAppFilterForm.class })
public class TipusNotificacionsSuperAdminController extends NotificacioAppController {

    @Override
    public String getTileForm() {
        return "notificacioAppFormSuperAdmin";
    }

    @Override
    public String getTileList() {
        return "notificacioAppListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "NotificacioAppSuperAdmin_FilterForm";
    }

    @Override
    public NotificacioAppForm getNotificacioAppForm(NotificacioAppJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        NotificacioAppForm notificacioAppForm = super.getNotificacioAppForm(_jpa, __isView, request,
                mav);

        if (notificacioAppForm.isNou()) {
            NotificacioAppJPA n = notificacioAppForm.getNotificacioApp();

            TraduccioJPA missatge = new TraduccioJPA();
            missatge.addTraduccio("ca",
                    new TraduccioMapJPA("Exemple de missatge amb parametre {1} i paàmetre {2}"));
            n.setMissatge(missatge);

            TraduccioJPA titol = new TraduccioJPA();
            titol.addTraduccio("ca", new TraduccioMapJPA("Exemple de títol amb parametre {0}"));
            n.setTitol(titol);

            n.setAjuda("{0}=Aquí s'ha de posar la descripció del parametre del títol ...\n"
                    + "{1}=Aquí s'ha de posar la descripció del parametre 1 del missatge ...\n"
                    + "{2}=Aquí s'ha de posar la descripció del parametre 2 del missatge ...");

            n.setActiva(true);

            notificacioAppForm.addHelpToField(CODI, "ajuda.nomesmajuscules");
        }

        return notificacioAppForm;
    }

    @Override
    public NotificacioAppFilterForm getNotificacioAppFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        NotificacioAppFilterForm notificacioAppFilterForm = super.getNotificacioAppFilterForm(
                pagina, mav, request);

        if (notificacioAppFilterForm.isNou()) {
            notificacioAppFilterForm.addHiddenField(AJUDA);
            notificacioAppFilterForm.addHiddenField(MISSATGEID);
            notificacioAppFilterForm.addHiddenField(NOTIFICACIOAPPID);
        }

        return notificacioAppFilterForm;
    }

}
