package es.caib.carpeta.front.controller;

import com.google.gson.Gson;
import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static es.caib.carpeta.commons.utils.Constants.TIPUS_ACCES_PLUGIN;

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

    /*
     * @RequestMapping(value = { "", "/" }, method = RequestMethod.GET) public
     * ModelAndView showAllPlugins(HttpServletRequest request, HttpServletResponse
     * response) throws I18NException {
     * 
     * ModelAndView mav = new ModelAndView("plugins"); // =>
     * /src/main/webapp/WEB-INF/views/pages/plugins.jsp
     * 
     * try {
     * 
     * String lang = LocaleContextHolder.getLocale().getLanguage(); // String
     * codiEntitat = sesionHttp.getEntitat(); Long entitatID =
     * sesionHttp.getEntitatID();
     * 
     * final Long seccioID = null;
     * 
     * List<PluginInfo> plugins = utilsEjb.getFrontPlugins(entitatID, lang,
     * seccioID);
     * 
     * mav.addObject("plugins", plugins);
     * 
     * } catch (Throwable e) { processExceptionRest(e, request, response); }
     * 
     * return mav;
     * 
     * }
     */

    @RequestMapping(value = "/showplugin/{pluginContext}/{idioma}", method = RequestMethod.POST)
    public ModelAndView showNormalPlugin(@PathVariable("pluginContext") String pluginContext,
            @PathVariable("idioma") String idioma, HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws Exception, I18NException {

        Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);
        final boolean isreact = false;
        final String pluginParameter = null;
        return showPlugin(pluginID, idioma, request, response, authentication, isreact,
                pluginParameter);
    }

    @RequestMapping(
            value = "/showreactplugin/{pluginContext}/{idioma}",
            method = RequestMethod.POST)
    public ModelAndView showReactPlugin(@PathVariable("pluginContext") String pluginContext,
            @PathVariable("idioma") String idioma, HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws Exception, I18NException {
        final boolean isreact = true;
        final String pluginParameter = null;
        Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);

        return showPlugin(pluginID, idioma, request, response, authentication, isreact,
                pluginParameter);
    }

    @RequestMapping(
            value = "/showplugin/{pluginContext}/{idioma}/p/**", // {pluginParameter}
            method = RequestMethod.POST)
    public ModelAndView showNormalPluginParameter(
            @PathVariable("pluginContext") String pluginContext,
            @PathVariable("idioma") String idioma,
            //@PathVariable("pluginParameter") String pluginParameter,
            HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws Exception, I18NException {
        
        String fullPath = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        
        int pos = fullPath.indexOf("/p/");
        
        // Rest Of URL
        String pluginParameter = fullPath.substring(pos + "/p/".length());
        
        
        log.info("PLUGIN HTMLREST OF URL(pluginParameter) => " + pluginParameter );

        Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);
        final boolean isreact = false;
        return showPlugin(pluginID, idioma, request, response, authentication, isreact,
                pluginParameter);
    }
    
    
    
    
    
    

    @RequestMapping(
            value = "/showreactplugin/{pluginContext}/{idioma}/p/**",
            method = RequestMethod.POST)
    public ModelAndView showReactPluginParameter(
            @PathVariable("pluginContext") String pluginContext,
            @PathVariable("idioma") String idioma,
            //@PathVariable("pluginParameter") String pluginParameter,
            HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws Exception, I18NException {
        final boolean isreact = true;
        
        
        String fullPath = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        
        int pos = fullPath.indexOf("/p/");
        
        // Rest Of URL
        String pluginParameter = fullPath.substring(pos + "/p/".length());
        
        
        log.info("PLUGIN REACT REST OF URL(pluginParameter) => " + pluginParameter );
        

        Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);

        return showPlugin(pluginID, idioma, request, response, authentication, isreact,
                pluginParameter);
    }

    /**
     * 
     * @param pluginID
     * @param idioma
     * @param request
     * @param response
     * @param authentication
     * @param isreact
     * @param pluginParameter
     * @return
     * @throws Exception
     * @throws I18NException
     */
    protected ModelAndView showPlugin(Long pluginID, String idioma, HttpServletRequest request,
            HttpServletResponse response, Authentication authentication, boolean isreact,
            String pluginParameter) throws Exception, I18NException {

        long temps = System.currentTimeMillis();
        try {
            log.info(" ENTRA A showPlugin(" + pluginID +  ", " + authentication + " )");

            final String administrationID;
            final UsuarioClave usuarioClave;

            if (authentication != null) {
                UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) authentication
                        .getPrincipal();
                administrationID = usuarioAutenticado.getUsuarioClave().getNif();
                usuarioClave = usuarioAutenticado.getUsuarioClave();
            } else {
                administrationID = null;
                usuarioClave = null;
            }

            // TODO canviar, mirar javascript window.location.href
            String urlBase = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + request.getContextPath();
            String baseFront = "";
            baseFront = urlBase;

            String urlToShowPluginPage = startPublicSignatureProcess(request, response, pluginID,
                    administrationID, baseFront, usuarioClave, pluginParameter);
            ModelAndView mav = new ModelAndView(new RedirectView(urlToShowPluginPage));

            Locale loc = new Locale(idioma);
            LocaleContextHolder.setLocale(loc);
            Locale.setDefault(loc);

            // String pluginID = request.getParameter("pluginID");
            // String administrationID= request.getParameter("administrationID");

            log.info("showPlugin:: urlBase = " + urlBase);
            log.info("showPlugin:: pluginID = " + pluginID);
            log.info("showPlugin:: administrationID = " + administrationID);

            /*
             * String contextPath = "/carpetafront";
             * //log.info("startTestPlugin:: contextPath => " + contextPath);
             * log.info("showPlugin:: URL BASE = " + urlBase); URL url = new URL(urlBase);
             * int port = url.getPort(); baseBack = url.getProtocol() + "://" +
             * url.getHost() + (port == -1 ? "" : (":" + port)) + contextPath;
             * log.info("showPlugin:: BASE BACK = " + baseBack);
             */

            if (isreact) {
                mav = new ModelAndView(new RedirectView(urlToShowPluginPage));
            } else {

//                log.info("XXXXXXXXXXXXXXXX   PLUGIN HTML 11111111");

                final String view = "plugin_contenidor"; // =>
                                                         // \WEB-INF\views\pages\plugin_contenidor.jsp

                mav = new ModelAndView(view);
                // mav.addObject("signaturesSetID", signaturesSetID);
                mav.addObject("urlToShowPluginPage", urlToShowPluginPage);

//                log.info("XXXXXXXXXXXXXXXX   PLUGIN HTML 22222222222");
            }

            log.info(" FINAL de showPlugin OK !!!!!!!!!!!");

             return mav;

        } catch (Throwable e) {
            /*
             * XYZ ZZZ S?HA DE REGISTRRA UN ERROR NO UN ACCESS EntitatJPA entitatJPA =
             * entitatEjb.findByCodi(sesionHttp.getEntitat());
             * 
             * accesLogicaEjb.crearAcces(usuarioAutenticado.getUsuarioClave(),
             * TIPUS_ACCES_PLUGIN, entitatJPA.getEntitatID(), pluginID, new Timestamp(new
             * Date().getTime()), LocaleContextHolder.getLocale().getLanguage(),
             * InetAddress.getLocalHost().getHostAddress(), false);
             */
            return processExceptionHtmlMav(e, request, response, temps);
        }
        
       

    }

    private String startPublicSignatureProcess(HttpServletRequest request,
            HttpServletResponse response, Long pluginID, String administrationID, String baseFront,
            UsuarioClave usuarioClave, String pluginParameter) throws Exception, I18NException {

        String urlToShowPluginPage = null;
        EntitatJPA entitatJPA = entitatEjb.findByCodi(sesionHttp.getEntitat());

//        try {

        // /pluginfront/showplugin/registre32/ca/p/GOIB-E-155%2F2021
        log.info("startPublicSignatureProcess => pluginParameter: ]" + pluginParameter + "[");

        String context = AbstractCarpetaFrontModuleController.PUBLIC_CONTEXTWEB;

        if (administrationID == null) {
            // XYZ ZZZ
            administrationID = "12345678Z";
        }

        String administrationIDEncriptat = HibernateFileUtil.encryptString(administrationID);

        if (pluginParameter == null) {
            urlToShowPluginPage = baseFront + context + "/showplugin/" + pluginID + "/"
                    + response.encodeURL(administrationIDEncriptat) + "/"
                    + Base64.getUrlEncoder().encodeToString(baseFront.getBytes());
        } else {
            urlToShowPluginPage = baseFront + context + "/showplugin/" + pluginID + "/"
                    + response.encodeURL(administrationIDEncriptat) + "/"
                    + Base64.getUrlEncoder().encodeToString(baseFront.getBytes()) + "/p/"
                    + pluginParameter;
        }

        log.info(" urlToShowPluginPage => " + urlToShowPluginPage);

        try {

            // ACCESS
            if(pluginID != sesionHttp.getAccesPlugin()) {
                accesLogicaEjb.crearAcces(usuarioClave, TIPUS_ACCES_PLUGIN, entitatJPA.getEntitatID(),
                        pluginID, new Timestamp(new Date().getTime()),
                        LocaleContextHolder.getLocale().getLanguage(),
                        request.getRemoteAddr(), true, sesionHttp.getIdSessio());
                sesionHttp.setAccesPlugin(pluginID);
            }

        } catch (Exception e) {
            log.error("S'ha produit un error creant un Accés: " + e.getMessage(), e);
        }

//        } catch (Throwable e) {
//
//            accesLogicaEjb.crearAcces(usuarioClave,TIPUS_ACCES_PLUGIN, entitatJPA.getEntitatID(),pluginID,new Timestamp(new Date().getTime()),LocaleContextHolder.getLocale().getLanguage(), InetAddress.getLocalHost().getHostAddress(), false);
//
//            processExceptionHtml(e, request, response);
//        }

        return urlToShowPluginPage;
    }

    @RequestMapping(value = { "/pluginicon/{pluginid}" }, method = RequestMethod.GET)
    public void getPluginIcon(@PathVariable("pluginid") Long pluginid, HttpServletRequest request,
            HttpServletResponse response) throws Exception, I18NException {
        getPluginIcon(pluginid, null, request, response);
    }

    @RequestMapping(value = { "/pluginicon/{pluginid}/{idioma}" }, method = RequestMethod.GET)
    public void getPluginIcon(@PathVariable("pluginid") Long pluginid,
            @PathVariable("idioma") String idioma, HttpServletRequest request,
            HttpServletResponse response) throws Exception, I18NException {

        try {

            if (idioma == null) {
                idioma = LocaleContextHolder.getLocale().getLanguage();
            }

            // LocaleContextHolder.setLocale(new Locale(idioma));
            // Locale.setDefault(new Locale(idioma));

            FileInfo fi = utilsEjb.getIconaPlugin(pluginid, idioma);

            if (fi != null) {
                response.setContentType(fi.getMime());
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + fi.getName() + "\"");
                response.setContentLength((int) fi.getData().length);

                response.getOutputStream().write(fi.getData());
            }

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }

    @RequestMapping(value = "/veureplugins/{seccioID}", method = RequestMethod.GET)
    public void getAllPlugins(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("seccioID") Long seccioID) throws I18NException {

        try {

            if (seccioID != null && seccioID == 0) {
                seccioID = null;
            }

            String lang = LocaleContextHolder.getLocale().getLanguage();
            // String codiEntitat = sesionHttp.getEntitat();
            Long entitatID = sesionHttp.getEntitatID();

            final boolean autenticat = true;
            List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(entitatID, lang, seccioID,
                    autenticat);

            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(pluginsEntitat);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }

    // XYZ ZZZ
    @RequestMapping(value = "/veureplugins", method = RequestMethod.GET)
    public void getAllPlugins(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        getAllPlugins(request, response, null);

    }

}
