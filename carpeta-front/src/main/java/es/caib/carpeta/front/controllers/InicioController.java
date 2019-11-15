package es.caib.carpeta.front.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class InicioController {

    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value="/inicio")
    public ModelAndView inicio(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("inicio");

        mav.addObject("breadcrumb", Arrays.asList("inicio"));

        return mav;

    }

    @RequestMapping(value = { "/accesibilidad"})
    public ModelAndView accesibilidad(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("accesibilidad");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "accesibilidad"));

        return mav;
    }

    @RequestMapping(value = { "/datosPersonales"})
    public ModelAndView datosPersonales(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("datosPersonales");

        mav.addObject("breadcrumb", Arrays.asList("inicio", "datosPersonales"));

        return mav;
    }
}
