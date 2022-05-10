package es.caib.carpeta.front.controller;

import com.google.gson.Gson;
import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.SeccioLogicaService;
import es.caib.carpeta.logic.utils.PluginInfo;
import es.caib.carpeta.model.entity.*;
import es.caib.carpeta.model.fields.IdiomaFields;
import es.caib.carpeta.persistence.*;
import es.caib.carpeta.pluginsib.carpetafront.api.FileInfo;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Conjunt de cridades REST per obtenir informació per a la presentació de la
 * pàgina WEB.
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = WebUIController.WEBUI_PATH)
public class WebUIController extends PluginFrontController {
    
    @EJB(mappedName = SeccioLogicaService.JNDI_NAME)
    SeccioLogicaService seccioLogicaEjb;

    public static final String WEBUI_PATH = "/webui";

    public static final String WEBUI_FAVICON_PATH = "/icona";

    public static final String WEBUI_LOGOLATERAL_PATH = "/logolateral";

    public static final String WEBUI_INFOLOGOLATERAL_PATH = "/infologolateral";

    public static final String ENLLAZ_LOGO_PATH = "/enllazlogo";

    public static final String ENTITAT_ICONA_PATH = "/entityicon";

    public static final String ENTITAT_CUSTOM_CSS = "/customcss";
    
    /**
     * 
     * @author anadal
     *
     */
    public static class SeccioInfo {

        protected long seccioID;
        protected String context;
        protected String nom;
        protected String descripcio;        
        protected String iconaID;
        protected int ordre;

        public SeccioInfo() {
            super();
        }

        public SeccioInfo(long seccioID, String nom,  String descripcio, String context, String iconaID, int ordre) {
            super();
            this.seccioID = seccioID;
            this.nom = nom;
            this.descripcio = descripcio;
            this.iconaID = iconaID;
            this.context = context;
            this.ordre = ordre;
        }

        public long getSeccioID() {
            return seccioID;
        }

