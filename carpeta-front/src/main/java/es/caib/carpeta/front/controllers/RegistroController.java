package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.RegWeb3Service;
import es.caib.carpeta.core.service.Sistra1Service;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.carpeta.front.utils.TramitesCiudadano;
import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.regweb3.ws.api.v3.AnexoWs;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/registro")
public class RegistroController {

    protected final Log log = LogFactory.getLog(getClass());

    @Value("${es.caib.carpeta.concsv.url}")    private String CONCSV_URL;

    @Value("${es.caib.carpeta.suport.correu}") private String SUPORT_CORREU;
    @Value("${es.caib.carpeta.suport.telefon}") private String SUPORT_TELEFON;
    @Value("${es.caib.carpeta.suport.autenticacio}") private String SUPORT_AUTENTICACIO;

    @Autowired
    RegWeb3Service regWeb3Service;

    @Autowired
    Sistra1Service sistra1Service;

    /**
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView registros(Authentication authentication) {

        Locale loc = LocaleContextHolder.getLocale();
        ModelAndView mav = new ModelAndView("registros");

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            ResultadoBusquedaWs registros = regWeb3Service.obtenerAsientosCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), 0);

            if(registros != null){
                log.info("Total registros: " +registros.getResults().size());
                mav.addObject("registros", registros.getResults());
            }

            List<TipoElementoExpediente> coms = new ArrayList<TipoElementoExpediente>();
//            coms.add(TipoElementoExpediente.REGISTRO);
            coms.add(TipoElementoExpediente.ENVIO);

            List<ElementoExpediente> tramitesSistra1Otros = sistra1Service.obtenerElementosExpediente(coms, CarpetaConstantes.ELEMENTO_NO_PENDIENTE, usuarioAutenticado.getUsuarioClave(), loc);

            if (tramitesSistra1Otros != null) {
                log.info("Total tramites acabados: " +tramitesSistra1Otros.size());
                TramitesCiudadano tramites = new TramitesCiudadano(tramitesSistra1Otros, loc);
                log.info("Tramites totales: " + tramites.getTramites().size());
                mav.addObject("tramites", tramites.getTramites());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("breadcrumb", Arrays.asList("inicio", "registro/list"));
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.registros"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);
        return mav;
    }

    @RequestMapping(value="/detalle/**", method = RequestMethod.GET)
    public ModelAndView registroDetalle(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("registroDetalle");

        String numeroRegistro = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        String numReg = numeroRegistro.replace("/registro/detalle/","");
        log.info("Numero registro:" + numReg);

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            AsientoRegistralWs asiento = regWeb3Service.obtenerAsientoCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), numReg);

            if(asiento != null){
                for (AnexoWs anexo : asiento.getAnexos()) {
                    if (anexo.isJustificante() && StringUtils.isNotEmpty(anexo.getCsv())) {
                        mav.addObject("justificante", CONCSV_URL.concat(anexo.getCsv()));
                    }
                }
                mav.addObject("asiento", asiento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("autenticacio", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        mav.addObject("numeroRegistro", numReg);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "registro/list", "registro/detalle"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.registro.detalle"));

        mav.addObject("suport_correu", SUPORT_CORREU);
        mav.addObject("suport_telefon", SUPORT_TELEFON);
        mav.addObject("suport_autenticacio", SUPORT_AUTENTICACIO);

        return mav;
    }
}
