package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.Sistra2Service;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TramiteController {

    protected final Log log = LogFactory.getLog(getClass());


    @Autowired
    Sistra2Service sistra2Service;


    @RequestMapping(value = { "/tramites"}, method = RequestMethod.GET)
    public ModelAndView tramites(ModelMap model, Authentication authentication) {

        ModelAndView mav = new ModelAndView("tramites");

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            List<RTramitePersistencia> tramites = sistra2Service.obtenerTramites(usuarioAutenticado.getUsuarioClave().getNif());

            mav.addObject("tramites", tramites);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value = { "/tramite/{id}"}, method = RequestMethod.GET)
    public RedirectView  tramite(@PathVariable("id") String idSesionTramitacion, Authentication authentication) {

        ModelAndView mav = new ModelAndView("tramites");

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            String url = sistra2Service.obtenerUrlTicketAcceso(usuarioAutenticado.getUsuarioClave(), idSesionTramitacion);

            return new RedirectView(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
