package es.caib.carpeta.front.controller;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.AuthenticationLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.UsuariEntitatLogicaService;
import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.persistence.TraduccioMapJPA;
import es.caib.carpeta.pluginsib.carpetafront.api.AbstractCarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;
import es.caib.carpeta.pluginsib.carpetafront.api.UserData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Locale;
import java.util.Map;

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

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;
    
    @EJB(mappedName = AuthenticationLogicaService.JNDI_NAME)
    protected AuthenticationLogicaService authenticationLogicaEjb;

    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}/{urlBase}")
    public ModelAndView showCarpetaFrontModuleWithUrlBase(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID,
            @PathVariable("administrationIDEncriptat") String administrationIDEncriptat,
            @PathVariable("urlBase") String urlBase) throws Exception {
        
        
        log.info("showCarpetaFrontModule:: pluginID => " + pluginID);
        log.info("showCarpetaFrontModule:: administrationIDEncriptat => " + administrationIDEncriptat);
        log.info("showCarpetaFrontModule:: urlBase => " + urlBase);
        

        String urlBaseDec = new String(Base64.getUrlDecoder().decode(urlBase), "utf-8");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBaseDec);
        
        final String parameter = null;

        return showCarpetaFrontModule(request, response, pluginID, administrationIDEncriptat, parameter);
    }
    
    
    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}/{urlBase}/p/{parameter}")
    public ModelAndView showCarpetaFrontModuleWithUrlBaseAndParameter(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID,
            @PathVariable("administrationIDEncriptat") String administrationIDEncriptat,
            @PathVariable("urlBase") String urlBase,
            @PathVariable("parameter") String parameter) throws Exception {
        
        
        log.info("showCarpetaFrontModule:: pluginID => " + pluginID);
        log.info("showCarpetaFrontModule:: administrationIDEncriptat => " + administrationIDEncriptat);
        log.info("showCarpetaFrontModule:: urlBase => " + urlBase);
        

        String urlBaseDec = new String(Base64.getUrlDecoder().decode(urlBase), "utf-8");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBaseDec);

        return showCarpetaFrontModule(request, response, pluginID, administrationIDEncriptat, parameter);
    }

    /*
    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}")
    public ModelAndView showCarpetaFrontModule(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID,
            @PathVariable("administrationIDEncriptat") String administrationIDEncriptat) throws Exception {
        
        final String parameter = null;
        return showCarpetaFrontModuleWithParameter(request, response, pluginID,
               administrationIDEncriptat,parameter);
    }
        
        
    @RequestMapping(value = "/showplugin/{pluginID}/{administrationIDEncriptat}/p/{parameter}")
    */ 
    protected ModelAndView showCarpetaFrontModule(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID,
            @PathVariable("administrationIDEncriptat") String administrationIDEncriptat,
            @PathVariable("parameter") String parameter
            ) throws Exception {

        String administrationID = HibernateFileUtil.decryptString(administrationIDEncriptat);

        // El plugin existeix?
        ICarpetaFrontPlugin carpetaFront;
        try {
            carpetaFront = pluginCarpetaFrontEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {

            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            return generateErrorMAV(request, pluginID, msg, e);
        }

        if (carpetaFront == null) {

            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            return generateErrorMAV(request, pluginID, msg, null);
        }

        String relativeBaseServlet = getRelativeBaseServlet();

        String relativeControllerBase = getRelativeControllerBase(request, relativeBaseServlet);
        String relativeRequestPluginBasePath = getRequestPluginBasePath(relativeControllerBase, pluginID,
                URLEncoder.encode(administrationIDEncriptat, "utf-8"));

        String urlBase = (String) request.getSession().getAttribute(SESSION_URL_BASE);

        String absoluteControllerBase = urlBase + relativeBaseServlet;
        String absoluteRequestPluginBasePath = getRequestPluginBasePath(absoluteControllerBase, pluginID,
                URLEncoder.encode(administrationIDEncriptat, "utf-8"));

        log.info("\n\n\n SHOW PLUGIN " + "\nadministrationID = " + administrationID + "\nadministrationIDEncriptat = "
                + administrationIDEncriptat + "\nURL_BASE = " + urlBase + "\nabsoluteControllerBase = "
                + absoluteControllerBase + "\nabsoluteRequestPluginBasePath = " + absoluteRequestPluginBasePath
                + "\nrelativeControllerBase = " + relativeControllerBase + "\nrelativeRequestPluginBasePath = "
                + relativeRequestPluginBasePath + "\n\n\n");
        
        
        
        TitlesInfo titlesInfo = carpetaFront.getTitlesInfo();
        if (titlesInfo == null) {
        
            PluginJPA plugin = (PluginJPA) this.pluginCarpetaFrontEjb.findByPrimaryKey(pluginID);

            titlesInfo = new TitlesInfo();
            Map<String, TraduccioMapJPA> titles= plugin.getNomTraduccions();
            for (String lang: titles.keySet()) {
                titlesInfo.getTitlesByLang().put(lang, titles.get(lang).getValor());
            }
            Map<String, TraduccioMapJPA> subtitles= plugin.getDescripcioTraduccions();
            for (String lang: subtitles.keySet()) {
                titlesInfo.getSubtitlesByLang().put(lang, subtitles.get(lang).getValor());
            }
            
            carpetaFront.setTitlesInfo(titlesInfo);
        }
        
        
        log.info("\n Cridant a  carpetaFront.getStartUrl() amb PluginParameter =>  " + parameter);
        

        String urlToPluginWebPage = carpetaFront.getStartUrl(absoluteRequestPluginBasePath,
                relativeRequestPluginBasePath, request, getUserData(administrationID), administrationIDEncriptat, parameter);

        log.info("\n\n\n SHOW PLUGIN 2222 urlToPluginWebPage =>  " + urlToPluginWebPage);

        return new ModelAndView(new RedirectView(urlToPluginWebPage, false));

    }

    public abstract UserData getUserData(String administrationID);

    private String getRelativeBaseServlet() {
        return "/public/carpetafrontservlet";
    }

    @RequestMapping(value = "/error")
    public ModelAndView errorProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("URL_FINAL") String urlFinal) throws Exception {

        ModelAndView mav = new ModelAndView("PluginFirmaFinal");
        mav.addObject("URL_FINAL", urlFinal);
        mav.addObject("window", "window");

        return mav;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processServlet(request, response, true);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processServlet(request, response, false);
    }

    protected void processServlet(HttpServletRequest request, HttpServletResponse response, boolean isGet)
            throws ServletException, IOException {


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
            log.info(" idAndQuery = " + idAndQuery);
        }

        index = idAndQuery.indexOf('/');

        String pluginIDStr = idAndQuery.substring(0, index);
        int index2 = idAndQuery.indexOf('/', index + 1);
        String administrationIDEncripted = URLDecoder.decode(idAndQuery.substring(index + 1, index2), "utf-8");
        String query = idAndQuery.substring(index2 + 1, idAndQuery.length());

        if (debug) {
            log.info(" pluginID = " + pluginIDStr);
            log.info(" administrationIDEncripted = " + administrationIDEncripted);
            log.info(" query = " + query);
        }

        // Així si que agafa be l'idioma del front
        Locale locale = Locale.getDefault();

        try {
            requestPlugin(request, response, pluginIDStr, administrationIDEncripted, query, locale, isGet, debug);
        } catch (Throwable th) {
            
            
            try {
                authenticationLogicaEjb.crearLog("Request a Plugin", null,uri,th,"Error desconegut cridant a un request d'un plugin: " + th.getMessage(), null);
            } catch (Throwable th2) {               
            }
            
            
            throw new IOException(th.getMessage(), th);
        }

    }

    /**
     * 
     * @param request
     * @param response
     * @param signaturesSetID
     * @param signatureIndex
     * @param query
     * @param isPost
     * @throws Exception
     * @throws I18NException
     */
    protected void requestPlugin(HttpServletRequest request, HttpServletResponse response, String pluginIDStr,
            String administrationIDEncripted, String query, Locale locale, boolean isGet, boolean debug)
            throws Exception {

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
            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            generateErrorAndRedirect(request, response, pluginID, msg, e);
            return;
        }
        if (carpetaFrontPlugin == null) {
            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            generateErrorAndRedirect(request, response, pluginID, msg, null);
            return;
        }

        if (debug) {
            log.debug("Original RelativePath = " + query);
            log.debug("Method = " + request.getMethod());
            log.debug(AbstractCarpetaFrontPlugin.servletRequestInfoToStr(request));
        }

        String urlBase = (String) request.getSession().getAttribute(SESSION_URL_BASE);

        String absoluteRequestPluginBasePath = getAbsoluteRequestPluginBasePath(urlBase, getRelativeBaseServlet(),
                pluginID, administrationIDEncripted);
        String relativeRequestPluginBasePath = getRelativeRequestPluginBasePath(request, getRelativeBaseServlet(),
                pluginID, administrationIDEncripted);

        if (debug) {
          log.debug("absoluteRequestPluginBasePath => " + absoluteRequestPluginBasePath);
          log.debug("relativeRequestPluginBasePath => " + relativeRequestPluginBasePath);
        }

        carpetaFrontPlugin.requestCarpetaFront(absoluteRequestPluginBasePath, relativeRequestPluginBasePath, query,
                request, response, administrationID, administrationIDEncripted, locale, isGet);

    }

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // ----------------------------- U T I L I T A T S ----------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    protected ModelAndView generateErrorMAV(HttpServletRequest request, Long pluginID, String msg, Throwable th) {

        String urlFinal = processError(request, pluginID, msg, th);


        ModelAndView mav = new ModelAndView(new RedirectView("/superadmin/pluginfront/list", true));
        // request.getSession().setAttribute("URL_FINAL", urlError);
        mav.addObject("URL_FINAL", urlFinal);

        return mav;
    }

    protected void generateErrorAndRedirect(HttpServletRequest request, HttpServletResponse response, Long pluginID,
            String msg, Throwable th) {

        String urlFinal = processError(request, pluginID, msg, th);

        try {

            String r = request.getContextPath() + getContextWeb() + "/error?URL_FINAL="
                    + URLEncoder.encode(urlFinal, "UTF8");

            response.sendRedirect(r);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected static String processError(HttpServletRequest request, Long pluginID, String msg, Throwable th) {

        String urlFinal;

        log.error(msg, th);
        urlFinal = getRelativePortaFIBBase(request);

        return urlFinal;
    }



    public static ModelAndView startPublicSignatureProcess(HttpServletRequest request, HttpServletResponse response,
            String view, long pluginID, String administrationID, String urlBase) throws I18NException {
        return startSignatureProcess(request, response, view, pluginID, administrationID, urlBase);
    }

    private static ModelAndView startSignatureProcess(HttpServletRequest request, HttpServletResponse response,
            String view, long pluginID, String administrationID, String urlBase)
            throws I18NException {

        // response.addHeader("X-Frame-Options", "SAMEORIGIN");

        request.getSession().setAttribute(SESSION_URL_BASE, urlBase);

        String context = PUBLIC_CONTEXTWEB;

        String administrationIDEncriptat = HibernateFileUtil.encryptString(administrationID);

        final String urlToShowPluginPage = urlBase + context + "/showplugin/" + pluginID + "/"
                + response.encodeURL(administrationIDEncriptat);

        log.info(" urlToShowPluginPage => " + urlToShowPluginPage);

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

    protected static String getAbsoluteRequestPluginBasePath(String baseUrl, String webContext, Long pluginID,
            String administrationIDEncripted) {

        String base = baseUrl + webContext;
        return getRequestPluginBasePath(base, pluginID, administrationIDEncripted);
    }

    public static String getRelativeRequestPluginBasePath(HttpServletRequest request, String webContext, Long pluginID,
            String administrationIDEncripted) {

        String base = getRelativeControllerBase(request, webContext);
        return getRequestPluginBasePath(base, pluginID, administrationIDEncripted);
    }

    private static String getRequestPluginBasePath(String base, Long pluginID, String administrationIDEncripted) {
        String absoluteRequestPluginBasePath = base + "/requestPlugin/" + pluginID + "/" + administrationIDEncripted;

        return absoluteRequestPluginBasePath;
    }

    /**
     * 
     * @return
     */
    public static long generateUniqueSignaturesSetID() {
        long id;
        synchronized (log) {
            id = (System.currentTimeMillis() * 1000000L) + System.nanoTime() % 1000000L;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        return id;
    }

    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

}
