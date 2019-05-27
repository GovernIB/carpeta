package es.caib.carpeta.front.controllers;

import es.caib.carpeta.front.config.UsuarioAutenticado;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InicioController {

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value="/inicio")
    public ModelAndView inicio(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("inicio");

        log.info("Dentro de inicioController");

        if(authentication != null){
            UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

            log.info("Usuario autenticado: " + usuarioAutenticado.getUsuarioClave().getNombreCompleto());
            return new ModelAndView("redirect:/principal");
        }

        return mav;

    }

    @RequestMapping(value = { "/tramites"}, method = RequestMethod.GET)
    public String tramites(ModelMap model) {
        return "tramites";
    }

    @RequestMapping(value = { "/registros"}, method = RequestMethod.GET)
    public String registros(ModelMap model) {
        return "registros";
    }

    @RequestMapping(value = { "/registreDetall"}, method = RequestMethod.GET)
    public String registreDetall(ModelMap model) {
        return "registro";
    }

    @RequestMapping(value = { "/accesibilidad"}, method = RequestMethod.GET)
    public String accesibilidad(ModelMap model) {
        return "accesibilidad";
    }
}
