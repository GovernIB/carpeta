package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.back.form.webdb.PluginFilterForm;
import es.caib.carpeta.back.form.webdb.PluginForm;

import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.PluginLogicaService;
import es.caib.carpeta.model.entity.Plugin;

/**
 * 
 * @author anadal
 * @author mgonzalez
 *
 */
@Controller
@RequestMapping(value = PluginFrontSuperAdminController.CONTEXTWEB)
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class PluginFrontSuperAdminController extends AbstractPluginSuperAdminController {

    public static final String CONTEXTWEB = "/superadmin/pluginfront";

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logCarpetaLogicaEjb;

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;
    
    @EJB(mappedName = PluginLogicaService.JNDI_NAME)
    protected PluginLogicaService pluginLogicaServiceEjb;

    @Override
    public int getTipus() {
        return Constants.PLUGIN_TIPUS_FRONT_PRIVAT;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("pluginfrontal.tipus.1")));
        __tmp.add(new StringKeyValue("2", I18NUtils.tradueix("pluginfrontal.tipus.2")));
        __tmp.add(new StringKeyValue("3", I18NUtils.tradueix("pluginfrontal.tipus.3")));
        return __tmp;
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);
        Set<Field<?>> hiddens = pluginForm.getHiddenFields();
        hiddens.remove(TITOLLLARGID);
        hiddens.remove(SUBTITOLLLARGID);
        hiddens.remove(TIPUS);
        
        pluginForm.addHelpToField(NOMID, I18NUtils.tradueix("titolcurt.help"));
        pluginForm.addHelpToField(DESCRIPCIOID, I18NUtils.tradueix("subtitolcurt.help"));
        pluginForm.addHelpToField(TITOLLLARGID, I18NUtils.tradueix("titolllarg.help"));
        pluginForm.addHelpToField(SUBTITOLLLARGID, I18NUtils.tradueix("subtitolllarg.help"));
        
        
        return pluginForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return Where.OR(TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PRIVAT),
                TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PUBLIC),
                TIPUS.equal(Constants.PLUGIN_TIPUS_FRONT_PUBLIC_I_PRIVAT));
    }

    @RequestMapping(value = "/reload/{pluginID}", method = RequestMethod.GET)
    public String reload(@PathVariable("pluginID") Long pluginID, HttpServletRequest request,
            HttpServletResponse response, PluginFilterForm filterForm) throws Exception, I18NException {

        if (!pluginCarpetaFrontEjb.deleteOfCache(pluginID)) {
            // plugin.avis.cache=No s´ha pogut esborrar la instància de la cache o no n´hi
            // havia cap.
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("plugin.avis.cache"));
        }
        ;

        return "redirect:" + getContextWeb() + "/list";

    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

        if (pluginFilterForm.isNou()) {
            pluginFilterForm.addAdditionalButtonForEachItem(
                    new AdditionalButton("fas fa-sync", "reload", getContextWeb() + "/reload/{0}", "btn-warning"));

            pluginFilterForm.setAttachedAdditionalJspCode(true);

            Set<Field<?>> hiddens = pluginFilterForm.getHiddenFields();
            hiddens.remove(CONTEXT);
            hiddens.remove(TIPUS);

            pluginFilterForm.addHiddenField(DESCRIPCIOID);

        }
        return pluginFilterForm;
    }

    @Override
    public void postValidate(HttpServletRequest request, PluginForm pluginForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, pluginForm, result);

        PluginJPA __target__ = pluginForm.getPlugin();

        if (pluginForm.isNou()) { // Creació
            // ================ CREATION

            // Check Unique - no PK
            if (result.getFieldErrorCount(CONTEXT.fullName) == 0) {
                java.lang.String __context = __target__.getContext();
                Long __count_ = null;
                try {
                    __count_ = this.pluginEjb
                            .count(org.fundaciobit.genapp.common.query.Where.AND(CONTEXT.equal(__context)));
                } catch (org.fundaciobit.genapp.common.i18n.I18NException e) {
                    e.printStackTrace();
                }
                ;
                if (__count_ == null || __count_ != 0) {
                    result.rejectValue(CONTEXT.fullName, "genapp.validation.unique",
                            new Object[] { String.valueOf(__context), get(CONTEXT) }, "Contexte repetit"
                    // new
                    // org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__context)),
                    // new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTEXT))
                    );
                }
            }

            // Check Unique - PK no AutoIncrement amb UNA SOLA PK
        } else {
            // ================ UPDATE

            // ====== Check Unique MULTIPLES - EDIT =======

            // Check Unique - no PK
            if (result.getFieldErrorCount(CONTEXT.fullName) == 0) {
                java.lang.String __context = __target__.getContext();
                java.lang.Long __pluginID = __target__.getPluginID();
                Long __count_ = null;
                try {
                    __count_ = pluginEjb.count(org.fundaciobit.genapp.common.query.Where.AND(CONTEXT.equal(__context),
                            PLUGINID.notEqual(__pluginID)));
                } catch (org.fundaciobit.genapp.common.i18n.I18NException e) {
                    e.printStackTrace();
                }
                ;
                if (__count_ == null || __count_ != 0) {
                    result.rejectValue(CONTEXT.fullName, "genapp.validation.unique",
                            new Object[] { String.valueOf(__context), get(CONTEXT) }, "Contexte repetit"
                    // new
                    // org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__context)),
                    // new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTEXT))
                    );
                }
            }

        }

    }

    @Override
    public PluginJPA create(HttpServletRequest request, PluginJPA plugin)
            throws Exception, I18NException, I18NValidationException {
        PluginJPA pluginJPA = super.create(request, plugin);

        try {
            LoginInfo loginInfo = LoginInfo.getInstance();

            auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_AFEGIR_PLUGIN, null,
                    loginInfo.getUsuariPersona().getUsername(),
                    pluginJPA.getNomTraduccions().get(Configuracio.getDefaultLanguage()).getValor());
        } catch (I18NException e) {

            String msg = "Error creant auditoria " + "(" + e.getMessage() + ")";
            log.error(msg, e);
        }

        return pluginJPA;
    }

    @Override
    public void delete(HttpServletRequest request, Plugin plugin) throws Exception, I18NException {
        String nom = findByPrimaryKey(request, plugin.getPluginID()).getNomTraduccions()
                .get(Configuracio.getDefaultLanguage()).getValor();
        
        if (pluginLogicaServiceEjb.deleteFull(plugin.getPluginID())){
        	
        	try {
                LoginInfo loginInfo = LoginInfo.getInstance();
                auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ELIMINAT_PLUGIN, null,
                        loginInfo.getUsuariPersona().getUsername(), nom);
            } catch (I18NException e) {

                String msg = "Error creant auditoria " + "(" + e.getMessage() + ")";
                log.error(msg, e);
            }
        	
        }else {
        	throw new I18NException("plugin.error.associat");
        }
        
    }
    
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, PluginForm pluginForm, Throwable __e) {
        String r = super.getRedirectWhenModified(request, pluginForm, __e);
        if (__e == null) {
            // Actualitzam els títols
            long pluginID = pluginForm.getPlugin().getPluginID();
            try {
                
                if (pluginCarpetaFrontEjb.existsInstanceForPluginID(pluginID)) {
                    ICarpetaFrontPlugin plug = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
                    plug.setTitlesInfo(pluginCarpetaFrontEjb.getTitlesInfo(pluginID));
                    
                    log.info(" XYZ ZZZ  Actualitzades traduccions de títols/subtítols del plugin amb ID "
                            +  pluginID);
                }
            } catch (I18NException e) {
                log.error("Error actualitzant les traduccions de títols/subtítols del plugin amb ID "
                    + pluginID + ": " + e.getMessage(), e);
            }
        }
        
        return r;
      }

}
