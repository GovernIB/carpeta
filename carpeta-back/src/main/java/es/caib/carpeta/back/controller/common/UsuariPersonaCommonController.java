package es.caib.carpeta.back.controller.common;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.UsuariController;
import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.model.entity.UsuariEntitat;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.UsuariEntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariPersonaCommonController extends UsuariController {

    @EJB(mappedName = es.caib.carpeta.ejb.UsuariEntitatLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.UsuariEntitatLocal usuariEntitatEjb;

    @Override
    public String getTileForm() {
        return "usuariFormCommon";
    }

    @Override
    public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        UsuariForm usuariForm;
        usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

        if (usuariForm.getUsuari().getUsuariID() != LoginInfo.getInstance().getUsuariPersona().getUsuariID()) {
            mav.setStatus(HttpStatus.FORBIDDEN);
        }

        if (usuariForm.getUsuari().getNif() != null) {
            usuariForm.addReadOnlyField(NIF);
        }

        if (usuariForm.getUsuari().getEmail() != null) {
            usuariForm.addReadOnlyField(EMAIL);
        }
        usuariForm.addReadOnlyField(USERNAME);
        usuariForm.addReadOnlyField(DARRERAENTITAT);

        return usuariForm;

    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public List<StringKeyValue> getReferenceListForDarreraEntitat(HttpServletRequest request, ModelAndView mav,
            UsuariForm usuariForm, Where where) throws I18NException {

        SubQuery<UsuariEntitat, Long> subQuery = usuariEntitatEjb.getSubQuery(UsuariEntitatFields.ENTITATID,
                UsuariEntitatFields.USUARIID.equal(LoginInfo.getInstance().getUsuariPersona().getUsuariID()));

        Where w = Where.AND(where, EntitatFields.ENTITATID.in(subQuery));

        return entitatRefList.getReferenceList(EntitatFields.ENTITATID, w);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
        if (__e == null) {

            LoginInfo.getInstance().setUsuariPersona(usuariForm.getUsuari());

            return "redirect:/";
        } else {
            return getTileForm();
        }
    }

}
