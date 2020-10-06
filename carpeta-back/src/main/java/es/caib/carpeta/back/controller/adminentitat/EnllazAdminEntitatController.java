package es.caib.carpeta.back.controller.adminentitat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.EnllazController;
import es.caib.carpeta.back.form.webdb.EnllazFilterForm;
import es.caib.carpeta.back.form.webdb.EnllazForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.model.fields.EnllazFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/enllaz")
@SessionAttributes(types = { EnllazForm.class, EnllazFilterForm.class })
public class EnllazAdminEntitatController extends EnllazController {

    @Override
    public String getTileForm() {
        return "enllazFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "enllazListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EnllazAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return EnllazFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public EnllazFilterForm getEnllazFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        EnllazFilterForm enllazFilterForm = super.getEnllazFilterForm(pagina, mav, request);

        if (enllazFilterForm.isNou()) {

            enllazFilterForm.setAddButtonVisible(false);

            enllazFilterForm.addHiddenField(ENTITATID);
        }

        return enllazFilterForm;
    }

    @Override
    public EnllazForm getEnllazForm(EnllazJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        EnllazForm enllazForm = super.getEnllazForm(_jpa, __isView, request, mav);

        if (enllazForm.isNou()) {
            enllazForm.getEnllaz().setEntitatID(LoginInfo.getInstance().getEntitatID());
        }

        enllazForm.addHiddenField(ENTITATID);

        return enllazForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("1", "NACIONAL"));
        /// I18NUtils.tradueix("nacional");
        __tmp.add(new StringKeyValue("2", "LOCAL"));
        __tmp.add(new StringKeyValue("3", "MUNDIAL"));
        return __tmp;
    }

}
