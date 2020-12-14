package es.caib.carpeta.front.controller;

import com.google.gson.Gson;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalLocal;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.jpa.AvisJPA;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.entity.Enllaz;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    @Autowired
    private SesionHttp sesionHttp;

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

    public static class SuportInfo {
        protected String tipus;
        protected String valor;

        public SuportInfo() {
            super();
            // TODO Auto-generated constructor stub
        }

        public SuportInfo(String tipus, String valor) {
            super();
            this.tipus = tipus;
            this.valor = valor;
        }

        public String getTipus() {
            return tipus;
        }

        public void setTipus(String tipus) {
            this.tipus = tipus;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

    }

    @RequestMapping(value = WEBUI_INFOLOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatInfoLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            String codiEntitat = sesionHttp.getEntitat();
            String idioma = LocaleContextHolder.getLocale().getLanguage();

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
            String codiEntitat = sesionHttp.getEntitat();

            Long iconaEntitatId = utilsEjb.getLogolateralEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    @RequestMapping(value = WEBUI_FAVICON_PATH, method = RequestMethod.GET)
    public void getEntitatFavicon(HttpServletRequest request, HttpServletResponse response) {

        try {
            String codiEntitat = sesionHttp.getEntitat();

            if(codiEntitat != null) {
                Long iconaEntitatId = utilsEjb.getIconaEntitat(codiEntitat);
                descarregaFitxer(response, iconaEntitatId);
            }


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

    @RequestMapping(value = "/menubarlinks", method = RequestMethod.GET)
    public void getMenuBarLinks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_MENU_BARRA;

        getEnllazosJSON(request, response, enllazType);
    }

    @RequestMapping(value = "/menuslidelinks", method = RequestMethod.GET)
    public void getMenuSlideLinks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT;

        getEnllazosJSON(request, response, enllazType);
    }

    protected void getEnllazosJSON(HttpServletRequest request, HttpServletResponse response, final int enllazType) {
        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

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
            //String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

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

    
    @RequestMapping(value = "/suport", method = RequestMethod.GET)
    public void getEntitatSuport(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            Map<String, String> suport = utilsEjb.getSuportEntitat(codiEntitat, lang);

            List<SuportInfo> suportInfo = new ArrayList<WebUIController.SuportInfo>();

            for (Map.Entry<String, String> dada : suport.entrySet()){
                suportInfo.add(new SuportInfo(dada.getKey(), dada.getValue()));
            }

            // Passar JSON de dadesSuport
            Gson gson = new Gson();
            String json = gson.toJson(suportInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
    }


    @RequestMapping(value = "/canviarIdioma/{idioma}", method = RequestMethod.GET)
    public void canviarIdiomaServidor(@PathVariable("idioma") String idioma, HttpServletRequest request, HttpServletResponse response) {

        try {
            Locale idiomaFinal = new Locale(idioma);
            LocaleContextHolder.setLocale(idiomaFinal);
            WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, idiomaFinal);
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
            String codiEntitat = sesionHttp.getEntitat();

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

    @RequestMapping(value = "/entityDefault", method = RequestMethod.GET)
    public void getEntityDefault(HttpServletRequest request, HttpServletResponse response) {

        try {
            //String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalLocal propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            log.info("Default Entity Code => " +  defaultEntityCode);
            sesionHttp.setEntitat(defaultEntityCode);

            List<StringKeyValue> skv = new ArrayList<StringKeyValue>(1);
            if(defaultEntityCode != null){
                skv.add(new StringKeyValue(defaultEntityCode, defaultEntityCode));
            }

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(skv);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
    }

    @RequestMapping(value = "/entitats", method = RequestMethod.GET)
    public void getEntitats(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();

            List<StringKeyValue> entitats = utilsEjb.getEntitats(lang);

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(entitats);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processException(e, response);
        }
    }

    @RequestMapping(value = "/nomEntitat", method = RequestMethod.GET)
    public void getNomEntitat(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            EntitatJPA entitat = entitatEjb.findByCodi(codiEntitat);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            response.getWriter().println(entitat.getNom().getTraduccio(lang).getValor());
            response.flushBuffer();

        } catch (Throwable e) {
            processException(e, response);
        }
    }

}
