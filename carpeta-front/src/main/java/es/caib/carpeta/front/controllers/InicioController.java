package es.caib.carpeta.front.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class InicioController {

    protected final Log log = LogFactory.getLog(getClass());

    @Value("${es.caib.carpeta.zonaper.url}")    private String ZONAPER_URL;
    @Value("${es.caib.carpeta.notificaciones.url}")    private String NOTIFICACIONES_URL;
    @Value("${es.caib.carpeta.regweb3.host}")    private String ENTORNO_URL;


    @RequestMapping(value="/inicio")
    public ModelAndView inicio(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("inicio");

//        mav.addObject("breadcrumb", Arrays.asList("inicio"));
        mav.addObject("entornoURL", ENTORNO_URL);
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("idioma", loc);
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.inicio"));

        return mav;

    }

    @RequestMapping(value = { "/accesibilidad"})
    public ModelAndView accesibilidad(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("accesibilidad");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "accesibilidad"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.accesibilidad"));

        return mav;
    }

    @RequestMapping(value = { "/datosPersonales"})
    public ModelAndView datosPersonales(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("datosPersonales");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "datosPersonales"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.datosPersonales"));

        return mav;
    }

//    @RequestMapping(value = { "/avisoLegal"})
//    public ModelAndView avisoLegal(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("avisoLegal");
//
//        mav.addObject("breadcrumb", Arrays.asList("inicio", "avisoLegal"));
//
//        return mav;
//    }

    @RequestMapping(value = { "/mapaWeb"})
    public ModelAndView mapaWeb(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("mapaWeb");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "mapaWeb"));
        mav.addObject("zonaperUrl", ZONAPER_URL);
        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.mapaWeb"));


        return mav;
    }

//    @RequestMapping(value="/anonim")
//    public ModelAndView anonim(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("anonim");
//
//        mav.addObject("breadcrumb", Arrays.asList("inicio", "anonim"));
//        mav.addObject("entornoURL", ENTORNO_URL);
//        Locale loc = LocaleContextHolder.getLocale();
//        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.inicio"));
//        mav.addObject("idioma", loc);
//
//        return mav;
//
//    }
}
