package es.caib.carpeta.back.controller.adminentitat;


import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import es.caib.carpeta.back.form.webdb.*;

import es.caib.carpeta.back.controller.webdb.LogCarpetaController;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.model.fields.*;

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
        return LogCarpetaFields.ENTITATCODI.equal(LoginInfo.getInstance().getEntitat().getCodi());
    }

    @Override
    public LogCarpetaFilterForm getLogCarpetaFilterForm(Integer pagina, ModelAndView mav,
                                                        HttpServletRequest request) throws I18NException {
        LogCarpetaFilterForm logCarpetaFilterForm = super.getLogCarpetaFilterForm(pagina, mav, request);

        if(logCarpetaFilterForm.isNou()) {
            logCarpetaFilterForm.addHiddenField(ENTITATCODI);
            logCarpetaFilterForm.addHiddenField(LOGID);
            logCarpetaFilterForm.addHiddenField(EXCEPCIO);
            logCarpetaFilterForm.addHiddenField(PETICIO);
            logCarpetaFilterForm.setAddButtonVisible(false);
            logCarpetaFilterForm.setDeleteSelectedButtonVisible(false);
            logCarpetaFilterForm.setDeleteButtonVisible(false);
            logCarpetaFilterForm.setEditButtonVisible(false);
            logCarpetaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-eye",
               "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));
            logCarpetaFilterForm.addGroupByField(TIPUS);
            logCarpetaFilterForm.addGroupByField(ESTAT);
            logCarpetaFilterForm.setOrderBy(LogCarpetaFields.DATAINICI.javaName);
            logCarpetaFilterForm.setOrderAsc(false);

        }

        return logCarpetaFilterForm;
    }

    @Override
    public LogCarpetaForm getLogCarpetaForm(LogCarpetaJPA _jpa,
                                            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        LogCarpetaForm logCarpetaForm = super.getLogCarpetaForm(_jpa, __isView, request, mav);

        if(logCarpetaForm.isNou()){
            logCarpetaForm.getLogCarpeta().setEntitatCodi(LoginInfo.getInstance().getEntitat().getCodi());
        }

        logCarpetaForm.addHiddenField(ENTITATCODI);
        logCarpetaForm.setAllFieldsReadOnly(LogCarpetaFields.ALL_LOGCARPETA_FIELDS);
        logCarpetaForm.setSaveButtonVisible(false);
        logCarpetaForm.setDeleteButtonVisible(false);


        return logCarpetaForm;
    }


    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
                                                         ModelAndView mav, Where where)  throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();


        for (int i = 0; i < Constants.TIPUS_LOG_ALL.length; i++) {
            int v = Constants.TIPUS_LOG_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.tipus." + v)));
        }

        return __tmp;

    }



    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
                                                         ModelAndView mav, Where where)  throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < Constants.ESTAT_LOG_ALL.length; i++) {
            int v = Constants.ESTAT_LOG_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.estat." + v)));
        }
        return __tmp;
    }
}
