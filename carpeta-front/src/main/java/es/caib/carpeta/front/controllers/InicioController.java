package es.caib.carpeta.front.controllers;

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

    @RequestMapping(value="/inici")
    public ModelAndView inicio(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("inici");

        log.info("Dentro de inicioController");

        if(authentication != null){
            return new ModelAndView("redirect:/principal");
        }

        return mav;

    }

    @RequestMapping(value = { "/tramitList"}, method = RequestMethod.GET)
    public String tramitlistPage(ModelMap model) {
        return "tramitList";
    }

    @RequestMapping(value = { "/registreList"}, method = RequestMethod.GET)
    public String registreListPage(ModelMap model) {
        return "registreList";
    }

    @RequestMapping(value = { "/registreDetall"}, method = RequestMethod.GET)
    public String registreDetallPage(ModelMap model) {
        return "registreDetall";
    }

    @RequestMapping(value = { "/accessibilitat"}, method = RequestMethod.GET)
    public String accessibilitatPage(ModelMap model) {
        return "accessibilitat";
    }
}
