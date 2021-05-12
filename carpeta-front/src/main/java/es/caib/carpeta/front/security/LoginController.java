package es.caib.carpeta.front.security;

import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.front.config.LoginRequestCache;
import es.caib.carpeta.front.controller.InicioController;
import es.caib.carpeta.front.controller.WebUIController;
import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_FRONT;

/**
 *
 * @author jpernia, anadal, mgonzalez
 *
 */
@Controller
public class LoginController {

    public static final String SESSION_RETURN_URL_POST_LOGIN = "SESSION_RETURN_URL_POST_LOGIN";

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @Autowired
    private LoginRequestCache loginRequestCache; // Cache peticiones

    @Autowired
    private SesionHttp sesionHttp;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logLogicaEjb;

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;

    // @Autowired
    // private RequestMappingHandlerMapping requestMappingHandlerMapping;

    // @Value("${es.caib.carpeta.loginib.entidad}")
    // private String entidad;

    @RequestMapping(value = "/prelogin")
    public String prelogin(HttpServletRequest request, HttpServletResponse response
    // , @PathVariable("mprotocol") String protocol, @PathVariable("mhost") String
    // host
    ) throws Exception {

        // String urluser = protocol + "//" + host + request.getContextPath();

        String callbackurl = request.getParameter("urlbase") + request.getContextPath();

        log.info(" XXX XYZ PRELOGIN CALLBACK  => ]" + callbackurl + "[");
        request.getSession().setAttribute(SESSION_RETURN_URL_POST_LOGIN, callbackurl);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
            throws Exception, I18NException {

        // Eliminam errors anteriors de la sessió
        if (sesionHttp.getErrorLogin() != null) {
            sesionHttp.setErrorLogin(null);
        }

        long temps = System.currentTimeMillis();

        // Error login
        if ("true".equals(request.getParameter("error"))) {

            // Log no autenticat/error
            // Cream les dades bàsiques de la petició per els logs
            StringBuilder peticio = new StringBuilder();
            peticio.append("Usuari: no definit").append("\n");
            peticio.append("classe: ").append(getClass().getName()).append("\n");
            logLogicaEjb.crearLog("Autenticació del Front", ESTAT_LOG_ERROR, TIPUS_LOG_AUTENTICACIO_FRONT,
                    System.currentTimeMillis() - temps, null, "Error de login", peticio.toString(), "", null);
            log.info("Error de login");

        }
        final SavedRequest savedRequest = loginRequestCache.getRequest(request, response);

        if (savedRequest != null && existeTicket(savedRequest)) {

            return autenticarTicket(savedRequest, Constants.TICKET_USER_CLAVE);

        } else {

            if (savedRequest != null) {

                log.info("Punto de entrada: " + savedRequest.getRedirectUrl());

                sesionHttp.setUrlEntrada(getUrlEntrada(savedRequest.getRedirectUrl()));

                log.info("iniciarSesionAutentificacion: " + savedRequest.getRedirectUrl());
            }

            String baseURL = (String) request.getSession().getAttribute(SESSION_RETURN_URL_POST_LOGIN);

            String url_callback_login = baseURL + "/redirigirLogin";
            String url_callback_error = baseURL + "/errorLogin";
            String language = LocaleContextHolder.getLocale().getLanguage();

            String url;
            try {

                url = securityService.iniciarSesionAutentificacion(url_callback_login, url_callback_error, language);

            } catch (Exception e) {
                log.error("Error Iniciant la sessió de seguretat amb Cl@ve: " + e.getMessage(), e);
                url = baseURL;
                sesionHttp.setErrorLogin(e.getMessage());
                return new ModelAndView("redirect:" + url);
            }

            log.info("Url autentificacion: " + url);

            return new ModelAndView("redirect:" + url);

        }

    }

    @RequestMapping(value = "/redirigirLogin")
    public String redirigirLogin(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        log.info("\nDentro de redirigirLogin: sesionHttp.getUrlEntrada() => ]" + sesionHttp.getUrlEntrada() + "[\n");
        
        //String callbackurl = (String) request.getSession().getAttribute(SESSION_RETURN_URL_POST_LOGIN);
        //log.info(" XXX XYZ redirigirLogin CALLBACK  => ]" + callbackurl + "[");

        // Gestionamos la url de entrada a la aplicación previa a autenticarse
        /**
         * XYZ ZZZ try { if (StringUtils.isNotEmpty(sesionHttp.getUrlEntrada())) {
         * response.sendRedirect(sesionHttp.getUrlEntrada()); } else {
         * response.sendRedirect("/carpetafront"); } } catch (IOException e) {
         * e.printStackTrace(); }
         */

        
        
        
        String fullUrlRedirect = (String)request.getSession().getAttribute(InicioController.SESSION_INITIAL_URL);
        
        log.info("\nDentro de redirigirLogin: fullUrlRedirect => ]" + fullUrlRedirect + "[\n");
        
        if (fullUrlRedirect == null) {
            fullUrlRedirect = "/";
        } else {
            request.getSession().removeAttribute(InicioController.SESSION_INITIAL_URL);
        }
        
        log.info("\n XYZ ZZZZ redirigirLogin => " + fullUrlRedirect + "\n");
        
        
        return "redirect:" + fullUrlRedirect ;

        // XYZ ZZZ

        // return new ModelAndView("inici");

    }

    @RequestMapping(value = "/sortir")
    public String salir(HttpServletRequest request) throws Exception {

        log.info("Dentro de salir");

        String baseURL = (String) request.getSession().getAttribute(SESSION_RETURN_URL_POST_LOGIN);

        log.info(" XXX XYZ  BASE URL LOGOUT => " + baseURL);

        String url_callback_logout = baseURL + "/salir";
        String IDIOMA = LocaleContextHolder.getLocale().getLanguage();

        String codiEntitat = sesionHttp.getEntitat();

//        log.info("WWWWWWWWWWWWWW Codi entitat: " + codiEntitat + " WWWWWWWWWWWWWWWWWW");

        securityService.iniciarSesionLogout(url_callback_logout, IDIOMA);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebUIController.SESSION_FULLINFO_MAP);
            session.invalidate();
        }

