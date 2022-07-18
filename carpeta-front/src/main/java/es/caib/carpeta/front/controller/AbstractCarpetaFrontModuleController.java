package es.caib.carpeta.front.controller;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.front.utils.ListenerLogCarpeta;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.UsuariEntitatLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.IListenerLogCarpeta;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Locale;

/**
 *
 * @author anadal
 *
 */

public abstract class AbstractCarpetaFrontModuleController extends HttpServlet {

    protected static Logger log = Logger.getLogger(AbstractCarpetaFrontModuleController.class);

    public static final String PUBLIC_CONTEXTWEB = "/public/carpetafrontmodule";

    public static final String SESSION_URL_BASE = "SESSION_URL_BASE";

    @EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
    protected UsuariEntitatLogicaService usuariEntitatEjb;

    @Autowired
    protected SesionHttp sesionHttp;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = AuthenticationLogicaService.JNDI_NAME)
    protected AuthenticationLogicaService authenticationLogicaEjb;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logCarpetaLogicaEjb;

    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}/{urlBase}") //
    public ModelAndView showCarpetaFrontModuleWithUrlBase(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("pluginID")
            Long pluginID, @PathVariable("administrationIDEncriptat")
            String administrationIDEncriptat, @PathVariable("urlBase")
            String urlBase) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("showCarpetaFrontModule:: pluginID => " + pluginID);
            log.debug("showCarpetaFrontModule:: administrationIDEncriptat => "
                    + administrationIDEncriptat);
            log.debug("showCarpetaFrontModule:: urlBase => " + urlBase);
        }
        String urlBaseDec = new String(Base64.getUrlDecoder().decode(urlBase), "utf-8");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBaseDec);

        final String parameter = null;

        return showCarpetaFrontModule(request, response, pluginID, administrationIDEncriptat,
                parameter);
    }

    /**
     * 
     */
    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}/{urlBase}/p/**") // {parameter}")
    public ModelAndView showCarpetaFrontModuleWithUrlBaseAndParameter(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("pluginID")
            Long pluginID, @PathVariable("administrationIDEncriptat")
            String administrationIDEncriptat, @PathVariable("urlBase")
            String urlBase) throws Exception {

        String fullPath = (String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        String parameter = fullPath.substring(fullPath.lastIndexOf("/p/") + "/p/".length());

        {
            if (log.isDebugEnabled()) {
                log.debug("showCarpetaFrontModuleWithUrlBaseAndParameter FULL " + fullPath);
                log.debug("showCarpetaFrontModuleWithUrlBaseAndParameter PARAMETER " + fullPath);
                log.debug("showCarpetaFrontModule:: pluginID => " + pluginID);
                log.debug("showCarpetaFrontModule:: administrationIDEncriptat => "
                        + administrationIDEncriptat);
                log.debug("showCarpetaFrontModule:: urlBase => " + urlBase);
            }
        }

        String urlBaseDec = new String(Base64.getUrlDecoder().decode(urlBase), "utf-8");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBaseDec);

        return showCarpetaFrontModule(request, response, pluginID, administrationIDEncriptat,
                parameter);
    }

    /**
     * 
     * @param request
     * @param response
     * @param pluginID
     * @param administrationIDEncriptat
     * @param parameter
     * @return
     * @throws Exception
     */
    protected ModelAndView showCarpetaFrontModule(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("pluginID")
            Long pluginID, @PathVariable("administrationIDEncriptat")
            String administrationIDEncriptat, @PathVariable("parameter")
            String parameter) throws Exception {

        String administrationID = HibernateFileUtil.decryptString(administrationIDEncriptat);

        // El plugin existeix?
        ICarpetaFrontPlugin carpetaFrontPlugin;
        try {
            carpetaFrontPlugin = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {
            String msg = I18NUtils.tradueix("plugin.carpetafront.notexist",
                    String.valueOf(pluginID));
            return generateErrorMAV(request, pluginID, msg, e);
        }

        if (carpetaFrontPlugin == null) {
            String msg = I18NUtils.tradueix("plugin.carpetafront.notexist",
                    String.valueOf(pluginID));
            return generateErrorMAV(request, pluginID, msg, null);
        }

        UserData userData = getUserData(administrationID);

        final boolean debug = log.isDebugEnabled();
        if (!carpetaFrontPlugin.isPublic()) {
            if (userData == null) {

                log.error("\n\n CONTROLLER S'ha intentat accedir al plugin " + pluginID
                            + " privat des d'una zona pública\n\n");
                String msg = I18NUtils.tradueix("plugin.carpetafront.notpublic",
                        String.valueOf(pluginID));
                return generateErrorMAV(request, pluginID, msg, null);
            } else {

                // NIF de petició i NIF de userData ha de ser el mateix
                if (!userData.getAdministrationID().equalsIgnoreCase(administrationID)) {
                    final String msg = "S'ha intentat accedir a una consulta del plugin privat amb NIF "
                            + administrationID + " però l'usuari loguejat és "
                            + userData.getAdministrationID();
                    if (debug) {
                        log.error("\n\n " + msg + "\n\n");
                    }
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
                    return generateErrorMAV(request, pluginID, msg, null);
                }
            }
        }

        String relativeBaseServlet = getRelativeBaseServlet();

        String relativeControllerBase = getRelativeControllerBase(request, relativeBaseServlet);
        String relativeRequestPluginBasePath = getRequestPluginBasePath(relativeControllerBase,
                pluginID, URLEncoder.encode(administrationIDEncriptat, "utf-8"));

        String urlBase = (String) request.getSession().getAttribute(SESSION_URL_BASE);

        String absoluteControllerBase = urlBase + relativeBaseServlet;
        String absoluteRequestPluginBasePath = getRequestPluginBasePath(absoluteControllerBase,
                pluginID, URLEncoder.encode(administrationIDEncriptat, "utf-8"));

        {
           String msg= "\n\n\n SHOW PLUGIN ";
            if (debug) {
                msg = msg + "\nadministrationID = " + administrationID;
            }
            msg = msg   + "\nadministrationIDEncriptat = " + administrationIDEncriptat + "\nURL_BASE = "
                        + urlBase + "\nabsoluteControllerBase = " + absoluteControllerBase
                        + "\nabsoluteRequestPluginBasePath = " + absoluteRequestPluginBasePath
                        + "\nrelativeControllerBase = " + relativeControllerBase
                        + "\nrelativeRequestPluginBasePath = " + relativeRequestPluginBasePath
                        + "\n\n\n";
            if (debug) {
                log.debug(msg);
            }
        }

        TitlesInfo titlesInfo = carpetaFrontPlugin.getTitlesInfo();
        if (titlesInfo == null) {
            titlesInfo = this.pluginCarpetaFrontEjb.getTitlesInfo(pluginID);
            carpetaFrontPlugin.setTitlesInfo(titlesInfo);
        }

        if (debug) {
            log.debug("\n Cridant a  carpetaFront.getStartUrl() amb PluginParameter =>  " + parameter);
        }

        String codiEntitat = null;
        if (sesionHttp != null && sesionHttp.getEntitat() != null) {
            codiEntitat = sesionHttp.getEntitat();
            if (debug) {
                log.debug(
                        "IListenerLogCarpeta->codiEntitat = sesionHttp.getEntitat() => " + codiEntitat);
            }
        } else {
            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            if (defaultEntityCode != null) {
                codiEntitat = defaultEntityCode;
                if (debug) {
                    log.debug("IListenerLogCarpeta->codiEntitat = defaultEntityCode => " + codiEntitat);
                }
            }
        }

        IListenerLogCarpeta logCarpeta = new ListenerLogCarpeta(pluginID, codiEntitat,
                logCarpetaLogicaEjb);

        String urlToPluginWebPage = carpetaFrontPlugin.getStartUrl(absoluteRequestPluginBasePath,
                relativeRequestPluginBasePath, request, userData, administrationIDEncriptat,
                parameter, logCarpeta);

        if (debug) {
            log.debug("\n\n\n SHOW PLUGIN 2222 urlToPluginWebPage =>  " + urlToPluginWebPage);
        }

        return new ModelAndView(new RedirectView(urlToPluginWebPage, false));

    }

    public abstract UserData getUserData(String administrationID);

    private String getRelativeBaseServlet() {
        return "/public/carpetafrontservlet";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processServlet(request, response, true);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processServlet(request, response, false);
    }

    protected void processServlet(HttpServletRequest request, HttpServletResponse response,
            boolean isGet) throws ServletException, IOException {

        final boolean debug = log.isDebugEnabled();

        if (debug) {
            log.debug(AbstractCarpetaFrontPlugin.servletRequestInfoToStr(request));
        }

        // uri =
        // /common/carpetafrontservlet/requestPlugin/[[pluginid]]/[[administrationIDEncripted]]//[[operacio]]
        String uri = request.getRequestURI();
        if (debug) {
            log.debug(" uri = " + uri);
        }
        // Si es vol canviar això, anar a web.xml

        final String BASE = getRelativeBaseServlet() + "/requestPlugin";
        int index = uri.indexOf(BASE);

        if (index == -1) {
            String msg = "URL base incorrecte !!!! Esperat " + BASE + ". URI = " + uri;
            throw new IOException(msg);
        }

        // idAndQuery = 1466408733012148444/-1/index.html
        String idAndQuery = uri.substring(index + BASE.length() + 1);
        if (debug) {
            log.debug(" idAndQuery = " + idAndQuery);
        }

        index = idAndQuery.indexOf('/');

        String pluginIDStr = idAndQuery.substring(0, index);
        int index2 = idAndQuery.indexOf('/', index + 1);
        String administrationIDEncripted = URLDecoder
                .decode(idAndQuery.substring(index + 1, index2), "utf-8");
        String query = idAndQuery.substring(index2 + 1, idAndQuery.length());

        if (debug) {
            log.debug(" pluginID = " + pluginIDStr);
            log.debug(" administrationIDEncripted = " + administrationIDEncripted);
            log.debug(" query = " + query);
        }

        // Així si que agafa be l'idioma del front
        Locale locale = Locale.getDefault();

        try {
            requestPlugin(request, response, pluginIDStr, administrationIDEncripted, query, locale,
                    isGet, debug);
        } catch (Throwable th) {

            try {
                authenticationLogicaEjb.crearLog("Request a Plugin", null, uri, th,
                        "Error desconegut cridant a un request d'un plugin: " + th.getMessage(),
                        null, request.getRequestedSessionId());
            } catch (Throwable th2) {
            }

            throw new IOException(th.getMessage(), th);
        }

    }

    /**
     * 
     * @param request
     * @param response
     * @param pluginIDStr
     * @param administrationIDEncripted
     * @param query
     * @param locale
     * @param isGet
     * @param debug
     * @throws Exception
     */
    protected void requestPlugin(HttpServletRequest request, HttpServletResponse response,
            String pluginIDStr, String administrationIDEncripted, String query, Locale locale,
            boolean isGet, boolean debug) throws Exception {

        String administrationID = HibernateFileUtil.decryptString(administrationIDEncripted);

        if (pluginIDStr == null || pluginIDStr.trim().length() == 0) {
            if (debug) {
                log.debug("query = " + pluginIDStr + "/" + administrationIDEncripted + "/" + query);
            }
            String msg = I18NUtils.tradueix("moduldefirma.senseplugin", pluginIDStr);
            generateErrorAndRedirect(request, response, -1L, msg, null);
            return;
        }

        long pluginID = Long.parseLong(pluginIDStr);

        ICarpetaFrontPlugin carpetaFrontPlugin;
        try {
            carpetaFrontPlugin = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {
            String msg = I18NUtils.tradueix("plugin.carpetafront.notexist",
                    String.valueOf(pluginID));
            generateErrorAndRedirect(request, response, pluginID, msg, e);
            return;
        }
        if (carpetaFrontPlugin == null) {
            String msg = I18NUtils.tradueix("plugin.carpetafront.notexist",
                    String.valueOf(pluginID));
            generateErrorAndRedirect(request, response, pluginID, msg, null);
            return;
        }

        UserData userData = getUserData(administrationID);

        if (!carpetaFrontPlugin.isPublic()) {
            if (userData == null) {
                log.error("\n\n S'ha intentat accedir a una consulta del plugin privat amb ID "
                        + pluginID + " des d'una zona pública\n\n");
                final String msg = I18NUtils.tradueix("plugin.carpetafront.notpublic",
                        String.valueOf(pluginID));
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
                return;
            } else {

                // NIF de petició i NIF de userData ha de ser el mateix
                if (!userData.getAdministrationID().equalsIgnoreCase(administrationID)) {
                    final String msg = "S'ha intentat accedir a una consulta del plugin privat amb NIF "
                            + administrationID + " però l'usuari loguejat és "
                            + userData.getAdministrationID();
                    if (debug)
                        log.error("\n\n " + msg + "\n\n");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
                    return;
                }
            }
        }

        if (debug) {
            log.debug("Original RelativePath = " + query);
            log.debug("Method = " + request.getMethod());
            log.debug(AbstractCarpetaFrontPlugin.servletRequestInfoToStr(request));
        }

        String urlBase = (String) request.getSession().getAttribute(SESSION_URL_BASE);

        String absoluteRequestPluginBasePath = getAbsoluteRequestPluginBasePath(urlBase,
                getRelativeBaseServlet(), pluginID, administrationIDEncripted);
        String relativeRequestPluginBasePath = getRelativeRequestPluginBasePath(request,
                getRelativeBaseServlet(), pluginID, administrationIDEncripted);

        if (debug) {
            log.debug("absoluteRequestPluginBasePath => " + absoluteRequestPluginBasePath);
            log.debug("relativeRequestPluginBasePath => " + relativeRequestPluginBasePath);
        }

        String codiEntitat = null;
        if (sesionHttp != null && sesionHttp.getEntitat() != null) {
            codiEntitat = sesionHttp.getEntitat();
            log.info(
                    "IListenerLogCarpeta->codiEntitat = sesionHttp.getEntitat() => " + codiEntitat);
        } else {
            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            if (defaultEntityCode != null) {
                codiEntitat = defaultEntityCode;
                log.info("IListenerLogCarpeta->codiEntitat = defaultEntityCode => " + codiEntitat);
            }
        }

        IListenerLogCarpeta logCarpeta = new ListenerLogCarpeta(pluginID, codiEntitat,
                logCarpetaLogicaEjb);

        carpetaFrontPlugin.requestCarpetaFront(absoluteRequestPluginBasePath,
                relativeRequestPluginBasePath, query, request, response, administrationID,
                administrationIDEncripted, locale, isGet, logCarpeta);

    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // ----------------------------- U T I L I T A T S -------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    @RequestMapping(value = "/error") // {parameter}")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("error")
            String error) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", error);

        return mav;
    }

    protected ModelAndView generateErrorMAV(HttpServletRequest request, Long pluginID, String msg,
            Throwable th) {

        String urlFinal = processError(request, pluginID, msg, th);

        ModelAndView mav = new ModelAndView(new RedirectView("/superadmin/pluginfront/list", true));
        // request.getSession().setAttribute("URL_FINAL", urlError);
        mav.addObject("URL_FINAL", urlFinal);

        return mav;
    }

    protected void generateErrorAndRedirect(HttpServletRequest request,
            HttpServletResponse response, Long pluginID, String msg, Throwable th) {

        String urlFinal = processError(request, pluginID, msg, th);

        try {
            String r = request.getContextPath() + getContextWeb() + "/error?error="
                    + URLEncoder.encode(msg, "UTF8") + "&URL_FINAL="
                    + URLEncoder.encode(urlFinal, "UTF8");

            response.sendRedirect(r);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

    protected static String processError(HttpServletRequest request, Long pluginID, String msg,
            Throwable th) {

        String urlFinal;

        log.error(msg, th);
        urlFinal = getRelativePortaFIBBase(request);

        return urlFinal;
    }

    // XYZ ZZZ NINGU CRIDA AQUEST MÈTODE !!!!!!
    public static ModelAndView startPublicSignatureProcess(HttpServletRequest request,
            HttpServletResponse response, String view, long pluginID, String administrationID,
            String urlBase) throws I18NException {
        return startSignatureProcess(request, response, view, pluginID, administrationID, urlBase);
    }

    // XYZ ZZZ NINGU CRIDA AQUEST MÈTODE !!!!!!
    private static ModelAndView startSignatureProcess(HttpServletRequest request,
            HttpServletResponse response, String view, long pluginID, String administrationID,
            String urlBase) throws I18NException {

        // response.addHeader("X-Frame-Options", "SAMEORIGIN");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBase);

        String context = PUBLIC_CONTEXTWEB;

        String administrationIDEncriptat = HibernateFileUtil.encryptString(administrationID);

        final String urlToShowPluginPage = urlBase + context + "/showplugin/" + pluginID + "/"
                + response.encodeURL(administrationIDEncriptat);

        if (log.isDebugEnabled()) {
            log.debug(" urlToShowPluginPage => " + urlToShowPluginPage);
        }
        ModelAndView mav = new ModelAndView(view);
        // mav.addObject("signaturesSetID", signaturesSetID);
        mav.addObject("urlToShowPluginPage", urlToShowPluginPage);

        return mav;
    }

    public static String getRelativePortaFIBBase(HttpServletRequest request) {
        return request.getContextPath();
    }

    public static String getRelativeControllerBase(HttpServletRequest request, String webContext) {
        return getRelativePortaFIBBase(request) + webContext;
    }

    protected static String getAbsoluteRequestPluginBasePath(String baseUrl, String webContext,
            Long pluginID, String administrationIDEncripted) {

        String base = baseUrl + webContext;
        return getRequestPluginBasePath(base, pluginID, administrationIDEncripted);
    }

    public static String getRelativeRequestPluginBasePath(HttpServletRequest request,
            String webContext, Long pluginID, String administrationIDEncripted) {

        String base = getRelativeControllerBase(request, webContext);
        return getRequestPluginBasePath(base, pluginID, administrationIDEncripted);
    }

    private static String getRequestPluginBasePath(String base, Long pluginID,
            String administrationIDEncripted) {
        String absoluteRequestPluginBasePath = base + "/requestPlugin/" + pluginID + "/"
                + administrationIDEncripted;

        return absoluteRequestPluginBasePath;
    }

    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

}
