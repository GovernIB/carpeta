package es.caib.carpeta.front.controller;

import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.logic.utils.EjbManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class InicioController {

    @Autowired
    private SesionHttp sesionHttp;

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value={"/entitat"}, method = RequestMethod.GET)
    public ModelAndView llistarEntitats(HttpServletRequest request) throws I18NException {

        String lang = LocaleContextHolder.getLocale().getLanguage();

        PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
        String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
        log.info("Default Entity Code => " +  defaultEntityCode);

        if(defaultEntityCode == null) {

            List<StringKeyValue> entitats = utilsEjb.getEntitats(lang);

            ModelAndView mav = new ModelAndView("entitat");
            mav.addObject("entitats", entitats);

            return mav;

        } else{

            return new ModelAndView("redirect:/entitat/"+defaultEntityCode);

        }

    }

    @RequestMapping(value={"/entitat/{codiEntitat}"}, method = RequestMethod.GET)
    public String triarEntitat(@PathVariable("codiEntitat") String codiEntitat, HttpServletRequest request) throws I18NException {

        String lang = LocaleContextHolder.getLocale().getLanguage();
        sesionHttp.setEntitat(codiEntitat);

        return "redirect:/";

    }


    @RequestMapping(value={"/"})
    public ModelAndView inicio(HttpServletRequest request) throws I18NException {

        String lang = LocaleContextHolder.getLocale().getLanguage();

        PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
        String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);

        if(defaultEntityCode == null && sesionHttp.getEntitat() == null) {

            List<StringKeyValue> entitats = utilsEjb.getEntitats(lang);

            ModelAndView mav = new ModelAndView("entitat");
            mav.addObject("entitats", entitats);

            return mav;

        } else{

            if(defaultEntityCode != null){
                sesionHttp.setEntitat(defaultEntityCode);
            }

            ModelAndView mav = new ModelAndView("inici");
            mav.addObject("entitat", sesionHttp.getEntitat());
            mav.addObject("defaultEntityCode", defaultEntityCode);

            return mav;

        }

    }

}
