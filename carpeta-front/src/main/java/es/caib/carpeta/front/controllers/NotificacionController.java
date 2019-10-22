package es.caib.carpeta.front.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


@Controller
@RequestMapping(value = "/notificacion")
public class NotificacionController {

    @Value("${es.caib.carpeta.zonaper.url}")    private String ZONAPER_URL;

    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView tramites(ModelMap model, Authentication authentication) {

        ModelAndView mav = new ModelAndView("notificaciones");

        mav.addObject("zonaperUrl", ZONAPER_URL);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "notificacion"));

        return mav;

    }




}
