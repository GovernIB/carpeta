package es.caib.carpeta.front.controller;



import com.google.gson.Gson;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.jpa.AvisJPA;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.entity.Enllaz;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Conjunt de cridades REST per obtenir informació per a la presentació de la
 * pàgina WEB.
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = WebUIController.WEBUI_PATH)
public class WebUIController extends CommonFrontController {

    public static final String WEBUI_PATH = "/webui";

    public static final String WEBUI_FAVICON_PATH = "/icona";

    public static final String WEBUI_LOGOLATERAL_PATH = "/logolateral";

    public static final String WEBUI_INFOLOGOLATERAL_PATH = "/infologolateral";

    public static final String ENLLAZ_LOGO_PATH = "/enllazlogo";

    /**
     * 
     * @author anadal
     *
     */
    public static class EnllazInfo {
        protected String label;
        protected String url;
        protected String urllogo;

        public EnllazInfo() {
            super();
            // TODO Auto-generated constructor stub
        }

        public EnllazInfo(String label, String url, String urllogo) {
            super();
            this.label = label;
            this.url = url;
            this.urllogo = urllogo;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrllogo() {
            return urllogo;
        }

        public void setUrllogo(String urllogo) {
            this.urllogo = urllogo;
        }

    }

    public static class AvisInfo {
        protected String label;
        protected String gravetat;

        public AvisInfo() {
            super();
            // TODO Auto-generated constructor stub
        }

        public AvisInfo(String label, String gravetat) {
            super();
            this.label = label;
            this.gravetat = gravetat;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getGravetat() {
            return gravetat;
        }

        public void setGravetat(String gravetat) {
            this.gravetat = gravetat;
        }

    }

    @RequestMapping(value = WEBUI_INFOLOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatInfoLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            // XYZ ZZZ Pendent codiEntitat
            String codiEntitat = "caib";
            String idioma = "ca";

            EntitatJPA entitat = utilsEjb.getEntitat(codiEntitat);


            List<EnllazInfo> enllazosInfo = new ArrayList<WebUIController.EnllazInfo>();
            
            EnllazInfo enllazInfo = new EnllazInfo(entitat.getNom().getTraduccio(idioma).getValor(),
                    entitat.getWebEntitat(), request.getContextPath() + WEBUI_PATH + WEBUI_LOGOLATERAL_PATH);

            enllazosInfo.add(enllazInfo);
            
            // Passar JSON 

            Gson gson = new Gson();
            String json = gson.toJson(enllazosInfo);

            response.setContentType("application/json");

            response.getWriter().write(json);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = WEBUI_LOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            // XYZ ZZZ Pendent codiEntitat
            String codiEntitat = "caib";

            Long iconaEntitatId = utilsEjb.getLogolateralEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = WEBUI_FAVICON_PATH, method = RequestMethod.GET)
    public void getEntitatFavicon(HttpServletRequest request, HttpServletResponse response) {

        try {
            // XYZ ZZZ Pendent codiEntitat
            String codiEntitat = "caib";

            Long iconaEntitatId = utilsEjb.getIconaEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = ENLLAZ_LOGO_PATH + "/{encodedenllazlogoid}", method = RequestMethod.GET)
    public void getEnllazLogo(@PathVariable("encodedenllazlogoid") String encodedenllazlogoid,
            HttpServletRequest request, HttpServletResponse response) {

        try {
            String fileIDStr = HibernateFileUtil.decryptString(encodedenllazlogoid);

            Long fileID = Long.parseLong(fileIDStr);

            descarregaFitxer(response, fileID);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @Deprecated
    @RequestMapping(value = "/socialnetworks", method = RequestMethod.GET)
    public void getSocialNetworksDeprecated(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL;

        getEnllazosJSON(request, response, enllazType);
    }


    @RequestMapping(value = "/socialnetworkslinks", method = RequestMethod.GET)
    public void getSocialNetworks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL;

        getEnllazosJSON(request, response, enllazType);
    }


    @RequestMapping(value = "/laterallinks", method = RequestMethod.GET)
    public void getLateralLinks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_LATERAL;

        getEnllazosJSON(request, response, enllazType);
    }


    @RequestMapping(value = "/centralfooterlinks", method = RequestMethod.GET)
    public void getCentalFooterLinks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_PEU_CENTRAL;

        getEnllazosJSON(request, response, enllazType);
    }

