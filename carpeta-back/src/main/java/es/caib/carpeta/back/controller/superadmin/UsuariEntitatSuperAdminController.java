package es.caib.carpeta.back.controller.superadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.UsuariEntitatController;
import es.caib.carpeta.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariEntitatForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.UsuariEntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/usuarientitat")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatSuperAdminController extends UsuariEntitatController {

    @Override
    public String getTileForm() {
        return "usuariEntitatForm" + getName();
    }

    @Override
    public String getTileList() {
        return "usuariEntitatList" + getName();
    }

    public boolean isSuperAdmin() {
        return true;
    }

    public String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }

    @Override
    public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        UsuariEntitatFilterForm usuariEntitatFilterForm;
        usuariEntitatFilterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);

        if (usuariEntitatFilterForm.isNou()) {
            if (!isSuperAdmin()) {
              usuariEntitatFilterForm.addHiddenField(ENTITATID);
            }
        }

        return usuariEntitatFilterForm;
    }

    @Override
    public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request, mav);

        if (usuariEntitatForm.isNou()) {
            if (!isSuperAdmin()) {
                usuariEntitatForm.getUsuariEntitat().setEntitatID(LoginInfo.getInstance().getEntitatID());
            }
            usuariEntitatForm.getUsuariEntitat().setActiu(true);
        }
        
        if (!isSuperAdmin()) {
          usuariEntitatForm.addHiddenField(ENTITATID);
        }
        

        return usuariEntitatForm;

    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "UsuariEntitat" + getName() + "_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return isSuperAdmin() ? null : UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        Where w;
        if (isSuperAdmin()) {
            w = where;
        } else {
            w = Where.AND(where, EntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        }

        return super.getReferenceListForEntitatID(request, mav, w);
    }
}
