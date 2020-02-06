package es.caib.carpeta.front.controllers;

import es.caib.carpeta.core.service.RegWeb3Service;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.carpeta.front.config.UsuarioAutenticado;
import es.caib.regweb3.ws.api.v3.AnexoWs;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping(value = "/registro")
public class RegistroController {

    protected final Log log = LogFactory.getLog(getClass());

    @Value("${es.caib.carpeta.concsv.url}")    private String CONCSV_URL;

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

        mav.addObject("breadcrumb", Arrays.asList("inicio", "registro/list"));Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.registros"));

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

        mav.addObject("numeroRegistro", numReg);
        mav.addObject("breadcrumb", Arrays.asList("inicio", "registro/list", "registro/detalle"));
        Locale loc = LocaleContextHolder.getLocale();
        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.registro.detalle"));

        return mav;
    }
}
