package es.caib.carpeta.front.security;

import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.front.utils.StringUtils;
import es.caib.carpeta.front.config.LoginRequestCache;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.commons.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class LoginController {
    
    
    public static final String SESSION_RETURN_URL_POST_LOGIN = "SESSION_RETURN_URL_POST_LOGIN";

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @Autowired
    private LoginRequestCache loginRequestCache; //Cache peticiones

    @Autowired
    private SesionHttp sesionHttp;

    //@Autowired
    //private RequestMappingHandlerMapping requestMappingHandlerMapping;

    //@Value("${es.caib.carpeta.loginib.entidad}")
    //private String entidad;


    @RequestMapping(value="/prelogin")
    public String prelogin(HttpServletRequest request, HttpServletResponse response
            //, @PathVariable("mprotocol") String protocol, @PathVariable("mhost") String host
            ) throws Exception {
        
        
        //String urluser = protocol + "//" + host + request.getContextPath();
        
        String urluser = request.getParameter("urlbase") + request.getContextPath();
        
        log.info(" XXX XYZ  urluser => " + urluser);
        request.getSession().setAttribute(SESSION_RETURN_URL_POST_LOGIN, urluser );
        
        return "redirect:/login";
    }

    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
            
        
        // Error login
        if ("true".equals(request.getParameter("error"))) {
            log.info("Error de login");
        }
        final SavedRequest savedRequest = loginRequestCache.getRequest(request, response);

        if(savedRequest != null && existeTicket(savedRequest)){

            return autenticarTicket(savedRequest, Constants.TICKET_USER_CLAVE);

        }else {

            if(savedRequest != null){

                log.info("Punto de entrada: " + savedRequest.getRedirectUrl());

                sesionHttp.setUrlEntrada(getUrlEntrada(savedRequest.getRedirectUrl()));

                log.info("iniciarSesionAutentificacion: " + savedRequest.getRedirectUrl());
            }
            
            String baseURL = (String)request.getSession().getAttribute(SESSION_RETURN_URL_POST_LOGIN);
            
            log.info(" XXX XYZ  BASE URL LOGIN => " + baseURL);
            
            String url_callback_login=baseURL + "/redirigirLogin";
            String url_callback_error= baseURL;
            //    es.caib.carpeta.loginib.url_callback_logout=http://localhost:8080/carpetafront/salir
            String IDIOMA = LocaleContextHolder.getLocale().getLanguage();

            String url = securityService.iniciarSesionAutentificacion(url_callback_login, url_callback_error, IDIOMA);

            log.info("Url autentificacion: " + url);

            return new ModelAndView("redirect:"+url);

        }

    }

    @RequestMapping(value="/redirigirLogin")
    public ModelAndView redirigirLogin(HttpServletRequest request, HttpServletResponse response) {

        log.info("Dentro de redirigirLogin: " + sesionHttp.getUrlEntrada());

        // Gestionamos la url de entrada a la aplicación previa a autenticarse
        try {
            if(StringUtils.isNotEmpty(sesionHttp.getUrlEntrada())){
                response.sendRedirect(sesionHttp.getUrlEntrada());
            }else{
                response.sendRedirect("/carpetafront");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("inici");

    }

    @RequestMapping(value="/sortir")
    public String salir(HttpServletRequest request) throws Exception{

        log.info("Dentro de salir");
        
        String baseURL = (String)request.getSession().getAttribute(SESSION_RETURN_URL_POST_LOGIN);
        
        log.info(" XXX XYZ  BASE URL LOGOUT => " + baseURL);
        
       
        String url_callback_logout= baseURL + "/salir";
        String IDIOMA = LocaleContextHolder.getLocale().getLanguage();

        securityService.iniciarSesionLogout(url_callback_logout, IDIOMA);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";

    }

    /**
     * Autentica via ticket.
     *
     * @param request Request
     * @param ticketUserName Usuario asociado al tipo de ticket
     * @return Vista que realiza el login automáticamente
     */
    private ModelAndView autenticarTicket(SavedRequest request, String ticketUserName) throws Exception {

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
        mav.addObject("ticketValue",ticket);

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
        log.info("Existe un ticket de autentificacion: "+ tickets.length);

        return true;
    }

    /**
     *
     * @param urlEntrada
     * @return
     */
    private String getUrlEntrada(String urlEntrada){
        try {

            // Opcional: Comprobar si coincide con alguno de los mappings definidos
            /*for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
                System.out.println("Item : " + entry.getKey().getPatternsCondition() + " Count : " + entry.getValue().getReturnType());
            }*/

           URL url = new URL(urlEntrada);

           return url.getPath();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
