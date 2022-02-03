package es.caib.carpeta.back.controller.superadmin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
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
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.model.entity.Entitat;
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

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

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
    public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

        if (entitatFilterForm.isNou()) {

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(EntitatFields.ALL_ENTITAT_FIELDS));

            hiddenFields.remove(EntitatFields.NOMID);
            hiddenFields.remove(EntitatFields.CODIDIR3);

            entitatFilterForm.setHiddenFields(hiddenFields);

        }

        return entitatFilterForm;

    }

    @Override
    public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);

        entitatForm.addHiddenField(PLUGINLOGINID);
        // entitatForm.addHiddenField(LOGINTEXTID);
        entitatForm.addHiddenField(CONTEXT);
        entitatForm.addHiddenField(COMMIT);

        return entitatForm;
    }

    @Override
    public void delete(HttpServletRequest request, Entitat entitat)
            throws Exception, I18NException {
        entitatLogicaEjb.deleteFull(entitat);
    }

}
