package es.caib.carpeta.front.controller;

import com.google.gson.Gson;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.logic.utils.PluginInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class InicioController {

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;

    protected final Log log = LogFactory.getLog(getClass());

    @Value("${es.caib.carpeta.zonaper.url}")    private String ZONAPER_URL;
    @Value("${es.caib.carpeta.notificaciones.url}")    private String NOTIFICACIONES_URL;



    @RequestMapping(value={"/", "/inici"})
    public ModelAndView inicio(HttpServletRequest request) throws I18NException {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        // TODO XYZ ZZZ falta entitat
        String codiEntitat = "caib";

        List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(codiEntitat, lang);

        ModelAndView mav = new ModelAndView("inici");

        // Passar JSON de pluginsEntitat
        Gson gson = new Gson();
        String plugins = gson.toJson(pluginsEntitat);
        mav.addObject("plugins", plugins);

        return mav;

    }

//    @RequestMapping(value={"/index"})
//    public ModelAndView index(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("index.html");
//
//        return mav;
//
//    }


//    @RequestMapping(value = { "/accessibilitat"})
//    public String accessibilitat(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("accessibilitat");
//
//        log.info("ENTRAMMM ACCESSIBILITAT");
//
////        mav.addObject("breadcrumb", Arrays.asList("inici", "accessibilitat"));
////        Locale loc = LocaleContextHolder.getLocale();
////        mav.addObject("title_page", "HEM ENTRAT ::::");
//
//        return "redirect:/accessibilitat";
//    }
//
//    @RequestMapping(value = { "/datosPersonales"})
//    public ModelAndView datosPersonales(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("datosPersonales");
//
//        mav.addObject("breadcrumb", Arrays.asList("inicio", "datosPersonales"));
//        Locale loc = LocaleContextHolder.getLocale();
//        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.datosPersonales"));
//
//        return mav;
//    }

//    @RequestMapping(value = { "/avisoLegal"})
//    public ModelAndView avisoLegal(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("avisoLegal");
//
//        mav.addObject("breadcrumb", Arrays.asList("inicio", "avisoLegal"));
//
//        return mav;
//    }

//    @RequestMapping(value = { "/mapaWeb"})
//    public ModelAndView mapaWeb(HttpServletRequest request) {
//
//        ModelAndView mav = new ModelAndView("mapaWeb");
//
//        mav.addObject("breadcrumb", Arrays.asList("inicio", "mapaWeb"));
//        mav.addObject("zonaperUrl", ZONAPER_URL);
//        mav.addObject("notificacionesUrl", NOTIFICACIONES_URL);
//        Locale loc = LocaleContextHolder.getLocale();
//        mav.addObject("title_page", ResourceBundle.getBundle("mensajes", loc).getString("titulo.mapaWeb"));
//
//
//        return mav;
//    }
}
