package es.caib.carpeta.back.controllers;

import es.caib.carpeta.core.service.ProductoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class InicioController {

    protected final Log log = LogFactory.getLog(getClass());

    @Value("${es.caib.carpeta.regweb3.host}")    private String ENTORNO_URL;

    @Autowired
    private ProductoService productoService;

    @RequestMapping(value="/inicio")
    public ModelAndView inicio(HttpServletRequest request, Authentication authentication) {

        String now = (new Date()).toString();

        ModelAndView mav = new ModelAndView("inicio");

        mav.addObject("now", now);
        mav.addObject("usuario", request.getUserPrincipal().getName());
        mav.addObject("roles", authentication.getAuthorities());

        mav.addObject("entornoURL", ENTORNO_URL);
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("idioma", loc);

        mav.addObject("test", productoService.test());
        return mav;
    }
}
