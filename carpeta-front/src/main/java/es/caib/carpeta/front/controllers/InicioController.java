package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.Sistra1Service;
import es.caib.carpeta.core.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Value("${es.caib.carpeta.suport.correu}") private String SUPORT_CORREU;
    @Value("${es.caib.carpeta.suport.telefon}") private String SUPORT_TELEFON;
    @Value("${es.caib.carpeta.suport.autenticacio}") private String SUPORT_AUTENTICACIO;

    @Autowired
    Sistra1Service sistra1Service;

    @RequestMapping(value="/inicio")
    public ModelAndView inicio(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("inicio");

//        mav.addObject("breadcrumb", Arrays.asList("inicio"));
//        mav.addObject("entornoURL", ENTORNO_URL);
        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("idioma", loc);
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.inicio"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;

    }

    @RequestMapping(value = { "/accesibilidad"})
    public ModelAndView accesibilidad(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("accesibilidad");

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("breadcrumb", Arrays.asList("inicio", "accesibilidad"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.accesibilidad"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;
    }

    @RequestMapping(value = { "/datosPersonales"})
    public ModelAndView datosPersonales(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("datosPersonales");

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("breadcrumb", Arrays.asList("inicio", "datosPersonales"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.datosPersonales"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;
    }


    @RequestMapping(value = { "/mapaWeb"})
    public ModelAndView mapaWeb(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("mapaWeb");

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("breadcrumb", Arrays.asList("inicio", "mapaWeb"));
        mav.addObject("zonaperUrl", ZONAPER_URL);
        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.mapaWeb"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;
    }

    @RequestMapping(value={"/anonim"})
    public ModelAndView anonim(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("anonim");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "anonim"));
        mav.addObject("entornoURL", ENTORNO_URL);
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.inicio"));
        mav.addObject("idioma", loc);

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;

    }

    @RequestMapping(value={"/anonim"}, method = RequestMethod.POST)
    public ModelAndView anonim(String claveAcceso) throws Exception {

        String urlAnonimo = sistra1Service.obtenerTramiteAnonimo(claveAcceso);

        if (StringUtils.isEmpty(urlAnonimo)) {
            ModelAndView mav = new ModelAndView("anonim");

            mav.addObject("breadcrumb", Arrays.asList("inicio", "anonim"));
            mav.addObject("entornoURL", ENTORNO_URL);
            Locale loc = LocaleContextHolder.getLocale();
            mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.inicio"));
            mav.addObject("idioma", loc);
            mav.addObject("error", "ERROR");

            mav.addObject("suport_correu", SUPORT_CORREU);
            mav.addObject("suport_telefon", SUPORT_TELEFON);
            mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

            return mav;
        }

        return new ModelAndView("redirect:"+urlAnonimo);

    }
}
