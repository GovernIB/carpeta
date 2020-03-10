package es.caib.carpeta.front.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;


@Controller
@RequestMapping(value = "/notificacion")
public class NotificacionController {

    @Value("${es.caib.carpeta.zonaper.url}")    private String ZONAPER_URL;
    @Value("${es.caib.carpeta.notificaciones.url}")    private String NOTIFICACIONES_URL;

    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView tramites(ModelMap model, Authentication authentication) {

        ModelAndView mav = new ModelAndView("notificaciones");

        mav.addObject("zonaperUrl", ZONAPER_URL);
        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "notificaciones"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.notificaciones"));

        return mav;

    }




}
