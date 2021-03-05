package es.caib.carpeta.front.controller;

import com.google.gson.Gson;

import org.fundaciobit.genapp.common.i18n.I18NException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static es.caib.carpeta.commons.utils.Constants.TIPUS_ACCES_PLUGIN;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author anadal
 * @author mgonzalez
 *
 */
@Controller
@RequestMapping(value = "/pluginfront", method = RequestMethod.GET)
public class PluginFrontController extends CommonFrontController {

    @Autowired
    protected SesionHttp sesionHttp;

    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    UtilitiesForFrontLogicaService utilsEjb;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logLogicaEjb;

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesLogicaEjb;


    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value = {"", "/" }, method = RequestMethod.GET)
    public ModelAndView showAllPlugins(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        ModelAndView mav = new ModelAndView("plugins"); // => /src/main/webapp/WEB-INF/views/pages/plugins.jsp

        try {

            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();
            
            final Long seccioID = null;

            List<PluginInfo> plugins = utilsEjb.getFrontPlugins(codiEntitat, lang, seccioID);

            mav.addObject("plugins", plugins);

        } catch (Throwable e) {
            processException(e, response);
        }

        return mav;

    }


    @RequestMapping(value = "/showplugin/{pluginID}/{idioma}", method = RequestMethod.POST)
    public ModelAndView showNormalPlugin(@PathVariable("pluginID") String pluginID, @PathVariable("idioma") String idioma,
                                         HttpServletRequest request,
                                         HttpServletResponse response, Authentication authentication) throws Exception, I18NException {

        final boolean isreact=false;
        return showPlugin(pluginID,  idioma,
                request,
                response, authentication, isreact);
    }

    @RequestMapping(value = "/showreactplugin/{pluginID}/{idioma}", method = RequestMethod.POST)
    public ModelAndView showReactPlugin(@PathVariable("pluginID") String pluginID, @PathVariable("idioma") String idioma,
                                        HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws Exception, I18NException {
        final boolean isreact=true;
        return showPlugin(pluginID,  idioma,
                request,
                response, authentication, isreact);
    }



    protected ModelAndView showPlugin(String pluginID,  String idioma,
                                      HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication, boolean isreact) throws Exception, I18NException {

        String administrationID = "";
        String baseFront = "";

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication.getPrincipal();
        administrationID = usuarioAutenticado.getUsuarioClave().getNif();


        try {

            Locale loc = new Locale(idioma);
            LocaleContextHolder.setLocale(loc);
            Locale.setDefault(loc);

            //String pluginID = request.getParameter("pluginID");
            //String administrationID= request.getParameter("administrationID");

            //TODO canviar, mirar javascript window.location.href
            String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

            log.info("showPlugin:: urlBase = " + urlBase);
            log.info("showPlugin:: pluginID = " + pluginID);
            log.info("showPlugin:: administrationID = " + administrationID);

            /*
            String contextPath = "/carpetafront";
            //log.info("startTestPlugin:: contextPath => " + contextPath);
            log.info("showPlugin:: URL BASE = " + urlBase);
            URL url = new URL(urlBase);
            int port = url.getPort();
            baseBack = url.getProtocol() + "://" + url.getHost() + (port == -1 ? "" : (":" + port)) + contextPath;
            log.info("showPlugin:: BASE BACK = " + baseBack);
            */
            baseFront = urlBase;

        } catch (Throwable e) {
            processException(e, response);
        }


        String urlToShowPluginPage = startPublicSignatureProcess(request, response, pluginID, administrationID, baseFront,usuarioAutenticado.getUsuarioClave());

        ModelAndView mav;

        if (isreact) {
            mav = new ModelAndView(new RedirectView(urlToShowPluginPage));
        } else {
            final String view = "plugin_contenidor"; // => \WEB-INF\views\pages\plugin_contenidor.jsp

            mav = new ModelAndView(view);
            // mav.addObject("signaturesSetID", signaturesSetID);
            mav.addObject("urlToShowPluginPage", urlToShowPluginPage);
        }


        return mav;

    }



    private String startPublicSignatureProcess(HttpServletRequest request, HttpServletResponse response,
                                               String pluginID, String administrationID, String baseFront, UsuarioClave usuarioClave)
            throws Exception, I18NException {

        String urlToShowPluginPage = null;
        EntitatJPA entitatJPA = entitatEjb.findByCodi(sesionHttp.getEntitat());

        try {

            String context = AbstractCarpetaFrontModuleController.PUBLIC_CONTEXTWEB;

            String administrationIDEncriptat = HibernateFileUtil.encryptString(administrationID);

            urlToShowPluginPage = baseFront + context + "/showplugin/" + pluginID
                    + "/" + response.encodeURL(administrationIDEncriptat)
                    + "/" + Base64.getUrlEncoder().encodeToString(baseFront.getBytes());

            log.info(" urlToShowPluginPage => " + urlToShowPluginPage);

            //ACCESS


            accesLogicaEjb.crearAcces(usuarioClave,TIPUS_ACCES_PLUGIN, entitatJPA.getEntitatID(),Long.parseLong(pluginID),new Timestamp(new Date().getTime()),LocaleContextHolder.getLocale().getLanguage(), InetAddress.getLocalHost().getHostAddress(), true);



        } catch (Throwable e) {

            accesLogicaEjb.crearAcces(usuarioClave,TIPUS_ACCES_PLUGIN, entitatJPA.getEntitatID(),Long.parseLong(pluginID),new Timestamp(new Date().getTime()),LocaleContextHolder.getLocale().getLanguage(), InetAddress.getLocalHost().getHostAddress(), false);

            processException(e, response);
        }

        return urlToShowPluginPage;
    }


    @RequestMapping(value = "/pluginicon/{pluginid}/{idioma}", method = RequestMethod.GET)
    public void  getPluginIcon(@PathVariable("pluginid") Long pluginid,@PathVariable("idioma") String idioma, HttpServletRequest request, HttpServletResponse response) throws Exception, I18NException  {

        try {
            LocaleContextHolder.setLocale(new Locale(idioma));

            Locale.setDefault(new Locale(idioma));

            FileInfo fi = utilsEjb.getIconaPlugin(pluginid, idioma);

            if (fi != null) {
                response.setContentType(fi.getMime());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fi.getName() + "\"");
                response.setContentLength((int) fi.getData().length);

                response.getOutputStream().write(fi.getData());
            }

        } catch (Throwable e) {
            processException(e, response);
        }

    }
    
    
    @RequestMapping(value = "/veureplugins/{seccioID}" , method = RequestMethod.GET)
    public void getAllPlugins(HttpServletRequest request, HttpServletResponse response,
          @PathVariable("seccioID") Long seccioID) throws I18NException {
            
        try {
            
            if (seccioID != null && seccioID == 0) {
                seccioID = null;
            }
            

            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(codiEntitat, lang, seccioID);

            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(pluginsEntitat);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
        
    }
    

    // XYZ ZZZ
    @RequestMapping(value = "/veureplugins" , method = RequestMethod.GET)
    public void getAllPlugins(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        getAllPlugins(request,  response, null);

    }


}
