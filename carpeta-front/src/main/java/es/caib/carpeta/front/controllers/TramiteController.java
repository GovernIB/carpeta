package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.Sistra1Service;
import es.caib.carpeta.core.service.Sistra2Service;
import es.caib.carpeta.core.utils.DateUtils;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.form.FechaBusqueda;
import es.caib.carpeta.front.utils.TramitesCiudadano;
import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping(value = "/tramite")
public class TramiteController {

    @Value("${es.caib.carpeta.suport.correu}") private String SUPORT_CORREU;
    @Value("${es.caib.carpeta.suport.telefon}") private String SUPORT_TELEFON;
    @Value("${es.caib.carpeta.suport.autenticacio}") private String SUPORT_AUTENTICACIO;

    protected final Log log = LogFactory.getLog(getClass());


    @Autowired
    Sistra2Service sistra2Service;

    @Autowired
    Sistra1Service sistra1Service;


    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView tramitesPendientes(Authentication authentication) {

        ModelAndView mav = new ModelAndView("tramitesPendientes", "fechaBusqueda", new FechaBusqueda());

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("breadcrumb", Arrays.asList("inicio", "tramite/list"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.tramites"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;

    }

    @RequestMapping(value = { "/list"}, method = RequestMethod.POST)
    public String tramitesPendientes(@ModelAttribute("fechaBusqueda") FechaBusqueda fechaBusqueda, Authentication authentication, ModelMap model) {

        Locale loc = LocaleContextHolder.getLocale();
        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {

            List<RTramitePersistencia> tramitesSistra2 = sistra2Service.obtenerTramites(usuarioAutenticado.getUsuarioClave().getNif(),
                    fechaBusqueda.getFechaInicio(), fechaBusqueda.getFechaFin());
            List<TramitePersistente> tramitesSistra1 = sistra1Service.obtenerTramites(usuarioAutenticado.getUsuarioClave().getNif(),
                    fechaBusqueda.getFechaInicio(), fechaBusqueda.getFechaFin());

            GregorianCalendar fechaIni = new GregorianCalendar();
            GregorianCalendar fechaFin = new GregorianCalendar();

            fechaIni.setTime(fechaBusqueda.getFechaInicio());
            fechaFin.setTime(DateUtils.sumarRestarDiasFecha(fechaBusqueda.getFechaFin(), 1));

            List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();
            coms.add(TipoElementoExpediente.PREENVIO);
            coms.add(TipoElementoExpediente.PREREGISTRO);

            List<ElementoExpediente> tramitesSistra1Otros = sistra1Service.obtenerElementosExpediente(coms, CarpetaConstantes.ELEMENTO_TODOS, usuarioAutenticado.getUsuarioClave(), loc, fechaIni, fechaFin);

            if(tramitesSistra2 != null){
                log.info("Tramites Sistra2: " + tramitesSistra2.size());
            }

            if(tramitesSistra1 != null){
                log.info("Tramites Sistra1: " + tramitesSistra1.size());
            }

            if(tramitesSistra1Otros != null){
                log.info("Tramites envio, preenvio, registro y preregistro Sistra1: " + tramitesSistra1Otros.size());
            }

            TramitesCiudadano tramites = new TramitesCiudadano(tramitesSistra2, tramitesSistra1, tramitesSistra1Otros, loc);
            log.info("Tramites totales: " + tramites.getTramites().size());
            model.addAttribute("tramites", tramites.getTramites());

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("breadcrumb", Arrays.asList("inicio", "tramite/list"));
        model.addAttribute("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.tramites"));

        model.addAttribute("suport_correu", SUPORT_CORREU);
        model.addAttribute("suport_telefon", SUPORT_TELEFON);
        model.addAttribute("suport_autenticacio", SUPORT_AUTENTICACIO);

        return "tramitesPendientes";
    }

    @RequestMapping(value = { "/sistra2/{id}"}, method = RequestMethod.GET)
    public RedirectView tramiteSistra2(@PathVariable("id") String idSesionTramitacion, Authentication authentication) {

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            String url = sistra2Service.obtenerUrlTicketAcceso(usuarioAutenticado.getUsuarioClave(), idSesionTramitacion);

            if(StringUtils.isNotEmpty(url)){
                log.info("Url: " + url);
                return new RedirectView(url);
            }else{
                return new RedirectView("/inici");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
