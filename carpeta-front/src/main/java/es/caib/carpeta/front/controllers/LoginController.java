package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.SecurityService;
import es.caib.carpeta.front.config.LoginRequestCache;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    SecurityService securityService;

    @Autowired
    private LoginRequestCache loginRequestCache; //Cache peticiones

    @Value("${es.caib.carpeta.loginib.entidad}")
    private String entidad;

    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("Dentro de LoginController : " + entidad);

        // Error login
        if ("true".equals(request.getParameter("error"))) {
            log.info("Error de login");
        }
        final SavedRequest savedRequest = loginRequestCache.getRequest(request, response);

        if(savedRequest == null){
            String url = securityService.iniciarSesionAutentificacion();

            log.info("URL: " + url);

            return new ModelAndView("redirect:"+url);
        }else {
            log.info("savedRequest.getRedirectUrl(): " + savedRequest.getRedirectUrl());

            return autenticarTicket(savedRequest);

        }

    }

    @RequestMapping(value="/principal")
    public ModelAndView principal(HttpServletRequest request) {

        log.info("Dentro de principal");

        ModelAndView mav = new ModelAndView("principal");

        final UsuarioAutenticado usuario = (UsuarioAutenticado) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        mav.addObject("nombre", usuario.getUsuarioClave().getNombre());
        mav.addObject("apellidos", usuario.getUsuarioClave().getApellido1() + ' ' + usuario.getUsuarioClave().getApellido2());
        mav.addObject("nif", usuario.getUsuarioClave().getNif());

        return mav;

    }

    @RequestMapping(value="/salir")
    public ModelAndView salir(HttpServletRequest request) throws Exception{

        log.info("Dentro de salir");

        securityService.iniciarSesionLogout();

        return new ModelAndView("logout");

    }

    /**
     * Autentica via ticket.
     *
     * @param pTicketName
     *            Nombre ticket
     * @param pTicketUser
     *            Usuario asociado al tipo de ticket
     * @return Vista que realiza el login automáticamente
     */
    private ModelAndView autenticarTicket(SavedRequest request) throws Exception {

        ModelAndView mav = new ModelAndView("loginTicket");

        // Obtenemos ticket de la peticion
        final String[] tickets = request.getParameterMap().get("ticket");
        if (tickets == null || tickets.length != 1) {
            throw new Exception("No existe ticket");
        }
        final String ticket = tickets[0];

        log.info("Dentro de autenticar ticket: " + tickets[0]);

        // Autenticamos automaticamente
        mav.addObject("ticketName","ticket-clave");
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

        final String[] tickets = request.getParameterMap().get("ticket");
        if (tickets == null || tickets.length != 1) {
            return false;
        }
        log.info("Dentro de existeTicket: "+ tickets.length);

        return true;
    }
}
