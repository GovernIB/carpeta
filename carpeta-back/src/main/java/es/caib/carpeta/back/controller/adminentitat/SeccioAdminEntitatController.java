package es.caib.carpeta.back.controller.adminentitat;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.SeccioController;
import es.caib.carpeta.back.form.webdb.SeccioFilterForm;
import es.caib.carpeta.back.form.webdb.SeccioForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.model.fields.SeccioFields;
import es.caib.carpeta.persistence.SeccioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/seccio")
@SessionAttributes(types = { SeccioForm.class, SeccioFilterForm.class })
public class SeccioAdminEntitatController extends SeccioController {

    @Override
    public String getTileForm() {
        return "seccioFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "seccioListAdminEntitat";
    }

    @Override

    public String getSessionAttributeFilterForm() {
        return "SeccioAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return SeccioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public SeccioForm getSeccioForm(SeccioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        SeccioForm seccioForm = super.getSeccioForm(_jpa, __isView, request, mav);

        seccioForm.addHiddenField(ACTIVA);
        seccioForm.addHiddenField(ENTITATID);
        seccioForm.addHiddenField(SECCIOPAREID);

        if (seccioForm.isNou()) {
            seccioForm.getSeccio().setEntitatID(LoginInfo.getInstance().getEntitatID());
            seccioForm.getSeccio().setActiva(true);
            seccioForm.getSeccio().setSeccioPareID(null);
        }

        return seccioForm;
    }

    @Override
    public SeccioFilterForm getSeccioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        SeccioFilterForm seccioFilterForm = super.getSeccioFilterForm(pagina, mav, request);
        if (seccioFilterForm.isNou()) {
            seccioFilterForm.addHiddenField(ACTIVA);
            seccioFilterForm.addHiddenField(ENTITATID);
            seccioFilterForm.addHiddenField(ICONAID);
            seccioFilterForm.addHiddenField(SECCIOPAREID);
        }

        return seccioFilterForm;
    }

}
