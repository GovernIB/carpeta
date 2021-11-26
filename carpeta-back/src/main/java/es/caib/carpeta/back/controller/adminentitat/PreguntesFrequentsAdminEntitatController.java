package es.caib.carpeta.back.controller.adminentitat;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.PreguntesFrequentsController;
import es.caib.carpeta.back.form.webdb.PreguntesFrequentsFilterForm;
import es.caib.carpeta.back.form.webdb.PreguntesFrequentsForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.model.fields.PreguntesFrequentsFields;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/faq")
@SessionAttributes(types = { PreguntesFrequentsForm.class, PreguntesFrequentsFilterForm.class })
public class PreguntesFrequentsAdminEntitatController extends PreguntesFrequentsController {

    @Override
    public String getTileForm() {
        return "preguntesFrequentsFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "preguntesFrequentsListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PreguntesFrequentsAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return PreguntesFrequentsFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public PreguntesFrequentsForm getPreguntesFrequentsForm(PreguntesFrequentsJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        PreguntesFrequentsForm preguntesFrequentsForm = super.getPreguntesFrequentsForm(_jpa,
                __isView, request, mav);

        preguntesFrequentsForm.getPreguntesFrequents()
                .setEntitatID(LoginInfo.getInstance().getEntitatID());

        preguntesFrequentsForm.addHiddenField(ENTITATID);

        return preguntesFrequentsForm;
    }

    @Override
    public PreguntesFrequentsFilterForm getPreguntesFrequentsFilterForm(Integer pagina,
            ModelAndView mav, HttpServletRequest request) throws I18NException {
        PreguntesFrequentsFilterForm preguntesFrequentsFilterForm;
        preguntesFrequentsFilterForm = super.getPreguntesFrequentsFilterForm(pagina, mav, request);

        if (preguntesFrequentsFilterForm.isNou()) {

            preguntesFrequentsFilterForm.addHiddenField(ENTITATID);

            preguntesFrequentsFilterForm.addHiddenField(RESPOSTAID);

            preguntesFrequentsFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(ORDRE) });
            
            preguntesFrequentsFilterForm.setFilterByFields(new ArrayList<Field<?>>());

        }

        return preguntesFrequentsFilterForm;
    }

}
