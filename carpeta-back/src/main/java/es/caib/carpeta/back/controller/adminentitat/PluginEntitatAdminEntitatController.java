package es.caib.carpeta.back.controller.adminentitat;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.PluginEntitatFilterForm;
import es.caib.carpeta.back.form.webdb.PluginEntitatForm;

import es.caib.carpeta.back.controller.webdb.PluginEntitatController;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.back.utils.OrdenacioPerTraduible;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.model.fields.SeccioFields;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/pluginEntitat")
@SessionAttributes(types = { PluginEntitatForm.class, PluginEntitatFilterForm.class })
public class PluginEntitatAdminEntitatController extends PluginEntitatController {

    @EJB(mappedName = es.caib.carpeta.ejb.PluginService.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginService pluginEjb;

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;

    @Override
    public String getTileForm() {
        return "pluginEntitatFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "pluginEntitatListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PluginEntitatAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return PluginEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request, ModelAndView mav,
            PluginEntitatForm pluginEntitatForm, Where where) throws I18NException {

        Where w;
        if (pluginEntitatForm.isReadOnlyField(PLUGINID)) {
            w = where;
        } else {
            SubQuery<PluginEntitat, Long> subquery = pluginEntitatEjb.getSubQuery(PluginEntitatFields.PLUGINID,
                    PluginEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
            w = Where.AND(where, PluginFields.PLUGINID.notIn(subquery));
        }

        return super.getReferenceListForPluginID(request, mav, w);
    }

    @Override
    public PluginEntitatFilterForm getPluginEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PluginEntitatFilterForm pluginEntitatFilterForm = super.getPluginEntitatFilterForm(pagina, mav, request);

        pluginEntitatFilterForm.addHiddenField(ENTITATID);

        SubQuery<PluginEntitat, Long> subquery = pluginEntitatEjb.getSubQuery(PluginEntitatFields.PLUGINID,
                PluginEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        if (0L == pluginEjb.count(PluginFields.PLUGINID.notIn(subquery))) {
            pluginEntitatFilterForm.setAddButtonVisible(false);
        } else {
            pluginEntitatFilterForm.setAddButtonVisible(true);
        }
        
        if (pluginEntitatFilterForm.isNou()) {
            pluginEntitatFilterForm.setDefaultOrderBy( new OrderBy[] { new OrderBy(PLUGINID)});
            
            pluginEntitatFilterForm.setAllItemsPerPage(new int[] {-1});
            pluginEntitatFilterForm.setItemsPerPage(-1);
            
        }
        

        return pluginEntitatFilterForm;
    }

    @Override
    public PluginEntitatForm getPluginEntitatForm(PluginEntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        PluginEntitatForm pluginEntitatForm = super.getPluginEntitatForm(_jpa, __isView, request, mav);

        pluginEntitatForm.addHiddenField(ENTITATID);

        if (pluginEntitatForm.isNou()) {
            pluginEntitatForm.getPluginEntitat().setEntitatID(LoginInfo.getInstance().getEntitatID());
            pluginEntitatForm.getPluginEntitat().setActiu(true);
        } else {
            pluginEntitatForm.addReadOnlyField(PLUGINID);
        }

        return pluginEntitatForm;
    }

    @Override
    public PluginEntitatJPA create(HttpServletRequest request, PluginEntitatJPA pluginEntitat)
            throws Exception, I18NException, I18NValidationException {
        PluginEntitatJPA pluginEntitatJPA = super.create(request, pluginEntitat);

        try {
            PluginJPA plugin = (PluginJPA) pluginEjb.findByPrimaryKey(pluginEntitatJPA.getPluginID());
            LoginInfo loginInfo = LoginInfo.getInstance();
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_AFEGIR_PLUGIN, loginInfo.getEntitatID(),
                    loginInfo.getUsuariPersona().getUsername(),
                    plugin.getNomTraduccions().get(Configuracio.getDefaultLanguage()).getValor());
        } catch (I18NException e) {
            String msg = "Error creant auditoria " + "(" + e.getMessage() + ")";
            log.error(msg, e);
        }

        return pluginEntitatJPA;
    }

    @Override
    public void delete(HttpServletRequest request, PluginEntitat pluginEntitat) throws Exception, I18NException {
        PluginJPA plugin = (PluginJPA) pluginEjb.findByPrimaryKey(pluginEntitat.getPluginID());
        super.delete(request, pluginEntitat);

        try {
            LoginInfo loginInfo = LoginInfo.getInstance();
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ELIMINAT_PLUGIN, null,
                    loginInfo.getUsuariPersona().getUsername(),
                    plugin.getNomTraduccions().get(Configuracio.getDefaultLanguage()).getValor());
        } catch (I18NException e) {

            String msg = "Error creant auditoria " + "(" + e.getMessage() + ")";
            log.error(msg, e);
        }
    }

    @Override
    public List<StringKeyValue> getReferenceListForSeccioID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        Where w = Where.AND(where, SeccioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        List<StringKeyValue> list = super.getReferenceListForSeccioID(request, mav, w);

        list.add(new StringKeyValue("", I18NUtils.tradueix("seccio.arrel")));

        return list;
    }
    
    
    @Override
    public List<PluginEntitat> executeSelect(ITableManager<PluginEntitat, Long> ejb, Where where,
            final OrderBy[] orderBy, final Integer itemsPerPage, final int inici)
            throws I18NException {
        
        List<PluginEntitat> list = super.executeSelect(pluginEntitatEjb, where, orderBy, itemsPerPage, inici);
        
        final Field<?> field = PluginEntitatFields.PLUGINID;
        Map<String, String> nomPerID = Utils.listToMap(getReferenceListForPluginID(null, null, null));
        
        
        new OrdenacioPerTraduible<PluginEntitat, String>() {
            @Override
            public String getPK(PluginEntitat o1) {
                return String.valueOf(o1.getPluginID());
            }
        }.ordenar(list, field, nomPerID, orderBy);
        
        
        return list;
        
    }
    

    
    

}
