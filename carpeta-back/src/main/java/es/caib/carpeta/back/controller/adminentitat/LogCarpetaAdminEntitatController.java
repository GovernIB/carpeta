package es.caib.carpeta.back.controller.adminentitat;


import es.caib.carpeta.back.controller.webdb.LogCarpetaController;
import es.caib.carpeta.back.form.webdb.LogCarpetaFilterForm;
import es.caib.carpeta.back.form.webdb.LogCarpetaForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import es.caib.carpeta.model.fields.PropietatGlobalFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 13/10/2020
 */

@Controller
@RequestMapping(value = "/adminentitat/logCarpeta")
@SessionAttributes(types = { LogCarpetaForm.class, LogCarpetaFilterForm.class })
public class LogCarpetaAdminEntitatController extends LogCarpetaController {


    @Override
    public String getTileForm() {
        return "logCarpetaFormAdminEntitat";
    }


    @Override
    public String getTileList() {
        return "logCarpetaListAdminEntitat";
    }


    @Override
    public String getSessionAttributeFilterForm() {
        return "LogCarpetaAdminEntitat_FilterForm";
    }


    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return LogCarpetaFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public LogCarpetaFilterForm getLogCarpetaFilterForm(Integer pagina, ModelAndView mav,
                                                        HttpServletRequest request) throws I18NException {
        LogCarpetaFilterForm logCarpetaFilterForm = super.getLogCarpetaFilterForm(pagina, mav, request);

        if(logCarpetaFilterForm.isNou()) {
            logCarpetaFilterForm.addHiddenField(ENTITATID);
            logCarpetaFilterForm.addHiddenField(LOGID);
            logCarpetaFilterForm.setAddButtonVisible(false);
            logCarpetaFilterForm.setDeleteSelectedButtonVisible(false);
            logCarpetaFilterForm.setEditButtonVisible(false);
            logCarpetaFilterForm.setDeleteButtonVisible(false);
        }
        return logCarpetaFilterForm;
    }

    @Override
    public LogCarpetaForm getLogCarpetaForm(LogCarpetaJPA _jpa,
                                            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        LogCarpetaForm logCarpetaForm = super.getLogCarpetaForm(_jpa, __isView, request, mav);

        if(logCarpetaForm.isNou()){
            logCarpetaForm.getLogCarpeta().setEntitatID(LoginInfo.getInstance().getEntitatID());
        }

        logCarpetaForm.addHiddenField(ENTITATID);
        return logCarpetaForm;
    }
}
