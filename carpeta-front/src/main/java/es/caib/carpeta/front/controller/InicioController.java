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
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class InicioController extends CommonFrontController {

    @Autowired
    private SesionHttp sesionHttp;

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value={"/entitat"}, method = RequestMethod.GET)
    public ModelAndView llistarEntitats(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        ModelAndView mav = new ModelAndView("entitat");

        try {

            String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            log.info("Default Entity Code => " + defaultEntityCode);

            if (defaultEntityCode == null) {

                List<StringKeyValue> entitats = utilsEjb.getEntitats(lang);

                mav.addObject("entitats", entitats);

            } else {

                mav = new ModelAndView("redirect:/entitat/" + defaultEntityCode);

            }

        } catch (Throwable e) {
            processException(e, response);
        }

        return mav;

    }

    @RequestMapping(value={"/entitat/{codiEntitat}"}, method = RequestMethod.GET)
    public String triarEntitat(@PathVariable("codiEntitat") String codiEntitat, HttpServletRequest request, HttpServletResponse response) throws I18NException {

        try{

            String lang = LocaleContextHolder.getLocale().getLanguage();
            sesionHttp.setEntitat(codiEntitat);

        } catch (Throwable e) {
            processException(e, response);
        }

        return "redirect:/";

    }


    @RequestMapping(value={"/"})
    public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        ModelAndView mav = new ModelAndView("inici");

        try{

            String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);

            List<StringKeyValue> entitats = utilsEjb.getEntitats(lang);

            if(defaultEntityCode == null && sesionHttp.getEntitat() == null) {

                if(entitats.size() > 1){

                    mav = new ModelAndView("entitat");
                    mav.addObject("entitats", entitats);
                    mav.addObject("numEntitats", entitats.size());

                } else{

                    sesionHttp.setEntitat(entitats.get(0).key);
                    mav.addObject("entitat", entitats.get(0).key);
                    mav.addObject("numEntitats", entitats.size());

                }

            } else{

                if(defaultEntityCode != null){
                    sesionHttp.setEntitat(defaultEntityCode);
                }

                mav.addObject("entitat", sesionHttp.getEntitat());
                mav.addObject("defaultEntityCode", defaultEntityCode);
                mav.addObject("numEntitats", entitats.size());

            }


        } catch (Throwable e) {
            processException(e, response);
        }

        return mav;

    }

}
