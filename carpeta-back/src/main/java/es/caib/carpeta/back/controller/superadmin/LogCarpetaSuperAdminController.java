package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.LogCarpetaFilterForm;
import es.caib.carpeta.back.form.webdb.LogCarpetaForm;
import es.caib.carpeta.back.form.webdb.PluginRefList;

import es.caib.carpeta.back.controller.webdb.LogCarpetaController;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import es.caib.carpeta.model.fields.PluginFields;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 19/10/2020
 * @author anadal (herència)
 */
@Controller
@RequestMapping(value = "/superadmin/logCarpeta")
@SessionAttributes(types = { LogCarpetaForm.class, LogCarpetaFilterForm.class })
public class LogCarpetaSuperAdminController extends LogCarpetaController {

    @Autowired
    protected PluginRefList pluginRefList;

    @Override
    public String getTileForm() {
        return "logCarpetaFormSuperAdmin";
    }

    @Override
    public String getTileList() {
        return "logCarpetaListSuperAdmin";
    }

    protected boolean isSuperAdmin() {
        return true;
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "LogCarpeta" + (isSuperAdmin() ? "SuperAdmin" : "AdminEntitat") + "_FilterForm";
    }

    @Override
    public LogCarpetaFilterForm getLogCarpetaFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        LogCarpetaFilterForm logCarpetaFilterForm = super.getLogCarpetaFilterForm(pagina, mav, request);

        if (logCarpetaFilterForm.isNou()) {
            if (isSuperAdmin()) {
                logCarpetaFilterForm.addGroupByField(ENTITATCODI);
            } else {
                logCarpetaFilterForm.addHiddenField(ENTITATCODI);
            }
            logCarpetaFilterForm.addGroupByField(PLUGINID);

            logCarpetaFilterForm.addHiddenField(LOGID);
            logCarpetaFilterForm.addHiddenField(EXCEPCIO);
            logCarpetaFilterForm.addHiddenField(ERROR);
            logCarpetaFilterForm.addHiddenField(PETICIO);
            logCarpetaFilterForm.setAddButtonVisible(false);
            logCarpetaFilterForm.setDeleteSelectedButtonVisible(false);
            logCarpetaFilterForm.setDeleteButtonVisible(false);
            logCarpetaFilterForm.setEditButtonVisible(false);
            logCarpetaFilterForm.addAdditionalButtonForEachItem(
                    new AdditionalButton("fas fa-eye", "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));
            logCarpetaFilterForm.addGroupByField(TIPUS);
            logCarpetaFilterForm.addGroupByField(ESTAT);
            
//            logCarpetaFilterForm.addGroupByField(DATAINICI);
            logCarpetaFilterForm.addFilterByField(DATAINICI);
            //Calendar cal = Calendar.getInstance(); 
            //logCarpetaFilterForm.setDataIniciFins(new Timestamp(cal.getTimeInMillis()));
            //cal.add(Calendar.MONTH, -1);
            //logCarpetaFilterForm.setDataIniciDesde(new Timestamp(cal.getTimeInMillis()));*/
            
            logCarpetaFilterForm.addFilterByField(IDSESSIO);

            logCarpetaFilterForm.setOrderBy(LogCarpetaFields.DATAINICI.javaName);
            logCarpetaFilterForm.setOrderAsc(false);
            logCarpetaFilterForm.setVisibleMultipleSelection(false);
            logCarpetaFilterForm.setAttachedAdditionalJspCode(true);

        }

        return logCarpetaFilterForm;
    }

    @Override
    public LogCarpetaForm getLogCarpetaForm(LogCarpetaJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        LogCarpetaForm logCarpetaForm = super.getLogCarpetaForm(_jpa, __isView, request, mav);

        if (!isSuperAdmin()) {
            logCarpetaForm.addHiddenField(ENTITATCODI);
        }

        logCarpetaForm.setSaveButtonVisible(false);
        logCarpetaForm.setDeleteButtonVisible(false);

        return logCarpetaForm;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < Constants.TIPUS_LOG_ALL.length; i++) {
            int v = Constants.TIPUS_LOG_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.tipus." + v)));
        }

        return __tmp;

    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < Constants.ESTAT_LOG_ALL.length; i++) {
            int v = Constants.ESTAT_LOG_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("logcarpeta.estat." + v)));
        }
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request, ModelAndView mav,
            LogCarpetaFilterForm logCarpetaFilterForm, List<LogCarpeta> list,
            Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws I18NException {

        List<StringKeyValue> pluginsIds = pluginRefList.getReferenceList(PluginFields.PLUGINID, where);

        List<String> pluginsIdKeys = new ArrayList<>();
        for (StringKeyValue skv : pluginsIds) {
            pluginsIdKeys.add(skv.getKey());
        }

        for (LogCarpeta logCarpeta : list) {
            if (logCarpeta.getPluginID() != null) {
                if (!pluginsIdKeys.contains(logCarpeta.getPluginID().toString())) {
                    pluginsIds.add(new StringKeyValue(logCarpeta.getPluginID().toString(),
                            I18NUtils.tradueix("plugin.esborrat")));
                }
            }
        }

        return pluginsIds;

    }

    @Override
    public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request, ModelAndView mav,
            LogCarpetaForm logCarpetaForm, Where where) throws I18NException {

        List<StringKeyValue> pluginsIds = pluginRefList.getReferenceList(PluginFields.PLUGINID, where);

        // TODO Si no lo contiene lo añade

        List<String> pluginsIdKeys = new ArrayList<>();
        for (StringKeyValue skv : pluginsIds) {
            pluginsIdKeys.add(skv.getKey());
        }
        if (logCarpetaForm.getLogCarpeta().getPluginID() != null) {
            if (!pluginsIdKeys.contains(logCarpetaForm.getLogCarpeta().getPluginID().toString())) {
                pluginsIds.add(new StringKeyValue(logCarpetaForm.getLogCarpeta().getPluginID().toString(),
                        I18NUtils.tradueix("plugin.esborrat")));
            }
        }

        return pluginsIds;
    }
}