    protected void getEnllazosJSON(HttpServletRequest request, HttpServletResponse response, final int enllazType) {
        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            // TODO XYZ ZZZ falta entitat
            String codiEntitat = "caib";

            List<Enllaz> enllazos = utilsEjb.getEnllazosByType(codiEntitat, lang, enllazType);

            List<EnllazInfo> enllazosInfo = new ArrayList<WebUIController.EnllazInfo>();

            for (Enllaz enllaz : enllazos) {

                EnllazJPA enllazJPA = (EnllazJPA) enllaz;

                String label = enllazJPA.getNomTraduccions().get(lang).getValor();
                String url = enllazJPA.getUrlTraduccions().get(lang).getValor();

                String urllogo = request.getContextPath() + WEBUI_PATH + ENLLAZ_LOGO_PATH + "/"
                        + HibernateFileUtil.encryptFileID(enllazJPA.getLogoID());

                enllazosInfo.add(new EnllazInfo(label, url, urllogo));
            }

            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(enllazosInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
    }

    @RequestMapping(value = "/textinformatiuentitat", method = RequestMethod.GET)
    public void getTextInformatiuEntitat(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            // TODO XYZ ZZZ falta entitat
            String codiEntitat = "caib";

            String texteInformatiu = utilsEjb.getTexteInformatiuEntitat(codiEntitat);

            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html");
                response.getWriter().println(texteInformatiu);
                response.flushBuffer();

            } catch (IOException io){
                log.error("Error obtening writer: " + io.getMessage(), io);
            }


        } catch (Throwable e) {
            processException(e, response);
        }
    }

    @RequestMapping(value = "/canviarIdioma/{idioma}", method = RequestMethod.GET)
    public void canviarIdiomaServidor(@PathVariable("idioma") String idioma, HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            log.info("teníem el llenguatge: " + lang);

            LocaleContextHolder.setLocale(new Locale(idioma));
            log.info("ARA TENIM el llenguatge: " + LocaleContextHolder.getLocale().getLanguage());

        } catch (Throwable e) {
            processException(e, response);
        }
    }

    @RequestMapping(value = "/avisosfrontpublic", method = RequestMethod.GET)
    public void getAvisosFrontPublic(HttpServletRequest request, HttpServletResponse response) {

        final int avisType = Constants.TIPUS_AVIS_FRONT_ABANS_LOGIN;
        getAvisosJSON(request, response, avisType);
    }

    @RequestMapping(value = "/avisosfrontprivat", method = RequestMethod.GET)
    public void getAvisosFrontPrivat(HttpServletRequest request, HttpServletResponse response) {

        final int avisType = Constants.TIPUS_AVIS_FRONT_DESPRES_LOGIN;
        getAvisosJSON(request, response, avisType);
    }

    protected void getAvisosJSON(HttpServletRequest request, HttpServletResponse response, final int avisType) {
        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            // TODO XYZ ZZZ falta entitat
            String codiEntitat = "caib";

            List<AvisJPA> avisos = utilsEjb.getAvisosByType(codiEntitat, avisType);

            List<AvisInfo> avisosInfo = new ArrayList<>();

            for (Avis avis : avisos) {

                AvisJPA avisJPA = (AvisJPA) avis;

                String label = avisJPA.getDescripcio().getTraduccio(lang).getValor();
                String gravetat = String.valueOf(avisJPA.getGravetat());

                avisosInfo.add(new AvisInfo(label, gravetat));
            }

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(avisosInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
    }
//
//    @RequestMapping(value = "/autenticat", method = RequestMethod.GET)
//    public void isAutenticat(HttpServletRequest request, HttpServletResponse response) {
//
//        try {
//            // TODO XYZ ZZZ Pendent codiEntitat
//            String codiEntitat = "caib";
//            Gson gson = new Gson();
//            response.setContentType("application/json");
//            if(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
//                response.getWriter().write(gson.toJson("false"));
//            }else{
//                response.getWriter().write(gson.toJson("true"));
//            }
//
//        } catch (Throwable e) {
//            processException(e, response);
//        }
//
//    }

}
