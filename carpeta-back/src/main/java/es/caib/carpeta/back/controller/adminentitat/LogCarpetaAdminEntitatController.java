package es.caib.carpeta.back.controller.adminentitat;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.controller.superadmin.LogCarpetaSuperAdminController;
import es.caib.carpeta.back.form.webdb.LogCarpetaFilterForm;
import es.caib.carpeta.back.form.webdb.LogCarpetaForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.model.fields.LogCarpetaFields;


/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 13/10/2020
 * @author anadal (herència)
 */

@Controller
@RequestMapping(value = "/adminentitat/logCarpeta")
@SessionAttributes(types = { LogCarpetaForm.class, LogCarpetaFilterForm.class })
public class LogCarpetaAdminEntitatController extends LogCarpetaSuperAdminController {

    @Override
    public String getTileForm() {
        return "logCarpetaFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "logCarpetaListAdminEntitat";
    }

    protected boolean isSuperAdmin() {
        return false;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return LogCarpetaFields.ENTITATCODI.equal(LoginInfo.getInstance().getEntitat().getCodi());
    }

}
