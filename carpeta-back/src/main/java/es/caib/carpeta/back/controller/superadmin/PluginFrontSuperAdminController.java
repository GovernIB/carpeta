package es.caib.carpeta.back.controller.superadmin;


import java.net.URL;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.form.webdb.PluginFilterForm;
import es.caib.carpeta.back.form.webdb.PluginForm;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaLocal;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = PluginFrontSuperAdminController.CONTEXTWEB)
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class PluginFrontSuperAdminController extends AbstractPluginSuperAdminController {
    
    
    public static  final String CONTEXTWEB = "/superadmin/pluginfront";

    @EJB(mappedName = PluginDeCarpetaFrontLogicaLocal.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaLocal pluginCarpetaFrontEjb;

    @Override
    public int getTipus() {
        return Constants.PLUGIN_TIPUS_FRONT;
    }
    
    
    
    
   @RequestMapping(value = "/testplugin/{pluginID}", method = RequestMethod.GET)
   public ModelAndView startTestPlugin(@PathVariable("pluginID") Long pluginID,
          HttpServletRequest request, HttpServletResponse response,
          PluginFilterForm filterForm) throws Exception, I18NException {
        
       String administrationID= request.getParameter("administrationID");
       String urlBase = request.getParameter("urlBase");
       
       log.info("startTestPlugin:: administrationID = " + administrationID);
       log.info("startTestPlugin:: URL BASE = " + urlBase);
       String contextPath = request.getContextPath();
       
       log.info("startTestPlugin:: contextPath => " + contextPath);
       
       URL url = new URL(urlBase);
       
       int port = url.getPort();
       
       String base = url.getProtocol() + "://" + url.getHost() + (port == -1? "" : (":" +port)) + contextPath; 
       
       log.info("startTestPlugin:: BASE = " + base);
       
       String view = "testPlugin";
       
       
       return CarpetaFrontModuleSuperAdminController.startPrivateSignatureProcess(request, response, view, pluginID, administrationID, base);

    }
    
   
   
   @RequestMapping(value = "/reload/{pluginID}", method = RequestMethod.GET)
   public String reload(@PathVariable("pluginID") Long pluginID,
          HttpServletRequest request, HttpServletResponse response,
          PluginFilterForm filterForm) throws Exception, I18NException {
       
       if (!pluginCarpetaFrontEjb.deleteOfCache(pluginID)) {
           HtmlUtils.saveMessageError(request, "XYZ ZZZ No s'ha pogut esborrar la instància de la cache o no n´hi havia cap.");
       };

       return "redirect:" + getContextWeb() + "/list";
       
   }
   
   @Override
   public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
           throws I18NException {
       PluginFilterForm pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

       if (pluginFilterForm.isNou()) {
           
           pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-play-circle",
                   "test", "javascript:testPlugin({0});", "btn-info"));
           
           
           
           pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-sync",
                   "reload", getContextWeb() + "/reload/{0}", "btn-warning"));
           
           
           pluginFilterForm.setAttachedAdditionalJspCode(true);
                   
       }
       
       return pluginFilterForm;
    
   }
}