        if (codiEntitat == null) {
            // Agafam l'entitat per defecte, si en té
            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();

            if (EjbManager.getDefaultEntityCode(propietatGlobalEjb) == null) {
                return "redirect:/entitat";
            } else {
                codiEntitat = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            }
//            log.info("WWWWWWWWWWWWWW entitat es null, posam DefaultEntity: " + codiEntitat + "WWWWWWWWWWWWWWWWWW");
        }

        return "redirect:/e/" + codiEntitat;

    }

    /**
     * Autentica via ticket.
     *
     * @param request        Request
     * @param ticketUserName Usuario asociado al tipo de ticket
     * @return Vista que realiza el login automáticamente
     */
    private ModelAndView autenticarTicket(SavedRequest request, String ticketUserName) throws Exception, I18NException {

        ModelAndView mav = new ModelAndView("loginTicket");

        // Obtenemos ticket de la peticion
        final String[] tickets = request.getParameterMap().get(Constants.TICKET_PARAM);
        if (tickets == null || tickets.length != 1) {
            throw new Exception("No existe ticket");
        }
        final String ticket = tickets[0];

        log.info("Autenticando el ticket: " + tickets[0]);

        // Autenticamos automaticamente
        mav.addObject("ticketName", ticketUserName);
        mav.addObject("ticketValue", ticket);

        return mav;
    }

    /**
     * Comprueba si existe ticket en request.
     *
     * @param request Request
     * @return boolean
     */
    private boolean existeTicket(SavedRequest request) {

        final String[] tickets = request.getParameterMap().get(Constants.TICKET_PARAM);
        if (tickets == null || tickets.length != 1) {
            return false;
        }
        log.info("Existe un ticket de autentificacion: " + tickets.length);

        return true;
    }

    /**
     *
     * @param urlEntrada
     * @return
     */
    private String getUrlEntrada(String urlEntrada) {
        try {

            // Opcional: Comprobar si coincide con alguno de los mappings definidos
            /*
             * for (Map.Entry<RequestMappingInfo, HandlerMethod> entry :
             * requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
             * System.out.println("Item : " + entry.getKey().getPatternsCondition() +
             * " Count : " + entry.getValue().getReturnType()); }
             */

            URL url = new URL(urlEntrada);

            return url.getPath();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
