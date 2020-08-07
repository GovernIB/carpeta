package es.caib.carpeta.back.controller.superadmin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.EntitatController;
import es.caib.carpeta.back.form.webdb.EntitatFilterForm;
import es.caib.carpeta.back.form.webdb.EntitatForm;
import es.caib.carpeta.model.fields.EntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatSuperAdminController extends EntitatController {

    @Override
    public String getTileForm() {
        return "entitatFormSuperAdmin";
    }

    @Override
    public String getTileList() {
        return "entitatListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EntitatSuperAdmnin_FilterForm";
    }

    @Override
    public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

        if (entitatFilterForm.isNou()) {

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(EntitatFields.ALL_ENTITAT_FIELDS));

            hiddenFields.remove(EntitatFields.NOMID);
            hiddenFields.remove(EntitatFields.CODIDIR3);

            entitatFilterForm.setHiddenFields(hiddenFields);

        }

        return entitatFilterForm;

    }

}
