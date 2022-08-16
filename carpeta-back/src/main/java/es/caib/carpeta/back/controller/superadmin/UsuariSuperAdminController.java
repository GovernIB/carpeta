package es.caib.carpeta.back.controller.superadmin;

import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.carpeta.back.controller.webdb.UsuariController;
import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;
import es.caib.carpeta.back.utils.PluginUserInformationUtils;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.UsuariLogicaService;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariSuperAdminController extends UsuariController {

    public static final String SESSION_LOAD_USERINFO = "SESSION_LOAD_USERINFO";

    @EJB(mappedName = UsuariLogicaService.JNDI_NAME)
    protected UsuariLogicaService usuariPersonaLogicaEjb;
    
    @EJB(mappedName = AuthenticationLogicaService.JNDI_NAME)
    protected AuthenticationLogicaService authenticationLogicaEjb;

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
    
    
    

    @RequestMapping(value = "/checkusername", method = RequestMethod.GET)
    public ModelAndView checkUsuariGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        request.getSession().removeAttribute(SESSION_LOAD_USERINFO);

        ModelAndView mav = new ModelAndView("crearusuariSuperAdmin");
        mav.addObject("contexte", getContextWeb());
        return mav;

    }

    @RequestMapping(value = "/checkusername", method = RequestMethod.POST)
    public String checkUsuariPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");

        if (username == null || username.trim().length() == 0) {
            // Ha d'introduir un username
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("usuari.avis.falta"));

        } else {

            username = username.trim();

            try {

                UsuariJPA persona;
                persona = usuariPersonaLogicaEjb.findByUsername(username);

                if (persona != null) {

                    // La Persona amb username '" + username + "' ja està donada d'alta al sistema
                    HtmlUtils.saveMessageError(request, I18NUtils.tradueix("usuari.alta.error.jaexisteix", username));

                } else {

                    persona = PluginUserInformationUtils.getUserInfoFromUserInformation(username, authenticationLogicaEjb);

                    if (persona == null) {
                        throw new Exception("Username not found");

                    } else {

                        request.getSession().setAttribute(SESSION_LOAD_USERINFO, persona);
                        // OK
                        return "redirect:" + getContextWeb() + "/new";
                    }
                }

            } catch (I18NException e1) {

                String msg = I18NUtils.tradueix("usuari.alta.error.excepcio", username, I18NUtils.getMessage(e1));

                HtmlUtils.saveMessageError(request, msg);

            } catch (Exception e) {

                String msg = I18NUtils.tradueix("usuari.alta.error.excepcio", username, e.getMessage());

                HtmlUtils.saveMessageError(request, msg);
            }

        }
        return "redirect:" + getContextWeb() + "/checkusername";
    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);
        if (usuariFilterForm.isNou()) {
            usuariFilterForm.addHiddenField(USUARIID);
            usuariFilterForm.addHiddenField(EMAIL);
            usuariFilterForm.addHiddenField(DARRERAENTITAT);
            usuariFilterForm.addHiddenField(IDIOMAID);

            usuariFilterForm.setAddButtonVisible(false);

            if (!Configuracio.isCAIB()) {
              usuariFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle", "usuari.afegir",
                    getContextWeb() + "/checkusername", "btn-success"));
            }

        }
        return usuariFilterForm;
    }

    @Override
    public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        UsuariForm usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

        if (usuariForm.isNou()) {
            UsuariJPA userInfo = (UsuariJPA) request.getSession().getAttribute(SESSION_LOAD_USERINFO);

            if (userInfo == null) {

                mav.setView(new RedirectView(getContextWeb() + "/checkusername", true));
                // Només mostrar USERNAME

                return usuariForm;

            } else {
                // Copia USER INFO a FORM
                usuariForm.setUsuari(userInfo);
                usuariForm.setHiddenFields(new HashSet<Field<?>>());
            }

        } else {
            // EDIT
            usuariForm.setHiddenFields(new HashSet<Field<?>>());
            usuariForm.addReadOnlyField(USERNAME);
        }

        usuariForm.addReadOnlyField(DARRERAENTITAT);

        return usuariForm;
    }

    @Override
    public UsuariJPA create(HttpServletRequest request, UsuariJPA usuari)
            throws I18NException, I18NValidationException {
        UsuariJPA u = (UsuariJPA) super.create(request, usuari);

        request.getSession().removeAttribute(SESSION_LOAD_USERINFO);

        return u;
    }
    
    @RequestMapping(value = "/testerror1", method = RequestMethod.GET)
    public String testError1(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        throw new Exception("Prova d'un error normal (Exception)");

    }

    @RequestMapping(value = "/testerror2", method = RequestMethod.GET)
    public String testError2(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        throw new I18NException("plugin.error.associat");

    }
    

}
