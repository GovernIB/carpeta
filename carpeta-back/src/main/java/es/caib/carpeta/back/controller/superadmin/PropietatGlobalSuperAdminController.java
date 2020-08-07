package es.caib.carpeta.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.PropietatGlobalController;
import es.caib.carpeta.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.carpeta.back.form.webdb.PropietatGlobalForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.jpa.PropietatGlobalJPA;
import es.caib.carpeta.model.fields.PropietatGlobalFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalSuperAdminController extends PropietatGlobalController {

    @Override
    public String getTileForm() {
        return "propietatGlobalForm" + getName();
    }

    @Override
    public String getTileList() {
        return "propietatGlobalList" + getName();
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PropietatGlobal" + getName() + "_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return isSuperAdmin() ? PropietatGlobalFields.ENTITATID.isNull()
                : PropietatGlobalFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    protected boolean isSuperAdmin() {
        return true;
    }

    protected String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }

    @Override
    public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {

        PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView, request, mav);

        if (propietatGlobalForm.isNou()) {
            if (isSuperAdmin()) {
                propietatGlobalForm.getPropietatGlobal().setEntitatID(null);
                propietatGlobalForm.addHiddenField(ENTITATID);
            } else {
                propietatGlobalForm.getPropietatGlobal().setEntitatID(LoginInfo.getInstance().getEntitatID());
                propietatGlobalForm.addReadOnlyField(ENTITATID);
            }
        }

        return propietatGlobalForm;

    }

    @Override
    public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PropietatGlobalFilterForm propietatGlobalFilterForm = super.getPropietatGlobalFilterForm(pagina, mav, request);

        if (propietatGlobalFilterForm.isNou()) {
            propietatGlobalFilterForm.addHiddenField(ENTITATID);
        }

        return propietatGlobalFilterForm;
    }

}
