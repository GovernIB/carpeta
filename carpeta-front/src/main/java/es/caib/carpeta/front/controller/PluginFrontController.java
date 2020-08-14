package es.caib.carpeta.front.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.logic.utils.PluginInfo;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/pluginfront", method = RequestMethod.GET)
public class PluginFrontController {
    
    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;
    
    
    protected final Log log = LogFactory.getLog(getClass());

    // XYZ ZZZ Llegir de constantds
    public static final String PUBLIC_CONTEXTWEB = "/public/carpetafrontmodule";
    
    
    
    @RequestMapping(value = {"", "/" }, method = RequestMethod.GET) 
    public ModelAndView showAllPlugins(HttpServletRequest request, HttpServletResponse response) throws I18NException {
        
        // TODO XYZ ZZZ falta idioma
        String lang = LocaleContextHolder.getLocale().getLanguage();
        // TODO XYZ ZZZ falta entitat
        String codiEntitat = "caib";
        
        List<PluginInfo> plugins = utilsEjb.getFrontPlugins(codiEntitat, lang);
        
        
        ModelAndView mav = new ModelAndView("plugins"); // => /src/main/webapp/WEB-INF/views/pages/plugins.jsp
        
        mav.addObject("plugins", plugins);
        
        return mav;
        
    }
    
    
    
    
    @RequestMapping(value = "/showplugin", method = RequestMethod.POST)
    public ModelAndView showPlugin(
           HttpServletRequest request, HttpServletResponse response) throws Exception, I18NException {
        log.info("ENTRA showPlugin front");
        
        long pluginID = Long.parseLong(request.getParameter("pluginID"));
        String administrationID= request.getParameter("administrationID");
        String urlBase = request.getParameter("urlBase");
        
        log.info("showPlugin:: pluginID = " + pluginID);
        log.info("showPlugin:: administrationID = " + administrationID);
        
        String contextPath = "/carpetaback";
        
        //log.info("startTestPlugin:: contextPath => " + contextPath);
        
        
        log.info("showPlugin:: URL BASE = " + urlBase);

        
        URL url = new URL(urlBase);
        
        int port = url.getPort();
        
        String baseBack = url.getProtocol() + "://" + url.getHost() + (port == -1? "" : (":" +port)) + contextPath; 
        
        log.info("showPlugin:: BASE BACK = " + baseBack);
        
        final String view = "plugin_contenidor"; // => \WEB-INF\views\pages\plugin_contenidor.jsp

        return startPublicSignatureProcess(request, response, view, pluginID, administrationID, baseBack);

     }
    


    private ModelAndView startPublicSignatureProcess(HttpServletRequest request, HttpServletResponse response,
            String view, long pluginID, String administrationID, String baseBack)
            throws Exception, I18NException {
        
        //response.addHeader("X-Frame-Options", "SAMEORIGIN");

        

        String context = PUBLIC_CONTEXTWEB;

        String administrationIDEncriptat = HibernateFileUtil.encryptString(administrationID);

        final String urlToShowPluginPage = baseBack + context + "/showplugin/" + pluginID 
                + "/" + response.encodeURL(administrationIDEncriptat)
                + "/" + Base64.getUrlEncoder().encodeToString(baseBack.getBytes());

        log.info(" urlToShowPluginPage => " + urlToShowPluginPage);

        ModelAndView mav = new ModelAndView(view);
        // mav.addObject("signaturesSetID", signaturesSetID);
        mav.addObject("urlToShowPluginPage", urlToShowPluginPage);

        return mav;
    }
    
}
