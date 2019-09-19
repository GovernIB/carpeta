package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.RegWeb3Service;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.JustificanteReferenciaWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/registro")
public class RegistroController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    RegWeb3Service regWeb3Service;


    /**
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
    public ModelAndView registros(Authentication authentication) {

        ModelAndView mav = new ModelAndView("registros");

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            ResultadoBusquedaWs registros = regWeb3Service.obtenerAsientosCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), 0);

            if(registros != null){
                log.info("Total registros: " +registros.getResults().size());
                mav.addObject("registros", registros.getResults());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value="/detalle/**", method = RequestMethod.GET)
    public ModelAndView registroDetalle(HttpServletRequest request, Authentication authentication) {

        ModelAndView mav = new ModelAndView("registroDetalle");

        String numeroRegistro = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        log.info("Numero registro:" + numeroRegistro.replace("/registro/detalle/",""));

        UsuarioAutenticado usuarioAutenticado = (UsuarioAutenticado)authentication.getPrincipal();

        try {
            AsientoRegistralWs asiento = regWeb3Service.obtenerAsientoCiudadano(usuarioAutenticado.getUsuarioClave().getNif(), numeroRegistro.replace("/registro/detalle/",""));
            JustificanteReferenciaWs justificante = regWeb3Service.obtenerReferenciaJustificante(numeroRegistro.replace("/registro/detalle/",""));

            mav.addObject("asiento", asiento);
            mav.addObject("justificante", justificante);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mav;
    }

}
