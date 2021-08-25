package es.caib.carpeta.front.controller;

import es.caib.carpeta.commons.utils.UsuarioClave;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.security.LoginController;
import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.persistence.EntitatJPA;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class InicioController extends CommonFrontController {

    @Autowired
    private SesionHttp sesionHttp;

    @Autowired
    SecurityService securityService;

    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    UtilitiesForFrontLogicaService utilsEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    EntitatLogicaService entitatEjb;

    protected static final Log log = LogFactory.getLog(InicioController.class);

    @RequestMapping(value = { "/entitat" }, method = RequestMethod.GET)
    public ModelAndView llistarEntitats(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        ModelAndView mav = new ModelAndView("entitat");

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);

            // Primera vegada que entra al front i no sap on anar
            if (defaultEntityCode == null && sesionHttp.getEntitat() == null) {

                List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

                mav.addObject("entitats", entitats);
                mav.addObject("langActual", lang);
                List<Idioma> idiomes = utilsEjb.getIdiomes();
                List<Idioma> idiomesActius = new ArrayList<>();
                for (Idioma idioma : idiomes) {
                    if (idioma.isSuportat()) {
                        idiomesActius.add(idioma);
                    }
                }
                mav.addObject("idiomes", idiomesActius);

            }
            // Primera vegada que entra al front però sap on anar
            if (defaultEntityCode != null && sesionHttp.getEntitat() == null) {

                mav = new ModelAndView("redirect:/e/" + defaultEntityCode);

            }
            // Ve per canviar d'entitat
            if (sesionHttp.getEntitat() != null) {

                List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

                mav.addObject("entitats", entitats);
                mav.addObject("langActual", lang);

            }

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }

        return mav;

    }

    public static final String SESSION_INITIAL_URL = "SESSION_INITIAL_URL";

    @RequestMapping(value = { "/fa/{fullAddress}" }, method = RequestMethod.GET)
    public void fullAddress(@PathVariable("fullAddress") String fullAddress,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String urlBaseDec = new String(Base64.getDecoder().decode(fullAddress), "utf-8");

        if (urlBaseDec.indexOf("entitat?lang=") == -1 && !urlBaseDec.endsWith("/entitat")) {
            log.info(" REBUT FULL ADRRESS  DES DE JAVASCRIPT: ]" + urlBaseDec + "[");
            request.getSession().setAttribute(SESSION_INITIAL_URL, urlBaseDec);
        } else {
            log.info(" IGNORANT  FULL ADRRESS  DES DE JAVASCRIPT: ]" + urlBaseDec + "[");
        }
        response.sendError(HttpServletResponse.SC_OK);
    }

    public class ReactNativeLogin {

        protected UsuarioAutenticado usuarioAutenticado;

        public final String codiEntitat;

        public final String urlBase;

        public final java.util.Date date;

        public ReactNativeLogin(String codiEntitat, String urlBase) {
            super();
            this.usuarioAutenticado = null;
            this.codiEntitat = codiEntitat;
            this.urlBase = urlBase;
            this.date = new Date();
        }

        public UsuarioAutenticado getUsuarioAutenticado() {
            return usuarioAutenticado;
        }

        public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
            this.usuarioAutenticado = usuarioAutenticado;
        }

    }

    public static final String SESSION_IS_REACTNATIVE = "SESSION_IS_REACTNATIVE";

    public static final String SESSION_LOGIN_CODE = "SESSION_LOGIN_CODE";

    private static final Map<String, ReactNativeLogin> reactNativeLogins = new HashMap<String, InicioController.ReactNativeLogin>();

    public static ReactNativeLogin getReactNativeLogin(String loginCode) {

        ReactNativeLogin rnl = reactNativeLogins.get(loginCode);

        if (rnl == null) {
            log.error("\n\n\n" + "NO S?HA TROBAT ReactNativeLogin amb codiLogin ]" + loginCode
                    + "[\n" + "  reactNativeLogins.size() => " + reactNativeLogins.size() + "\n");

            int count = 1;
            for (String lc : reactNativeLogins.keySet()) {
                log.error((count++) + " - ]" + lc + "[\n\n\n");
            }

        }

        return rnl;
    }

    @RequestMapping(value = { "/public/doLogin" }, method = RequestMethod.GET)
    public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response)
            throws I18NException, IOException {

        log.info("XYZ ZZZ ENTRA A doLogin ...");
        
        
        String urlBase = request.getParameter("urlbase");
        log.info("XYZ ZZZ ENTRA A doLogin => urlbase = ]" + urlBase + "[");

        Boolean isRN = (Boolean) request.getSession().getAttribute(SESSION_IS_REACTNATIVE);
        log.info("XYZ ZZZ ENTRA A doLogin => SESSION_IS_REACTNATIVE = ]" + isRN + "[");
        if (isRN == null) {

            return new ModelAndView(new RedirectView(
                    "/prelogin?urlbase=" + URLEncoder.encode(urlBase, "UTF-8"), true));

        } else {

            log.info("XYZ ZZZ ENTRA A doLogin => Obri pagina carpetaappdologin E:" + sesionHttp.getEntitat() );
            
            ModelAndView mav = new ModelAndView("carpetaappdologin");

            mav.addObject("codientitat", sesionHttp.getEntitat());
            mav.addObject("urlbase", URLEncoder.encode(urlBase, "UTF-8"));

            return mav;

            // return "redirect:/public/ea/" + sesionHttp.getEntitat() + " ?urlbase=" +
            // URLEncoder.encode(urlBase, "UTF-8");
        }

    }

    @RequestMapping(value = { "/public/rnhp/{codiEntitat}" }, method = RequestMethod.GET)
    public String reactNativeHomePage(@PathVariable("codiEntitat") String codiEntitat,
            HttpServletRequest request, HttpServletResponse response)
            throws I18NException, IOException {
        
        log.info("XYZ ZZZ ENTRA A reactNativeHomePage");

        assignEntitat(codiEntitat, request, response);

        request.getSession().setAttribute(SESSION_IS_REACTNATIVE, Boolean.TRUE);
        log.info(" reactNativeHomePage => posant SESSION_IS_REACTNATIVE  a ]"
                + request.getSession().getAttribute(SESSION_IS_REACTNATIVE) + "[");

        return "redirect:/";
    }

    @RequestMapping(value = { "/public/ea/{codiEntitat}" }, method = RequestMethod.GET)
    public String loginAndroidBegin(@PathVariable("codiEntitat") String codiEntitat,
            HttpServletRequest request, HttpServletResponse response)
            throws I18NException, IOException {

        assignEntitat(codiEntitat, request, response);

        String codiLogin = UUID.randomUUID().toString().replace(' ', '0');

        String urlBase = request.getParameter("urlbase");

        log.info("loginAndroidBegin => URL BASE: ]" + urlBase + "[");

        String urlReturn = urlBase + request.getContextPath() + "/public/eaf/" + codiLogin + "/"
                + codiEntitat;

        log.info("loginAndroidBegin => URL DE RETORN: " + urlReturn);

        request.getSession().setAttribute(InicioController.SESSION_INITIAL_URL, urlReturn);

        // return "redirect:/";

        // return triarEntitat(codiEntitat, request, response);

        reactNativeLogins.put(codiLogin, new ReactNativeLogin(codiEntitat, urlBase));

        return "redirect:/prelogin?urlbase=" + URLEncoder.encode(urlBase, "UTF-8");

    }

    @RequestMapping(value = { "/public/eaf/{codiLogin}/{codiEntitat}" }, method = RequestMethod.GET)
    public ModelAndView loginAndroidFinal(@PathVariable("codiLogin") String codiLogin,
            @PathVariable("codiEntitat") String codiEntitat, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        log.info(" ENTRA A loginAndroidFinal(" + codiLogin + ")");

        // response.setContentType("text/html");
        // response.setCharacterEncoding("UTF8");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mav = new ModelAndView("carpetaapp");
        mav.addObject("codiLogin", codiLogin);

        if (authentication != null) {

            Object principal = authentication.getPrincipal();

            if (principal != null && principal instanceof UsuarioAutenticado) {

                UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado) principal;

                log.info("loginAndroidFinal => Registram codiLogin ]" + codiLogin + "[");

                ReactNativeLogin rnl = reactNativeLogins.get(codiLogin);
                rnl.setUsuarioAutenticado(usuarioAutenticado);

                UsuarioClave uc = usuarioAutenticado.getUsuarioClave();

                mav.addObject("uc", uc);

                assignEntitat(codiEntitat, request, response);

            }
        }

        return mav;

    }

    @RequestMapping(value = { "/public/lrn/{codiLogin}" }, method = RequestMethod.GET)
    public String loginReactNativeBegin(@PathVariable("codiLogin") String codiLogin,
            HttpServletRequest request, HttpServletResponse response)
            throws I18NException, IOException {

        log.info("loginReactNative => ENTRA a LRN  (" + codiLogin + ")");

        ReactNativeLogin rnl = reactNativeLogins.get(codiLogin);

        request.getSession().setAttribute(SESSION_IS_REACTNATIVE, Boolean.TRUE);
        log.info(" loginReactNative => posant SESSION_IS_REACTNATIVE  a ]"
                + request.getSession().getAttribute(SESSION_IS_REACTNATIVE) + "[");
        request.getSession().setAttribute(InicioController.SESSION_LOGIN_CODE, codiLogin);

        log.info(" loginReactNative => posant SESSION_LOGIN_CODE a ]"
                + request.getSession().getAttribute(SESSION_LOGIN_CODE) + "[");

        String urlReturn = /* Configuracio.getAppUrl() */ rnl.urlBase + request.getContextPath()
                + "/public/lrnf/" + codiLogin;

        log.info("loginAndroid => URL DE RETORN: " + urlReturn);

        request.getSession().setAttribute(InicioController.SESSION_INITIAL_URL, urlReturn);

        return "redirect:/prelogin?urlbase=" + URLEncoder.encode(rnl.urlBase, "UTF-8");
    }

    @RequestMapping(value = { "/public/lrnf/{codiLogin}" }, method = RequestMethod.GET)
    public String loginReactNativeFinal(@PathVariable("codiLogin") String codiLogin,
            HttpServletRequest request, HttpServletResponse response)
            throws I18NException, IOException {

        log.info("loginReactNativeFINAL => ENTRA a LRN  (" + codiLogin + ")");

        ReactNativeLogin rnl = reactNativeLogins.get(codiLogin);

        assignEntitat(rnl.codiEntitat, request, response);

        /*
         * request.getSession().setAttribute(SESSION_IS_REACTNATIVE, Boolean.TRUE);
         * log.info(" loginReactNative => posant SESSION_IS_REACTNATIVE  a ]" +
         * request.getSession().getAttribute(SESSION_IS_REACTNATIVE) + "[");
         * request.getSession().setAttribute(InicioController.SESSION_LOGIN_CODE,
         * codiLogin);
         * 
         * log.info(" loginReactNative => posant SESSION_LOGIN_CODE a ]" +
         * request.getSession().getAttribute(SESSION_LOGIN_CODE) + "[");
         */
        return "redirect:/";
    }

    @RequestMapping(value = { "/e/{codiEntitat}" }, method = RequestMethod.GET)
    public String triarEntitat(@PathVariable("codiEntitat") String codiEntitat,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        assignEntitat(codiEntitat, request, response);

        // http://localhost:8080/carpetafront/#/moduls/registre32

        String fullUrlRedirect = (String) request.getSession()
                .getAttribute(InicioController.SESSION_INITIAL_URL);

        if (fullUrlRedirect == null) {
            fullUrlRedirect = "/";
        } else {

            try {
                URL url = new URL(fullUrlRedirect);

                String file = url.getFile();

                log.info("\n\n XYZ ZZZ PATH FILE ]" + file + "[\n\n");

                if (file == null || file.trim().length() == 0) {
                    // OK NO passam a LOGIN
                } else {

                    String cp = request.getContextPath();

                    if (file.startsWith(cp + "/seccio/") || file.startsWith(cp + "/modul/")
                            || file.startsWith(cp + "/moduls/")) {

                        fullUrlRedirect = "/prelogin?urlbase=" + URLEncoder.encode(
                                url.getProtocol() + "://" + url.getHost()
                                        + ((url.getPort() == -1) ? "" : (":" + url.getPort())),
                                "UTF-8");

                    }
                }

            } catch (Exception e) {

                log.error("Error parsejant url " + fullUrlRedirect, e);
                fullUrlRedirect = "/";
            }

        }

        log.info("\n XYZ ZZZZ SELECCIO ENTITAT  redirigir a => " + fullUrlRedirect + "\n");

        return "redirect:" + fullUrlRedirect;

    }

    protected void assignEntitat(String codiEntitat, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        String IDIOMA = LocaleContextHolder.getLocale().getLanguage();
        EntitatJPA entitat = entitatEjb.findByCodi(codiEntitat);
        sesionHttp.setNomEntitat(entitat.getNom().getTraduccio(IDIOMA).getValor());

        try {
            // Eliminam sessió anterior
            if (sesionHttp.getEntitat() != null) {
                if (!sesionHttp.getEntitat().equals(codiEntitat)) {
                    String initialURL = (String) request.getSession()
                            .getAttribute(SESSION_INITIAL_URL);
                    String baseURL = (String) request.getSession()
                            .getAttribute(LoginController.SESSION_RETURN_URL_POST_LOGIN);
                    String url_callback_logout = baseURL + "/salir";

                    Boolean isReactNative = (Boolean) request.getSession()
                            .getAttribute(SESSION_IS_REACTNATIVE);
                    String loginCode = (String) request.getSession()
                            .getAttribute(SESSION_LOGIN_CODE);

                    securityService.iniciarSesionLogout(url_callback_logout, IDIOMA);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    SecurityContextHolder.getContext().setAuthentication(null);
                    if (initialURL != null) {
                        request.getSession().setAttribute(SESSION_INITIAL_URL, initialURL);
                    }

                    if (isReactNative != null) {
                        request.getSession().setAttribute(SESSION_IS_REACTNATIVE, isReactNative);
                    }

                    if (loginCode != null) {
                        request.getSession().setAttribute(SESSION_LOGIN_CODE, loginCode);
                    }
                }
            }

            sesionHttp.setEntitat(codiEntitat);

            long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID,
                    EntitatFields.CODI.equal(codiEntitat));
            sesionHttp.setEntitatID(entitatID);

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }
    }

    @RequestMapping(value = { "/" })
    public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response,
            HttpSession session) throws I18NException {

        ModelAndView mav = new ModelAndView("inici");

        // Posam la sessió de la variable global (per 60 segons)
        PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
        if (EjbManager.getFrontSessionTime(propietatGlobalEjb) != null) {
            int frontSessionTime = Integer
                    .parseInt(EjbManager.getFrontSessionTime(propietatGlobalEjb));
            mav.addObject("maxInactiveInterval", frontSessionTime * 60);
        } else {
            mav.addObject("maxInactiveInterval", 30 * 60);
        }

        try {

            String lang = LocaleContextHolder.getLocale().getLanguage();

            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            String canviardefront = EjbManager.getCanviarDeFront(propietatGlobalEjb);

            List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

            String errorDeLogin = sesionHttp.getErrorLogin();

            if (defaultEntityCode == null && sesionHttp.getEntitat() == null) {

                if (entitats.size() > 1) {

                    mav = new ModelAndView("entitat");
                    mav.addObject("entitats", entitats);
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);
                    List<Idioma> idiomes = utilsEjb.getIdiomes();
                    List<Idioma> idiomesActius = new ArrayList<>();
                    for (Idioma idioma : idiomes) {
                        if (idioma.isSuportat()) {
                            idiomesActius.add(idioma);
                        }
                    }
                    mav.addObject("idiomes", idiomesActius);
                    mav.addObject("langActual", lang);

                } else {

                    String codiEntitat = entitats.get(0).getCodi();

                    long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID,
                            EntitatFields.CODI.equal(codiEntitat));
                    sesionHttp.setEntitatID(entitatID);

                    sesionHttp.setEntitat(codiEntitat);

                    mav.addObject("entitat", codiEntitat);
                    mav.addObject("nomEntitat", entitats.get(0).getNom());
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);

                }

            } else if (defaultEntityCode != null && sesionHttp.getEntitat() == null) {

                EntitatJPA entitat = entitatEjb.findByCodi(defaultEntityCode);

                if (entitat != null) {

                    long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID,
                            EntitatFields.CODI.equal(defaultEntityCode));
                    sesionHttp.setEntitatID(entitatID);

                    sesionHttp.setEntitat(defaultEntityCode);

                    mav.addObject("entitat", sesionHttp.getEntitat());
                    mav.addObject("nomEntitat", entitat.getNom());
                    mav.addObject("defaultEntityCode", defaultEntityCode);
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);

                } else {

                    if (sesionHttp.getEntitat() != null) {
                        mav.addObject("entitat", sesionHttp.getEntitat());
                        mav.addObject("nomEntitat", sesionHttp.getNomEntitat());
                        mav.addObject("numEntitats", entitats.size());
                        mav.addObject("canviarDeFront", canviardefront);
                    } else {
                        mav = new ModelAndView("entitat");
                        mav.addObject("entitats", entitats);
                    }

                }
            } else if (sesionHttp.getEntitat() != null) {

                mav.addObject("entitat", sesionHttp.getEntitat());
                mav.addObject("nomEntitat", sesionHttp.getNomEntitat());
                mav.addObject("numEntitats", entitats.size());
                mav.addObject("canviarDeFront", canviardefront);
                mav.addObject("errorLogin", errorDeLogin);

            }

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }

        return mav;

    }

    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws I18NException {

        ModelAndView mav = new ModelAndView("error");

        try {

            mav.addObject("error", e.getMessage());

        } catch (Throwable e1) {
            log.error("Error: " + e1.getMessage(), e1);
        }

        return mav;

    }

}
