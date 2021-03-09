package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.AuditoriaFilterForm;
import es.caib.carpeta.back.form.webdb.AuditoriaForm;
import es.caib.carpeta.back.form.webdb.EntitatRefList;
import es.caib.carpeta.back.form.webdb.PluginRefList;

import es.caib.carpeta.back.controller.webdb.AuditoriaController;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.logic.UsuariLogicaService;
import es.caib.carpeta.model.entity.Auditoria;
import es.caib.carpeta.model.fields.AuditoriaFields;
import es.caib.carpeta.model.fields.EntitatFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez Date: 10/11/2020
 */
@Controller
@RequestMapping(value = "/superadmin/auditoria")
@SessionAttributes(types = { AuditoriaForm.class, AuditoriaFilterForm.class })
public class AuditoriaSuperAdminController extends AuditoriaController {

    @EJB(mappedName = UsuariLogicaService.JNDI_NAME)
    protected UsuariLogicaService usuariPersonaLogicaEjb;

    // References
    @Autowired
    protected PluginRefList pluginRefList;

    @Autowired
    protected EntitatRefList entitatRefList;


    @Override
    public String getTileForm() {
        return "auditoriaForm" + getName();
    }

    @Override
    public String getTileList() {
        return "auditoriaList" + getName();
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Auditoria" + getName() + "_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return isSuperAdmin() ? null : AuditoriaFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    protected boolean isSuperAdmin() {
        return true;
    }

    protected String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }

    @Override
    public AuditoriaFilterForm getAuditoriaFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        AuditoriaFilterForm auditoriaFilterForm = super.getAuditoriaFilterForm(pagina, mav, request);

        if (auditoriaFilterForm.isNou()) {

            if (!isSuperAdmin()) {
                auditoriaFilterForm.addHiddenField(ENTITATID);
            }
            auditoriaFilterForm.addHiddenField(AUDITORIAID);
            auditoriaFilterForm.setAddButtonVisible(false);
            auditoriaFilterForm.setDeleteSelectedButtonVisible(false);
            auditoriaFilterForm.setDeleteButtonVisible(false);
            auditoriaFilterForm.setEditButtonVisible(false);

            /*
             * auditoriaFilterForm.addGroupByField(TIPUS);
             * auditoriaFilterForm.addGroupByField(ENTITATID);
             */

            auditoriaFilterForm.setOrderBy(AuditoriaFields.DATAAUDIT.javaName);
            auditoriaFilterForm.setOrderAsc(false);
            auditoriaFilterForm.setVisibleMultipleSelection(false);
        }
        return auditoriaFilterForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < Constants.TIPUS_AUDITORIA_ALL.length; i++) {
            int v = Constants.TIPUS_AUDITORIA_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("auditoria.tipus." + v)));
        }
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request, ModelAndView mav,
            AuditoriaForm auditoriaForm, Where where) throws I18NException {

        List<StringKeyValue> entitats = entitatRefList.getReferenceList(EntitatFields.ENTITATID, where);

        List<String> entitatIdKeys = new ArrayList<>();
        for (StringKeyValue skv : entitats) {
            entitatIdKeys.add(skv.getKey());
        }
        if (auditoriaForm.getAuditoria().getEntitatID() != null) {
            if (!entitatIdKeys.contains(auditoriaForm.getAuditoria().getEntitatID().toString())) {
                entitats.add(new StringKeyValue(auditoriaForm.getAuditoria().getEntitatID().toString(),
                        I18NUtils.tradueix("entitat.esborrada")));
            }
        }
        return entitats;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request, ModelAndView mav,
            AuditoriaFilterForm auditoriaFilterForm, List<Auditoria> list, Map<Field<?>, GroupByItem> _groupByItemsMap,
            Where where) throws I18NException {

        List<StringKeyValue> entitats = entitatRefList.getReferenceList(EntitatFields.ENTITATID, where);

        List<String> entitatIdKeys = new ArrayList<>();
        for (StringKeyValue skv : entitats) {
            entitatIdKeys.add(skv.getKey());
        }
        for (Auditoria auditoria : list) {
            if (auditoria.getEntitatID() != null) {
                if (!entitatIdKeys.contains(auditoria.getEntitatID().toString())) {
                    entitats.add(new StringKeyValue(auditoria.getEntitatID().toString(),
                            I18NUtils.tradueix("entitat.esborrada")));
                }
            }
        }
        return entitats;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where);
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
        return false;
    }

}
