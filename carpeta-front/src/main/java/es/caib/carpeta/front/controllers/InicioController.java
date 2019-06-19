package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.RegWeb3Service;
import es.caib.carpeta.core.service.Sistra2Service;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class InicioController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    RegWeb3Service regWeb3Service;

    @Autowired
    Sistra2Service sistra2Service;

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

    @RequestMapping(value = { "/accesibilidad"}, method = RequestMethod.GET)
    public ModelAndView accesibilidad(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("accesibilidad");

        log.info("ENTRAMMMMMMMMMM");
        return mav;
    }
}