        public void setSeccioID(long seccioID) {
            this.seccioID = seccioID;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getDescripcio() {
            return descripcio;
        }
        public void setDescripcio(String descripcio) {
            this.descripcio = descripcio;
        }
        public String getIconaID() {
            return iconaID;
        }
        public void setIconaID(String iconaID) {
            this.iconaID = iconaID;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public int getOrdre() {
            return ordre;
        }

        public void setOrdre(int ordre) {
            this.ordre = ordre;
        }

    }

    
    
    
    /**
     * 
     * @author anadal
     *
     */
    public static class EnllazInfo {
        
        protected String enllazID;
        protected String label;
        protected String labelDescription;
        protected String url;
        protected String urllogo;
        protected int ordre;

        public EnllazInfo() {
            super();
        }

        public EnllazInfo(String enllazID, String label, String labelDescription, String url, String urllogo, int ordre) {
            super();
            this.enllazID = enllazID;
            this.label = label;
            this.labelDescription = labelDescription;
            this.url = url;
            this.urllogo = urllogo;
            this.ordre = ordre;
        }
        
        
        

        public String getEnllazID() {
            return enllazID;
        }

        public void setEnllazID(String enllazID) {
            this.enllazID = enllazID;
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

        public String getLabelDescription() {
            return labelDescription;
        }

        public void setLabelDescription(String labelDescription) {
            this.labelDescription = labelDescription;
        }

        public int getOrdre() {
            return ordre;
        }

        public void setOrdre(int ordre) {
            this.ordre = ordre;
        }
        
        
        
    }
    
    /**
     * 
     * @author anadal
     *
     */
    public static class EntitatInfo {
        protected String nom;
        protected String html;
        protected String color;
        protected String htmlAccessibilitat;
        protected String htmlAvislegal;

        
        public EntitatInfo() {
            super();
        }
        public EntitatInfo(String nom, String html, String color, String htmlAccessibilitat, String htmlAvislegal) {
            super();
            this.nom = nom;
            this.html = html;
            this.color = color;
            this.htmlAccessibilitat = htmlAccessibilitat;
            this.htmlAvislegal = htmlAvislegal;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getHtml() {
            return html;
        }
        public void setHtml(String html) {
            this.html = html;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public String getHtmlAccessibilitat() { return htmlAccessibilitat; }
        public void setHtmlAccessibilitat(String htmlAccessibilitat) { this.htmlAccessibilitat = htmlAccessibilitat; }
        public String getHtmlAvislegal() { return htmlAvislegal; }
        public void setHtmlAvislegal(String htmlAvislegal) { this.htmlAvislegal = htmlAvislegal; }
    
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

    public static class EntitatCanviInfo {
        protected String nom;
        protected String descripcio;
        protected String urlIcona;
        protected String codi;


        public EntitatCanviInfo() {
            super();
        }
        public EntitatCanviInfo(String nom, String descripcio, String urlIcona, String codi) {
            super();
            this.nom = nom;
            this.descripcio = descripcio;
            this.urlIcona = urlIcona;
            this.codi = codi;
        }
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getDescripcio() {
            return descripcio;
        }
        public void setDescripcio(String descripcio) {
            this.descripcio = descripcio;
        }
        public String getUrlIcona() {
            return urlIcona;
        }
        public void setUrlIcona(String urlIcona) {
            this.urlIcona = urlIcona;
        }
        public String getCodi() {
            return codi;
        }
        public void setCodi(String codi) {
            this.codi = codi;
        }


    }

    /**
     *
     * @author jpernia
     *
     */
    public static class FaqInfo {

        protected String title;
        protected String content;

        public FaqInfo() {
            super();
        }

        public FaqInfo(String title, String content) {
            super();
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }

    }

    @RequestMapping(value = WEBUI_INFOLOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatInfoLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            String codiEntitat = sesionHttp.getEntitat();
            String idioma = LocaleContextHolder.getLocale().getLanguage();

            EntitatJPA entitat = utilsEjb.getEntitat(codiEntitat);


            List<EnllazInfo> enllazosInfo = new ArrayList<WebUIController.EnllazInfo>();
            
            EnllazInfo enllazInfo = new EnllazInfo(null, entitat.getNom().getTraduccio(idioma).getValor(),
                     null, entitat.getWebEntitat(), request.getContextPath() + WEBUI_PATH + WEBUI_LOGOLATERAL_PATH, 0);

            
            enllazosInfo.add(enllazInfo);
            
            // Passar JSON 

            Gson gson = new Gson();
            String json = gson.toJson(enllazosInfo);

            response.setContentType("application/json");

            response.getWriter().write(json);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }

   @RequestMapping(value = WEBUI_LOGOLATERAL_PATH, method = RequestMethod.GET)
    public void getEntitatLogoLateral(HttpServletRequest request, HttpServletResponse response) {

        try {
            String codiEntitat = sesionHttp.getEntitat();

            Long iconaEntitatId = utilsEjb.getLogolateralEntitat(codiEntitat);

            descarregaFitxer(response, iconaEntitatId);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
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
            processExceptionRest(e, request, response);
        }

    }
    
    
    @RequestMapping(value = ENTITAT_CUSTOM_CSS + "/{entitatid}", method = RequestMethod.GET)
    public void getCustomCss(@PathVariable("entitatid") String entitatid, HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
    		response.setContentType("text/css");
    		
    		if(entitatid != null) {
    			Long customCssEntitatId = utilsEjb.getCustomCssEntitat(entitatid);
                if (customCssEntitatId != null)
                	descarregaFitxer(response, customCssEntitatId);
    		}
    		
    	} catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    	
    }
    

    @RequestMapping(value = WEBUI_FAVICON_PATH + "/{entitatid}", method = RequestMethod.GET)
    public void getEntitatIcona(@PathVariable("entitatid") String entitatid, HttpServletRequest request, HttpServletResponse response) {

        try {

            if(entitatid != null) {
                Long iconaEntitatId = utilsEjb.getIconaEntitat(entitatid);
                descarregaFitxer(response, iconaEntitatId);
            }


        } catch (Throwable e) {
            processExceptionRest(e, request, response);
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
            processExceptionRest(e, request, response);
        }

    }

    @Deprecated
    @RequestMapping(value = "/socialnetworks", method = RequestMethod.GET)
    public void getSocialNetworksDeprecated(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL;

        getEnllazosJSON(request, response, enllazType);
    }


   /* @RequestMapping(value = "/socialnetworkslinks", method = RequestMethod.GET)
    public void getSocialNetworks(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = es.caib.carpeta.commons.utils.Constants.TIPUS_ENLLAZ_FRONT_XARXA_SOCIAL;

        getEnllazosJSON(request, response, enllazType);
    }*/


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
    
    @RequestMapping(value = "/menuslidelinkssorted", method = RequestMethod.GET)
    public void getMenuSlideLinksSorted(HttpServletRequest request, HttpServletResponse response) {

        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT;
               

        try {
            Long seccioID = null;
            
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            List<EnllazInfo> enllazosInfo = getEnllazos(request, enllazType, seccioID, lang, codiEntitat);

            List<ItemInfo> itemsInfo = new ArrayList<ItemInfo>();
            
            for (EnllazInfo enllazInfo : enllazosInfo) {
                itemsInfo.add(ItemInfo.createFromEnllazInfo(enllazInfo));
            }
            
            Map<String, Object> fullInfo = null;
            fullInfo = new TreeMap<String, Object>();            
            fullInfo.put("items", itemsInfo);
            
            
            // Passar itemsInfo a JSON
            Gson gson = new Gson();
            String json = gson.toJson(fullInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");
            
            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }
    
    
    // XYZ ZZZ
    /*@RequestMapping(value = "/menupseudoplugin", method = RequestMethod.GET)
    public void getMenuPseudoPlugin(HttpServletRequest request, HttpServletResponse response ) {

        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN;
        
        Long seccioPareID = null;

        getEnllazosJSON(request, response, enllazType, seccioPareID);
    }*/
    
    
   /* @RequestMapping(value = "/menupseudoplugin/{seccioPareID}", method = RequestMethod.GET)
    public void getMenuPseudoPlugin(HttpServletRequest request, HttpServletResponse response, @PathVariable("seccioPareID") Long seccioPareID) {

        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN;

        getEnllazosJSON(request, response, enllazType, seccioPareID);
    }*/

    
    protected void getEnllazosJSON(HttpServletRequest request, HttpServletResponse response, final int enllazType) {
       final Long seccioID = null;
       getEnllazosJSON(request, response, enllazType, seccioID);
    }
    
    

    protected void getEnllazosJSON(HttpServletRequest request, HttpServletResponse response, final int enllazType, Long seccioID) {
        try {
            if (seccioID != null && seccioID == 0) {
                seccioID = null;
            }            
            
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            List<EnllazInfo> enllazosInfo = getEnllazos(request, enllazType, seccioID, lang, codiEntitat);

            // Passar enllazosInfo a 
            Gson gson = new Gson();
            String json = gson.toJson(enllazosInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }

    protected List<EnllazInfo> getEnllazos(HttpServletRequest request, final int enllazType, Long seccioID, String lang,
            String codiEntitat) throws I18NException {

        List<Enllaz> enllazos = utilsEjb.getEnllazosByType(codiEntitat, lang, enllazType, seccioID);

        List<EnllazInfo> enllazosInfo = new ArrayList<WebUIController.EnllazInfo>();
        
        for (Enllaz enllaz : enllazos) {

            EnllazJPA enllazJPA = (EnllazJPA) enllaz;

            String label = enllazJPA.getNomTraduccions().get(lang).getValor();
            String url = enllazJPA.getUrlTraduccions().get(lang).getValor();

            String urllogo = request.getContextPath() + WEBUI_PATH + ENLLAZ_LOGO_PATH + "/"
                    + HibernateFileUtil.encryptFileID(enllazJPA.getLogoID());

            String desc;
            if (enllazType == Constants.TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN) {
                if (enllazJPA.getDescripcio() == null) {
                    desc = label;
                } else {
                    desc = enllazJPA.getDescripcioTraduccions().get(lang).getValor();
                }
            } else {
                desc = null;
            }
            
            EnllazInfo ei = new EnllazInfo(String.valueOf(enllazJPA.getEnllazID()),
                    label, desc, url, urllogo, enllazJPA.getOrdre());            
            enllazosInfo.add(ei);
        }
        return enllazosInfo;
    }
    
    
    
    @RequestMapping(value = "/seccioplugin/{seccioContext}/{pluginContext}", method = RequestMethod.GET)
    public void getSeccioPlugin(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("seccioContext") String seccioContext, @PathVariable("pluginContext") String pluginContext) {
        try {
            
            String lang = LocaleContextHolder.getLocale().getLanguage();
//            log.info("/seccioplugin/" + seccioContext + "/" + pluginContext + " ==> " + lang);
            
            long entitatID = sesionHttp.getEntitatID();
            /*
            Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);
  
            PluginInfo pluginInfo = utilsEjb.getFrontPluginInfo(  lang, pluginID);
            */
            PluginInfo pluginInfo = utilsEjb.getFrontPluginInfoByContext(lang, pluginContext, entitatID);
            
            
            SeccioInfo seccioInfo = getSeccioInfo(request, seccioContext, lang);

            Gson gson = new Gson();
            String json = gson.toJson(new Object[] { seccioInfo, pluginInfo });

            log.info(" Seccio-Plugin amb seccioContext = " + seccioContext + "-" + pluginContext  + ":\n" + json + "\n");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }

    
    

    @RequestMapping(value = "/seccio/{seccioContext}", method = RequestMethod.GET)
    public void getSeccio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("seccioContext") String seccioContext) {
        try {
            
            String lang = LocaleContextHolder.getLocale().getLanguage();
//            log.info("/seccio/" + seccioContext + " ==> " + lang);
            
            SeccioInfo seccioInfo = getSeccioInfo(request, seccioContext, lang);

            // Passar enllazosInfo a
            Gson gson = new Gson();
            String json = gson.toJson(seccioInfo);

            log.info(" Seccio amb Context = " + seccioContext + ":\n" + json + "\n");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }

    protected SeccioInfo getSeccioInfo(HttpServletRequest request, String seccioContext, String lang) throws I18NException {
        
        Seccio seccio = seccioLogicaEjb.findByContext(seccioContext);

        SeccioJPA seccioJPA = (SeccioJPA) seccio;

        String nom ,descripcio;
        if (seccioJPA.getNomTraduccions().get(lang) == null) {  
            log.error(" TENIM UN LLENGUATGE DESCONEGUT [" + lang + "] !!!!!", new Exception());
            nom = seccioJPA.getNomTraduccions().get(Configuracio.getDefaultLanguage()).getValor();
            descripcio = seccioJPA.getDescripcioTraduccions().get(Configuracio.getDefaultLanguage()).getValor();                
        } else {
            nom = seccioJPA.getNomTraduccions().get(lang).getValor();
            descripcio = seccioJPA.getDescripcioTraduccions().get(lang).getValor();
        }

        String urllogo = request.getContextPath() + WEBUI_PATH + ENLLAZ_LOGO_PATH + "/"
                + HibernateFileUtil.encryptFileID(seccioJPA.getIconaID());

        SeccioInfo seccioInfo = new SeccioInfo(seccioJPA.getSeccioID(), nom, descripcio, seccioJPA.getContexte(), urllogo, seccioJPA.getOrdre());
        return seccioInfo;
    }
    
    
    @RequestMapping(value = "/seccions/{seccioPareID}", method = RequestMethod.GET)
    public void getSeccions(HttpServletRequest request, HttpServletResponse response,@PathVariable("seccioPareID") Long seccioPareID) {

        if (seccioPareID != null && seccioPareID == 0) {
            seccioPareID = null;
        }

        getSeccionsJSON(request, response, seccioPareID);
    }

    
    
    
    protected void getSeccionsJSON(HttpServletRequest request, HttpServletResponse response, final Long seccioPareID) {
        try {
            
            long entitatID = sesionHttp.getEntitatID();
            
//            log.info(" XYZ ZZZ ENTITAT ID = " + entitatID );
            
            List<SeccioInfo> seccioInfo = getSeccionsInfo(request, seccioPareID, entitatID);

            // Passar enllazosInfo a 
            Gson gson = new Gson();
            String json = gson.toJson(seccioInfo);
            
            
            log.info(" Seccions amb PAREID = " + seccioPareID + ":\n" + json + "\n");
            

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }

    protected List<SeccioInfo> getSeccionsInfo(HttpServletRequest request, final Long seccioPareID, long entitatID)
            throws I18NException {
        List<Seccio> seccions = seccioLogicaEjb.findByEntity(entitatID, seccioPareID);

        List<SeccioInfo> seccioInfo = new ArrayList<SeccioInfo>();
        
        String lang = LocaleContextHolder.getLocale().getLanguage();

        for (Seccio seccio : seccions) {

            SeccioJPA seccioJPA = (SeccioJPA) seccio;

            String nom = seccioJPA.getNomTraduccions().get(lang).getValor();
            String descripcio = seccioJPA.getDescripcioTraduccions().get(lang).getValor();

            String urllogo = request.getContextPath() + WEBUI_PATH + ENLLAZ_LOGO_PATH + "/"
                    + HibernateFileUtil.encryptFileID(seccioJPA.getIconaID());

            
            
            seccioInfo.add(new SeccioInfo(seccioJPA.getSeccioID(), nom, descripcio, seccioJPA.getContexte(), urllogo, seccioJPA.getOrdre()));
        }
        return seccioInfo;
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
            processExceptionRest(e, request, response);
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
            processExceptionRest(e, request, response);
        }
    }


    @RequestMapping(value = "/canviarIdioma/{idioma}", method = RequestMethod.GET)
    public void canviarIdiomaServidor(@PathVariable("idioma") String idioma, HttpServletRequest request, HttpServletResponse response) {

        try {
            Locale idiomaFinal = new Locale(idioma);
            LocaleContextHolder.setLocale(idiomaFinal);
            WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, idiomaFinal);
        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        } finally {
            request.getSession().removeAttribute(SESSION_FULLINFO_MAP);
            request.getSession().removeAttribute(SESSION_FULLINFO_SORTED_MAP);
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

            List<Avis> avisos = utilsEjb.getAvisosByType(codiEntitat, avisType);

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
            processExceptionRest(e, request, response);
        }
    }

    /*@RequestMapping(value = "/entityDefault", method = RequestMethod.GET)
    public void getEntityDefault(HttpServletRequest request, HttpServletResponse response) {

        try {
            //String lang = LocaleContextHolder.getLocale().getLanguage();

            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            String defaultEntityCode = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            log.info("Default Entity Code => " +  defaultEntityCode);
            sesionHttp.setEntitat(defaultEntityCode);

            long entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID, EntitatFields.CODI.equal(defaultEntityCode));
            sesionHttp.setEntitatID(entitatID);


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
    }*/

    @RequestMapping(value = "/entitats", method = RequestMethod.GET)
    public void getEntitats(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();

            List<EntitatJPA> entitats = utilsEjb.getEntitatsFull(lang);

            List<EntitatCanviInfo> entitatsInfo = new ArrayList<>();

            for (Entitat entitat : entitats) {

                EntitatJPA entitatJPA = (EntitatJPA) entitat;

                String nom = entitatJPA.getNom().getTraduccio(lang).getValor();
                String descripcio = entitatJPA.getEntitatDescFront();
                String urlIcona = request.getContextPath() + WEBUI_PATH + ENTITAT_ICONA_PATH + "/"
                        + entitatJPA.getEntitatID();
                String codi = entitatJPA.getCodi();

                entitatsInfo.add(new EntitatCanviInfo(nom, descripcio, urlIcona, codi));
            }

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(entitatsInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }
/*
    @RequestMapping(value = "/nomEntitat", method = RequestMethod.GET)
    public void getEntitatNom(HttpServletRequest request, HttpServletResponse response) {

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
    }*/
    
    
    @RequestMapping(value = "/infoEntitat", method = RequestMethod.GET)
    public void getEntitatInfo(HttpServletRequest request, HttpServletResponse response) {

        try {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            String codiEntitat = sesionHttp.getEntitat();

            EntitatJPA entitat = entitatEjb.findByCodi(codiEntitat);
            
            EntitatInfo ei = new EntitatInfo();
            
            ei.setNom(entitat.getNom().getTraduccio(lang).getValor());
            ei.setColor(entitat.getColorMenu());
            
            if (entitat.getLoginText() != null && entitat.getLoginText().getTraduccio(lang) != null) {
                ei.setHtml(entitat.getLoginText().getTraduccio(lang).getValor());
//                log.info(" XYZ ZZZ RETORNAT HTML PER ENTITAT " + codiEntitat);
            }

            // Mira si té declaració d'Accessibilitat i Avís Legal definida al back, segons idioma del front
            switch(lang) {
                case "ca":
                    if (entitat.getAccessibilitatCa() != null) {
                        ei.setHtmlAccessibilitat(entitat.getAccessibilitatCa());
                    }else{
                        ei.setHtmlAccessibilitat("");
                    }

                    if (entitat.getAvisLegalCa() != null) {
                        ei.setHtmlAvislegal(entitat.getAvisLegalCa());
                    }else{
                        ei.setHtmlAvislegal("");
                    }

                    break;
                case "es":
                    if (entitat.getAccessibilitatEs() != null) {
                        ei.setHtmlAccessibilitat(entitat.getAccessibilitatEs());
                    }else{
                        ei.setHtmlAccessibilitat("");
                    }

                    if (entitat.getAvisLegalEs() != null) {
                        ei.setHtmlAvislegal(entitat.getAvisLegalEs());
                    }else{
                        ei.setHtmlAvislegal("");
                    }

                    break;
            }

//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html");
//            response.getWriter().println(entitat.getNom().getTraduccio(lang).getValor());
//            response.flushBuffer();
            
            
            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(ei);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);


        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }
    

    @RequestMapping(value = "/idiomesFront", method = RequestMethod.GET)
    public void getIdiomes(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<String> idiomaInfo = getIdiomes();

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(idiomaInfo);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }

    protected List<String> getIdiomes() throws I18NException {
        List<Idioma> idiomesFront = idiomaEjb.select(IdiomaFields.SUPORTAT.equal(true), new OrderBy(IdiomaFields.ORDRE));

        List<String> idiomaInfo = new ArrayList<>();
        for (Idioma idioma : idiomesFront) {
            idiomaInfo.add(idioma.getIdiomaID());
        }
        return idiomaInfo;
    }

    @RequestMapping(value = "/entityicon/{entityID}", method = RequestMethod.GET)
    public void  getEntityIcon(@PathVariable("entityID") Long entityID, HttpServletRequest request, HttpServletResponse response) throws Exception, I18NException {

        try {

            FileInfo fi = utilsEjb.getIconEntity(entityID);

            if (fi != null) {
                response.setContentType(fi.getMime());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fi.getName() + "\"");
                response.setContentLength((int) fi.getData().length);

                response.getOutputStream().write(fi.getData());
            }

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }
    
    
    @RequestMapping(value = "/plugin/{pluginContext}", method = RequestMethod.GET)
    public void getPlugin(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginContext") String pluginContext) {
        try {
            
            String lang = LocaleContextHolder.getLocale().getLanguage();
            
            Long pluginID = utilsEjb.getFrontPluginIDByContext(pluginContext);
            
            Long entitatID = sesionHttp.getEntitatID();
            
            
            PluginInfo pluginInfo = utilsEjb.getFrontPluginInfo(lang, pluginID, entitatID);
            
           

            // Passar enllazosInfo a
            Gson gson = new Gson();
            String json = gson.toJson(pluginInfo);

            log.info(" PluginInfo amb ID = " + pluginID + ":\n" + json + "\n");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
    }
    
    
    public static final String SESSION_FULLINFO_MAP = "SESSION_FULLINFO_MAP";
    
    
    
    @RequestMapping(value = "/fullinfo/{seccioContext}" , method = RequestMethod.GET)
    public void getFullInfo(HttpServletRequest request, HttpServletResponse response,
          @PathVariable("seccioContext") String seccioContext) throws I18NException {
            
        try {
            
            log.info(" XYZ ZZZ  REQUEST CALL fullinfo \n seccioContext == " + seccioContext + "\n");

            if (seccioContext != null && seccioContext.equals("0")) {
                seccioContext = null;
            }

            String lang = LocaleContextHolder.getLocale().getLanguage();

            String codiEntitat = sesionHttp.getEntitat();
            Long entitatID = sesionHttp.getEntitatID();
            
            String fullID = codiEntitat + "_" + seccioContext; // + "_" + lang; ????
            
            Map<String, Map<String, Object>> cacheFullInfo = (Map<String, Map<String, Object>>) request.getSession().getAttribute(SESSION_FULLINFO_MAP);
            
            Map<String, Object> fullInfo = null;
            
            if (cacheFullInfo == null) {
//                log.info(" Carregant FullInfo de BBDD ... ");
                cacheFullInfo = new TreeMap<String, Map<String, Object>>();
                request.getSession().setAttribute(SESSION_FULLINFO_MAP, cacheFullInfo);
            } else {
//                log.info(" Emprant Cache de FullInfo ... ");
                fullInfo = cacheFullInfo.get(fullID);
            }

            if (fullInfo == null) {
                fullInfo = new HashMap<String, Object>();
                
                final Long seccioID;
                
                // seccio
                if (seccioContext != null){
                    SeccioInfo seccioInfo = getSeccioInfo(request, seccioContext, lang);
                    fullInfo.put("seccio", seccioInfo);
                    seccioID = seccioInfo.getSeccioID();
                } else {
                    seccioID = null;
                }
                
                // Plugins
                
                log.info(" XYZ ZZZ  REQUEST CALL fullinfo \n entitatID == " + entitatID + "\n");
                
                // XYZ ZZZ Suportar multiples nivells de PLugins Publics ????
                final boolean autenticat = true;
                List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(entitatID, lang, seccioID, autenticat);
                fullInfo.put("veureplugins", pluginsEntitat);
                
                // Menus de Links
                {
                    final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT;
                    List<EnllazInfo> menusdelinks = getEnllazos(request, enllazType, seccioID, lang,
                            codiEntitat);
                    fullInfo.put("menuslidelinks", menusdelinks);
                }
                
                // Idiomes 
                {
                    List<String> idiomaInfo = getIdiomes();
                    fullInfo.put("idiomesFront", idiomaInfo);
                }
                
                // menupseudoplugin
                {
                    final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN;
                    List<EnllazInfo> menusdePseudoPlugins = getEnllazos(request, enllazType, seccioID, lang,
                            codiEntitat);
                    fullInfo.put("menupseudoplugin", menusdePseudoPlugins);
                }
                
                // seccions
                {
                    List<SeccioInfo> seccioInfo = getSeccionsInfo(request, seccioID, entitatID);
                    fullInfo.put("seccions", seccioInfo);
                }
                
                
                
                // Nom entitat
                {
                    EntitatJPA entitat = entitatEjb.findByCodi(codiEntitat);
                    fullInfo.put("nomEntitat", entitat.getNom().getTraduccio(lang).getValor());
                }

                cacheFullInfo.put(fullID, fullInfo);

            }
            
            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(fullInfo);
            
            //log.info(" FULLINFO  amb seccioID = " + seccioID + ":\n" + json + "\n");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
        
    }
    
    
    public static final String SESSION_FULLINFO_SORTED_MAP = "SESSION_FULLINFO_SORTED_MAP";
    
    
    
    @RequestMapping(value = "/fullinfosortedauth/{seccioContext}" , method = RequestMethod.GET)
    public void getFullInfoSortedAuth(HttpServletRequest request, HttpServletResponse response,
          @PathVariable("seccioContext") String seccioContext) throws I18NException {
        
        final boolean autenticat = true;
        getInternalFullInfoSorted(request, response,
                seccioContext, autenticat);
        
    }
    
    @RequestMapping(value = "/fullinfosorted/{seccioContext}" , method = RequestMethod.GET)
    public void getFullInfoSorted(HttpServletRequest request, HttpServletResponse response,
          @PathVariable("seccioContext") String seccioContext) throws I18NException {
        
        final boolean autenticat = false;
        getInternalFullInfoSorted(request, response,
                seccioContext, autenticat);
        
    }
    
    protected void getInternalFullInfoSorted(HttpServletRequest request, HttpServletResponse response,
            String seccioContext, boolean autenticat) throws I18NException {

        try {

            if (seccioContext != null && seccioContext.equals("0")) {
                seccioContext = null;
            }

            String lang = LocaleContextHolder.getLocale().getLanguage();

            String codiEntitat = sesionHttp.getEntitat();
            Long entitatID = sesionHttp.getEntitatID();
            
            String fullID = codiEntitat + "_" + seccioContext + "_" + autenticat; // + "_" + lang; ???? 
            
            Map<String, Map<String, Object>> cacheFullInfo = (Map<String, Map<String, Object>>) request.getSession().getAttribute(SESSION_FULLINFO_SORTED_MAP);
            
            Map<String, Object> fullInfo = null;
            
            if (cacheFullInfo == null) 
            {
//                log.info(" Carregant FullInfo de BBDD ... ");
                cacheFullInfo = new TreeMap<String, Map<String, Object>>();
                request.getSession().setAttribute(SESSION_FULLINFO_SORTED_MAP, cacheFullInfo);
            } else {
//                log.info(" Emprant Cache de FullInfo ... ");
                fullInfo = cacheFullInfo.get(fullID);
            }

            if (fullInfo == null) {
                fullInfo = new HashMap<String, Object>();
                
                // Idiomes 
                {
                    List<String> idiomaInfo = getIdiomes();
                    fullInfo.put("idiomesFront", idiomaInfo);
                }
                
                // Nom entitat
                {
                    EntitatJPA entitat = entitatEjb.findByCodi(codiEntitat);
                    fullInfo.put("nomEntitat", entitat.getNom().getTraduccio(lang).getValor());
                }
                
                // seccio
                final Long seccioID;
                if (seccioContext != null){
                    SeccioInfo seccioInfo = getSeccioInfo(request, seccioContext, lang);
                    fullInfo.put("seccio", seccioInfo);
                    seccioID = seccioInfo.getSeccioID();
                } else {
                    seccioID = null;
                }
                
                List<ItemInfo> items = new ArrayList<ItemInfo>();
                
                // Menus de Links
                {
                    final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_MENU_DESLLISANT;
                    List<EnllazInfo> menusdelinks = getEnllazos(request, enllazType, seccioID, lang,
                            codiEntitat);
                    for (EnllazInfo enllazInfo : menusdelinks) {
                        items.add(ItemInfo.createFromEnllazInfo(enllazInfo));
                    }
                    
                    //fullInfo.put("menuslidelinks", menusdelinks);
                    
                }
                
                
                // PLUGINS segons autenticat
                {
                    List<PluginInfo> pluginsEntitat = utilsEjb.getFrontPlugins(entitatID, lang, seccioID, autenticat);
                  //fullInfo.put("veureplugins", pluginsEntitat);
                    for (PluginInfo pluginInfo : pluginsEntitat) {
                        items.add(ItemInfo.createFromPluginInfo(pluginInfo));
                    }
                }
                
                
                if (autenticat) {

                    

                    // menupseudoplugin
                    {
                        final int enllazType = Constants.TIPUS_ENLLAZ_FRONT_PSEUDOPLUGIN;
                        List<EnllazInfo> menusdePseudoPlugins = getEnllazos(request, enllazType, seccioID, lang,
                                codiEntitat);
                        //fullInfo.put("menupseudoplugin", menusdePseudoPlugins);
                        for (EnllazInfo enllazInfo : menusdePseudoPlugins) {
                            items.add(ItemInfo.createFromPseuDoPlugin(enllazInfo));
                        }
                    }

                    // Enllasos En un clic
                    {
                        int enllazType = Constants.TIPUS_ENLLAZ_FRONT_EN_UN_CLIC;
                        List<EnllazInfo> enllasosEnunclic = getEnllazos(request, enllazType, seccioID, lang,
                                codiEntitat);
                        for (EnllazInfo enllazInfo : enllasosEnunclic) {
                            items.add(ItemInfo.createFromEnunclic(enllazInfo));
                        }
                    }
                    
                    // seccions
                    {
                        List<SeccioInfo> seccioInfo = getSeccionsInfo(request, seccioID, entitatID);
                        //fullInfo.put("seccions", seccioInfo);
                        for (SeccioInfo s : seccioInfo) {

                            //seleccionar plugins que són d'aquesta secció
                            List<PluginInfo> pluginsSeccio = utilsEjb.getFrontPlugins(entitatID, lang, s.seccioID, true);

                            items.add(ItemInfo.createFromSeccioInfo(s, pluginsSeccio));
                        }
                    }
                } 
                
                
                Collections.sort(items);
                
                fullInfo.put("items", items);
                
                cacheFullInfo.put(fullID, fullInfo);

                }
            
            // Passar JSON de pluginsEntitat
            Gson gson = new Gson();
            String json = gson.toJson(fullInfo);
            
            //log.info(" FULLINFO  amb seccioID = " + seccioID + ":\n" + json + "\n");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            byte[] utf8JsonString = json.getBytes("UTF8");

            response.getOutputStream().write(utf8JsonString);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }
        
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public void getFaq(HttpServletRequest request, HttpServletResponse response) {

        try {
            String codiEntitat = sesionHttp.getEntitat();
            String lang = LocaleContextHolder.getLocale().getLanguage();

            List<PreguntesFrequents> faqs = utilsEjb.getFaqsByEntity(codiEntitat, lang);

            List<FaqInfo> faqsInfo = new ArrayList<FaqInfo>();

            for (PreguntesFrequents faq : faqs) {

                PreguntesFrequentsJPA faqJPA = (PreguntesFrequentsJPA) faq;

                String title = faqJPA.getEnunciatTraduccions().get(lang).getValor();
                String content = faqJPA.getRespostaTraduccions().get(lang).getValor();

                if(faq.getFitxer1ID()!=null) {
                    Fitxer fit1 = utilsEjb.getFileInfo(faq.getFitxer1ID());
                    File file1 = FileSystemManager.getFile(faq.getFitxer1ID());
                    byte[] fileContent1 = FileUtils.readFileToByteArray(file1);
                    String encodedString1 = Base64.getEncoder().encodeToString(fileContent1);
                    FileInputStream fis = new FileInputStream(file1);

                    if(ImageIO.read(fis) != null){
                        content = content + "<p><img class=\"imatgeFaq\" src=\"data:" + fit1.getMime() + ";base64," + encodedString1 + "\" alt=\"\" /></p>";
                    } else if(fit1.getMime().equals("application/pdf")){
                        content = content + "<p><a href=\"/carpetafront/webui/pdfview/" + fit1.getFitxerID() + "\" target=\"_blank\">" + fit1.getNom() + "</a></p>";
                    }

                }

                if(faq.getFitxer2ID()!=null) {
                    Fitxer fit2 = utilsEjb.getFileInfo(faq.getFitxer2ID());
                    File file2 = FileSystemManager.getFile(faq.getFitxer2ID());
                    byte[] fileContent2 = FileUtils.readFileToByteArray(file2);
                    String encodedString2 = Base64.getEncoder().encodeToString(fileContent2);
                    FileInputStream fis2 = new FileInputStream(file2);

                    if(ImageIO.read(fis2) != null){
                        content = content + "<p><img class=\"imatgeFaq\" src=\"data:" + fit2.getMime() + ";base64," + encodedString2 + "\" alt=\"\" /></p>";
                    } else if(fit2.getMime().equals("application/pdf")){
                        content = content + "<p><a href=\"/carpetafront/webui/pdfview/" + fit2.getFitxerID() + "\" target=\"_blank\">" + fit2.getNom() + "</a></p>";
                    }

                }

                if(faq.getFitxer3ID()!=null) {
                    Fitxer fit3 = utilsEjb.getFileInfo(faq.getFitxer3ID());
                    File file3 = FileSystemManager.getFile(faq.getFitxer3ID());
                    byte[] fileContent3 = FileUtils.readFileToByteArray(file3);
                    String encodedString3 = Base64.getEncoder().encodeToString(fileContent3);
                    FileInputStream fis3 = new FileInputStream(file3);

                    if(ImageIO.read(fis3) != null){
                        content = content + "<p><img class=\"imatgeFaq\" src=\"data:" + fit3.getMime() + ";base64," + encodedString3 + "\" alt=\"\" /></p>";
                    } else if(fit3.getMime().equals("application/pdf")){
                        content = content + "<p><a href=\"/carpetafront/webui/pdfview/" + fit3.getFitxerID() + "\" target=\"_blank\">" + fit3.getNom() + "</a></p>";
                    }

                }



                FaqInfo fi = new FaqInfo(title, content);
                faqsInfo.add(fi);
            }

            // Passar JSON
            Gson gson = new Gson();
            String json = gson.toJson(faqsInfo);

//            log.info(json);

            response.setContentType("application/json");

            response.getWriter().write(json);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }

    @RequestMapping(value = "/pdfview/{pdfId}", method = RequestMethod.GET)
    public void getPdf(@PathVariable("pdfId") Long pdfId, HttpServletRequest request, HttpServletResponse response) {

        try {

            log.info("Entram pdfview: " + pdfId);
            descarregaFitxer(response, pdfId);

        } catch (Throwable e) {
            processExceptionRest(e, request, response);
        }

    }

}
