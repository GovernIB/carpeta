package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.Sistra1Service;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
@RequestMapping(value = "/notificacion")
public class NotificacionController {

    @Value("${es.caib.carpeta.zonaper.url}")    private String ZONAPER_URL;
    @Value("${es.caib.carpeta.notificaciones.url}")    private String NOTIFICACIONES_URL;
    @Value("${es.caib.carpeta.sistra1.zonaper.url}")    private String SISTRA1_ZONAPER_URL;

    @Autowired
    Sistra1Service sistra1Service;

    protected final Log log = LogFactory.getLog(getClass());


    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView tramites(ModelMap model, Authentication authentication) throws Exception {

        ModelAndView mav = new ModelAndView("notificaciones");
        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("zonaperUrl", ZONAPER_URL);
        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "notificaciones"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.notificaciones"));

        return mav;

    }

    @RequestMapping(value = { "/comunicaciones"}, method = RequestMethod.GET)
    public ModelAndView comunicaciones(ModelMap model, Authentication authentication) throws Exception {

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();
        Locale loc = LocaleContextHolder.getLocale();

        List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();
        coms.add(TipoElementoExpediente.COMUNICACION);
        coms.add(TipoElementoExpediente.NOTIFICACION);
//        coms.add(TipoElementoExpediente.ENVIO);
//        coms.add(TipoElementoExpediente.REGISTRO);
//        coms.add(TipoElementoExpediente.PREREGISTRO);
//        coms.add(TipoElementoExpediente.PREENVIO);

        List<ElementoExpediente> comunicaciones = sistra1Service.obtenerElementosExpediente(coms, CarpetaConstantes.ELEMENTO_TODOS, usuarioAutenticado.getUsuarioClave(), loc);

        ModelAndView mav = new ModelAndView("comunicaciones");

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("sistraZonaperUrl", SISTRA1_ZONAPER_URL);
        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
        mav.addObject("comunicacions", comunicaciones);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "notificacion/list", "notificaciones.otras"));

        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.comunicaciones"));

        return mav;

    }


}
