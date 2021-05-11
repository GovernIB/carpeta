package es.caib.carpeta.front.controller;

import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.front.service.SecurityService;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Idioma;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.persistence.EntitatJPA;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InicioController extends CommonFrontController {

    @Autowired
    private SesionHttp sesionHttp;

    @Autowired
    SecurityService securityService;

    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    UtilitiesForFrontLogicaService utilsEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    EntitatLogicaService entitatEjb;

    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = { "/entitat"}, method = RequestMethod.GET)
    public ModelAndView llistarEntitats(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        ModelAndView mav = new ModelAndView("entitat");

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);

            // Primera vegada que entra al front i no sap on anar
            if (defaultEntityCode == null && sesionHttp.getEntitat() == null) {

                List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

                mav.addObject("entitats", entitats);
                mav.addObject("langActual", lang);
                List<Idioma> idiomes = utilsEjb.getIdiomes();
                List<Idioma> idiomesActius = new ArrayList<>();
                for(Idioma idioma : idiomes){
                    if(idioma.isSuportat()){
                        idiomesActius.add(idioma);
                    }
                }
                mav.addObject("idiomes", idiomesActius);

            }
            // Primera vegada que entra al front però sap on anar
            if (defaultEntityCode != null && sesionHttp.getEntitat() == null) {

                mav = new ModelAndView("redirect:/e/" + defaultEntityCode);

            }
            // Ve per canviar d'entitat
            if (sesionHttp.getEntitat() != null) {

                List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

                mav.addObject("entitats", entitats);
                mav.addObject("langActual", lang);

            }

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }

        return mav;

    }

    @RequestMapping(value = { "/e/{codiEntitat}" }, method = RequestMethod.GET)
    public String triarEntitat(@PathVariable("codiEntitat") String codiEntitat, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        try {
            // Eliminam sessió anterior
            if(sesionHttp.getEntitat() != null) {
                if (!sesionHttp.getEntitat().equals(codiEntitat)) {
                    String baseURL = (String) request.getSession().getAttribute("SESSION_RETURN_URL_POST_LOGIN");
                    String url_callback_logout = baseURL + "/salir";
                    String IDIOMA = LocaleContextHolder.getLocale().getLanguage();
                    securityService.iniciarSesionLogout(url_callback_logout, IDIOMA);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            }

            sesionHttp.setEntitat(codiEntitat);

            long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID, EntitatFields.CODI.equal(codiEntitat));
            sesionHttp.setEntitatID(entitatID);

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }

        return "redirect:/";

    }

    @RequestMapping(value = { "/" })
    public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws I18NException {

        
        ModelAndView mav = new ModelAndView("inici");

        // Posam la sessió de la variable global (per 60 segons)
        PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
        if(EjbManager.getFrontSessionTime(propietatGlobalEjb) != null) {
            int frontSessionTime = Integer.parseInt(EjbManager.getFrontSessionTime(propietatGlobalEjb));
            mav.addObject("maxInactiveInterval", frontSessionTime * 60);
        }else{
            mav.addObject("maxInactiveInterval", 30 * 60);
        }

        try {

            String lang = LocaleContextHolder.getLocale().getLanguage();

            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            String canviardefront = EjbManager.getCanviarDeFront(propietatGlobalEjb);

            List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

            String errorDeLogin = sesionHttp.getErrorLogin();

            if (defaultEntityCode == null && sesionHttp.getEntitat() == null) {

                if (entitats.size() > 1) {

                    mav = new ModelAndView("entitat");
                    mav.addObject("entitats", entitats);
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);
                    List<Idioma> idiomes = utilsEjb.getIdiomes();
                    List<Idioma> idiomesActius = new ArrayList<>();
                    for(Idioma idioma : idiomes){
                        if(idioma.isSuportat()){
                            idiomesActius.add(idioma);
                        }
                    }
                    mav.addObject("idiomes", idiomesActius);
                    mav.addObject("langActual", lang);

                } else {


                    String codiEntitat = entitats.get(0).getCodi();

                    long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID, EntitatFields.CODI.equal(codiEntitat));
                    sesionHttp.setEntitatID(entitatID);

                    sesionHttp.setEntitat(codiEntitat);

                    mav.addObject("entitat", codiEntitat);
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);

                }

            } else if (defaultEntityCode != null && sesionHttp.getEntitat() == null) {

                EntitatJPA entitat = entitatEjb.findByCodi(defaultEntityCode);

                if (entitat != null) {

                    long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID, EntitatFields.CODI.equal(defaultEntityCode));
                    sesionHttp.setEntitatID(entitatID);

                    sesionHttp.setEntitat(defaultEntityCode);


                    mav.addObject("entitat", sesionHttp.getEntitat());
                    mav.addObject("defaultEntityCode", defaultEntityCode);
                    mav.addObject("numEntitats", entitats.size());
                    mav.addObject("canviarDeFront", canviardefront);

                } else {

                    if (sesionHttp.getEntitat() != null) {
                        mav.addObject("entitat", sesionHttp.getEntitat());
                        mav.addObject("numEntitats", entitats.size());
                        mav.addObject("canviarDeFront", canviardefront);
                    } else {
                        mav = new ModelAndView("entitat");
                        mav.addObject("entitats", entitats);
                    }

                }
            } else if (sesionHttp.getEntitat() != null) {

                mav.addObject("entitat", sesionHttp.getEntitat());
                mav.addObject("numEntitats", entitats.size());
                mav.addObject("canviarDeFront", canviardefront);
                mav.addObject("errorLogin", errorDeLogin);

            }

        } catch (Throwable e) {
            processExceptionHtml(e, request, response);
        }

        return mav;

    }

    @RequestMapping(value = { "/error"}, method = RequestMethod.GET)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception e) throws I18NException {

        ModelAndView mav = new ModelAndView("error");

        try {

            mav.addObject("error", e.getMessage());

        } catch (Throwable e1) {
            log.error("Error: " + e1.getMessage(), e1);
        }

        return mav;

    }

}
