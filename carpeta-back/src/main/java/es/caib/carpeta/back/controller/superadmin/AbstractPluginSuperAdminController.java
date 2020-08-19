package es.caib.carpeta.back.controller.superadmin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.PluginController;
import es.caib.carpeta.back.form.webdb.PluginFilterForm;
import es.caib.carpeta.back.form.webdb.PluginForm;
import es.caib.carpeta.jpa.PluginJPA;
import es.caib.carpeta.model.fields.PluginFields;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPluginSuperAdminController extends PluginController {

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("plugin.tipus.1")));
        return __tmp;
    }

    @Override
    public String getTileForm() {
        return "pluginFormSuperAdmin";
    }

    @Override
    public String getTileList() {
        return "pluginListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Plugin" + getTipus() + "SuperAdmin_FilterForm";
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);

        pluginForm.addHiddenField(TIPUS);

        if (pluginForm.isNou()) {
            pluginForm.getPlugin().setTipus(getTipus());
            pluginForm.getPlugin().setActiu(true);
        }

        return pluginForm;
    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

        if (pluginFilterForm.isNou()) {
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(PluginFields.ALL_PLUGIN_FIELDS));

            hiddenFields.remove(PluginFields.NOMID);
            hiddenFields.remove(PluginFields.DESCRIPCIOID);
            hiddenFields.remove(PluginFields.CLASSE);
            hiddenFields.remove(PluginFields.ACTIU);

            pluginFilterForm.setHiddenFields(hiddenFields);
        }

        return pluginFilterForm;

    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return TIPUS.equal(getTipus());
    }
    
    @Override
    public final String getEntityNameCode() {
        return "plugin.tipus." + getTipus();
    }



    @Override
    public final String getEntityNameCodePlural() {
        return "plugin.tipus." + getTipus() + ".plural";
    }

    public abstract int getTipus();

}
