package es.caib.carpeta.back.controller.adminentitat;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

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
import es.caib.carpeta.commons.utils.Constants;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_AFEGIR_PLUGIN;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_AUDIT_ELIMINAT_PLUGIN;
import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.logic.AuditoriaLogicaLocal;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.PluginEntitatFields;
import es.caib.carpeta.model.fields.PluginFields;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/pluginEntitat")
@SessionAttributes(types = { PluginEntitatForm.class, PluginEntitatFilterForm.class })
public class PluginEntitatAdminEntitatController extends PluginEntitatController {

    @EJB(mappedName = es.caib.carpeta.ejb.PluginLocal.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginLocal pluginEjb;

    @EJB(mappedName = AuditoriaLogicaLocal.JNDI_NAME)
    protected AuditoriaLogicaLocal auditoriaLogicaEjb;


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

        SubQuery<PluginEntitat, Long> subquery = pluginEntitatEjb.getSubQuery(PluginEntitatFields.PLUGINID,
                PluginEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        
        

        return super.getReferenceListForPluginID(request, mav, PluginFields.PLUGINID.notIn(subquery));
    }

    @Override
    public PluginEntitatFilterForm getPluginEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PluginEntitatFilterForm pluginEntitatFilterForm = super.getPluginEntitatFilterForm(pagina, mav, request);

        pluginEntitatFilterForm.addHiddenField(ENTITATID);

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
        }

        return pluginEntitatForm;
    }

    @Override
    public PluginEntitatJPA create(HttpServletRequest request, PluginEntitatJPA pluginEntitat)
            throws Exception,I18NException, I18NValidationException {
        PluginEntitatJPA pluginEntitatJPA =super.create(request,pluginEntitat);

        try{
            PluginJPA plugin = (PluginJPA)pluginEjb.findByPrimaryKey(pluginEntitatJPA.getPluginID());
            LoginInfo loginInfo = LoginInfo.getInstance();
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_AFEGIR_PLUGIN ,loginInfo.getEntitatID(),loginInfo.getUsuariPersona().getUsername(), plugin.getNomTraduccions().get(Constants.DEFAULT_LANGUAGE).getValor());
        }catch(I18NException e){
            String msg = "Error creant auditoria "+ "("+  e.getMessage() + ")";
            log.error(msg, e);
        }

        return pluginEntitatJPA;
    }

    @Override
    public void delete(HttpServletRequest request, PluginEntitat pluginEntitat) throws Exception,I18NException {
        PluginJPA plugin = (PluginJPA)pluginEjb.findByPrimaryKey(pluginEntitat.getPluginID());
        super.delete(request,pluginEntitat);

        try{
            LoginInfo loginInfo = LoginInfo.getInstance();
            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ELIMINAT_PLUGIN,null,loginInfo.getUsuariPersona().getUsername(), plugin.getNomTraduccions().get(Constants.DEFAULT_LANGUAGE).getValor());
        }catch(I18NException e){

            String msg = "Error creant auditoria "+ "("+  e.getMessage() + ")";
            log.error(msg, e);
        }
    }


}
